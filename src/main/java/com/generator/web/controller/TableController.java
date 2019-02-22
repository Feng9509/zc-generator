package com.generator.web.controller;

import com.generator.entity.*;
import com.generator.dao.TableDao;
import com.generator.utils.ConvertUtil;
import com.github.pagehelper.PageHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

/**
 * @descrption 
 * @Date 2019/2/18
 * @Auther zhangchao
 */
@Controller
@RequestMapping("/sys/table")
public class TableController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TableController.class);
    private static Configuration cfg = null;

    @Autowired
    private TableDao tableDao;
    @Autowired
    DataSource dataSource;
    @Value("${databasename}")
    private String databasename;

    static {
        try {
            cfg = new Configuration(Configuration.getVersion());
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            LOGGER.error("初始化Freemarker模板引擎出错！", e);
        }
    }

    @RequestMapping("/list.htm")
    public String listTables() {
        return "table_list";
    }

    /**
     * @descrption 查询数据库表并分页展示到前台
     * @param  [page, rows]
     * @return  Page<Table>
     * @Date 2019/2/18
     * @Author zhangchao
     */
    @ResponseBody
    @RequestMapping("listpage.json")
    public Page<Table> listPage(HttpServletResponse response, Integer page, Integer rows) {
        Page<Table> result;
        try {
            PageHelper.startPage(page, rows);
            com.github.pagehelper.Page tables = (com.github.pagehelper.Page) tableDao.select(databasename);
            result = new Page<Table>(tables.getTotal(), tables);
        } catch (Exception e) {
            LOGGER.error("查询分页数据出错！【page: {}, rows: {}】", page, rows, e);
            result = new Page<>();
        }
        return result;
    }

    /**
     * @descrption 通过数据库元数据来获取表的相关信息生成实体类
     * @param  [tablename, comment]
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     * @Date 2019/2/19
     * @Author zhangchao
     */
    @ResponseBody
    @RequestMapping("generate.json")
    public Map<String, Object> generate(String tablename, String comment) {
        Map<String, Object> result = new HashMap<>();

        try {
            Connection connection = dataSource.getConnection();
            // 获取数据库元数据
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // 生成实体类
            result = generateEntity(databaseMetaData, tablename, comment);
            // 生成mapper文件
            result = generateMapper(databaseMetaData, tablename);

        } catch (Exception e) {
            return result;
        }

        result.put("msg", "成功");
        return result;
    }

    /**
     * @descrption
     * @param  [databaseMetaData, tablename, comment]
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     * @Date 2019/2/19
     * @Author zhangchao
     */
    private Map<String, Object> generateEntity(DatabaseMetaData databaseMetaData, String tablename, String comment) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "成功");
        List<Column> columnList = new ArrayList<>();
        FileWriter fileWriter = null;
        // 定义为数组-->因为lambda中不能修改外部变量的值，除非变量是实例变量或者数组。
        final boolean[] dateFlag = {false};
        // 根据元数据和表名获取此表的所有列
        try {
            // 实体类模板
            Template template = cfg.getTemplate("entityTemplate.ftl");

            // 生成实体类所需数据
            columnList = getColumns(databaseMetaData, tablename);
            columnList.forEach( item-> {
                if ("date".equalsIgnoreCase(item.getJavatype())) {
                    dateFlag[0] = true;
                    return;
                }
            });
            String classname = ConvertUtil.getEntityName(tablename);
            EntityTemplate entityTemplate = new EntityTemplate(classname, comment, dateFlag[0], columnList);

            Map<String, Object> param = new HashMap<>();
            param.put("param", entityTemplate);

            // 将实体类输出到项目路径下并以表名首字母大写为文件名。
            fileWriter = new FileWriter(new File(classname + ConvertUtil.ENTITYE_FILE_SUFFIX));
            template.process(param, fileWriter);
        } catch (Exception e) {
            LOGGER.error("生成实体类出错！【tablename: {}】", tablename, e);
            result.put("msg", "生成实体类出错");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.error("生成实体类中关闭文件输出流出错！");
                result.put("msg", "失败");
            }
        }

        return result;
    }

    private Map<String, Object> generateMapper(DatabaseMetaData databaseMetaData, String tablename) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "成功");
        List<Column> columnList = new ArrayList<>();
        List<Column> idsTemp = new ArrayList<>();
        List<Column> ids = new ArrayList<>();
        FileWriter fileWriter = null;

        try {
            // 主键
            idsTemp = getIds(databaseMetaData, tablename);
            // 所有列数据
            columnList = getColumns(databaseMetaData, tablename);

            if (idsTemp != null && idsTemp.size() > 0) {
                ids = new ArrayList<>();
                for (Column id : idsTemp) {
                    Iterator<Column> iterator = columnList.iterator();
                    while (iterator.hasNext()) {
                        Column next = iterator.next();
                        if (id.getColumn() != null && next.getColumn() != null && id.getColumn().equals(next.getColumn())) {
                            iterator.remove();
                            ids.add(next);
                        }
                    }
                }
            }

            // Mapper文件模板
            Template template = cfg.getTemplate("MapperTemplate.ftl");
            MapperTemplate mapperTemplate = new MapperTemplate(tablename, ids, columnList);

            Map<String, Object> param = new HashMap<>();
            param.put("param", mapperTemplate);
            fileWriter = new FileWriter(new File(ConvertUtil.getMapperName(tablename) + ConvertUtil.MAPPER_FILE_SUFFIX));
            template.process(param, fileWriter);
        } catch (Exception e) {
            LOGGER.error("生成映射文件出错！【tablename: {}】", tablename, e);
            result.put("msg", "生成映射文件出错");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.error("生成映射文件出错中关闭文件输出流出错！");
                result.put("msg", "失败");
            }
        }
        return result;
    }

    /**
     * @descrption 获取表中所有的字段
     * @param  [databaseMetaData, tablename]
     * @return  java.util.List<Column>
     * @Date 2019/2/19
     * @Author zhangchao
     */
    private List<Column> getColumns(DatabaseMetaData databaseMetaData, String tablename) {
        List<Column> columnList = new ArrayList<>();
        Column column = null;
        try {
            ResultSet columns = databaseMetaData.getColumns(null, null, tablename, null);
            while (columns.next()) {
                column = new Column();

                column.setColumn(columns.getString("COLUMN_NAME"));
                column.setProperty(ConvertUtil.convertColumnName(column.getColumn()));
                String jdbctype = columns.getString("TYPE_NAME");
                column.setJdbctype(ConvertUtil.jdbctype2MybatisJdbctype(jdbctype));
                column.setJavatype(ConvertUtil.jdbctype2Javatype(jdbctype));
                column.setComment(columns.getString("REMARKS"));

                columnList.add(column);
            }
        } catch (Exception e) {
            LOGGER.error("根据元数据获取表数据出错！【tablname: {}】", tablename);
            return null;
        }

        return columnList;
    }

    /**
     * @descrption 获取表中的主键
     * @param  [databaseMetaData, tablename]
     * @return  java.util.List<Column>
     * @Date 2019/2/19
     * @Author zhangchao
     */
    private List<Column> getIds(DatabaseMetaData databaseMetaData, String tablename) {
        List<Column> ids = new ArrayList<>();
        try {
            ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tablename);
            Column column = null;
            while (primaryKeys.next()) {
                column = new Column();

                column.setColumn(primaryKeys.getString("COLUMN_NAME"));
                ids.add(column);
            }
        } catch (Exception e) {
            LOGGER.error("根据元数据获取表主键出错！【tablname: {}】", tablename);
            return null;
        }
        return ids;
    }
}


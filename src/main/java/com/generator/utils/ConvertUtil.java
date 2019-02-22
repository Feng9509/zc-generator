package com.generator.utils;

/**
 * @descrption 转换工具
 * @Date 2019/2/22
 * @Auther zhangchao
 */
public class ConvertUtil {
    public static final String ENTITYE_FILE_SUFFIX = ".java";
    public static final String MAPPER_FILE_SUFFIX = ".xml";

    /**
     * @descrption 将列名转小写作为bean的字段
     * @param  [columnName]
     * @return  java.lang.String
     * @Date 2019/2/22
     * @Author zhangchao
     */
    public static String convertColumnName(String columnName) {
        return columnName.toLowerCase();
    }

    /**
     * @descrption 根据数据库类型返回java类型
     * @param  [jdbcType]
     * @return  java.lang.String
     * @Date 2019/2/22
     * @Author zhangchao
     */
    public static String jdbctype2Javatype(String jdbcType) {
        jdbcType = jdbcType.toUpperCase();
        switch (jdbcType) {
            case "VARCHAR":
            case "CHAR":
            case "TEXT":
            case "BOLB":
                return "String";
            case "BIT":
            case "INT":
            case "INT UNSIGNED":
            case "SMALLINT":
            case "TINYINT":
            case "BIGINT":
                return "Integer";
            case "DATE":
            case "TIME":
            case "TIMESTAMP":
            case "DATETIME":
                return "Date";
            default:return jdbcType;
        }
    }

    /**
     * @descrption 根据数据库类型返回mybatis的jdbcType类型
     * @param  [jdbcType]
     * @return  java.lang.String
     * @Date 2019/2/22
     * @Author zhangchao
     */
    public static String jdbctype2MybatisJdbctype(String jdbcType) {
        jdbcType = jdbcType.toUpperCase();
        switch (jdbcType) {
            case "VARCHAR":
            case "CHAR":
            case "TEXT":
            case "BOLB":
                return "VARCHAR";
            case "BIT":
            case "INT":
            case "INT UNSIGNED":
            case "SMALLINT":
            case "TINYINT":
            case "BIGINT":
                return "INTEGER";
            case "DATE":
            case "TIME":
            case "TIMESTAMP":
            case "DATETIME":
                return "TIMESTAMP";
            default:return jdbcType;
        }
    }

    /**
     * @descrption 表名首字母大写作为类名
     * @param  [tablename]
     * @return  java.lang.String
     * @Date 2019/2/22
     * @Author zhangchao
     */
    public static String getEntityName(String tablename) {
        return tablename.substring(0, 1).toUpperCase() + tablename.substring(1);
    }

    /**
     * @descrption 获取类名并加上Dao后缀作为映射文件名称
     * @param  [tablename]
     * @return  java.lang.String
     * @Date 2019/2/22
     * @Author zhangchao
     */
    public static String getMapperName(String tablename) {
        return getEntityName(tablename) + "Dao";
    }
}

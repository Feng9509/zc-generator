package com.generator.entity;

import java.util.List;

/**
 * @descrption 封闭生成映射文件所需的数据
 * @Date 2019/2/22
 * @Auther zhangchao
 */
public class MapperTemplate {
    private String tablename;       // 表名
    private List<Column> ids;       // 主键(可能存在联合主键)
    private List<Column> columns;   // 所有列

    public MapperTemplate() {}

    public MapperTemplate(String tablename, List<Column> ids, List<Column> columns) {
        this.tablename = tablename;
        this.ids = ids;
        this.columns = columns;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<Column> getIds() {
        return ids;
    }

    public void setIds(List<Column> ids) {
        this.ids = ids;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

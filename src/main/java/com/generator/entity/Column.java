package com.generator.entity;

/**
 * @descrption 数据库表与java实体类之间的映射
 * @Date 2019/2/19
 * @Auther zhangchao
 */
public class Column {
    private String column;      // 数据库字段
    private String property;    // bean属性
    private String jdbctype;    // 数据库类型
    private String javatype;    // java类型
    private String comment;     // 备注

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getJdbctype() {
        return jdbctype;
    }

    public void setJdbctype(String jdbctype) {
        this.jdbctype = jdbctype;
    }

    public String getJavatype() {
        return javatype;
    }

    public void setJavatype(String javatype) {
        this.javatype = javatype;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

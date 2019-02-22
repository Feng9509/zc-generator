package com.generator.entity;

import java.util.List;

/**
 * @descrption 封闭生成实体类所需的数据
 * @Date 2019/2/22
 * @Auther zhangchao
 */
public class EntityTemplate {
    private String classname;       // 类名
    private String comment;         // 备注
    private Boolean dateflag;       // 是否有日期类型
    private List<Column> columns;   // 所有字段

    public EntityTemplate() {}

    public EntityTemplate(String classname, String comment, Boolean dateflag, List<Column> columns) {
        this.classname = classname;
        this.comment = comment;
        this.dateflag = dateflag;
        this.columns = columns;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDateflag() {
        return dateflag;
    }

    public void setDateflag(Boolean dateflag) {
        this.dateflag = dateflag;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

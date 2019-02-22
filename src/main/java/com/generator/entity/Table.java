package com.generator.entity;

import java.io.Serializable;

/**
 * @descrption 前台展示的数据库表数据
 * @Date 2019/2/22
 * @Auther zhangchao
 */
public class Table implements Serializable {
    private String name;
    private String comment;
    private String createtime;
    private String updatetime;

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}

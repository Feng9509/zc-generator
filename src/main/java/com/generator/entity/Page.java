package com.generator.entity;

import java.util.List;

/**
 * @descrption 分页数据
 * @Date 2019/2/22
 * @Auther zhangchao
 */
public class Page<T> {
    private Long total;     // 数据总数
    private List<T> rows;   // 当页数据

    public Page() {}

    public Page(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

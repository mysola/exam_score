package com.wangyang.dto;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
    private long total;        //总记录数
    private List<T> rows;    //结果集


    public PageBean(List<T> list) {
        this.total = list.size();
        this.rows = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

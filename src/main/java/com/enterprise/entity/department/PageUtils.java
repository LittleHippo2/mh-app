package com.enterprise.entity.department;


public class PageUtils {

    private Integer page;

    private Integer size;

    private Object data;

    public PageUtils(Integer page, Integer size, Object data) {
        this.page = page;
        this.size = size;
        this.data = data;
    }

    public PageUtils() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.enterprise.entity;

import java.io.Serializable;

public class ResultMap implements Serializable {


    //方法开始时间
    private String startTime;

    //方法结束时间
    private String endTime;

    //方法总耗时
    private String timeConsuming;

    //平台接口调用开始时间
    private String apiStartTime;

    //平台接口调用结束时间
    private String apiEndTime;

    //平台接口调用总耗时
    private String apiCallTime;

    //方法返回值
    private Object data;

    //数据总条数
    private Integer count;

    //当前页
    private Integer page;

    //每页数据条数
    private Integer size;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public String getApiStartTime() {
        return apiStartTime;
    }

    public void setApiStartTime(String apiStartTime) {
        this.apiStartTime = apiStartTime;
    }

    public String getApiEndTime() {
        return apiEndTime;
    }

    public void setApiEndTime(String apiEndTime) {
        this.apiEndTime = apiEndTime;
    }

    public String getApiCallTime() {
        return apiCallTime;
    }

    public void setApiCallTime(String apiCallTime) {
        this.apiCallTime = apiCallTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
}

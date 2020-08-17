package com.enterprise.entity;

import com.enterprise.entity.page.PageModel;

import java.io.Serializable;

public class LogPo extends PageModel implements Serializable {
    private String param;
    private String result;
    private long beginTime;
    private long endTime;
    private long time;
    private String status;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getbeginTime() {
        return beginTime;
    }

    public void setbeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

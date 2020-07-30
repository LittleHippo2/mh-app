package com.enterprise.entity.department;

import java.io.Serializable;
import java.util.List;

public class DeptTree implements Serializable {

    private String title;

    private String pId;

    private String id;

    private List<DeptTree> children;

    public String getpId() {
        return pId;
    }

    public void setPid(String pId) {
        this.pId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DeptTree> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTree> children) {
        this.children = children;
    }
}

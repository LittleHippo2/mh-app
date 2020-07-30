package com.enterprise.util;

import com.enterprise.entity.department.DeptTree;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 生成树状结构的工具类
 */
public class TreeToolUtils {

    private List<DeptTree> rootList; //根节点对象存放到这里

    private List<DeptTree> bodyList; //其他节点存放到这里，可以包含根节点

    public TreeToolUtils(List<DeptTree> rootList, List<DeptTree> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    public List<DeptTree> getTree(){   //调用的方法入口
        if(bodyList != null && !bodyList.isEmpty()){
            //声明一个map，用来过滤已操作过的数据
            Map<String,String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(beanTree,map));
            return rootList;
        }
        return null;
    }

    public void getChild(DeptTree treeDto,Map<String,String> map){
        List<DeptTree> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getId()))
                .filter(c ->c.getpId().equals(treeDto.getId()))
                .forEach(c ->{
                    map.put(c.getId(),c.getpId());
                    getChild(c,map);
                    childList.add(c);
                });
        treeDto.setChildren(childList);

    }
}

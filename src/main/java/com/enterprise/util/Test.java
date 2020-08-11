package com.enterprise.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void substr(){
        List list = new ArrayList();

        for(int a = 1; a<= 36501; a++){
            list.add(a);
        }

        List list1 = new ArrayList();
        long st = System.currentTimeMillis();
        for(int b = 0; b< list.size(); b++){
            if (b%100 ==0){
                if (b+100 >= list.size()){
                    list1 = list.subList(b, list.size());
                    System.out.println(list1);
                }else {
                    list1 = list.subList(b, b + 100);
                    System.out.println(list1);
                }
            }
        }
        long et = System.currentTimeMillis();
        System.out.println("第一种方法耗时"+(et-st)+"ms");

        long st1 = System.currentTimeMillis();
        for (int b =0; b<list.size(); b++){
            if (b%100 ==0){
                if (b+100 >= list.size()){
                   list1 = (List) list.stream().skip(b).limit(list.size()).collect(Collectors.toList());
                }else {
                    list1 = (List) list.stream().skip(b).limit(b+100).collect(Collectors.toList());
                }
            }
        }
        long et1 = System.currentTimeMillis();
        System.out.println("第二种方法耗时"+(et1-st1)+"ms");
    }

    public static void main(String[] args) {
        substr();
    }
}

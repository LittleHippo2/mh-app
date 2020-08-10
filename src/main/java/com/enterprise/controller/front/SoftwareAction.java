package com.enterprise.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 负一屏Action
 * Created by Cesiumai on 2016/6/14.
 */
@Controller("softwareAction")
@RequestMapping("/")
public class SoftwareAction {
    @RequestMapping("softwareList")
    public String contact() {
        return "/front/software/softwareList";
    }
}

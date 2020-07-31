package com.enterprise.controller.front;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 平台集成说明
 */
@Controller("integrationController")
@RequestMapping("/")
public class IntegrationAction {

    @RequestMapping("api")
    public String apis() {
        return "/front/api/api_test";
    }



}

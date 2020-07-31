package com.enterprise.controller.front;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 平台api接口测试
 */
@Controller
public class ApiTestController {


    @RequestMapping(value = "/apiTest")
    public String apiTest(HttpServletRequest request) {

        return "admin/api_test";

    }

}

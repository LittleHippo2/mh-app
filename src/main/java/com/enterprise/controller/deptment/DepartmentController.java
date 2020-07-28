package com.enterprise.controller.deptment;

import com.enterprise.entity.ResultMap;
import com.enterprise.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/sync")
    @ResponseBody
    public ResultMap syncDeptAndUser(@RequestParam(value = "token")String token) throws Exception {
        return departmentService.syncDeptAndUser(token);
    }
}

package com.enterprise.controller.deptment;

import com.enterprise.entity.ResultMap;
import com.enterprise.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/manage/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 增量同步
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping("/sync")
    @ResponseBody
    public ResultMap syncDeptAndUser(@RequestParam(value = "token")String token) throws Exception {
        return departmentService.syncDeptAndUser(token);
    }

    @RequestMapping("/selectList")
    public String selectList(){
        return "/manage/department/departmentList";
    }

    @RequestMapping("/csse/selectList")
    public String selectCsseList(){
        return "/manage/csseDepartment/csseDepartmentList";
    }

    @RequestMapping("/getDeptList")
    @ResponseBody
    public ResultMap getDeptList(){
        return departmentService.getDeptList();
    }


    @RequestMapping("/selectDeptList")
    @ResponseBody
    public ResultMap selectDeptList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                @RequestParam(value = "limit", defaultValue = "10")Integer limit,
                                @RequestParam(value = "fatherId", required = false)String fatherId){
        return departmentService.selectDeptList(page, limit, fatherId);

    }

    @RequestMapping("/selectUserList")
    @ResponseBody
    public ResultMap selectUserList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                @RequestParam(value = "limit", defaultValue = "10")Integer limit,
                                @RequestParam(value = "deptId", required = false)String deptId){
        return departmentService.selectUserList(page, limit, deptId);

    }

    /**
     * 全量同步接口
     * @param token
     * @return
     */
    @RequestMapping("/csse/selectDeptList")
    @ResponseBody
    public ResultMap selectCsseDeptList(@RequestParam(value = "token")String token,
                                        @RequestParam(value = "fatherId", defaultValue = "-1")String fatherId,
                                        @RequestParam(value = "invalid", required = false)String invalid){
        return departmentService.selectCsseDeptList(token, fatherId, invalid);
    }

    @RequestMapping("/csse/selectDeptListInfo")
    @ResponseBody
    public ResultMap selectCsseDeptList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10")Integer limit,
                                        @RequestParam(value = "token")String token,
                                        @RequestParam(value = "fatherId", defaultValue = "-1")String fatherId,
                                        @RequestParam(value = "invalid", required = false)String invalid){
        return departmentService.selectCsseDeptListInfo(page, limit, token, fatherId, invalid);
    }




}

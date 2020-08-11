package com.enterprise.controller.deptment;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.entity.ResultMap;
import com.enterprise.service.DepartmentService;
import com.enterprise.util.PlatFormUtil;
import com.enterprise.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private TokenServiceImpl tokenService;

    @Value("#{config['api.appid']}")
    private String appid;

    @Value("#{config['api.secret']}")
    private String secret;

    /**
     * 增量同步
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping("/sync")
    @ResponseBody
    public ResultMap syncDeptAndUser(@RequestParam(value = "token")String token) throws Exception {
        // fix token Unauthorized issue. 202008041316(zst)
        String nowToken = PlatFormUtil.getAccessToken();
        if (token.equals(nowToken)) {
            return departmentService.syncDeptAndUser(token);
        } else {
            return departmentService.syncDeptAndUser(nowToken);
        }
    }

    @RequestMapping("/selectList")
    public String selectList(){
        return "/manage/department/departmentList";
    }

    @RequestMapping("/user/selectList")
    public String selectUserList(){
        return "/manage/user/userList";
    }

    @RequestMapping("/csse/selectList")
    public String selectCsseList(){
        return "/manage/csseDepartment/csseDepartmentList";
    }
    @RequestMapping("/csse/user/selectList")
    public String selectCsseUserList(){
        return "/manage/csseUser/csseUserList";
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

    @RequestMapping("/selectDeptByDeptId")
    @ResponseBody
    public ResultMap selectDeptByDeptId(HttpServletRequest request,
                                        @RequestParam(value = "token")String token,
                                         @RequestParam(value = "deptId")String deptId){
        ResultMap resultMap = tokenService.getAccessToken(appid, secret);

        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
        token = result.getString("access_token");
        return departmentService.selectDeptByDeptId(request, token, deptId);
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
        ResultMap resultMap = tokenService.getAccessToken(appid, secret);

        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
        token = result.getString("access_token");
        return departmentService.selectCsseDeptList(token, fatherId, invalid);
    }

    @RequestMapping("/csse/selectDeptListInfo")
    @ResponseBody
    public ResultMap selectCsseDeptList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10")Integer limit,
                                        @RequestParam(value = "token")String token,
                                        @RequestParam(value = "fatherId", defaultValue = "-1")String fatherId,
                                        @RequestParam(value = "invalid", required = false)String invalid){
        ResultMap resultMap = tokenService.getAccessToken(appid, secret);

        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
        token = result.getString("access_token");
        return departmentService.selectCsseDeptListInfo(page, limit, token, fatherId, invalid);
    }

    @RequestMapping("/csse/selectUserList")
    @ResponseBody
    public ResultMap selectCsseUserList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10")Integer limit,
                                        @RequestParam(value = "token")String token,
                                        @RequestParam(value = "deptId", defaultValue = "root")String deptId){
        ResultMap resultMap = tokenService.getAccessToken(appid, secret);

        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
        token = result.getString("access_token");
        return departmentService.selectCsseUserList(page, limit, token, deptId);
    }

    @RequestMapping("/csse/selectUserInfo")
    @ResponseBody
    public ResultMap selectUserInfo(HttpServletRequest request,
                                    @RequestParam(value = "token")String token,
                                    @RequestParam(value = "userId",required = false)String userId,
                                    @RequestParam(value = "account",required = false)String account){
        ResultMap resultMap = tokenService.getAccessToken(appid, secret);

        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
        token = result.getString("access_token");
        return departmentService.selectCsseUserInfo(request, token, userId, account);
    }

    @RequestMapping("/csse/test")
    @ResponseBody
    public ResultMap test(){
        ResultMap resultMap = tokenService.getAccessToken(appid, secret);

        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
        String token = result.getString("access_token");
        return departmentService.test(token);
    }

}

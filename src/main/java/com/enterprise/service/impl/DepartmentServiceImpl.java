package com.enterprise.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enterprise.dao.DepartmentDao;
import com.enterprise.dao.ServersManage;
import com.enterprise.entity.ResultMap;
import com.enterprise.entity.department.DepartmentPo;
import com.enterprise.entity.department.DeptTree;
import com.enterprise.entity.department.PageUtils;
import com.enterprise.entity.department.UserPo;
import com.enterprise.service.DepartmentService;
import com.enterprise.util.HttpClientUtil;
import com.enterprise.util.TimeUtils;
import com.enterprise.util.TreeToolUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service("departmentService")
public class DepartmentServiceImpl extends ServersManage<DepartmentPo, DepartmentDao> implements DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Value("#{config['api.ip']}")
    private String apiIp;

    @Value("#{config['api.sync.dept']}")
    private String apiSyncDept;

    @Value("#{config['api.get.deptList']}")
    private String apiCsseDeptList;




    @Resource(name = "departmentDao")
    public void setDao(DepartmentDao departmentDao) {
        this.dao = departmentDao;
    }

    /**
     * @param token 微服务token
     * @return
     */
    @Override
    @Transactional
    public ResultMap syncDeptAndUser(String token) throws Exception {

        logger.info("******************************************************************************************************************************************************************");
        Long starttime = dao.getTimestamp();
        logger.info("token：" + token);
        ResultMap resultMap = new ResultMap();
        //方法开始时间
        String startTime = TimeUtils.getNow();
        logger.debug("方法开始时间为：" + startTime);
        resultMap.setStartTime(startTime);
        long startTime1 = System.currentTimeMillis();

//        Map<String, Object> map = new HashMap<>();
//
//        map.put("starttime", starttime);
//        map.put("department", department);
//        map.put("access_token", token);

        String url = apiIp + apiSyncDept + "?starttime=" + starttime + "&department=&access_token=" + token;
        logger.info("请求的接口为：" + url);
        logger.info("请求方式：GET");
        long apiStartTime1 = System.currentTimeMillis();
        // String obj = HttpUtil.get(url, map, 3000, 3000);
        String obj = HttpClientUtil.httpGet(url);
        logger.info(obj);
        long apiEndTime1 = System.currentTimeMillis();
        String apiCallTime = (apiEndTime1 - apiStartTime1) + "ms";

        dao.updateTimestamp((long) (apiEndTime1 / 1000));

        resultMap.setApiCallTime(apiCallTime);
        logger.debug("同步组织机构和用户接口总耗时：" + apiCallTime);

        //将同步过来的数据插入本地数据库
        try {
            insertTable(obj, resultMap, starttime);
        } catch (Exception e) {
            logger.error(e);
            dao.updateTimestamp((long) 1);
        }


        //方法结束时间
        long endTime2 = System.currentTimeMillis();
        String endTime = TimeUtils.getNow();
        logger.debug("方法结束时间为：" + endTime);
        resultMap.setEndTime(endTime);
        String timeConsuming = (endTime2 - startTime1) + "ms";
        resultMap.setTimeConsuming(timeConsuming);

        logger.debug("方法总耗时：" + timeConsuming);
        logger.info("*******************************************************************************************************************************************************************");
        return resultMap;
    }

    public ResultMap insertTable(String obj, ResultMap resultMap, long starttime) {

        String resultCode = JSONObject.parseObject(obj).getString("rsltmsg");
        if (resultCode != null) {
            JSONArray org = JSONObject.parseObject(obj).getJSONArray("org");
            JSONArray user = JSONObject.parseObject(obj).getJSONArray("user");
            if (org.size() == 0 && user.size() == 0) {
                resultMap.setData("增量同步数据为空，请检查时间戳是否是10位或者部门id是否正确,如果都正确，可能此时间之后或者此部门id下没有新增或修改过后的数据。");
            } else {
                List<DepartmentPo> deptList = new ArrayList<>();
                List<UserPo> userList = new ArrayList<>();
                if (org.size() != 0) {
                    //使用java8 forEach 的并联流方法，并且加上lambda 表达式的写法，可以提高遍历效率，并且代码的可读性提高
                    org.parallelStream().forEach(a -> {
                        DepartmentPo departmentPo = new DepartmentPo();
                        departmentPo.setDeptId(((JSONObject) a).getString("organId"));
                        departmentPo.setName(((JSONObject) a).getString("organName"));
                        departmentPo.setParentId(((JSONObject) a).getString("fatherId"));
                        departmentPo.setLevel(((JSONObject) a).getString("p"));
                        departmentPo.setOrderId(((JSONObject) a).getInteger("orderId"));
                        departmentPo.setRemark("1");
                        departmentPo.setOperator("1");
                        departmentPo.setOperatorIp("1");
                        departmentPo.setOperatorTime(LocalDate.now() + "");
                        departmentPo.setType(((JSONObject) a).getInteger("type"));
                        departmentPo.setCode(((JSONObject) a).getString("code"));
                        departmentPo.setTimestamp(((JSONObject) a).getLong("timestamp"));

                        if (starttime != 1) {
                            switch (((JSONObject) a).getInteger("type")) {
                                case 2:
                                    //type 为2 新增
                                    dao.insertDeptByOne(departmentPo);
                                    break;
                                default:
                                    dao.updateDepartment(departmentPo);
                                    break;
                            }
                        } else {
                            dao.insertDeptByOne(departmentPo);
                        }
                    });
                }
                if (user.size() != 0) {
                    user.parallelStream().forEach(a -> {
                        UserPo userPo = new UserPo();
                        userPo.setUserId(((JSONObject) a).getString("userid"));
                        userPo.setUserName(((JSONObject) a).getString("fullname"));
                        userPo.setTelephone(((JSONObject) a).getString("mobile"));
                        userPo.setMail(((JSONObject) a).getString("userEmail"));
                        userPo.setAccount(((JSONObject) a).getString("account"));
                        userPo.setDepId(((JSONObject) a).getString("organId"));
                        userPo.setStatus(((JSONObject) a).getInteger("type"));
                        userPo.setOperatorTime(LocalDate.now() + "");
                        userPo.setIsManager(((JSONObject) a).getInteger("isManager"));
                        userPo.setOrderId(((JSONObject) a).getInteger("orderId"));
                        userPo.setTimestamp(((JSONObject) a).getLong("timestamp"));

                        if (starttime != 1) {
                            switch (((JSONObject) a).getInteger("type")) {
                                case 2:
                                    dao.insertUserByOne(userPo);
                                    break;
                                default:
                                    dao.updateUser(userPo);
                                    break;
                            }
                        } else {
                            dao.insertUserByOne(userPo);
                        }
                    });
                }
            }
        } else {
            resultCode = JSONObject.parseObject(obj).getString("error");
            if ("invalid_token".equals(resultCode)) {
                resultMap.setData("微服务token不合法，请获取新的微服务token");
            } else {
                resultMap.setData(obj);
            }
        }
        return resultMap;
    }

    @Override
    public ResultMap getDeptList() {
        ResultMap map = new ResultMap();
        List<DepartmentPo> list = dao.selectDeptList();
        List<DeptTree> rootList = new ArrayList<>();//根节点
        List<DeptTree> bodyList = new ArrayList<>();//子节点

        list.stream().forEach(a -> {
            DeptTree deptTree = new DeptTree();
            deptTree.setPid(a.getParentId());
            deptTree.setId(a.getDeptId());
            deptTree.setTitle(a.getName());
            deptTree.setChildren(null);


            if (a.getDeptId().equals("root")) {
                rootList.add(deptTree);
            } else {
                bodyList.add(deptTree);
            }
        });
        TreeToolUtils utils = new TreeToolUtils(rootList, bodyList);
        List<DeptTree> result = utils.getTree();
        map.setData(result);
        return map;
    }

    /**
     * 查询部门
     */
    @Override
    public ResultMap selectDeptList(Integer page, Integer limit, String fatherId) {

        ResultMap resultMap = new ResultMap();

        resultMap.setPage(page);
        resultMap.setSize(limit);
        page = (page-1)*limit;

        PageUtils pageUtils = new PageUtils(page, limit, fatherId);

        resultMap.setData(dao.selectDepartment(pageUtils));
        resultMap.setCount(dao.countDept(fatherId));
        return resultMap;
    }

    /**
     * 查询人员
     */

    @Override
    public ResultMap selectUserList(Integer page, Integer limit, String deptId) {
        ResultMap resultMap = new ResultMap();

        resultMap.setPage(page);
        resultMap.setSize(limit);
        page = (page-1)*limit;

        PageUtils pageUtils = new PageUtils(page, limit, deptId);
        resultMap.setData( dao.selectUser(pageUtils));
        resultMap.setCount(dao.countUser(deptId));
        return resultMap;
    }

    /**
     * 全量同步
     */

    @Override
    public ResultMap selectCsseDeptList(String token,  String fatherId, String invalid) {

        ResultMap resultMap = new ResultMap();
        logger.info("********************************************************获取部门列表开始**********************************************************");
        String csseUrl =apiCsseDeptList.replace("{departmentid}", fatherId);
        String url;
        if (invalid  != null){
            //包含禁用数据
            url  = apiIp+csseUrl+"?access_token="+token+"&sublevel&invalid";
        }else{
            url  = apiIp+csseUrl+"?access_token="+token+"&sublevel";
        }
        logger.info("请求的接口为："+url);
        logger.info("请求类型：GET");
        String obj = HttpClientUtil.httpGet(url);

        JSONArray jsonArray = JSONObject.parseArray(obj);

        List<DeptTree> rootList = new ArrayList<>();//根节点
        List<DeptTree> bodyList = new ArrayList<>();//子节点

        jsonArray.stream().forEach(a -> {
            DeptTree deptTree = new DeptTree();
            deptTree.setPid(((JSONObject) a).getString("fatherId"));
            deptTree.setId(((JSONObject) a).getString("organId"));
            deptTree.setTitle(((JSONObject) a).getString("organName"));
            deptTree.setChildren(null);

            if (((JSONObject) a).getString("organId").equals("root")) {
                rootList.add(deptTree);
            } else {
                bodyList.add(deptTree);
            }
        });
        TreeToolUtils utils = new TreeToolUtils(rootList, bodyList);
        List<DeptTree> result = utils.getTree();
        resultMap.setData(result);

        logger.info("********************************************************获取部门列表结束**********************************************************");
        return resultMap;
    }

    @Override
    public ResultMap selectCsseDeptListInfo(Integer page, Integer limit,String token, String fatherId, String invalid) {
        logger.info("********************************************************获取部门列表信息开始**********************************************************");
        String startTime = TimeUtils.getNow();
        ResultMap resultMap = new ResultMap();
        resultMap.setStartTime(startTime);
        String csseUrl =apiCsseDeptList.replace("{departmentid}", fatherId);
        String url;
        if (invalid  != null){
            //包含禁用数据
            url  = apiIp+csseUrl+"?access_token="+token+"&sublevel&invalid";
        }else{
            url  = apiIp+csseUrl+"?access_token="+token+"&sublevel";
        }
        logger.info("请求的接口为："+url);
        logger.info("请求类型：GET");
        long apiStartTime = System.currentTimeMillis();
        String obj = HttpClientUtil.httpGet(url);
        long apiEndTime = System.currentTimeMillis();
        resultMap.setApiCallTime((apiEndTime-apiStartTime)+"ms");
        JSONArray jsonArray = JSONObject.parseArray(obj);
        int count = jsonArray.size();
        page = (page-1)*limit;
        List<DepartmentPo> list = new ArrayList<>();
        jsonArray.stream().skip((long)page).limit(limit).forEach( a -> {
            DepartmentPo departmentPo = new DepartmentPo();
            departmentPo.setDeptId(((JSONObject) a).getString("organId"));
            departmentPo.setName(((JSONObject) a).getString("organName"));
            departmentPo.setParentId(((JSONObject) a).getString("fatherId"));
            departmentPo.setLevel(((JSONObject) a).getString("p"));
            departmentPo.setOrderId(((JSONObject) a).getInteger("orderId"));
            departmentPo.setRemark("1");
            departmentPo.setOperator("1");
            departmentPo.setOperatorIp("1");
            departmentPo.setOperatorTime(LocalDate.now() + "");
            //departmentPo.setType(((JSONObject) a).getInteger("type"));
            departmentPo.setCode(((JSONObject) a).getString("code"));
            departmentPo.setTimestamp(((JSONObject) a).getLong("timestamp"));
            list.add(departmentPo);
        });
        resultMap.setCount(count);
        resultMap.setData(list);
        String endTime = TimeUtils.getNow();
        resultMap.setEndTime(endTime);
        logger.info("********************************************************获取部门列表信息结束**********************************************************");
        return resultMap;
    }

    @Override
    public ResultMap selectDeptByDeptId(String token, String deptId) {

        ResultMap resultMap = new ResultMap();
        logger.info("********************************************************获取部门详细信息开始**********************************************************");



        logger.info("********************************************************获取部门详细信息结束**********************************************************");
        return null;
    }




}

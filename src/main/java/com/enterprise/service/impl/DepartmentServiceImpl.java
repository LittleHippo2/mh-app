package com.enterprise.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enterprise.dao.DepartmentDao;
import com.enterprise.dao.ServersManage;
import com.enterprise.entity.ResultMap;
import com.enterprise.entity.User;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl extends ServersManage<DepartmentPo, DepartmentDao> implements DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Value("#{config['api.ip']}")
    private String apiIp;

    @Value("#{config['api.sync.dept']}")
    private String apiSyncDept;

    @Value("#{config['api.get.deptList']}")
    private String apiCsseDeptList;

    @Value("#{config['api.get.dept.info']}")
    private String apiCsseDeptInfo;

    @Value("#{config['api.get.user.list']}")
    private String apiCsseUserList;

    @Value("#{config['api.get.user.info.id']}")
    private String apiCsseUserInfoById;

    @Value("#{config['api.get.user.info.account']}")
    private String apiCsseUserInfoByAccount;



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

        logger.info("******************************************************************************************增量同步开始************************************************************************");

        Long starttime = dao.getTimestamp();
        logger.info("token：" + token);
        ResultMap resultMap = new ResultMap();
        //方法开始时间
        String startTime = TimeUtils.getNow();
        logger.debug("方法开始时间为：" + startTime);
        resultMap.setStartTime(startTime);
        long startTime1 = System.currentTimeMillis();

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
        insertTable(obj, resultMap, starttime);

//        int a[] = {1,2,3};
//        int b = a[4];

        //方法结束时间
        long endTime2 = System.currentTimeMillis();
        String endTime = TimeUtils.getNow();
        logger.debug("方法结束时间为：" + endTime);
        resultMap.setEndTime(endTime);
        String timeConsuming = (endTime2 - startTime1) + "ms";
        resultMap.setTimeConsuming(timeConsuming);

        logger.debug("方法总耗时：" + timeConsuming);
        logger.info("*******************************************************************************************增量同步结束************************************************************************");
        return resultMap;
    }

    @Transactional
    public ResultMap insertTable(String obj, ResultMap resultMap, long starttime) {

        String resultCode = JSONObject.parseObject(obj).getString("rsltmsg");
        List<DepartmentPo> insertDeptList = new ArrayList<>();
        List<DepartmentPo> updateDeptList = new ArrayList<>();
        List<UserPo> insertUserList = new ArrayList<>();
        List<UserPo> updateUserList = new ArrayList<>();
        List<User> usersList = new ArrayList<>();
        if (resultCode != null) {
            JSONArray org = JSONObject.parseObject(obj).getJSONArray("org");
            JSONArray user = JSONObject.parseObject(obj).getJSONArray("user");
            if (org.size() == 0 && user.size() == 0) {
                resultMap.setData("同步完成，增量同步数据为空，上次同步时间之后没有新增或修改过的数据。");
            } else {
                if (org.size() != 0) {
                    //使用java8 forEach 的并联流方法，并且加上lambda 表达式的写法，可以提高遍历效率，并且代码的可读性提高
                    org.stream().forEach(a -> {
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
                                    insertDeptList.add(departmentPo);
                                    break;
                                default:
                                    //修改和删除
                                    updateDeptList.add(departmentPo);
                                    break;
                            }
                        } else {
                            insertDeptList.add(departmentPo);
                        }
                    });
                    List<DepartmentPo> list1;
                        if (insertDeptList.size() != 0){
                            for(int b = 0; b< insertDeptList.size(); b++){
                                if (b%300 ==0){
                                    if (b+300 >= insertDeptList.size()){
                                        list1 = insertDeptList.subList(b, insertDeptList.size());
                                        dao.insertDepartment(list1);
                                    }else {
                                        list1 = insertDeptList.subList(b, b + 300);
                                        dao.insertDepartment(list1);
                                    }
                                }
                            }
                        }
                        if (updateDeptList.size() != 0){
                            dao.updateDepartment(updateDeptList);
                        }
                }
                if (user.size() != 0) {
                    user.stream().forEach(a -> {
                        UserPo userPo = new UserPo();
                        User user1 = new User();
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

//                        user1.setUsername(userPo.getAccount());
//                        user1.setPassword("1");
//                        user1.setCreateAccount(userPo.getAccount());
//                        user1.setUpdateAccount(userPo.getAccount());
//                        user1.setNickname(userPo.getUserName());
//                        user1.setEmail("root@root.com");
//                        user1.setPortrait("attached/headPortrait/20160606/146519569961677556119.jpg");
                        if (starttime != 1) {
                            switch (((JSONObject) a).getInteger("type")) {
                                case 2:
                                    //2是新增
                                    insertUserList.add(userPo);
                                    break;
                                default:
                                    //修改和删除
                                    updateUserList.add(userPo);
                                    break;
                            }
                        } else {
                            insertUserList.add(userPo);
                            usersList.add(user1);
                        }
                    });
                    List<UserPo> list;
                    if (insertUserList.size() != 0){
                        for(int b = 0; b< insertUserList.size(); b++){
                            if (b%300 ==0){
                                if (b+300 >= insertUserList.size()){
                                    list = insertUserList.subList(b, insertUserList.size());
                                    dao.insertUser(list);
                                }else {
                                    list = insertUserList.subList(b, b + 300);
                                    dao.insertUser(list);
                                }
                            }
                        }
                    }
                    if (updateUserList.size() != 0){
                        dao.updateUser(updateUserList);
                    }

                }
                resultMap.setData("同步完成！共同步组织机构数据："+org.size()+"条。同步人员信息："+user.size()+"条。");
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
            departmentPo.setCode(((JSONObject) a).getString("code"));
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
    public ResultMap selectDeptByDeptId(HttpServletRequest request,String token, String deptId) {

        ResultMap resultMap = new ResultMap();
        logger.info("********************************************************获取部门详细信息开始**********************************************************");

        String startTime = TimeUtils.getNow();
        resultMap.setStartTime(startTime);
        String csseUrl = apiCsseDeptInfo.replace("{departmentid}",deptId);

        String url = apiIp+csseUrl+"?access_token="+token;
        logger.info("请求的接口为："+url);
        logger.info("请求类型：GET");

        long apiStartTime = System.currentTimeMillis();
        String obj = HttpClientUtil.httpGet(url);
        long apiEndTime = System.currentTimeMillis();
        resultMap.setApiCallTime((apiEndTime-apiStartTime)+"ms");

        JSONObject jsonObject = JSONObject.parseObject(obj);
        DepartmentPo departmentPo = new DepartmentPo();
        departmentPo.setDeptId(jsonObject.getString("organId"));
        departmentPo.setName(jsonObject.getString("organName"));
        departmentPo.setParentId(jsonObject.getString("fatherId"));
        departmentPo.setCode(jsonObject.getString("code"));

        HttpSession session = request.getSession();
        session.setAttribute("deptInfo", JSONObject.toJSON(departmentPo));

        resultMap.setData(departmentPo);
        String endTime = TimeUtils.getNow();
        resultMap.setEndTime(endTime);
        logger.info("********************************************************获取部门详细信息结束**********************************************************");
        return null;
    }

    @Override
    public ResultMap selectCsseUserList(Integer page, Integer limit, String token, String deptId) {
        ResultMap resultMap = new ResultMap();
        logger.info("********************************************************获取用户列表信息开始**********************************************************");
        String startTime = TimeUtils.getNow();
        resultMap.setStartTime(startTime);
        String csseUrl = apiCsseUserList.replace("{departmentid}", deptId);

        String url = apiIp+csseUrl+"?access_token="+token;
        logger.info("请求的接口为："+url);
        logger.info("请求类型：GET");
        long apiStartTime = System.currentTimeMillis();
        String obj = HttpClientUtil.httpGet(url);
        long apiEndTime = System.currentTimeMillis();
        resultMap.setApiCallTime((apiEndTime-apiStartTime)+"ms");
        JSONArray jsonArray = JSONObject.parseArray(obj);
        int count = jsonArray.size();
        page = (page-1)*limit;
        List<UserPo> list = new ArrayList<>();

        jsonArray.stream().skip((long)page).limit(limit).forEach(a -> {
            UserPo userPo = new UserPo();
            userPo.setUserId(((JSONObject) a).getString("userid"));
            userPo.setUserName(((JSONObject) a).getString("fullname"));
            userPo.setAccount(((JSONObject) a).getString("account"));
            userPo.setDepId(((JSONObject) a).getString("organId"));
            list.add(userPo);
        });
        resultMap.setData(list);
        resultMap.setCount(count);
        String endTime = TimeUtils.getNow();
        resultMap.setEndTime(endTime);
        logger.info("********************************************************获取用户列表信息结束**********************************************************");
        return resultMap;
    }

    @Override
    public ResultMap selectCsseUserInfo(HttpServletRequest request, String token, String userId, String account) {
        ResultMap resultMap = new ResultMap();
        logger.info("********************************************************获取用户详细信息开始**********************************************************");
        String startTime = TimeUtils.getNow();
        resultMap.setStartTime(startTime);
        String csseUrl = null;
        if (userId != null){
            csseUrl = apiCsseUserInfoById.replace("{userid}",userId);
        }else if (account !=null){
            csseUrl = apiCsseUserInfoByAccount.replace("{account}",account);
        }
        if (csseUrl != null){
            String url = apiIp+csseUrl+"?access_token="+token;
            logger.info("请求的接口为："+url);
            logger.info("请求类型：GET");
            long apiStartTime = System.currentTimeMillis();
            String obj = HttpClientUtil.httpGet(url);
            long apiEndTime = System.currentTimeMillis();
            resultMap.setApiCallTime((apiEndTime-apiStartTime)+"ms");

            JSONObject jsonObject = JSONObject.parseObject(obj);

            UserPo userPo = new UserPo();
            userPo.setUserId(jsonObject.getString("userid"));
            userPo.setUserName(jsonObject.getString("fullname"));
            userPo.setAccount(jsonObject.getString("account"));
            userPo.setDepId(jsonObject.getString("organId"));
            userPo.setIsManager(jsonObject.getInteger("isManager"));

            HttpSession session = request.getSession();
            session.setAttribute("userInfo", JSONObject.toJSON(userPo));

            resultMap.setData(userPo);

        }else{
            resultMap.setData("userID 和 account 都为空或者没有获取到 对应csseUrl的值");
        }

        String endTime = TimeUtils.getNow();
        resultMap.setEndTime(endTime);
        logger.info("********************************************************获取用户详细信息结束**********************************************************");
        return resultMap;
    }

    @Override
    public void truncateTable() {
        dao.truncateTable();
    }

    @Override
    public void updateTime(Long time) {
        dao.updateTimestamp(time);
    }

    @Override
    public ResultMap test(String token) {

        ResultMap resultMap = new ResultMap();

        String url = apiIp + apiSyncDept + "?starttime=" + 1 + "&department=&access_token=" + token;


        String obj = HttpClientUtil.httpGet(url);

        JSONArray org = JSONObject.parseObject(obj).getJSONArray("org");
        JSONArray user = JSONObject.parseObject(obj).getJSONArray("user");

        List<DepartmentPo> list = new ArrayList<>();
        List<UserPo> userList = new ArrayList<>();
        List<DepartmentPo> nullList = new ArrayList<>();
//        user.stream().forEach(a -> {
//            UserPo userPo = new UserPo();
//            User user1 = new User();
//            userPo.setUserId(((JSONObject) a).getString("userid"));
//            userPo.setUserName(((JSONObject) a).getString("fullname"));
//            userPo.setTelephone(((JSONObject) a).getString("mobile"));
//            userPo.setMail(((JSONObject) a).getString("userEmail"));
//            userPo.setAccount(((JSONObject) a).getString("account"));
//            userPo.setDepId(((JSONObject) a).getString("organId"));
//            userPo.setStatus(((JSONObject) a).getInteger("type"));
//            userPo.setOperatorTime(LocalDate.now() + "");
//            userPo.setIsManager(((JSONObject) a).getInteger("isManager"));
//            userPo.setOrderId(((JSONObject) a).getInteger("orderId"));
//            userPo.setTimestamp(((JSONObject) a).getLong("timestamp"));
//
////            user1.setUsername(userPo.getAccount());
////            user1.setPassword("1");
////            user1.setCreateAccount(userPo.getAccount());
////            user1.setUpdateAccount(userPo.getAccount());
////            user1.setNickname(userPo.getUserName());
////            user1.setEmail("root@root.com");
////            user1.setPortrait("attached/headPortrait/20160606/146519569961677556119.jpg");
//
//            userList.add(userPo);
//        });
//        nullList = list.stream().filter(a -> a.getDeptId()==null).collect(Collectors.toList());
//        System.out.println(nullList);
//       int a =  dao.insertDepartment(list);
        org.stream().forEach(a -> {
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

            list.add(departmentPo);

        });

        //int a = dao.insertUser(userList);
        List<DepartmentPo> list1;
        for(int b = 0; b< list.size(); b++){
            if (b%100 ==0){
                if (b+100 >= list.size()){
                    list1 = list.subList(b, list.size());
                    dao.insertDepartment(list1);
                }else {
                    list1 = list.subList(b, b + 100);
                    dao.insertDepartment(list1);
                }
            }
        }
      //  dao.insertDepartment(list);


        return resultMap;
    }

    @Override
    public ResultMap selectUserByAccount(String account) {
        ResultMap resultMap= new ResultMap();

        UserPo userPo = dao.selectUserByAccount(account);
        resultMap.setData(userPo);
        return resultMap;
    }
}

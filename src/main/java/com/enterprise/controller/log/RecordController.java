package com.enterprise.controller.log;


import com.enterprise.controller.BaseController;
import com.enterprise.entity.LogPo;
import com.enterprise.service.LogService;
import com.enterprise.service.Services;
import com.enterprise.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/manage/log/")
public class RecordController extends BaseController<LogPo> {
    private static final String page_toList = "/manage/log/logList";
//    private static final String page_toEdit = "/manage/log/serviceEdit";
//    private static final String page_toAdd = "/manage/log/serviceEdit";
    @Autowired
    private LogService LogService;
    @Override
    public Services<LogPo> getService() {
        return LogService;
    }

    private RecordController() {
        super.page_toList=page_toList;
        super.page_toEdit=page_toEdit;
        super.page_toAdd=page_toAdd;
    }

    @RequestMapping("logTest")
    @ResponseBody
    public String logTest(){
        LogUtil logUtil = new LogUtil();
        long time = System.currentTimeMillis();
        logUtil.addLog(Double.valueOf(Math.random()).toString(),Double.valueOf(Math.random()).toString(),time-100,time+100,200,"0");
        return page_toList;
    }
}

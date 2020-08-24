package com.enterprise.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.entity.Messages;
import com.enterprise.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class TodoAction {

    @Resource
    private MessageService messageService;

    @RequestMapping("todo")
    @ResponseBody
    public String todo(){
        Messages msg = new Messages();
        List<Messages> msgList = messageService.selectList(msg);
        JSONObject resultJson = new JSONObject();
        resultJson.put("result","success");
        resultJson.put("count",msgList.size()+"");
        return resultJson.toString();
    }
}

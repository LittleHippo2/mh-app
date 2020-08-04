<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/manage/system/pageBase.jsp" %>
<%@ page info="平台维护人员信息" %>
<div>


    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>表单集合演示</legend>
    </fieldset>

    <form class="layui-form" action="" lay-filter="userForm">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">用户名称</label>
            <div class="layui-input-block">
                <input type="text" name="userName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">用户id</label>
            <div class="layui-input-block">
                <input type="text" name="userId" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">账号</label>
            <div class="layui-input-block">
                <input type="text" name="account" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">部门id</label>
            <div class="layui-input-block">
                <input type="text" name="depId" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>
            </div>
        </div>
        <%--<div class="layui-form-item">--%>
            <%--<label class="layui-form-label" style="width: 100px">权限</label>--%>
            <%--<div class="layui-input-block">--%>
                <%--<input type="text" name="isManager" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label"  style="width: 100px">用户角色</label>
            <div class="layui-input-block">
                <input type="radio" name="isManager" value=0 title="普通用户" >
                <input type="radio" name="isManager" value=1 title="安全审计员">
                <input type="radio" name="isManager" value=2 title="安全管理员" >
                <input type="radio" name="isManager" value=4 title="系统管理员">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">返回</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate;

        var data = <%=session.getAttribute("userInfo")%>;

        console.log(data);

        form.val('userForm',{
            "userId": data.userId,
            "userName": data.userName,
            "depId": data.depId,
            "account": data.account,
            "isManager": data.isManager,
        });

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });


        //监听提交
        form.on('submit(demo1)', function(data){
            // layer.alert(JSON.stringify(data.field), {
            //     title: '最终的提交信息'
            // })
            // return false;
            window.location.href="/manage/csseUser/csseUserList.jsp";
            return false;
        });





    });
</script>
<%@ include file="/manage/system/baseFoot.jsp" %>
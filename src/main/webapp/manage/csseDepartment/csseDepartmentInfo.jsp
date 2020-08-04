<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/manage/system/pageBase.jsp" %>
<%@ page info="平台维护组织机构信息" %>
<div>


    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>表单集合演示</legend>
    </fieldset>

    <form class="layui-form" action="" lay-filter="userForm">
        <div class="layui-form-item">
            <label class="layui-form-label">部门名称</label>
            <div class="layui-input-block">
                <input type="text" name="deptName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">部门id</label>
            <div class="layui-input-block">
                <input type="text" name="deptId" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>
            </div>
        </div>
        <%--<div class="layui-form-item">--%>
            <%--<label class="layui-form-label">账号</label>--%>
            <%--<div class="layui-input-block">--%>
                <%--<input type="text" name="account" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="layui-form-item">--%>
            <%--<label class="layui-form-label">部门id</label>--%>
            <%--<div class="layui-input-block">--%>
                <%--<input type="text" name="depId" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="layui-form-item">--%>
            <%--<label class="layui-form-label">权限</label>--%>
            <%--<div class="layui-input-block">--%>
                <%--<input type="text" name="isManager" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">复选框</label>--%>
        <%--<div class="layui-input-block">--%>
        <%--<input type="checkbox" name="like[write]" title="写作">--%>
        <%--<input type="checkbox" name="like[read]" title="阅读" checked="">--%>
        <%--<input type="checkbox" name="like[game]" title="游戏">--%>
        <%--</div>--%>
        <%--</div>--%>

        <%--<div class="layui-form-item" pane="">--%>
        <%--<label class="layui-form-label">原始复选框</label>--%>
        <%--<div class="layui-input-block">--%>
        <%--<input type="checkbox" name="like1[write]" lay-skin="primary" title="写作" checked="">--%>
        <%--<input type="checkbox" name="like1[read]" lay-skin="primary" title="阅读">--%>
        <%--<input type="checkbox" name="like1[game]" lay-skin="primary" title="游戏" disabled="">--%>
        <%--</div>--%>
        <%--</div>--%>

        <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">单选框</label>--%>
        <%--<div class="layui-input-block">--%>
        <%--<input type="radio" name="sex" value="男" title="男" checked="">--%>
        <%--<input type="radio" name="sex" value="女" title="女">--%>
        <%--<input type="radio" name="sex" value="禁" title="禁用" disabled="">--%>
        <%--</div>--%>
        <%--</div>--%>

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

        var data = <%=session.getAttribute("deptInfo")%>;

        console.log(data);

        form.val('userForm',{
            "deptName": data.name,
            "deptId": data.deptId,
            // "depId": data.depId,
            // "account": data.account,
            // "isManager": data.isManager,
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
            window.location.href="/manage/csseDepartment/csseDepartmentInfo.jsp";
            return false;
        });
    });
</script>


</div>
<%@ include file="/manage/system/baseFoot.jsp" %>
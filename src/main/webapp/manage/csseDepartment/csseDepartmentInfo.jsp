<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/manage/system/pageBase.jsp" %>
<%@ page info="平台维护组织机构" %>
<style>

</style>
<div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>人员详情</legend>
    </fieldset>
    <div>
        <form class="layui-form" action="" lay-filter="userForm">
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">部门名称</label>
                <div class="layui-input-block">
                    <input type="text" name="deptName" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                           class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">部门id</label>
                <div class="layui-input-block">
                    <input type="text" name="deptId" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                           class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">机构简称</label>
                <div class="layui-input-block">
                    <input type="text" name="code" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                           class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">所属机构</label>
                <div class="layui-input-block">
                    <input type="text" name="parentId" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                           class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">返回</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        var data = <%=session.getAttribute("deptInfo")%>;

        console.log(data);

        form.val('userForm', {
            "deptName": data.name,
            "deptId": data.deptId,
            "code": data.code,
            "parentId": data.parentId,
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
        form.on('submit(demo1)', function (data) {
            window.location.href = "/manage/csseDepartment/csseDepartmentList.jsp";
            return false;
        });
    });
</script>


</div>
<%@ include file="/manage/system/baseFoot.jsp" %>
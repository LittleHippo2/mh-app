<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/manage/system/pageBase.jsp" %>
<%@ page info="平台维护组织机构" %>

<style>
    table {
        table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        width: 100%;
    }

    td {
        word-break: keep-all; /* 不换行 */
        white-space: nowrap; /* 不换行 */
        overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
        text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/
    }

</style>
<link rel="stylesheet" href="../../resource/layui/css/layui.css">
<script src="../../resource/layui/layui.js"></script>


<div class="layui-row" >
    <div class="layui-col-md3">
        <div style=" ">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>部门树图</legend>
            </fieldset>
            <%--<div id="deptList" class="demo-tree demo-tree-box"></div>--%>
            <div id="deptList" class="demo-tree demo-tree-box" style="width: 400px; height: 800px; overflow: scroll;"></div>
        </div>
    </div>
    <div class="layui-col-md9" style="">
        <div style="margin-top: 50px;">
            <table id="demo" lay-filter="test"></table>
        </div>
    </div>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs layui-btn-xs" lay-event="detail">查看</a>
    <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
    <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
</script>


<script>
    layui.use(['tree', 'util', 'table'], function () {
        var tree = layui.tree
            , layer = layui.layer
            , util = layui.util
            , table = layui.table


        renderTable();

        // to_page();
        function renderTable(id) {
            table.render({
                elem: '#demo'
                , height: 'full-200'
                , url: '/manage/department/csse/selectDeptListInfo' //数据接口
                ,toolbar: true //开启头部工具栏，并为其绑定左侧模板
                , where: {
                    token:"624c60ff-154b-4148-9881-3dfb927e8275",
                    fatherId: id
                }
                , parseData: function (res) {
                    return {
                        "count": res.count,
                        "code": 100,
                        "data": res.data
                    }
                }
                , page: true
                , cols: [[ //表头
                    {field: 'index', title: '序号', width: 300, type: 'numbers'}
                    , {field: 'deptId', title: 'ID', width: 300, sort: true}
                    , {field: 'name', title: '部门名称', width: 300}
                    , {field: 'code', title: '部门简称', width: 300, sort: true}
                    ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}

                ]]
                , response: {
                    statusName: 'code'
                    , statusCode: '100'
                    , msgName: 'msg'
                    , countName: 'count'
                    , dataName: 'data'
                }
            });
        }

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            // var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    // var data = checkStatus.data;
                    // layer.alert(JSON.stringify(data));
                    break;
            };
        });
        //监听头工具条
        table.on('tool(test)', function(obj){
            var data = obj.data;
            console.log(data)
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.deptId + ' 的查看操作');
            }
            // else if(obj.event === 'del'){
            //     layer.confirm('真的删除行么', function(index){
            //         obj.del();
            //         layer.close(index);
            //     });
            // } else if(obj.event === 'edit'){
            //     layer.alert('编辑行：<br>'+ JSON.stringify(data))
            // }
        });
        //监听右侧工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            }
            // else if(obj.event === 'del'){
            //     layer.confirm('真的删除行么', function(index){
            //         obj.del();
            //         layer.close(index);
            //     });
            // } else if(obj.event === 'edit'){
            //     layer.alert('编辑行：<br>'+ JSON.stringify(data))
            // }
        });


        $(function () {
            $.ajax({
                url: "/manage/department/csse/selectDeptList",
                data:{
                    token:"624c60ff-154b-4148-9881-3dfb927e8275"
                }
                ,success: function (res) {
                    tree.render({
                        elem: '#deptList' //默认是点击节点可进行收缩
                        , data: res.data
                        , showLine: false
                        , click: function (obj) {
                            console.log(obj.data); //得到当前点击的节点数据
                            if (obj.data.id == 'root') {
                                renderTable()
                            } else {
                                renderTable(obj.data.id)
                            }
                        }
                    });
                    console.log(res.data);
                }
            })
        })

    });


</script>

<%@ include file="/manage/system/baseFoot.jsp" %>
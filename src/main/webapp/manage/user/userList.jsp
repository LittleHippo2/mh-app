<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/manage/system/pageBase.jsp" %>
<%@ page info="应用维护人员列表" %>

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
<%--<script type="text/html" id="toolbarDemo">--%>
    <%--<div class="layui-btn-container">--%>
        <%--<button class="layui-btn-radius layui-btn-warm layui-btn-sm" lay-event="getCheckData">增量同步</button>--%>
    <%--</div>--%>
<%--</script>--%>
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
                , url: '/manage/department/selectUserList' //数据接口
                ,toolbar: true //开启头部工具栏，并为其绑定左侧模板
                , where: {
                    deptId: id
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
                    {field: 'index', title: '序号', width: 150, type: 'numbers'}
                    , {field: 'userId', title: 'ID', width: 300, sort: true}
                    , {field: 'userName', title: '用户名称', width: 300}
                    , {field: 'account', title: '账号', width: 300, sort: true}
                    , {field: 'depId', title: '所在部门ID', width: 300, sort: true}
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
        function syncData(){
            $.ajax({
                url:"/manage/department/sync",
                data:{
                    token:"3ba736c3-dc81-439b-a110-84735d41094a"
                },
                success:function (res) {
                    console.log(res)
                }
            })
        }

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            // var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    // var data = checkStatus.data;
                    syncData()
                    // layer.alert(JSON.stringify(data));
                    break;
            };
        });


        $(function () {
            $.ajax({
                url: "/manage/department/getDeptList",
                success: function (res) {
                    tree.render({
                        elem: '#deptList' //默认是点击节点可进行收缩
                        , data: res.data
                        , showLine: false
                        , click: function (obj) {
                            console.log(obj.data); //得到当前点击的节点数据
                            // console.log(obj.state); //得到当前节点的展开状态：open、close、normal
                            // console.log(obj.elem); //得到当前节点元素
                            //
                            // console.log(obj.data.children); //当前节点下是否有子节点
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
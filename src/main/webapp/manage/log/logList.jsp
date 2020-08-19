<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/manage/system/pageBase.jsp" %>
<%@ page info="日志" %>

<form action="<%=path%>/manage/log" method="post">
    <div style="height:auto!important;height:550px;min-height:550px;">
        <div class="filter">
            <div style="float: right">

                <input type="text" value="${e.param}" class="input-medium search-query" name="param" placeholder="入参"/>
                <button method="selectList" class="btn btn-info" style="padding:0px 5px;margin-top: -4px;"
                        onclick="selectList(this)">
                    <i class="icon-search"></i>查询
                </button>
            </div>
        </div>
        <table class="table table-bordered table-hover table-striped">
            <tr>
                <th width="50"><input type="checkbox" id="firstCheckbox"/></th>
                <th>入参</th>
                <th>出参</th>
                <th>接口调用开始时间</th>
                <th>接口调用结束时间</th>
                <th>耗时</th>
                <th>状态</th>
            </tr>
            <c:forEach var="item" items="${pager.list}">
                <tr>
                    <td><input type="checkbox" name="ids"
                               value="${item.id}"/></td>
                    <td>${item.param}</td>
                    <td>${item.result}</td>
                    <td>${item.beginTime}</td>
                    <td>${item.endTime}</td>
                    <td>${item.time}</td>
                    <td>${item.status}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="71" style="text-align: center;">
                    <%@ include file="/manage/system/page.jsp" %>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">
    $(function () {
        $("#firstCheckbox").on("click", function () {
            if ($(this).prop("checked")) {
                $("input[type=checkbox]").prop("checked", true);
            } else {
                $("input[type=checkbox]").prop("checked", false);
            }
        });
    });
    // function submitIDs(obj, tip) {
    //     if ($("input:checked").size() == 0) {
    //         alert("请先选择要操作的内容！");
    //         return false;
    //     }
    //
    //     if (confirm(tip)) {
    //         //createMark();
    //         var _form = $("form");
    //         _form.attr("action", $(obj).attr("method"));
    //         _form.submit();
    //     }
    //     return false;
    // }
    //查询
    function selectList(obj) {

        var _form = $("form");
        _form.attr("action", $(obj).attr("method"));
        _form.submit();
    }
</script>

<%@ include file="/manage/system/baseFoot.jsp" %>

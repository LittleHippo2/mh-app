<%--
  Created by IntelliJ IDEA.
  User: Cesiumai
  Date: 2016/6/15
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%--<!-- bootstrap -->
<link rel="stylesheet" href="<%=path %>/resource/bootstrap-3.3.5/css/bootstrap.min.css" type="text/css">
<script type="text/javascript" src="<%=path %>/resource/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/bootstrap-3.3.5/js/bootstrap-modal.js"></script>--%>
<script type="text/javascript">

    /**
     * 重新加载 布尔类型 是否忽略缓存 为true时强制刷新
     * @param bypassCache
     */
    function reload() {
        var bypassCache;
        if ($('#isTrue').is(':checked')) {
            bypassCache = 'true';
        }
        if ($('#isFalse').is(':checked')) {
            bypassCache = 'false';
        }
        if (typeof Desktop == "undefined") {
            console.log('Desktop undefined');
            __reload__(bypassCache)
            return true;
        } else {
            Desktop.__reload__(bypassCache);
            return true;
        }

    }

    /**
     * 清空缓存
     * @param bypassCache  是否忽略清理Cookie
     */
    function clearCache(bypassCache) {
        var bypassCookie;
        if ($('#yes').is(':checked')) {
            bypassCookie = 'true';
        }
        if ($('#no').is(':checked')) {
            bypassCookie = 'false';
        }
        console.log(bypassCookie)
        if (typeof Desktop == "undefined") {
            console.log('Desktop undefined');
            __clearCache__(bypassCookie);
            return true;
        } else {
            Desktop.__clearCache__(bypassCookie);
            return true;
        }
    }
</script>
<style>

    .nav{
        list-style: none outside none;
    }
    .nav li {
        width: 77px;
        height: 107px;
        float: left;
        line-height: 107px;
        font-size: 17px;
        color: #262626;
        margin-left: 16px;
        margin-right: 16px;
        text-align: center;
    }
    .nav li a {
        text-decoration: none;
        color: inherit;
        padding-top: 10px;
        font-family: Microsoft Yahei;
        padding: 0px 0px;
    }
    }


    .nav li a:hover{
        border-top: 3px solid #424C55;

    }
</style>
<div style="width:95%;height: 107px;margin: 0 auto;padding-left: 10%">
    <div style="width: 201px;height: 107px;float: left;">
        <a href="index">
        <img src="<%=SystemManage.getInstance().getSystemSetting().getImageRootPath() %><%=SystemManage.getInstance().getSystemSetting().getLogo() %>"
             width="201" height="107"/>
            </a>
    </div>
    <div style="width: 1000px;height: 107px;float: left;overflow: hidden;padding-left: 5%;">
    <ul class="nav">
        <li>
            <a href="<%=path%>/index">首 页</a>
        </li>
        <li>
            <a href="<%=path%>/about">关于我们</a>
        </li>
        <li>
            <a href="<%=path%>/service">服务领域</a>
        </li>
        <li>
            <a href="<%=path%>/article">新闻动态</a>
        </li>
        <%--<li>--%>
        <%--<a href="<%=path%>/recruitment">诚聘英才</a>--%>
        <%--</li>--%>
        <li>
            <a href="<%=path%>/message">在线留言</a>
        </li>
        <li>
            <a href="<%=path%>/contact">联系我们</a>
        </li>
        <li>
            <a href="<%=path%>/softwareList">应用展示</a>
        </li>
        <%--<li>
            <a href="<%=path%>/api">接口集成</a>
        </li>--%>
    </ul>
</div>
    <div style="width:200px;height: 107px;float: right;padding-top: 35px;">
        <!-- 按钮触发模态框 -->
        <button class="btn btn-primary" data-toggle="modal" data-target="#reload" >刷新</button>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="reload" tabindex="-1" role="dialog" aria-labelledby="reloadLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="reloadLabel">重新加载</h4>
                    </div>
                    <div class="modal-body">
                        <div class="radio">
                            <label>是否忽略缓存：</label>
                            <label>
                                <input type="radio" name="optionsRadios" id="isTrue" value="isTrue" checked="true">是
                            </label>
                            <label>
                                <input type="radio" name="optionsRadios" id="isFalse" value="isFalse">否
                            </label>

                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="reload()" data-dismiss="modal">确认刷新</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
        <!-- 按钮触发模态框 -->
        <button class="btn btn-primary" data-toggle="modal" data-target="#clearCach">清理缓存</button>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="clearCach" tabindex="-1" role="dialog" aria-labelledby="clearCachLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="clearCachLabel">清理缓存</h4>
                    </div>
                    <div class="modal-body">

                        <div class="radio">
                            <label>是否忽略清理Cookie：</label>
                            <label>
                                <input type="radio" name="optionsRadios" id="yes" value="yes" checked="true">是
                            </label>
                            <label>
                                <input type="radio" name="optionsRadios" id="no" value="no">否
                            </label>

                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="clearCache()" data-dismiss="modal">清除</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/manage/system/htmlBase.jsp" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<div class="navbar navbar-default" id="navbar">
    <div class="navbar-container" id="navbar-container">

    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">

            <ul class="nav nav-list" id="side-menu">
                <li><a class="menu-item" href="javascript:void(0)" onclick="get_m_token_show()"><i class="icon-double-angle-right"></i><span class="app-menu__label">获取微服务access_token</span></a></li>
                <li class="menu-item active"><a id="item_" href="javascript:void(0)" onclick="get_sso_show()"><i class="icon-edit"></i><span class="menu-text">登录服务</span><b class="arrow icon-angle-down"></b></a>
                    <ul class="submenu">
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="get_sso_token_show()"><i class="icon-double-angle-right"></i>获取单点登录token</a></li>
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="get_sso_user_show()"><i class="icon-double-angle-right"></i>获取用户信息</a></li>
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="valid_sso_token_show()"><i class="icon-double-angle-right"></i>验证token</a></li>
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="refresh_sso_token_show()"><i class="icon-double-angle-right"></i>刷新单点登录token有效期</a></li>
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="change_sso_password_show()"><i class="icon-double-angle-right"></i>修改密码</a></li>
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="change_sso_oapassword_show()"><i class="icon-double-angle-right"></i>修改密码（不校验密码复杂度）</a></li>
                        <li class="menu-item" ><a class="" href="javascript:void(0)" onclick="logou_sso_show()"><i class="icon-double-angle-right"></i>注销单点登录token</a></li>
                    </ul>
                </li>

                <li class="menu-item active"><a class="app-menu__item" href="javascript:void(0)" onclick="get_deparement_show()"><i class="icon-edit"></i><span class="menu-text">组织机构服务</span><b class="arrow icon-angle-down"></b></a>
                    <ul class="submenu">
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_deparement_list_show()"><i class="icon-double-angle-right"></i>获取部门列表</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_more_department_list_show()"><i class="icon-double-angle-right"></i>获取多部门列表</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_department_info_show()"><i class="icon-double-angle-right"></i>获取部门详情</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_user_list_show()"><i class="icon-double-angle-right"></i>获取人员列表</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_userinfoByAccount_show()"><i class="icon-double-angle-right"></i>获取人员详情（根据account）</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_userinfo_show()"><i class="icon-double-angle-right"></i>获取人员详情</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="sync_show()"><i class="icon-double-angle-right"></i>增量同步接口</a></li>
                    </ul>
                </li>
                <li class="menu-item active"><a class="app-menu__item" href="javascript:void(0)" onclick="get_msg_show()"><i class="icon-edit"></i><span class="menu-text">消息服务</span><b class="arrow icon-angle-down"></b></a>
                    <ul class="submenu">
                        <li><a class="menu-item" href="javascript:void(0)" onclick="msg_one_user_show()"><i class="icon-double-angle-right"></i>发送消息至个人</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="msg_more_user_show()"><i class="icon-double-angle-right"></i>发送消息至多人</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="msg_one_department_show()"><i class="icon-double-angle-right"></i>发送消息至部门</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="msg_more_department_show()"><i class="icon-double-angle-right"></i>发送消息至多部门</a></li>
                    </ul>
                </li>
                <li class="menu-item active"><a class="app-menu__item" href="javascript:void(0)" onclick="get_logs_show()"><i class="icon-edit"></i><span class="menu-text">日志服务</span><b class="arrow icon-angle-down"></b></a>
                    <ul class="submenu">
                        <li><a class="menu-item" href="javascript:void(0)" onclick="add_log_show()"><i class="icon-double-angle-right"></i>增加日志操作</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_log1_show()"><i class="icon-double-angle-right"></i>根据用户名审计应用日志</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_log2_show()"><i class="icon-double-angle-right"></i>根据应用标识审计应用日志</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="get_log3_show()"><i class="icon-double-angle-right"></i>根据应用标识审计平台日志</a></li>
                    </ul>
                </li>
                <!--<li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-cog"></i><span class="app-menu__label">检索服务</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>搜索公文标准</a></li>
                        <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>搜索邮件标准</a></li>
                        <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>搜索应用内部数据标准</a></li>
                    </ul>
                </li>-->
                <li class="menu-item active"><a class="app-menu__item" href="javascript:void(0)" onclick="get_subscribe_show()"><i class="icon-edit"></i><span class="menu-text">订阅服务</span><b class="arrow icon-angle-down"></b></a>
                    <ul class="submenu">
                        <li><a class="menu-item" href="javascript:void(0)" onclick="subscribe_add_show()"><i class="icon-double-angle-right"></i>添加订阅信息</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="subscribe_delete_show()"><i class="icon-double-angle-right"></i>删除订阅信息</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="subscribe_user_show()"><i class="icon-double-angle-right"></i>查询用户的订阅信息</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="subscribe_article_user_show()"><i class="icon-double-angle-right"></i>查询用户是否订阅指定文章</a></li>
                    </ul>
                </li>
                <!-- <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-cog"></i><span class="app-menu__label">待办任务服务</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                     <ul class="treeview-menu">
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>应用前端调用桌面更新</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>应用前端关闭窗口</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>待办任务服务调用应用获取待办任务数量</a></li>
                     </ul>
                 </li>
                 <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-cog"></i><span class="app-menu__label">win32本地程序接入规范</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                     <ul class="treeview-menu">
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>添加注册表项</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>添加办公桌面白名单</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>启动命令</a></li>
                     </ul>
                 </li>
                 <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-cog"></i><span class="app-menu__label">应用前端接口</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                     <ul class="treeview-menu">
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>应用待办任务数设置</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>应用前端关闭窗口</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>应用窗口关闭事件拦截处理（注册模式）</a></li>
                         <li><a class="treeview-item" href="javascript:void(0)"><i class="icon fa fa-circle-o"></i>应用窗口关闭事件拦截处理（定义模式）</a></li>
                     </ul>
                 </li>-->
                <li class="menu-item active"><a class="app-menu__item" href="javascript:void(0)" onclick="desktop_show()"><i class="icon-edit"></i><span class="menu-text">办公桌面信息屏接口</span><b class="arrow icon-angle-down"></b></a>
                    <ul class="submenu">
                        <li><a class="menu-item" href="javascript:void(0)" onclick="openAppShow()"><i class="icon-double-angle-right"></i>打开应用</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="setWinSizeShow()"><i class="icon-double-angle-right"></i>设置新窗口打开大小</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="getAppListShow()"><i class="icon-double-angle-right"></i>获取应用列表</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="getAppListInfoShow()"><i class="icon-double-angle-right"></i>获取应用详情</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="reloadShow()"><i class="icon-double-angle-right"></i>重新加载</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" onclick="clearCacheShow()"><i class="icon-double-angle-right"></i>清空缓存</a></li>
                        <li><a class="menu-item" href="javascript:void(0)" ><i class="icon-double-angle-right"></i>应用需实现接口说明</a></li>
                    </ul>
                </li>


            </ul>


            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(function(){

        "use strict";

        var treeviewMenu = $('.app-menu');

        // Toggle Sidebar
        $('[data-toggle="sidebar"]').click(function(event) {
            event.preventDefault();
            $('.app').toggleClass('sidenav-toggled');
        });

        // Activate sidebar treeview toggle
        $("[data-toggle='treeview']").click(function(event) {
            event.preventDefault();
            if(!$(this).parent().hasClass('is-expanded')) {
                treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');
            }
            $(this).parent().toggleClass('is-expanded');
        });

        // Set initial active toggle
        $("[data-toggle='treeview.'].is-expanded").parent().toggleClass('is-expanded');

        //Activate bootstrip tooltips
        $("[data-toggle='tooltip']").tooltip();

    });
    </script>

<div region="north" split="true" title="基本信息" style="padding-left:20%;padding-top:5%;height:150px">

    <table class="table table-striped table-bordered table-hover">
        <tr>
            <td th:width="300"><label>接口调用时长:</label></td>
            <td th:width="500"><label id="time"></label></td>
        </tr>
        <tr>
            <td th:width="200"><label>单点登录access_token: </label></td>
            <td th:width="500"><label id="sso_token"></label></td>
        </tr>
        <tr>
            <td th:width="300"><label>微服务access_token: </label></td>
            <td th:width="500"><label id="microservice_token"></label></td>
        </tr>

    </table>
    <br><br>
</div>
<div  id="content" title="接口测试" style="padding-left:20%;padding-top:8%">

</div>
</body>

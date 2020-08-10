<%--
  Created by IntelliJ IDEA.
  User: Cesiumai
  Date: 2016/7/8
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/front/common/common.jsp"%>


<body>
<%@include file="/front/common/navigation.jsp"%>

<div class="banner" style="background-image: url(<%=path%>/resource/images/banner.jpg)"></div>
<div class="warp_main">
    <div class="warp_left">
        <div class="warp_left_box">
            <h3>应用展示</h3>

        </div>
    </div>
    <div class="warp_right">
        <div class="breadcrumb">
            <a href="<%=path%>/index">首页</a>
            >
            <a href="<%=path%>/contact">应用展示</a>
        </div>
        <div style="overflow: hidden;">
            <div class="article_content">

<%--                <input type="submit"   value="获取应用列表" onclick="getAppList()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/>--%>
                <input type="submit" class="btn btn-primary"  value="获取应用详情" onclick="getAppListInfo()" />
<%--                <input type="submit" class="btn btn-primary"  value="设置新窗口大小" onclick="setWinSize()"/>--%>
<%--                <input type="submit"   value="打开应用" onclick="openApp()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/>--%>

                <!-- 按钮触发模态框 -->
                <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">设置新窗口大小</button>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">设置新窗口大小</h4>
                            </div>
                            <div class="modal-body">
                                <label>请输入新窗口宽：</label><input  id="wide" type="text"   style="width:100px;"  value="" />
                                <label>请输入新窗口高：</label><input  id="hight" type="text"   style="width:100px;"  value="" />

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" onclick="setWinSize()" data-dismiss="modal">提交更改</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

    <br>
                <br>
                <table id="softwareList" class="table table-striped table-bordered table-hover" align="center">
                    <thead>
                    <tr>
                        <th><label>应用名称</label></th>
                        <th><label>应用类型</label></th>
                        <th><label>操作</label></th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
   /* $(function(){
        getAppListInfo();

    });*/
    /**
     * 打开应用
     * @param appid
     * @param redirect 回调地址 可为空
     */
    function openApp(appid) {
        // var appid = "cpk.csse.demoapp_app2b_ptfwjkgf";
        var redirect = "";
        console.log("应用id："+appid);

        if (typeof Desktop == "undefined") {
            console.log("Desktop undefined");
            _service_._openApp_(appid, redirect);
        } else {
            console.log("openApp");
            Desktop.__openApp__(appid, redirect);
        }

    }

    /**
     * 设置新窗口打开大小
     * @param wide 宽
     * @param hight 高
     */
    function setWinSize() {
        var wide = $("#wide").val();
        var hight = $("#hight").val();
        console.log("wide:"+wide+" hight:"+hight);
        if (wide==""&&hight=="")
        {
            console.log("请输入宽和高！");
            alert("请输入宽和高！");
            return false;
        }

        if (!(/(^[1-9]\d*$)/.test(wide))&&!(/(^[1-9]\d*$)/.test(hight)))

        {
            console.log('请输入正确的宽和高！');
            alert('请输入正确的宽和高！');
            return false;

        }else{

            if (typeof Desktop == "undefined") {
                console.log("Desktop undefined");
                _service_._setLinkWindowSize_(wide, hight);
            } else {
                console.log("__setLinkWindowSize__");
                Desktop.__setLinkWindowSize__(wide, hight);
            }

        }



    }

    /**
     * 获取应用列表
     * @param  回调方法
     */
    function getAppList() {
        if (typeof Desktop == "undefined") {
            console.log('Desktop undefined');
            var appids =_service_._getAppList_();
            console.log(appids);
            __onGetAppList__(appids);
        } else {
            //回调方法data为一个形参  json格式
            Desktop.__getAppList__(function (data) {
                alert(data);
                console.log('获取应用列表成功：' + data);
                var appIdList = JSON.parse(data);
                appIdList.forEach(function (appId) {

                    console.log(appId);
                });
            });
        }
    }

    /**
     * 获取应用列表详情
     * @param callback 回调方法
     */
    function getAppListInfo(callback) {

        $("#softwareList tbody").html("");
        var html='';
        if (typeof Desktop == "undefined") {
            console.log('Desktop undefined');
            var appInfoList =  _service_._getAppInfoList_();
            __onGetAppInfoList__(appInfoList);
        } else {
            Desktop.__getAppInfoList__(function (data) {
              //  console.log('获取应用信息列表成功：' + data);
        // var data= '[{"status": 0, "desktops": 1, "name": "应用测试", "deletable": true, "icon64": "iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDoxNDQwQjQ2Nzk4NENFNjExQjQ2QUYwMjU0Njc3QjY5MCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoyQjVEQkQxRUIyNUQxMUU3QkMwM0Y3QTFGNDlDM0QwNSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoyQjVEQkQxREIyNUQxMUU3QkMwM0Y3QTFGNDlDM0QwNSIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjI1MTgyRUVBMjNCMkU3MTFCMTQ5QUNBOEQ4MjFERTIzIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjE0NDBCNDY3OTg0Q0U2MTFCNDZBRjAyNTQ2NzdCNjkwIi8+IDwvcmRmOkRlc2NyaXB0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", "installed": true, "type": "web", "id": "legacycpk.csse.apptest", "icon": "file:///cpk/app/legacycpk.csse.apptest/icons/legacycpk.csse.apptest.png?r=SY0haGDd"}]';
            var appInfoList = JSON.parse(data);
                appInfoList.forEach(function (appInfo) {
                    var object = JSON.parse(JSON.stringify(appInfo));
                    console.log('应用名称：'+object.name);
                    console.log('应用id：'+object.id);
                    html=html+'<tr><td><img src="data:image/jpeg;base64,'+object.icon64+'"  width="50px"/>&nbsp;'
                        +object.name+'</td><td>'
                        +object.type+'</td><td><input type="submit"   value="打开应用" onclick="openApp(\''+object.id+'\')" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/></td></tr>'
                    //console.log(html)

                });
               // console.log('表格代码：' + html);
                $("#softwareList tbody").append(html);
            });

        }

    }

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
        } else {
            Desktop.__reload__(bypassCache);
        }

    }

    /**
     * 清空缓存
     * @param bypassCache  是否忽略清理Cookie
     */
    function clearCache(bypassCache) {
        var bypassCookie;
        if ($('#isTrue').is(':checked')) {
            bypassCookie = 'true';
        }
        if ($('#isFalse').is(':checked')) {
            bypassCookie = 'false';
        }
        if (typeof Desktop == "undefined") {
            console.log('Desktop undefined');
            __clearCache__(bypassCookie);
        } else {
            Desktop.__clearCache__(bypassCookie);
        }
    }

    //初始化接口，可通过判断新接口的“Desktop”是否“undefined”，判断新接口是否可用。
    function __init__() {
        console.log('初始化...');
        if (typeof(Desktop) == 'undefined') {
            console.warn('Desktop异常...');
            return ;
        }
        //当办公桌面上应用数据发生变动时（如应用代办数发生变化）调用该方法，应用可在该方法中实现更新应用列表、更新待办事项数等业务操作。
        Desktop.__dataUpdated__.connect(function () {
            console.log('*****数据更新*****');
        })
    }


    //配套旧接口使用
    function __onGetAppList__(appIds) {
        console.warn(appIds);
        console.warn(appIds.split(";"));
    }
    // 获取应用信息列表成功；appInfoList为应用数据对象数
    function __onGetAppInfoList__(appInfoList) {
        console.warn(JSON.stringify(appInfoList));
    }

    function Base64() {

        // private property
        _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

        // public method for encoding
        this.encode = function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = _utf8_encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output +
                    _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                    _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
            }
            return output;
        }

        // public method for decoding
        this.decode = function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = _keyStr.indexOf(input.charAt(i++));
                enc2 = _keyStr.indexOf(input.charAt(i++));
                enc3 = _keyStr.indexOf(input.charAt(i++));
                enc4 = _keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = _utf8_decode(output);
            return output;
        }

        // private method for UTF-8 encoding
        _utf8_encode = function (string) {
            string = string.replace(/\r\n/g,"\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }

            }
            return utftext;
        }

        // private method for UTF-8 decoding
        _utf8_decode = function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while ( i < utftext.length ) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i+1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i+1);
                    c3 = utftext.charCodeAt(i+2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    }
</script>
<%@include file="/front/common/foot.jsp" %>

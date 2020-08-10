

function todo() {
    window.__set_todo_count__(5);
}

function get_m_token_show(){ //1.2.1获取微服务access_token
	var vue=$("#access").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#access").attr("class","menu-item");
	}else{
		$("#access").attr("class","menu-item active");
	}
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>获取微服务access_token</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">各众创应用在调用平台提供的核心服务接口（除登录服务外）时，需携带微服务access_token以便通过认证从而获取返回信息。该access_token表示应用的身份，是应用的全局唯一凭证，需通过应用id（client_id）及秘钥（client_secret）调用获取微服务access_token接口获得。</p>'
		+'<p style="text-indent:2em;">其中，应用id（client_id）及秘钥（client_secret）需向平台统一申请。</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/uaa/oauth/token?client_id={client_id}&client_secret={secret}&grant_type={type}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>client_id</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>应用id</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>client_secret</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>应用密钥</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>grant_type</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>授权类型（当前仅支持client_credentials）</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>微服务access_token</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>token_type</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>token类型</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>expires_in</label></td>'
		+'<td><label>long</label></td>'
		+'<td><label>有效时间（单位为秒，默认有效期为604800秒，即7天）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>scope</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>申请权限的范围</label></td>'
		+'</tr>'
		+'</table><br>'
		
		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_m_token()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入appid :</label></td>'
		+'<td><input  id="appid" type="text" value="base.csse.desktop"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入secret: </label></td>'
		+'<td><input  id="secret" type="text"  value="a75b5772-f005-4dbe-b5e0-9ac7793533cc"  style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';


 }
function get_sso_show(){ //登录服务
	var div = document.getElementById("content");
	var vue=$("#login").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#login").attr("class","menu-item");
	}else{
		$("#login").attr("class","menu-item active");
	}


	div.innerHTML ='<b><p>登录服务</p></b>'
		+'<p>接口说明</p>'
		+'<p style="text-indent:2em;">各个众创应用在获取到单点登录token后，需先调用验证token接口，验证该token的合法性，若token合法则表示登录成功，实现免等操作。具体登录的人员信息，可通过调用获取用户信息接口获得。</p>'
		+'<p style="text-indent:2em;">验证token相关说明：</p>'
		+'<p style="text-indent:4em;">1）桌面应用在token到期前自动续租保持不变；</p>'
		+'<p style="text-indent:4em;">2）桌面崩溃后，通过桌面守护进程自动重启，启动完成后继续使用之前有效的token；</p>'
		+'<p style="text-indent:4em;">3）桌面守护进程崩溃了，提示桌面发生重大错误，需要重新登录，强制退出所有app，再次登录，获取新的有效token；</p>'
		+'<p style="text-indent:4em;">4）桌面会定期刷新验证token有效性。</p>'

}

 function get_sso_token_show(){ //2.2.1获取单点登录access_token

	/* var vue=$("#login_get").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#login_get").attr("class","menu-item");
	 }else{
		 $("#login_get").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
			
	div.innerHTML ='<b><p>获取单点登录access_token</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">1）受平台托管的Web应用，从桌面点击对应的应用图标时，桌面会将单点登录token（access_token）添加至应用主页的url后</p>'
		+'<p style="text-indent:2em;">2）受平台托管的客户端应用，可通过调用桌面提供的dbus接口获取单点登录token</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/authen</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b></p>'
		+'<p style="text-indent:4em;">Content-Type: application/x-www-form-urlencoded</p>'
		+'<p style="text-indent:4em;">client_ip: {桌面客户端IP地址}（可通过HTTP请求中获取客户端IP）</p>'
		+'<p style="text-indent:4em;">is_encrypt: {Boolean}（是否加密。若为true则密码需通过AES/CBC/ZeroPadding方式进行加密；若为false或无此参数则以明文方式进行参数传输。）</p>'
		+'<p style="text-indent:4em;">platform: 多终端登录标识 pc，pad</p>'
		+'<p style="text-indent:4em;">is_validate: 只校验不发token标识 默认false</p>'

		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>username</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>用户名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>password</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>loginMode</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>登录模式（采用用户口令登录时为100，采用证书模式登录时为010）</label></td>'
		+'</tr>'
		+'<td><label>service</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>服务地址（无需更改）</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>登录凭证</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_sso_token()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入用用户账号 :</label></td>'
		+'<td><input  id="sso_username" type="text"  style="width:500px;"  value="sysadmin" /></td></tr>'
		+'<tr><td><label>请输入密码 :</label></td>'
		+'<td><input  id="sso_password" type="text"   style="width:500px;"  value="password02!" /></td></tr>'
		+'<tr><td><label>请输入登录模式:</label></td>'
		+'<td><input  id="sso_loginmodel" type="text"   style="width:500px;"  value="100" /></td></tr>'
		+'<tr><td><label>请输入service地址:</label></td>'
		+'<td><input  id="sso_service" type="text"   style="width:500px;"  value="172.16.5.200:10004/zrsso51/index.html" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';


 }
 function get_sso_user_show(){  //2.2.2获取单点登录用户信息

	 /*var vue=$("#login_user").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#login_user").attr("class","menu-item");
	 }else{
		 $("#login_user").attr("class","menu-item active");
	 }*/


	var div = document.getElementById("content");
			
	div.innerHTML ='<b><p>获取单点登录用户信息</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">用于应用获取单点登录用户的基本信息。</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/user</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b></p>'
		+'<p style="text-indent:4em;">Content-Type: application/x-www-form-urlencoded</p>'
		+'<p style="text-indent:4em;">client_ip: {桌面客户端IP地址}（可通过HTTP请求中获取客户端IP）</p>'


		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>调用接口凭证</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>账号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>attrMap</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>扩展属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ca</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务认证授权中心</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>delete</label></td>'
		+'<td><label>boolean</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>editPwdTime</label></td>'
		+'<td><label>Date</label></td>'
		+'<td><label>密码修改时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>enddate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期截止时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>failedLoginCount</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>失败登录统计</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fullname</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>全名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ip</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>ip地址</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isManager</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>管理员类型（0：普通用户；1：安全审计员；2：安全管理员；3：安全审计员+安全管理员 4：系统管理员；5：安全审计员+系统管理员； 6：安全管理员+系统管理员； 7：安全审计员+安全管理员+系统管理员）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>loginMode</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>登录模式（允许通过用户名口令时为100，允许通过证书模式时为010，允许通过口令+证书模式时为110）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>manageLevel</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>职务</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>mobile</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>手机号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>nodeType</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>节点类型</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orgName</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>password</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>secLevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密级（-1：非涉密；1：一般；2：重要；3：核心）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sex</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>性别（0：男；1：女）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡序列号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>spid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡型号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startdate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>long</label></td>'
		+'<td><label>最后一次操作的时间戳</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tokenId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令的种子</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>type</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>最后一次操作的类型（0：删除；1：修改；2：新增）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>userid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useremail</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>邮箱</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useruuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organIds</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属机构id集合</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>manageOrgan</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>人员的权限关系</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_sso_user()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function valid_sso_token_show(){  //2.2.3验证token
/*
	 var vue=$("#login_check").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#login_check").attr("class","menu-item");
	 }else{
		 $("#login_check").attr("class","menu-item active");
	 }*/

	var div = document.getElementById("content");
			
	div.innerHTML ='<b><p>验证token</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">用于应用验证当前单点登录用户的token有效性。</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/valtoken/{token}</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b>client_ip: {桌面客户端IP地址}（可通过HTTP请求中获取客户端IP）</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>调用接口凭证</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="valid_sso_token()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function refresh_sso_token_show(){ //2.2.4刷新单点登录token有效期
	/* var vue=$("#login_refresh").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#login_refresh").attr("class","menu-item");
	 }else{
		 $("#login_refresh").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>刷新单点登录token有效期</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">当token过期后或者过期前，调用此接口刷新token的有效期</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/refresh/{token}</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b>client_ip: {桌面客户端IP地址}（可通过HTTP请求中获取客户端IP）</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>调用接口凭证</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="refresh_sso_token()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function change_sso_password_show(){  //2.2.5修改密码
	 /*var vue=$("#login_password").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#login_password").attr("class","menu-item");
	 }else{
		 $("#login_password").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>修改密码</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">修改用户密码</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/password</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b></p>'
		+'<p style="text-indent:4em;">Content-Type: application/x-www-form-urlencoded</p>'
		+'<p style="text-indent:4em;">is_encrypt: {Boolean}（是否加密。若为true则旧密码、新密码、重复新密码三个参数均需通过AES/CBC/ZeroPadding方式进行加密；若为false或无此参数则以明文方式进行参数传输）</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>用户名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>oldpassword</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>旧密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>newpassword</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>新密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>repassword</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>重复新密码</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="sso_password()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入用户账号:</label></td>'
		+'<td><input  id="sso_username" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入旧密码:</label></td>'
		+'<td><input  id="sso_old_password" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入新密码:</label></td>'
		+'<td><input  id="sso_new_password" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请重复新密码:</label></td>'
		+'<td><input  id="sso_re_password" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
function change_sso_oapassword_show(){  //2.2.5修改密码
	/*var vue=$("#login_oapassword").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#login_oapassword").attr("class","menu-item");
	}else{
		$("#login_oapassword").attr("class","menu-item active");
	}*/

	var div = document.getElementById("content");

	div.innerHTML ='<b><p>修改密码</p ></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">修改用户密码（不校验秘法复杂度）</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/oapassword</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b></p>'
		+'<p style="text-indent:4em;">Content-Type: application/x-www-form-urlencoded</p>'
		+'<p style="text-indent:4em;">is_encrypt: {Boolean}（是否加密。若为true则旧密码、新密码、重复新密码三个参数均需通过AES/CBC/ZeroPadding方式进行加密；若为false或无此参数则以明文方式进行参数传输）</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>用户名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>oldpassword</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>旧密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>newpassword</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>新密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>repassword</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>重复新密码</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="sso_oapassword()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入用户账号:</label></td>'
		+'<td><input  id="sso_username" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入旧密码:</label></td>'
		+'<td><input  id="sso_old_password" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入新密码:</label></td>'
		+'<td><input  id="sso_new_password" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请重复新密码:</label></td>'
		+'<td><input  id="sso_re_password" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
}
 function logou_sso_show(){//2.2.6注销单点登录token
	/* var vue=$("#login_logout").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#login_logout").attr("class","menu-item");
	 }else{
		 $("#login_logout").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>注销单点登录token</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">注销当前当前单点登录的用户token</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>POST</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/sso/logout/{token}</p>'
		+'<p style="text-indent:2em;"><b>请求头部：</b>client_ip: {桌面客户端IP地址}（可通过HTTP请求中获取客户端IP）</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>调用接口凭证</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>result</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="logou_sso()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }

function get_deparement_show(){ //组织结构服务
	/*var div = document.getElementById("content");
	var vue=$("#depart").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#depart").attr("class","menu-item");
	}else{
		$("#depart").attr("class","menu-item active");
	}*/
	div.innerHTML ='<b><p>组织结构服务</p></b>'
		+'<p>接口说明</p>'
		+'<p style="text-indent:2em;">办公平台基础组织机构数据由平台统一维护，其他所有应用均无权限修改平台统一的组织机构数据。组织机构服务提供两种应用模式。</p>'
		+'<p style="text-indent:2em;">应用模式一:软件直接通过调用组织机构相关接口来获取组织机构数据。（建议新建的应用选用此模式）</p>'
		+'<p style="text-indent:2em;">应用模式二:针对已经完成建设，且具备独立组织机构的软件系统，可通过调用同步组织机构数据，将办公平台组织机构数据同步至自身系统。（建议对组织机构数据有特殊需求或已经建设完成具备独立组织机构管理功能的应用选用此模式）</p>'
		+'<p style="text-indent:2em;">两种模式不建议混用，如选用模式二同步组织机构数据后，应用中需调用组织机构数据时需从自身组织机构中读取相关数据，且需定时调用平台提供的增量同步接口使应用自身组织机构数据和平台相关数据保持一致。</p>'
		+'<p style="text-indent:2em;">注：组织机构接口的返回值不包含返回码及返回结果说明，若参数不合法则返回结果为空。</p>'
}
 function get_deparement_list_show(){ //3.2.1获取部门列表

	 var vue=$("#depart_get").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#depart_get").attr("class","menu-item");
	 }else{
		 $("#depart_get").attr("class","menu-item active");
	 }
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>获取部门列表</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">获取办公平台指定部门下的机构列表</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/department/{departmentid}/subdepartments?sublevel&access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>departmentid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>部门id</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sublevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>包含全部子级</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>invalid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>包含禁用数据</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>code</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门简拼</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>extAttribute</label></td>'
		+'<td><label>map</label></td>'
		+'<td><label>额外属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fatherId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>上级部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isDelete</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organName</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orguuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>p</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>路径，即从根节点到达该部门经过的所有部门的ID，root是根部门，-1表示无上级部门</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isTemporary</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否为临时 0为否，1为是</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startDate</label></td>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>部门开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>endDate</label></td>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>结束时间</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_deparement_list()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入部门id：</label></td>'
		+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="root" /></td></tr>'
		+'<tr><td><label>是否包含子级和无效数据：</label></td>'
		+'<td><input type="checkbox" id="sublevel"  /><label>包含子级(sublevel)</label><input type="checkbox" id="invalid"  /><label>包含无效数据(invalid)</label></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function get_more_department_list_show(){ //3.2.2获取多部门列表
	/* var vue=$("#depart_gets").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#depart_gets").attr("class","menu-item");
	 }else{
		 $("#depart_gets").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>获取多部门列表</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">获取办公平台多个部门下的机构列表</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/department/{departmentid1,departmentid2,...,deparimentidn}/subdepartments?access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>departmentid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>部门id</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sublevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>包含全部子级</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>invalid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>包含禁用数据</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>code</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门简拼</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>extAttribute</label></td>'
		+'<td><label>map</label></td>'
		+'<td><label>额外属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fatherId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>上级部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isDelete</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organName</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orguuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>p</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>路径，即从根节点到达该部门经过的所有部门的ID，root是根部门，-1表示无上级部门</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isTemporary</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否为临时 0为否，1为是</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startDate</label></td>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>部门开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>endDate</label></td>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>结束时间</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_more_department_list()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入部门id：</label></td>'
		+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="a29211c1-211f-4c2a-8747-1905055ec6d0,root" /><label>(id之间以,符号隔开)</label></td></tr>'
		+'<tr><td><label>是否包含子级和无效数据：</label></td>'
		+'<td><input type="checkbox" id="sublevel"  /><label>包含子级(sublevel)</label><input type="checkbox" id="invalid"  /><label>包含无效数据(invalid)</label></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function get_department_info_show(){ //3.2.3获取部门详情
	/* var vue=$("#depart_info").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#depart_info").attr("class","menu-item");
	 }else{
		 $("#depart_info").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>获取部门详情</p></b>'

		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">获取指定部门的详细信息</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/department/{departmentid}?access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>departmentid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>部门id</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>code</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门简拼</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>extAttribute</label></td>'
		+'<td><label>map</label></td>'
		+'<td><label>额外属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fatherId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>上级部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isDelete</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organName</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orguuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>p</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>路径，即从根节点到达该部门经过的所有部门的ID，root是根部门，-1表示无上级部门</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isTemporary</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否为临时 0为否，1为是</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startDate</label></td>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>部门开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>endDate</label></td>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>结束时间</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'

		+'<input type="submit"   value="测试一下" onclick="get_department_info()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入部门id：</label></td>'
		+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="root" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function get_user_list_show(){ //3.2.4获取人员列表
	/* var vue=$("#depart_userlist").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#depart_userlist").attr("class","menu-item");
	 }else{
		 $("#depart_userlist").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
			
	div.innerHTML ='<b><p>获取人员列表</p></b>'

		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">获取指定部门下的人员列表</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/department/{departmentid}/userinfos?access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>departmentid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>部门id</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sublevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>包含全部子级</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>invalid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>包含禁用数据</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>账号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ca</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务认证授权中心</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>duty</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>职务</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>endDate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期截止时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>extAttribute</label></td>'
		+'<td><label>map</label></td>'
		+'<td><label>额外属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>failedLoginCount</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>失败登录次数统计</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fullname</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>全名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ip</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>ip地址</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isDelete</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isManager</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>管理员类型（0：普通用户；1：安全审计员；2：安全管理员；4：系统管理员）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>mobile</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>手机</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>password</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>position</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>职级</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>secLevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密级（-1：非涉密；1：一般；2：重要；3：核心）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sex</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>性别（0：男；1：女）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡序列号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>spid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡型号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startdate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>座机</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tokenId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令的种子</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>userid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useremail</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>邮箱</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useruuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organIds</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属机构id集合</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>manageOrgan</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>人员的权限关系</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>relation</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属关系集合</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_user_list()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入部门id：</label></td>'
		+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="root" /></td></tr>'
		+'<tr><td><label>是否包含子级和无效数据：</label></td>'
		+'<td><input type="checkbox" id="sublevel"  /><label>包含子级(sublevel)</label><input type="checkbox" id="invalid"  /><label>包含无效数据(invalid)</label></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function get_userinfoByAccount_show(){  //3.2.5获取人员详情（根据account）
	/* var vue=$("#depart_userinfo").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#depart_userinfo").attr("class","menu-item");
	 }else{
		 $("#depart_userinfo").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>获取人员详情（根据account）</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">根据账号获取指定人员的详细信息</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/userinfoext/{account}?access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>人员账号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>账号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ca</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务认证授权中心</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>duty</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>职务</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>endDate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期截止时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>extAttribute</label></td>'
		+'<td><label>map</label></td>'
		+'<td><label>额外属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>failedLoginCount</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>失败登录次数统计</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fullname</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>全名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ip</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>ip地址</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isDelete</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isManager</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>管理员类型（0：普通用户；1：安全审计员；2：安全管理员；4：系统管理员）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>mobile</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>手机</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>password</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>position</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>职级</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>secLevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密级（-1：非涉密；1：一般；2：重要；3：核心）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sex</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>性别（0：男；1：女）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡序列号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>spid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡型号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startdate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>座机</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tokenId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令的种子</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>userid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useremail</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>邮箱</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useruuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organIds</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属机构id集合</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>manageOrgan</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>人员的权限关系</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>relation</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属关系集合</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_userinfoByAccount()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入人员在账号：</label></td>'
		+'<td><input  id="m_userid" type="text"   style="width:500px;"  value="sysadmin" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
function get_userinfo_show(){  //3.2.6获取人员详情
	/*var vue=$("#depart_departByAccount").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#depart_departByAccount").attr("class","menu-item");
	}else{
		$("#depart_departByAccount").attr("class","menu-item active");
	}*/
    var div = document.getElementById("content");

    div.innerHTML ='<b><p>获取人员详情</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">根据用户id获取指定人员的详细信息</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/userinfo/{userid}?access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>userid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>人员id</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>account</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>账号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ca</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务认证授权中心</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>dn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>目录服务辨别名称</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>duty</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>职务</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>endDate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期截止时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>extAttribute</label></td>'
		+'<td><label>map</label></td>'
		+'<td><label>额外属性</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>failedLoginCount</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>失败登录次数统计</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>fullname</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>全名</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>ip</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>ip地址</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isDelete</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>是否禁用（0：启用；1：禁用）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>isManager</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>管理员类型（0：普通用户；1：安全审计员；2：安全管理员；4：系统管理员）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>mobile</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>手机</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>orderId</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>顺序号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>部门ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>password</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>position</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>职级</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>secLevel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>密级（-1：非涉密；1：一般；2：重要；3：核心）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sex</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>性别（0：男；1：女）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>sn</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡序列号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>spid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令卡型号</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>startdate</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户有效期开始时间</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tel</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>座机</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>tokenId</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>动态口令的种子</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>userid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useremail</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>邮箱</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>useruuid</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>用户ID</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>organIds</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属机构id集合</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>manageOrgan</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>人员的权限关系</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>relation</label></td>'
		+'<td><label>list</label></td>'
		+'<td><label>用户所属关系集合</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="get_userinfoById()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入人员id：</label></td>'
		+'<td><input  id="m_userid" type="text"   style="width:500px;"  value="1" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

}
 function sync_show(){ //3.2.7增量同步接口
	/* var vue=$("#depart_sync").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#depart_sync").attr("class","menu-item");
	 }else{
		 $("#depart_sync").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>获取人员详情</p></b>'
		+'<p>1、接口说明</p>'
		+'<p style="text-indent:2em;">获取某一时间点后的所有变更数据（含组织机构及用户数据）</p>'
		+'<p style="text-indent:2em;"><b>请求方式：</b>GET</p>'
		+'<p style="text-indent:2em;"><b>请求地址：</b>http://ip:port/api/org/syncdepartments?starttime={starttime}&department={department}&access_token={access_token}</p>'
		+'<p style="text-indent:2em;"><b>参数说明：</b></p><br>'
		+'<p style="text-indent:2em;">请求参数说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>是否必输</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>access_token</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>微服务token</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>starttime</label></td>'
		+'<td><label>long</label></td>'
		+'<td><label>是</label></td>'
		+'<td><label>搜索范围起始时间，取值为时间戳（10位，如1509969860）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>department</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>否</label></td>'
		+'<td><label>按部门下的条件搜索</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p style="text-indent:2em;">返回结果说明</p>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr>'
		+'<th><label>参数</label></th>'
		+'<th><label>类型</label></th>'
		+'<th><label>说明</label></th>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltcode</label></td>'
		+'<td><label>int</label></td>'
		+'<td><label>返回码</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>rsltmsg</label></td>'
		+'<td><label>string</label></td>'
		+'<td><label>返回结果说明</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>timestamp</label></td>'
		+'<td><label>long</label></td>'
		+'<td><label>时间戳（服务器返回结果时的时间，各个应用可自行保存，待下次获取增量数据时作为请求的起始时间）</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>org</label></td>'
		+'<td><label>Array</label></td>'
		+'<td><label>组织机构变更数据</label></td>'
		+'</tr>'
		+'<tr>'
		+'<td><label>user</label></td>'
		+'<td><label>Array</label></td>'
		+'<td><label>用户变更数据</label></td>'
		+'</tr>'
		+'</table><br>'

		+'<p>2、接口测试</p>'
		+'<input type="submit"   value="测试一下" onclick="sync()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入starttime：</label></td>'
		+'<td><input  id="starttime" type="text"   style="width:500px;"  value="1512727052" /></td></tr>'
		+'<tr><td><label>请输入部门id：</label></td>'
		+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="root" /><label>(部门id可选填)</label></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }


function get_msg_show() { //消息服务

	/*var div = document.getElementById("content");
	var vue=$("#message").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#message").attr("class","menu-item");
	}else{
		$("#message").attr("class","menu-item active");
	}*/
	div.innerHTML = '<b><p>消息服务</p></b>'
		+'<p>接口说明</p>'
		+'<p style="text-indent:2em;">平台统一提供消息队列服务，受平台托管应用可调用消息服务接口，将消息推送至办公桌面上的消息盒子中。</p>'

}
 function msg_one_user_show() { //4.3.1发送消息至个人
	/* var vue=$("#message_toOne").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#message_toOne").attr("class","menu-item");
	 }else{
		 $("#message_toOne").attr("class","menu-item active");
	 }*/
	 var div = document.getElementById("content");
	 var content_demo = '{"appid":"legacycpk.csse.cms","type":"application","title":"title","content":"中文","display":{"system":true,"notification":true,"msgbox":true}}';
	 div.innerHTML = '<b><p>发送消息至个人</p></b>'
		 + '<input type="submit"   value="测试一下" onclick="msg_one_user()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		 + '<table class="table table-striped table-bordered table-hover">'
		 + '<tr><td><label>请输入人员id：</label></td>'
		 + '<td><input  id="m_userid" type="text"   style="width:500px;"  value="342189d5-d5ea-458b-9928-fb91588dfe81" /></td></tr>'
		 + '<tr><td><label>请输入消息内容：</label></td>'
		 + '<td><input  id="m_content" type="text"   style="width:500px;"  value=' + content_demo + ' /></td></tr>'
		 + '<tr><td><label>返回结果</label></td>'
		 + '<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

 }
 function msg_more_user_show(){  //4.3.2.发送消息至多人
	/* var vue=$("#message_toMany").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#message_toMany").attr("class","menu-item");
	 }else{
		 $("#message_toMany").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
	var content_demo='{"appid":"legacycpk.csse.cms","type":"application","title":"title","content":"中文","display":{"system":true,"notification":true,"msgbox":true}}';
	div.innerHTML ='<b><p>发送消息至多人</p></b>'
		+'<input type="submit"   value="测试一下" onclick="msg_more_user()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF" /><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入人员id：</label></td>'
		+'<td><input  id="m_userid" type="text"   style="width:500px;"  value="1,3c88f676-8699-4c40-a436-3d220453e5a4" /><label>(id之间以,符号隔开)</label></td></tr>'
		+'<tr><td><label>请输入消息内容：</label></td>'
		+'<td><input  id="m_content" type="text"   style="width:500px;"  value='+content_demo+' /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

 }
 function msg_one_department_show(){ //4.3.3.发送消息至部门
	/* var vue=$("#message_toDepart").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#message_toDepart").attr("class","menu-item");
	 }else{
		 $("#message_toDepart").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
	var content_demo='{"appid":"legacycpk.csse.cms","type":"application","title":"title","content":"中文","display":{"system":true,"notification":true,"msgbox":true}}';
	div.innerHTML ='<b><p>发送消息至部门</p></b>'
		+'<input type="submit"   value="测试一下" onclick="msg_one_department()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入部门id：</label></td>'
		+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="root" /></td></tr>'
		+'<tr><td><label>请输入消息内容：</label></td>'
		+'<td><input  id="m_content" type="text"   style="width:500px;"  value='+content_demo+' /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function msg_more_department_show(){ //4.3.4.发送消息至多部门
	/* var vue=$("#message_toDeparts").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#message_toDeparts").attr("class","menu-item");
	 }else{
		 $("#message_toDeparts").attr("class","menu-item active");
	 }*/
		var div = document.getElementById("content");
		var content_demo='{"appid":"legacycpk.csse.cms","type":"application","title":"title","content":"中文","display":{"system":true,"notification":true,"msgbox":true}}';
		div.innerHTML ='<b><p>发送消息至多部门</p></b>'
			+'<input type="submit"   value="测试一下" onclick="msg_more_department()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
			+'<table class="table table-striped table-bordered table-hover">'
			+'<tr><td><label>请输入多个部门id：</label></td>'
			+'<td><input  id="m_departmentid" type="text"   style="width:500px;"  value="root,a29211c1-211f-4c2a-8747-1905055ec6d0" /><label>(id之间以,符号隔开)</label></td></tr>'
			+'<tr><td><label>请输入消息内容：</label></td>'
			+'<td><input  id="m_content" type="text"   style="width:500px;"  value='+content_demo+' /></td></tr>'
			+'<tr><td><label>返回结果</label></td>'
			+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

 }

function get_logs_show() { //日志服务

	/*var div = document.getElementById("content");
	var vue=$("#log").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#log").attr("class","menu-item");
	}else{
		$("#log").attr("class","menu-item active");
	}*/
	div.innerHTML = '<b><p>日志服务</p></b>'
		+'<p>接口说明</p>'
		+'<p style="text-indent:2em;">办公平台针对众创应用提供统一日志管理功能，所有应用需按照规定的日志格式，将日志推送至平台日志服务器。</p>'

}
 function add_log_show(){ //5.1.增加日志操作
	/* var vue=$("#log_add").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#log_add").attr("class","menu-item");
	 }else{
		 $("#log_add").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
	var test_log='{'
		+	'"useraccount":"sysadmin",'
		+	'"appname": "ceshi",'
		+	'"appid": "client",'
		+	'"appip": "10.55.10.65",'
		+	'"clientip":"10.55.30.1",'
		+	'"currenttime":"1470154736000",'
		+	'"systemid":"单点登录系统123123",'
		+	'"functionname":"单点登录",'
		+	'"success":"1",'
		+	'"objectid":"admin",'
		+	'"classname":"55",'
		+	'"methodname":"55",'
		+	'"codeLine":16,'
		+	'"description":"do something",'
		+	'"tag":"5",'
		+	'"callbackurl":"http://192.168.17.159:8080"'
		+	'}'	;

	div.innerHTML ='<b><p>增加日志操作</p></b>'

		+'<input type="submit"   value="测试一下" onclick="add_log()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入日志内容：</label></td>'
		+'<td><textarea id="logdetail" rows="10" cols="80"   />'+test_log+'</textarea></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

 }
 function get_log1_show(){ //8.2.2　根据用户名审计应用日志
	/* var vue=$("#log_audByName").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#log_audByName").attr("class","menu-item");
	 }else{
		 $("#log_audByName").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>根据用户名审计应用日志</p></b>'
		+'<input type="submit"   value="测试一下" onclick="get_log1()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入用户账号：</label></td>'
		+'<td><input  id="m_userid" type="text"   style="width:500px;"  value="sysadmin" /></td></tr>'
		+'<tr><td><label>请输入开始时间：</label></td>'
		+'<td><input  id="log_starttime" type="text"   style="width:500px;"  value="1470154736000" /></td></tr>'
		+'<tr><td><label>请输入结束时间：</label></td>'
		+'<td><input  id="log_endtime" type="text"   style="width:500px;"  value="1570154736000" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function get_log2_show(){ //根据应用标识审计应用日志
	/* var vue=$("#log_audByAppid").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#log_audByAppid").attr("class","menu-item");
	 }else{
		 $("#log_audByAppid").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");

	div.innerHTML ='<b><p>根据应用标识审计应用日志</p></b>'
		+'<input type="submit"   value="测试一下" onclick="get_log2()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入appid：</label></td>'
		+'<td><input  id="log_appid" type="text"   style="width:500px;"  value="legacycpk.csse.cms" /></td></tr>'
		+'<tr><td><label>请输入开始时间：</label></td>'
		+'<td><input  id="log_starttime" type="text"   style="width:500px;"  value="1470154736000" /></td></tr>'
		+'<tr><td><label>请输入结束时间：</label></td>'
		+'<td><input  id="log_endtime" type="text"   style="width:500px;"  value="1570154736000" /></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function get_log3_show(){ //根据应用标识审计平台日志
	/* var vue=$("#log_audByAppidPt").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#log_audByAppidPt").attr("class","menu-item");
	 }else{
		 $("#log_audByAppidPt").attr("class","menu-item active");
	 }*/
	 var div = document.getElementById("content");

	 div.innerHTML ='<b><p>根据应用标识审计平台日志</p></b>'
		 +'<input type="submit"   value="测试一下" onclick="get_log3()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		 +'<table class="table table-striped table-bordered table-hover">'
		 +'<tr><td><label>请输入appid：</label></td>'
		 +'<td><input  id="log_appid" type="text"   style="width:500px;"  value="legacycpk.csse.cms" /></td></tr>'
		 +'<tr><td><label>请输入开始时间：</label></td>'
		 +'<td><input  id="log_starttime" type="text"   style="width:500px;"  value="1470154736000" /></td></tr>'
		 +'<tr><td><label>请输入结束时间：</label></td>'
		 +'<td><input  id="log_endtime" type="text"   style="width:500px;"  value="1570154736000" /></td></tr>'
		 +'<tr><td><label>返回结果</label></td>'
		 +'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }

/* function search_show(apiname){ //6.1.获取应用基本信息
		var div = document.getElementById("content");
			
		div.innerHTML ='<p>'+apiname+'</p>'
		+'请输入应用架构：<input  id="search_arch" type="text"   style="width:500px;"  value="" /></br></br>'
		+'请输入apps：<input  id="search_apps" type="text"   style="width:500px;"  value="" />(多个id之间用;分隔)</br></br>'
		+'请输入时间戳：<input  id="search_time" type="text"   style="width:500px;"  value="" /></br></br>'
		+'请输入单点登录用户的token：<input  id="search_token" type="text"   style="width:500px;"  value="" /></br>'
		+'</br></br>'
		+'<input type="submit"   value="测试一下" onclick="search()" />'
		+'<p>返回结果</p>'
		+'<textarea id="textareaid" rows="30" cols="120"   />'
		+'</textarea>'
		;
 }*/
function get_subscribe_show() { //订阅服务
	/*var div = document.getElementById("content");
	var vue=$("#subscribe").attr("class");
	if(vue.indexOf("active")!=-1){
		$("#subscribe").attr("class","menu-item");
	}else{
		$("#subscribe").attr("class","menu-item active");
	}*/
	div.innerHTML = '<b><p>订阅服务</p></b>'
		+'<p>接口说明</p>'
		+'<p style="text-indent:2em;">平台提供订阅服务接口，能够为众创应用提供添加订阅、获取订阅列表、删除订阅及查询用户是否订阅接口。</p>'

}

 function subscribe_add_show(){//6.1添加订阅信息
	/* var vue=$("#subscribe_add").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#subscribe_add").attr("class","menu-item");
	 }else{
		 $("#subscribe_add").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
	var  content ='{'
				+		'"useruuid":"342189d5-d5ea-458b-9928-fb91588dfe81",'
				+		'"id":"22222",'
				+		'"title":"文章标题test",'
				+		'"createDate":"2017-11-21 07:30:30"'
				+	'}';
	div.innerHTML ='<b><p>1添加订阅信息</p></b>'
		+'<input type="submit"   value="测试一下" onclick="subscribe_add()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入订阅信息：</label></td>'
		+'<td><textarea id="subscribe_detail" rows="10" cols="80"   />'+content+'</textarea></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function subscribe_delete_show(){ //6.2.删除订阅信息
	/* var vue=$("#subscribe_del").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#subscribe_del").attr("class","menu-item");
	 }else{
		 $("#subscribe_del").attr("class","menu-item active");
	 }*/
	var div = document.getElementById("content");
	var  content2 ='{'
				+		'"useruuid":"342189d5-d5ea-458b-9928-fb91588dfe81",'
				+		'"id":"22222"'
				+	'}';
	div.innerHTML ='<b><p>删除订阅信息</p></b>'
		+'<input type="submit"   value="测试一下" onclick="subscribe_delete()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入删除信息：</label></td>'
		+'<td><textarea id="subscribe_delete_detail" rows="10" cols="80"   />'+content2+'</textarea></td></tr>'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
 }
 function subscribe_user_show(){ //6.3.查询用户的订阅信息
	/* var vue=$("#subscribe_search").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#subscribe_search").attr("class","menu-item");
	 }else{
		 $("#subscribe_search").attr("class","menu-item active");
	 }*/
		var div = document.getElementById("content");
			
		div.innerHTML ='<b><p>查询用户的订阅信息</p></b>'
			+'<input type="submit"   value="测试一下" onclick="subscribe_user()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
			+'<table class="table table-striped table-bordered table-hover">'
			+'<tr><td><label>请输入人员id：</label></td>'
			+'<td><input  id="search_userid" type="text"   style="width:500px;"  value="342189d5-d5ea-458b-9928-fb91588dfe81" /></td></tr>'
			+'<tr><td><label>请输入每页显示条数：</label></td>'
			+'<td><input  id="search_limit" type="text"   style="width:500px;"  value="10" /></td></tr>'
			+'<tr><td><label>请输入当前页数：</label></td>'
			+'<td><input  id="search_page" type="text"   style="width:500px;"  value="1" /></td></tr>'
			+'<tr><td><label>返回结果</label></td>'
			+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

 }
 function subscribe_article_user_show(){ //6.4.查询用户是否订阅了该文章
	/* var vue=$("#subscribe_get").attr("class");
	 if(vue.indexOf("active")!=-1){
		 $("#subscribe_get").attr("class","menu-item");
	 }else{
		 $("#subscribe_get").attr("class","menu-item active");
	 }*/
		var div = document.getElementById("content");
			
		div.innerHTML ='<b><p>查询用户是否订阅了该文章</p></b>'
			+'<input type="submit"   value="测试一下" onclick="subscribe_article_user()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
			+'<table class="table table-striped table-bordered table-hover">'
			+'<tr><td><label>请输入人员id：</label></td>'
			+'<td><input  id="search_userid" type="text"   style="width:500px;"  value="342189d5-d5ea-458b-9928-fb91588dfe81" /></td></tr>'
			+'<tr><td><label>请输入文章id：</label></td>'
			+'<td><input  id="search_articleid" type="text"   style="width:500px;"  value="22222" /></td></tr>'
			+'<tr><td><label>返回结果</label></td>'
			+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

 }

function desktop_show() { //办公桌面信息屏接口
	var div = document.getElementById("content");
	div.innerHTML = '<b><p>办公桌面信息屏接口</p></b>'
		+'<p>接口说明</p>'
		+'<p style="text-indent:2em;">众创开发商需根据平台提出的办公桌面信息屏接口接入标准，实现应用与办公桌面信息屏的交互。</p>'
		+'<p style="text-indent:2em;">因安全方面考虑，本章节办公桌面信息屏接口只在办公桌面客户端特定分支版本可用，主分支版本和其他未开放权限的分支版本无法使用，如需使用请咨询办公桌面研发人员。</p>'

}
 //负一屏接口
function openAppShow(){ //打开应用
    var div = document.getElementById("content");

    div.innerHTML ='<b><p>打开应用</p></b>'
		+'<input type="submit"   value="测试一下" onclick="openApp()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入应用id：</label></td>'
		+'<td><input  id="softwareid" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入回调地址：</label></td>'
		+'<td><input  id="redirect" type="text"   style="width:500px;"  value="" /></td></tr></table>';
}
function setWinSizeShow(){ //设置新窗口打开大小
    var div = document.getElementById("content");

    div.innerHTML ='<b><p>设置新窗口打开大小</p></b>'

		+'<input type="submit"   value="测试一下" onclick="setWinSize()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>请输入新窗口宽：</label></td>'
		+'<td><input  id="wide" type="text"   style="width:500px;"  value="" /></td></tr>'
		+'<tr><td><label>请输入新窗口高：</label></td>'
		+'<td><input  id="hight" type="text"   style="width:500px;"  value="" /></td></tr></table>';

}
function getAppListShow(){ //获取应用列表
    var div = document.getElementById("content");

    div.innerHTML ='<b><p>获取应用列表</p></b>'
		+'<input type="submit"   value="测试一下" onclick="getAppList()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';

}
function getAppListInfoShow(){ //获取应用详情
    var div = document.getElementById("content");

    div.innerHTML ='<b><p>获取应用详情</p></b>'
		+'<input type="submit"   value="测试一下" onclick="getAppListInfo()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+'<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>返回结果</label></td>'
		+'<td><textarea id="textareaid" rows="20" cols="120"/></textarea></td></tr></table>';
}
function reloadShow() { //重新加载
	var div = document.getElementById("content");

	div.innerHTML = '<b><p>重新加载</p></b>'
		+ '<input type="submit"   value="测试一下" onclick="reloadShow()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+ '<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>是否忽略缓存：</label></td>'
		+'<td><input type="checkbox" id="isTrue"  /><label>是</label><input type="checkbox" id="isFalse"  /><label>否</label></td></tr></table>';
}
function clearCacheShow(){ //清空缓存
    var div = document.getElementById("content");

    div.innerHTML ='<b><p>清空缓存</p></b>'
		+ '<input type="submit"   value="测试一下" onclick="clearCache()" style="background-color: #6fb3e0;border-color:#6fb3e0;color:#FFF"/><br><br>'
		+ '<table class="table table-striped table-bordered table-hover">'
		+'<tr><td><label>是否忽略清理Cookie：</label></td>'
		+'<td><input type="checkbox" id="isTrue"  /><label>是</label><input type="checkbox" id="isFalse"  /><label>否</label></td></tr></table>';
}
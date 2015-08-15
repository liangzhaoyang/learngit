<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>管理员登陆界面</title>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" />
	
	<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript">
		function enter(){
			if(event.keyCode ==13)
				check_submit();
		}	 
	    function check_submit(){
	    	var name = $.trim($("#txtName").val());
            var password = $.trim($("#txtPsw").val());
            if(name.length<=0){
            	$("#err").html("账号不能为空");
            	if(password.length<=0){
            		$("#err").html("账号和密码不能为空");
            	}
            }else if(password.length<=0){
            	$("#err").html("密码不能为空");
            }else{
            	
            	 $.getJSON("${pageContext.request.contextPath }/LoginServlet", {"time":new Date(), "admin_name": name, "password":password }, function (data) {
            		 if(data==1){
            			 window.document.location="${pageContext.request.contextPath }/index.jsp";
            			 
            		 }else{
            			 //alert("haha");
            			 $("#err").html("用户名或密码错误");
            		 }
            	 });
            	 
            }
        }
	</script>
</head>

<body onKeyDown = "enter()">
	<div id="container">
		<div id="login">
			<div id="form">
				<form action="" method="post">
					<div id="input">
						<div>账号：<input type="text" name="username" id="txtName"></div>
						<div>密码：<input type="password" name="password" id="txtPsw"></div>
					</div>
					<div id="err"></div>
					<div id="btn">
						<input type="button" onclick="check_submit()" value="登录">&nbsp;
						<input type="reset" value="重置">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="net.domain.User" %>
<%@page import="net.factory.DaoFactory" %>
<%
	User user = new User();
	/* if (session.getAttribute("user")==null){
		out.println("<script>alert('未登录系统！');top.location.href='login.jsp';</script>");
	}else{ */
		user = (User)session.getAttribute("user");
	/* } */
	
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>添加管理员</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/add.css"/>
    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-ui.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function () {

        $("#txtPassword").focusin(function () {
            $("#imgToPwd").attr("src", "../images/img/img2.jpg").show();
            $("#pwdMsg").css('color', 'black').html("6-20位大小写英文、数字或符号组成").show();
        }).focusout(function () {
            var pwd = $.trim($(this).val());
            $("#pwdMsg").html("").hide();
            if ((pwd.length > 0) && (pwd.length < 6)) {
                $("#pwdMsg").css('color', 'red').html("密码长度只能在6-20位字符之间").show();
            }
            else if (pwd.length > 20) {
                $("#pwdMsg").css('color', 'red').html("密码长度只能在6-20位字符之间").show();
            }
            else {
                if (pwd.length > 0) {
                    //验证密码。密码至少要数字、字母、符号中两种的组合,且长度不能少于6
                    var isPwd = /(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{6,16}/;
                    if (!isPwd.test(pwd)) {
                        $("#pwdMsg").css('color', 'red').html("6-20位字符,可由大小写英文、数字或符号组成").show();
                    }
                    else {
                        $("#imgToPwd").attr("src", "../images/img/sucess.png").show();
                    }
                }
            }
        });
        
        $("#txtPassword1").focusin(function () {
            $("#imgToPwd1").attr("src", "../images/img/img2.jpg").show();
            $("#pwd1Msg").css('color', 'black').html("请再次输入密码").show();
        }).focusout(function () {
            var pwd = $.trim($("#txtPassword").val());
            var pwd1 = $.trim($(this).val());
            $("#pwd1Msg").html("").hide();
            if (pwd1.length > 0) {
                if (pwd != pwd1) {
                    $("#pwd1Msg").css('color', 'red').html("两次输入密码不一致").show();
                }
                else {
                    $("#imgToPwd1").attr("src", "../images/img/sucess.png").show();
                }
            }
        });

        $("#txtPhone").focusin(function () {
            $("#imgToPhone").attr("src", "../images/img/img3.jpg").show();
            $("#phoneMsg").css('color', 'black').html("请输入手机号码").show();
        }).focusout(function () {
            var phone = $.trim($("#txtPhone").val());
            $("#phoneMsg").html("").hide();
            if (phone.length > 0) {
                var isPhone = /^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/;
                if (!isPhone.test(phone)) {
                    $("#phoneMsg").css('color', 'red').html("手机号码格式有误，请输入正确的手机号").show();
                }
                else {
                    $("#imgToPhone").attr("src", "../images/img/sucess.png").show();
                }
            }
        });
        
     });

    $(function () {
    	var userid=<%=user.getUserid()%>;
        $("#btnCHA").click(function () {
            $("#pwdMsg").html("").hide();
            $("#pwd1Msg").html("").hide();
            if (CheckMsg()) {
                var password = $.trim($("#txtPassword").val());
                //alert(password);
                var url ="${pageContext.request.contextPath }/UserManageServlet?action=ch_psw&id="+userid+"&password="+password;
                var args={"time":new Date()};
                $.getJSON(url,args,function(data){
                	if(data==1){
                		alert("修改密码成功！");
                		window.location.href="${pageContext.request.contextPath }/jsp/tab.jsp";
                	}else{
                		alert("修改密码失败!");
                	}
                });
               
            }
        });
        
        $("#reset").click(function () {
            $("#pwdMsg").html("").hide();
            $("#pwd1Msg").html("").hide();
            $("#imgToPwd").attr("src", "../images/img/img2.jpg").show();
            $("#imgToPwd1").attr("src", "../images/img/img2.jpg").show();
        });
    });
    
    
    function CheckMsg() {
        
        var pwd = $.trim($("#txtPassword").val());
        if (pwd.length == 0) {
            $("#pwdMsg").css('color', 'red').html("请输入密码").show();
            return false;
        }
        else if (pwd.length > 20 || pwd.length < 6) {
            $("#pwdMsg").css('color', 'red').html("密码长度只能在6-20位字符之间").show();
            return false;
        }
        else {
            //验证密码。密码至少要数字、字母、符号中两种的组合,且长度不能少于6
            var isPwd = /(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{6,16}/;
            if (!isPwd.test(pwd)) {
                $("#pwdMsg").css('color', 'red').html("6-20位字符,可由大小写英文、数字或符号组成").show();
                return false;
            }
        }
        var pwd1 = $.trim($("#txtPassword1").val());
        if (pwd1.length == 0) {
            $("#pwd1Msg").css('color', 'red').html("请确认密码").show();
            return false;
        }
        if (pwd != pwd1) {
            $("#pwd1Msg").css('color', 'red').html("两次密码输入不一致").show();
            return false;
        }
        var phone = $.trim($("#txtPhone").val());
        if (phone.length > 0) {
            var isPhone = /^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/;
            if (!isPhone.test(phone)) {
                $("#phoneMsg").css('color', 'red').html("手机号码格式有误，请输入正确的手机号").show();
                return false;
            }
        }
        return true;
    }
    </script>
</head>
<body>

    <input type="hidden" id="loginIdState" value="0" /><input type="hidden" id="emailState" value="0" />
    <div class="wrap">
        <div class="reg">
            <div class="reg_title">
                <h1>修&nbsp;改&nbsp;密&nbsp;码</h1>
            </div>
            <div class="reg_box">
            <form action="" name="form">
                <ul>
                    <li>
                        <p class="p1"><b>*</b>重新设置密码：</p>
                        <p><input name="" type="password" id="txtPassword" class="input" /><span class="img1"><img src="../images/img/img2.jpg" id="imgToPwd" /></span></p>
                        <p class="p3" id="pwdMsg" style="display: none;"></p>
                    </li>
                    <li>
                        <p class="p1"><b>*</b>再次确认密码：</p>
                        <p><input name="" type="password" id="txtPassword1" class="input" /><span class="img1"><img src="../images/img/img2.jpg" id="imgToPwd1" /></span></p>
                        <p class="p3" id="pwd1Msg" style="display: none;"></p>
                    </li>
                    <li>
                        <input name="addUser" type="button" id="btnCHA" value="提交" style="cursor: pointer;" class="submit_u" />
                        <input name="reset" type="reset" value="重 置" id="reset" style="cursor: pointer;" class="update" />
                   </li>
                </ul>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
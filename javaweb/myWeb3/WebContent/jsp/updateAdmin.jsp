<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="net.domain.User" %>
<%@page import="net.factory.DaoFactory" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
		User admin = (User)session.getAttribute("user");
		String permission=admin.getPermission();
		if(permission.equals("普通")){
			out.print("<script>alert('您不是高级管理员，没有编辑权限！');location.href='tab.jsp';</script>");
		}

  /*取得id号，根据该id号调用业务层取得管理员的详细信息*/
  String id = request.getParameter("id");
  User user = DaoFactory.getUserDaoInstance().GetUserInfoById(Integer.parseInt(id));

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>添加管理员</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/add.css"/>
    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-ui.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function () {
    	var userid=<%=user.getUserid()%>;
        $("#txtLoginId").focusin(function () {
            $("#imgToLoginId").attr("src", "../images/img/img1.jpg").show();
            $("#loginIdMsg").css('color', 'black').html("4-20位中英文、数字及特殊符号组合").show();
        }).focusout(function () {
            $("#loginIdMsg").html("").hide();
            var newLoginId = $.trim($(this).val());
            if ((newLoginId.length > 0) && (newLoginId.length < 4)) {
                $("#loginIdMsg").css('color', 'red').html("用户名长度只能在4-20位字符之间").show();
            }
            else if (newLoginId.length > 0) {
                $.getJSON("${pageContext.request.contextPath }/UserManageServlet?action=check_u", {"time":new Date(), "admin_name": newLoginId,"userid":userid }, function (data) {
                    if (data == "yes") {
                        $("#loginIdState").val("0");
                        //用户已经存在,不可以注册
                        flag=false;
                        $("#loginIdMsg").css('color', 'red').html("该用户名已被使用，请重新输入").show();
                    }
                    else {
                        $("#loginIdState").val("1");
                        //用户不存在,可以注册
                        $("#imgToLoginId").attr("src", "../images/img/sucess.png").show();
                    }
                });
            }
        });

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

        $("#txtEmail").focusin(function () {
            $("#imgToEmail").attr("src", "../images/img/img4.jpg").show();
            $("#emailMsg").css('color', 'black').html("请输入密保邮箱").show();
        }).focusout(function () {
            var email = $.trim($("#txtEmail").val());
            $("#emailMsg").html("").hide();
            if (email.length > 0) {
                var isEmail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
                if (!isEmail.test(email)) {
                    $("#emailMsg").css('color', 'red').html("邮箱格式错误").show();
                }
                else {
                	 $.getJSON("${pageContext.request.contextPath }/UserManageServlet?action=check_u2", {"time":new Date(), "email": email,"userid":userid }, function (data) {
                         if (data == "yes") {
                             $("#emailState").val("0");
                             //用户已经存在,不可以注册
                             $("#emailMsg").css('color', 'red').html("该邮箱已被使用，请重新输入").show();
                         }
                         else {
                             $("#emailState").val("1");
                             //用户不存在,可以注册
                             $("#imgToEmail").attr("src", "../images/img/sucess.png").show();
                         }
                     }); 
                }
            }
        });
    });

    $(function () {
    	//alert($('input[name="role"]:checked').val());
    	var userid=<%=user.getUserid()%>;
        $("#btnupdate").click(function () {
            $("#loginIdMsg").html("").hide();
            $("#pwdMsg").html("").hide();
            $("#pwd1Msg").html("").hide();
            $("#phoneMsg").html("").hide();
            $("#emailMsg").html("").hide();
            //alert("hehe");
            if (CheckMsg()) {
                var loginId = $.trim($("#txtLoginId").val());
                var password = $.trim($("#txtPassword").val());
                var phone = $.trim($("#txtPhone").val());
                var email = $.trim($("#txtEmail").val());
                var permission=$('input[name="role"]:checked').val();
                //window.location="${pageContext.request.contextPath }/UserManageServlet?action=add&name="+loginId+"&password="+password+"&phone="+phone+"&email="+email+"&permission="+permission;
                var urlStr ="${pageContext.request.contextPath }/UserManageServlet?action=update&id="+userid+"&name="+loginId+"&password="+password+"&phone="+phone+"&email="+email;
                var f= document.createElement('form');
                f.action = urlStr;
                f.method = 'post';
                document.body.appendChild(f);
                var temp=document.createElement('input');
                temp.type= 'hidden';
                temp.value=permission; 
                temp.name='permission';
                f.appendChild(temp);
                f.submit();
            }
        });
    });
    function CheckMsg() {

        var newLoginId = $.trim($("#txtLoginId").val());
        //if(newLoginId!=username){
        	if (newLoginId.length < 4) {
                $("#loginIdMsg").css('color', 'red').html("用户名长度只能在4-20位字符之间").show();
                return false;
            }
            if ($("#loginIdState").val() == "0") {
                //用户已经存在,不可以注册
                $("#loginIdMsg").css('color', 'red').html("该用户名已被使用，请重新输入").show();
                return false;
            } 
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
            //}
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
        
        var email = $.trim($("#txtEmail").val());
        //if(email!=user_email){
        	 if (email.length == 0) {
                 $("#emailMsg").css('color', 'red').html("请输入邮箱").show();
                 return false;
             }
             else {
                 var isEmail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
                 if (!isEmail.test(email)) {
                     $("#emailMsg").css('color', 'red').html("邮箱格式错误").show();
                     return false;
                 }else if ($("#emailState").val() == "0") {
                     $("#emailMsg").css('color', 'red').html("该邮箱已被使用，请重新输入").show();
                     return false;
                 }
             }
        //}
        return true;
    }
    </script>
</head>
<body>
    <input type="hidden" id="loginIdState" value="1" /><input type="hidden" id="emailState" value="1" />
    <div class="wrap">
        <div class="reg">
            <div class="reg_title">
                <h1>更&nbsp;新&nbsp;管&nbsp;理&nbsp;员&nbsp;信&nbsp;息</h1>
            </div>
            <div class="reg_box">
                <ul>
                    <li>
                        <p class="p1"><b>*</b>账号：</p>
                        <p><input name="" type="text" id="txtLoginId" class="input" value="<%=user.getUsername() %>" /><span class="img1"><img src="../images/img/img1.jpg" id="imgToLoginId" /></span></p>
                        <p class="p3" id="loginIdMsg" style="display: none;"></p>
                    </li>
                    <li>
                        <p class="p1"><b>*</b>设置密码：</p>
                        <p><input name="" type="password" id="txtPassword" class="input" value="<%=user.getPassword() %>" /><span class="img1"><img src="../images/img/img2.jpg" id="imgToPwd" /></span></p>
                        <p class="p3" id="pwdMsg" style="display: none;"></p>
                    </li>
                    <li>
                        <p class="p1"><b>*</b>确认密码：</p>
                        <p><input name="" type="password" id="txtPassword1" class="input" value="<%=user.getPassword() %>" /><span class="img1"><img src="../images/img/img2.jpg" id="imgToPwd1" /></span></p>
                        <p class="p3" id="pwd1Msg" style="display: none;"></p>
                    </li>
                    <li>
                        <p class="p1">手机：</p>
                        <p><input name="" type="text" id="txtPhone" class="input" value="<%=user.getIphone() %>" /><span class="img1"><img src="../images/img/img3.jpg" id="imgToPhone" /></span></p>
                        <p class="p3" id="phoneMsg" style="display: none;"></p>
                    </li>
                    <li>
                        <p class="p1"><b>*</b>邮箱：</p>
                        <p><input name="" type="text" id="txtEmail" class="input" value="<%=user.getEmail() %>" /><span class="img1"><img src="../images/img/img4.jpg" id="imgToEmail" /></span></p>
                        <p class="p3" id="emailMsg" style="display: none;"></p>
                    </li>
                    <li>
                        <p class="p1"><b>*</b>权限：</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;<input name="role" type="radio" class="txtPermission" value="高级" <c:if test='<%=user.getPermission().equals("高级") %>'>checked="checked"</c:if> />&nbsp;高级
                        &nbsp;&nbsp;&nbsp;&nbsp;<input name="role" type="radio" class="txtPermission" value="普通" <c:if test='<%=user.getPermission().equals("普通") %>'>checked="checked"</c:if> />&nbsp;&nbsp;普通
                        </p>
                    </li>
                    <li>
                        <input name="updateUser" type="button" id="btnupdate" value="更  新" style="cursor: pointer;" class="submit_u" />
                        <input name="back" type="button" value="返 回" style="cursor: pointer;" class="update" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/tab.jsp';" />
                   </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
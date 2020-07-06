<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="public/head.jsp" />
</head>
<body>
<jsp:include page="public/navbar.jsp" />
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12">
            <div class="panel panel-info" style="margin-top: 10px">
                <div class="panel-heading text-center"><b>注册</b></div>
                <div class="panel-body">
                    <div class="col-sm-7">
                        <img src="img/login_baner.png" alt="">
                    </div>
                    <div class="col-sm-5" style="margin-top: 10%;">
                        <form class="form-horizontal" name="myRegisterForm" action="UserServlet?doing=registe" method="post">
                            <div class="form-group">
                                <label for="email" class="col-sm-3 control-label">邮箱</label>
                                
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="邮箱" required autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nickname" class="col-sm-3 control-label">昵称</label>
                                
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="昵称" required autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>

                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="密码" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="comfpassword" class="col-sm-3 control-label">确认密码</label>

                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="确认密码" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="col-sm-3 control-label">地址</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="address" name="address" placeholder="地址" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <label>已有帐号？<a href="login.jsp">立即登录</a></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button onclick="checkPwd()" type="button" class="btn btn-lg btn-primary">立即注册</button>
                                	<script>
                                		function checkPwd(){
                                			var pwd = document.getElementById("password").value;
                                			var pwd2 = document.getElementById("confirmpassword").value;
                                			if(pwd==""||pwd2==""){
                                				return false;
                                			}
                                			if(pwd==pwd2){
                                				myRegisterForm.submit();
                                			}else{
                                				alert("密码不一致");
                                			}
                                		}
                                	</script>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="public/footer.jsp" />
        <c:if test="${user3=='No' }">
        	<script>
        		alert("用户已存在");
        	</script>
        </c:if> 
        <c:if test="${user5=='No' }">
        	<script>
        		alert("注册失败！");
        	</script>
        </c:if> 
    </div>
</div>
</body>
</html>
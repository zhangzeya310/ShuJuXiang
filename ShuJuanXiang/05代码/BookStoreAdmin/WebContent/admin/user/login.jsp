<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>后台管理系统</title>
	<link rel="shortcut icon" href="../images/favicon.png">
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,400italic,700,800' />
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Raleway:300,200,100' />
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" type='text/css' href="/admin/js/bootstrap/dist/css/bootstrap.css">
	<link rel="stylesheet" type='text/css' href="/admin/fonts/font-awesome-4/css/font-awesome.min.css">
	<!-- Custom styles for this template -->
	<link rel="stylesheet" type='text/css' href="/admin/css/style.css" />
</head>

<body class="texture">
	<div id="cl-wrapper" class="login-container">
		<div class="middle-login">
			<div class="block-flat">
				<div class="header">
					<h3 class="text-center"><img class="logo-img" src="/admin/images/logo.png" alt="logo"/>后台管理系统</h3>
				</div>
				<div>
					<form id="loginform" style="margin-bottom: 0px !important;" class="form-horizontal" action="${request.getContextPath}/admin/login.do?doWhat=login" method="post">
						<div class="content">
							<h5 class="title text-center"><strong>登录</strong></h5>
							<hr/>
							<div class="form-group">
								<div class="col-sm-12">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user"></i></span>
										<input type="text" name="username" placeholder="用户名" id="email" class="form-control">
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-lock"></i></span>
										<input type="password" name="password" placeholder="密码" id="password" class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="foot">
							<a  class="btn btn-primary" onclick="document.getElementById('loginform').submit()">登录</a>
							<a href="#" class="btn btn-warning">忘记密码</a>
						</div>
					</form>
				</div>
			</div>
			<div class="text-center out-links">
				<a href="#">310 &copy; 2017 All Rights Reserved.</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/admin/js/jquery.js"></script>
	<script type="text/javascript" src="/admin/js/behaviour/general.js"></script>
	<!-- Bootstrap core JavaScript -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/admin/js/behaviour/voice-commands.js"></script>
	<script type="text/javascript" src="/admin/js/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/admin/js/jquery.flot/jquery.flot.js"></script>
	<script type="text/javascript" src="/admin/js/jquery.flot/jquery.flot.pie.js"></script>
	<script type="text/javascript" src="/admin/js/jquery.flot/jquery.flot.resize.js"></script>
	<script type="text/javascript" src="/admin/js/jquery.flot/jquery.flot.labels.js"></script>
</body>
</html>
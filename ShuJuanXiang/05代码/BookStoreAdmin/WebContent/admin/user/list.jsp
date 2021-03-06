<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台管理系统</title>
    <link rel="shortcut icon" href="images/favicon.png">
    <link rel="stylesheet" type="text/css" href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,400italic,700,800' />
    <link rel="stylesheet" type="text/css" href='http://fonts.googleapis.com/css?family=Raleway:300,200,100' />
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="js/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="js/jquery.gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4/css/font-awesome.min.css" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="js/jquery.nanoscroller/nanoscroller.css" />
    <link rel="stylesheet" type="text/css" href="js/jquery.easypiechart/jquery.easy-pie-chart.css" />
    <link rel="stylesheet" type="text/css" href="js/bootstrap.switch/bootstrap-switch.css" />
    <link rel="stylesheet" type="text/css" href="js/bootstrap.datetimepicker/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" type="text/css" href="js/jquery.select2/select2.css" />
    <link rel="stylesheet" type="text/css" href="js/bootstrap.slider/css/slider.css" />
    <link rel="stylesheet" type="text/css" href="js/jquery.icheck/skins/square/blue.css" />
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
    <!-- Fixed navbar -->
    <jsp:include page="/admin/common/navbar.jsp" />
    <div id="cl-wrapper" class="fixed-menu">
        <!-- Fixed silder -->
        <jsp:include page="/admin/common/sidebar.jsp" />
        <div class="container-fluid" id="pcont">
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div class="block-flat">
                            <div class="header">
                            	<div class="row">
                                    <div class="col-sm-2">
                                        <h3>用户列表</h3>
                                    </div>
                                </div>

                            <div class="content">
                                <table class="no-border">
                                    <thead class="no-border">
                                        <tr>
                                            <th style="width:2%;">
                                                <input class="icheck" id="check-all" type="checkbox" name="checkall">
                                            </th>
                                            <th class="text-center" style="width:3%;"><strong>UID</strong></th>
                                            <th class="text-center" style="width:10%;"><strong>用户邮箱</strong></th>
                                            <th class="text-center" style="width:10%;"><strong>用户密码</strong></th>
                                            <th class="text-center" style="width:10%;"><strong>操作</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody class="no-border-y">
                                    	<c:forEach items="${users}" var="user">
	                                        <tr>
	                                            <td><input type="checkbox" class="icheck"></td>
	                                            <td class="text-center">${user.adminid}</td>
	                                            <td class="text-center">${user.name}</td>
	                                            <td class="text-center">${user.password}</td>
	                                            <td class="text-center">
	                                            	<a href="/admin/login.do?doWhat=deleteAdmin&uid=${user.adminid}" class="btn btn-danger btn-xs"><i class="fa fa-times"></i>删除该用户</a>
	                                            </td>
	                                        </tr>

                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                                <div class="col-sm-offset-4 col-sm-6">
                                    <ul class="pagination pagination-sm">
                                        <span id="total" hidden> ${totalPage }</span>
                                        <li class="first"><a onclick="pageSplit('first')" href="javascript:void(0)"><span class="glyphicon glyphicon-step-backward"></span>首页</a></li>
                                        <li class="prev"><a onclick="pageSplit('pre')" href="javascript:void(0)"><span class="glyphicon glyphicon-chevron-left"></span> 上一页</a></li>

                                        <li onclick="pageSplit('next')" class="next"><a href="javascript:void(0)">下一页 <span class="glyphicon glyphicon-chevron-right"></span></a></li>
                                        <li onclick="pageSplit('end')" class="last"><a href="javascript:void(0)">尾页 <span class="glyphicon glyphicon-step-forward"></span></a></li>

                                        <form method="post" action="${pageContext.request.contextPath}/admin/login.do?doWhat=listAdmin" name="myform">
                                            <input type="hidden" id="current" name="currentPage" value="${currentPage}">
                                        </form>
                                    </ul>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.nanoscroller/jquery.nanoscroller.js"></script>
    <script type="text/javascript" src="js/jquery.sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="js/jquery.easypiechart/jquery.easy-pie-chart.js"></script>
    <script type="text/javascript" src="js/behaviour/general.js"></script>
    <script type="text/javascript" src="js/jquery.ui/jquery-ui.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery.nestable/jquery.nestable.js"></script>
    <script type="text/javascript" src="js/bootstrap.switch/bootstrap-switch.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/jquery.select2/select2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.slider/js/bootstrap-slider.js"></script>
    <script type="text/javascript" src="js/jquery.gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //initialize the javascript
            App.init();
            $("#check-all").on('ifChanged', function() {
                var checkboxes = $(".no-border-y").find(':checkbox');
                if ($(this).is(':checked')) {
                    checkboxes.iCheck('check');
                } else {
                    checkboxes.iCheck('uncheck');
                }
            });
        });

        function pageSplit(obj) {
            var currentPage = document.getElementById("current");
            var total = parseInt(document.getElementById("total").innerHTML);
            if ("first" == obj) {
                currentPage.value = 1;
            } else if ("end" == obj) {
                currentPage.value = total;
            } else if ("pre" == obj) {
                //var temp = parseInt(currentPage.value)-1;
                var temp = currentPage.value - 1;
                if (temp <= 0) {
                    temp = 1;
                }
                currentPage.value = temp;
            } else if ("next" == obj) {
                var temp = parseInt(currentPage.value) + 1;
                if (temp >= total) {
                    temp = total;
                }
                currentPage.value = temp;
            }
            myform.submit();
        }
    </script>
    <!-- Bootstrap core JavaScript -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="js/behaviour/voice-commands.js"></script>
    <script type="text/javascript" src="js/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.flot/jquery.flot.js"></script>
    <script type="text/javascript" src="js/jquery.flot/jquery.flot.pie.js"></script>
    <script type="text/javascript" src="js/jquery.flot/jquery.flot.resize.js"></script>
    <script type="text/javascript" src="js/jquery.flot/jquery.flot.labels.js"></script>
    <script type="text/javascript" src="js/jquery.icheck/icheck.min.js"></script>
</body>
</html>
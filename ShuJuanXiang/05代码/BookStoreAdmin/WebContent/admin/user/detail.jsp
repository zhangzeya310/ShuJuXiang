<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/favicon.png" />
    <title>Clean Zone</title>
    <!-- <link rel="stylesheet" type="text/css" href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,400italic,700,800' />
    <link rel="stylesheet" type="text/css" href='http://fonts.googleapis.com/css?family=Raleway:300,200,100' /> -->
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
    <link rel="stylesheet" type="text/css" href="js/bootstrap.multiselect/css/bootstrap-multiselect.css" />
    <link rel="stylesheet" type="text/css" href="js/jquery.multiselect/css/multi-select.css" />
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="js/jquery.uploadify/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
    <!-- Fixed navbar -->
    <jsp:include page="../common/navbar.jsp" />
    <div id="cl-wrapper" class="fixed-menu">
        <!-- Fixed silder -->
        <jsp:include page="../common/sidebar.jsp" />
        <div class="container-fluid" id="pcont">
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-12 col-md-12">
						<div class="block-flat">
					    	<div class="header">
					    		<h3>修改用户信息</h3>
						    </div>

                            <form action="/admin/user.do?doWhat=updateUser&uid=${user.uid}" id="edituser" method="post">
						    <div class="content">
                                <table class="no-border">
                                    <thead class="no-border">
                                        <tr>
                                            <th class="text-left" style="width:50%;"><strong>用户信息</strong></th>
                                            <th class="text-left" style="width:25%;"><strong>旧值</strong></th>
                                            <th class="text-left" style="width:25%;"><strong>新值</strong></th>

                                        </tr>
                                    </thead>
                                    <tbody class="no-border-y">

                                    <tr>
                                        <td align="left">
                                            <b>用户邮箱</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${user.email}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="email" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>昵称</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${user.nickname}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="nickname" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>用户密码</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${user.password}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="password" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>状态</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${user.status}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="status" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>用户地址</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${user.address}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="address" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    </tbody>
                                </table>
                                <div style="border-top:1px dashed #999;">
                                	<div class="row" style="text-align: center">
	                                    <div class="col-sm-12 col-md-12" >
                                            <input type="submit"  class="btn btn-info btn-xs">
                                            <input type="reset" class="btn btn-info btn-xs">
	                                    </div>
	                                </div>
			                    </div>
						    </div>
                        </form>
						</div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.select2/select2.min.js"></script>
    <script type="text/javascript" src="js/jquery.parsley/dist/parsley.js"></script>
    <script type="text/javascript" src="js/bootstrap.slider/js/bootstrap-slider.js"></script>
    <script type="text/javascript" src="js/jquery.nanoscroller/jquery.nanoscroller.js"></script>
    <script type="text/javascript" src="js/jquery.nestable/jquery.nestable.js"></script>
    <script type="text/javascript" src="js/behaviour/general.js"></script>
    <script type="text/javascript" src="js/jquery.ui/jquery-ui.js"></script>
    <script type="text/javascript" src="js/bootstrap.switch/bootstrap-switch.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/jquery.icheck/icheck.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.multiselect/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="js/jquery.multiselect/js/jquery.multi-select.js"></script>
    <script type="text/javascript" src="js/jquery.uploadify/jquery.uploadify.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //initialize the javascript
            App.init();
            $('#categories').multiselect({
                enableFiltering: true,
                maxHeight: 250,
                filterPlaceholder: '搜索',
            });
            
            $('#secondCategories').multiselect({
                enableFiltering: true,
                maxHeight: 200,
                filterPlaceholder: '搜索',
            });
            
            // 上传书籍Logo
        	$("#uploadLogo").uploadify({
        		'auto'				: false,
        		'buttonText'		: '上传书籍文件',
        		'fileTypeExts'		: '*.gif; *.jpg; *.jpeg; *.png;',
        		'fileSizeLimit'		: '1024KB',
        		'preventCaching'	: false,
        		'removeTimeout'		: 3,
        		'removeCompleted'	: false,
        		'multi'				: false,
        		'swf'				: 'js/jquery.uploadify/uploadify.swf',
        		'uploader'			: '__CONTROLLER__/uploadLogo',
        		//'uploadLimit'		: 1,
        		'onUploadSuccess' : function(file, result) {
        			var msg = $.parseJSON(result);
        			if(1 == msg.code){
        				$('#logo').attr('src','__PUBLIC__'+msg.data.url);
        				$('#logo').parent().children('input').val($('#logo').attr('src'));
        			}
        		}
            });
        });
    </script>
    <!-- Bootstrap core JavaScript -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="js/behaviour/voice-commands.js"></script>
    <script type="text/javascript" src="js/bootstrap/dist/js/bootstrap.min.js"></script>
</body>

</html>
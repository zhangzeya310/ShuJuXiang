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
					    		<h3>修改图书信息</h3>
						    </div>

                            <form action="/admin/user.do?doWhat=updateBook&uid=${book.bid}" id="editbook" method="post">
						    <div class="content">
                                <table class="no-border">
                                    <thead class="no-border">
                                        <tr>
                                            <th class="text-left" style="width:50%;"><strong>图书信息</strong></th>
                                            <th class="text-left" style="width:25%;"><strong>旧值</strong></th>
                                            <th class="text-left" style="width:25%;"><strong>新值</strong></th>

                                        </tr>
                                    </thead>
                                    <tbody class="no-border-y">

                                    <tr>
                                        <td align="left">
                                            <b>书籍名称</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.name}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="name" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>书籍作者</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.author}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="author" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>价格</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.price}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="price" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>折扣</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.discount}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="discount" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>出版社</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.publishing}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="publishing" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>出版时间</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.publishTime}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="publishTime" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>版次</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.edition}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="edition" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>页数</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.pageNum}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="pageNum" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>ISNB</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.isnb}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <input type="text" class="" name="isnb" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>图书简介</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.bookDescription}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <textarea  class="" name="bookDescription" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>管理员评论</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.editorComment}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <textarea  class="" name="editorComment" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>作者简介</b>
                                        </td>
                                        <td align="left" style="line-height:40px;">${book.authordescription}</td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="btn-group">
                                                <textarea  class="" name="authordescription" value="">
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>图片</b>
                                        </td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="form-group">

                                                <div class="col-sm-6 col-md-6">
                                                    <input type="file" class="form-control" name="imgUrlMid" id="imgUrlMid" >
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>籍一级类别</b>
                                        </td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="form-group">
                                                <div class="col-sm-6 col-md-6">
                                                    <div class="input-group btn-group">	<span class="input-group-addon"><i class="fa fa-tag"></i></span>
                                                        <select id="categories" class="form-control" name="category">
                                                            <c:forEach items="${categories}" var="category">
                                                                <option value="${category.categoryId}">${category.categoryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left">
                                            <b>书籍二级类别</b>
                                        </td>
                                        <td align="left" style="line-height:20px;">
                                            <div class="form-group">
                                                <div class="form-group">
                                                    <div class="col-sm-6 col-md-6">
                                                        <div class="input-group btn-group">	<span class="input-group-addon"><i class="fa fa-tag"></i></span>
                                                            <select id="secondCategories" class="form-control" name="secondCategory">
                                                                <c:forEach items="${secondCategories}" var="secondCategory">
                                                                    <option value="${secondCategory.secondCategoryId}">${secondCategory.secondCategoryName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
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
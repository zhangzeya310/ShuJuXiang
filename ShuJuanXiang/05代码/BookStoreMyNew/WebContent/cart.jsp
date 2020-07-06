<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="panel panel-success" style="margin-top: 10px">
                <div class="panel-heading text-center"><b>购物车列表清单</b></div>
                <div class="alert-warning text-center" style="line-height: 40px;">
                   	 如果没有登录！登录后购物车的商品将保存到您账号中
                    <a href="login.jsp" class="btn btn-danger btn-xs">立即登录</a>
                </div>
                <div class="panel-body">
                    <div class="bs-callout bs-callout-info" id="callout-scrollspy-needs-nav"></div>
                </div>
                <div class="table table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>商品</th>
                            <th>单价（元）</th>
                            <th>数量</th>
                            <th>小计</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
							<c:forEach items="${cartList}" var="item" varStatus="myIndex">
	                            <tr>
	                                <td align="left">
	                                    <div style="width:100px;height:100px"><img src="${item.getBook().getURL()}" style="width:100%;height:100%;" alt="kkk"> </div> 
	                                    <b>${item.getBook().getName()}</b>
	                                </td>
	                                <td align="left" style="line-height:80px;"><fmt:formatNumber type="number" value="${item.getBook().getPrice() * item.getBook().getDiscount() * 0.1}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></td>
	                                <td align="left" style="line-height:80px;">
	                                    <div class="btn-group">
	                                        <a href="javascript:void(0)" onclick="changeOne('${myIndex.index}','${pageContext.request.contextPath}/CartServlet?doing=subOne&bookId=${item.getBook().getBid()}')" class="btn btn-default btn-xs"><i class="glyphicon glyphicon-arrow-down"></i></a>
	                                        <a href="#" id="bookNumAjax${myIndex.index}" class="btn btn-default btn-xs disabled">${item.getNum()}</a>
	                                        <a href="javascript:void(0)" onclick="changeOne('${myIndex.index}','${pageContext.request.contextPath}/CartServlet?doing=addOne&bookId=${item.getBook().getBid()}')" class="btn btn-default btn-xs"><i class="glyphicon glyphicon-arrow-up"></i></a>
	                                    	<script type="text/javascript">
	                                    		//得到ajax对象
	                                    		function getMyAjax(){
	                                    			var xhr = null;
	                                    			if(window.ActiveXObject){
	                                    				xhr = new ActiveXObject("Microsoft.XMLHTTP");
	                                    			}else if(window.XMLHttpRequest){
	                                    				xhr = new XMLHttpRequest();
	                                    			}else{
	                                    				alert("not support ajax");
	                                    			}
	                                    			return xhr;
	                                    		}
	                                    		//进行异步交互
	                                    		function changeOne(index,path){
	                                    			var xhr = getMyAjax();
	                                    			if(xhr!=null){
	                                    				xhr.open("get", path);
	                                    				xhr.onreadystatechange=function(){
	                                    					if(xhr.readyState==4&&xhr.status==200){
	                                    						var text=xhr.responseText;
	                                    						if(text!=""&&text!=null&&text!="-1"){
	                                    							var s = text.split("-");
	                                    							document.getElementById("bookNumAjax"+index).innerHTML=s[0];
	                                    							document.getElementById("sum"+index).innerHTML=s[1];
	                                    							document.getElementById("sumCartPrice").innerHTML=s[2];
	                                    						}
	                                    					}
	                                    				}
	                                    			}
	                                    			xhr.send();
	                                    		}
	                                    	</script>
	                                    </div>
	                                </td>
	                                <td align="left" id="sum${myIndex.index }" style="line-height:80px;"><fmt:formatNumber type="number" value="${item.getBook().getPrice() * (item.getBook().getDiscount() * 0.1) * item.getNum()}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></td>
	                                <td align="left" style="line-height:80px;"><a class="btn btn-danger btn-xs" href="CartServlet?doing=deleteCart&bookId=${item.getBook().getBid()}">删除</a></td>
	                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="border-top:1px dashed #999;">
                        <div style="line-height: 45px;">
                        	<c:if test="${not empty cartList }">
	                            <div class="col-sm-5">
	                                <a style="text-decoration: none;background: #876;color: #eff;" href="${pageContext.request.contextPath}/search.jsp">继续购物 </a>
	                            </div>
	                            <div class="col-sm-5">
	                                <div class="col-sm-6">
	                                   	 已选择<b class="text-danger"> ${cartList.size()} </b>件商品
	                                </div>
	                                <div class="col-sm-6" id="sumCartPrice">
	                                   	 总价（不含运费）：<b class="text-danger">￥<fmt:formatNumber type="number" value="${cartSumPrice}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></b>
	                                </div>
	                            </div>
	                            <div class="col-sm-2">
	                                <a href="OrderServlet?doing=addOrder" type="submit" class="btn btn-danger btn-lg btn-block">提交订单</a>
	                            </div>
                        	</c:if>
                        	<c:if test="${empty cartList }">
                        		<div class="col-sm-5">
	                                <a style="text-decoration: none;background: #876;color: #eee;" href="${pageContext.request.contextPath}/search.jsp">继续购物 </a>
	                            </div>
	                            <div class="col-sm-5">
	                                <div class="col-sm-6">
	                                   	 已选择<b class="text-danger"> 0 </b>件商品
	                                </div>
	                                <div class="col-sm-6">
	                                   	 总价（不含运费）：<b class="text-danger">￥0.0</b>
	                                </div>
	                            </div>
                        	</c:if>
                        </div>
                    </div>
                </div>
            </div>
           <%--  <%
            BookDaoImpl bookDao = new BookDaoImpl();
            ArrayList<Book> randbooks = bookDao.getRandBooks();
            request.getSession().setAttribute("randbooks", randbooks);
            %> --%>
            <h3 class="page-header">猜你喜欢</h3>
            <div class="row">
            	<c:forEach items="${randbooks}" var="randbook">
	                <div class="col-sm-3">
	                    <div class="thumbnail">
	                    	<a href="item.do?bid=${randbook.getBid()}"><img src="upload/${randbook.getImgUrlMid()}" alt=""></a>
	                        <div class="caption">
	                            <h5><a href="item.do?bid=${randbook.getBid()}">${randbook.getName()}</a></h5>
	                            <p><label class="label label-danger">¥${randbook.getPrice()* randbook.getDiscount() * 0.1 }</label></p>
	                            <span>${randbook.getAuthor()}</span><br>
	                            <span>${randbook.getPublishing()}</span><br>
	                            <span>${randbook.getPublishTime()}</span>
	                        </div>
	                    </div>
	                </div>
	            </c:forEach>
            </div>
        </div>
        <jsp:include page="public/footer.jsp" />
    </div>
</div>
</body>
</html>
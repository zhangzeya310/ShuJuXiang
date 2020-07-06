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
                <c:if test="${empty user }">
                	<div class="alert-warning text-center" style="line-height: 40px;">
                   	 	您还没有登录！登录后购物车的商品将保存到您账号中
                   	 	<a href="login.jsp" class="btn btn-danger btn-xs">立即登录</a>
               		</div>
                </c:if>
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
                        </tr>
                        </thead>
                        <tbody>
							<c:forEach items="${orderNow}" var="item" varStatus="myIndex">
	                            <tr>
	                                <td align="left">
	                                   <%--  <img src="upload/${item.getBook().getImgUrlSmall()}" alt=""> --%>
	                                    <b>${item.getBook().getName()}</b>
	                                </td>
	                                <td align="left" style="line-height:80px;"><fmt:formatNumber type="number" value="${item.getBook().getPrice() * item.getBook().getDiscount() * 0.1}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></td>
	                                <td align="left" style="line-height:80px;">
	                                    <div class="btn-group">
	                                        <a href="#" class="btn btn-default btn-xs disabled">${item.getNum()}</a>
	                                    </div>
	                                </td>
	                                <td align="left" style="line-height:80px;"><fmt:formatNumber type="number" value="${item.getBook().getPrice() * (item.getBook().getDiscount() * 0.1) * item.getNum()}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></td>
	                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="border-top:1px dashed #999;">
                        <div style="line-height: 45px;">
                        	<c:if test="${not empty orderNow }">
	                            <div class="col-sm-5">
	                                <div class="col-sm-6">
	                                   	共<b class="text-danger"> ${orderNow.size()} </b>件商品
	                                </div>
	                                <div class="col-sm-6" id="sumCartPrice">
	                                   	 总价（不含运费）：<b class="text-danger">￥<fmt:formatNumber type="number" value="${orderSumPrice}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></b>
	                                </div>
	                            </div>
	                            <div class="col-sm-2">
	                            	<font size="4" color="#333" style="background: #eef;">已提交</font>
	                            </div>
                        	</c:if>
                        </div>
                    </div>
                </div>
            </div>
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
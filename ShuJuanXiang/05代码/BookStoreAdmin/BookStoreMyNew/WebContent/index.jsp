<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="public/head.jsp" />
<link rel="stylesheet" href="css/carousel.css">
</head>
<body>
<jsp:include page="public/navbar.jsp" />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
        	<c:forEach items="${categorys}" var="category">
        		<div class="panel panel-info nav-sidebar">
	               <div class="panel-heading" role="tab" id="${category.getCategoryDesc()}">
	                    <h4 class="panel-title">
	                        <a href=""><b>${category.getCategoryName()}</b></a>
	                    </h4>
	                </div>
	                <div id="${category.getCategoryDesc()}-item" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="${category.getCategoryDesc()}" aria-expanded="true">
	                    <ul class="list-group">
	                    	<c:forEach items="${category.getSecondCategory()}" var="secondCategory">
	                    		<li class="list-group-item"><a href="BookServlet?doing=queryBooksByCidAndSid&cid=${category.categoryId}&sid=${secondCategory.secondCategoryId}">${secondCategory.getSecondCategoryName()}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </div>
	            </div>
        	</c:forEach>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="1" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="2" class="active"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item">
                        <img class="first-slide" src="img/longzuda.jpg" alt="First slide">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1><label class="label label-success">新书推荐:一</label></h1>
                                <p> </p>
                               <!--  <p><a class="btn btn-lg btn-primary" href="#" role="button">点击查看</a></p> -->
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img class="second-slide" src="img/wukongda.jpg" alt="Second slide">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1><label class="label label-success">新书推荐:二</label></h1>
                                <p> </p>
                                <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">点击查看</a></p> -->
                            </div>
                        </div>
                    </div>
                    <div class="item active">
                        <img class="third-slide" src="img/sanshengda.jpeg" alt="Third slide">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1><label class="label label-success">新书推荐:三</label></h1>
                                <p> <%-- <code>内容: XXXXX XXXXXXXXXX XXXXXXXXXXXXXX XXXXX XXXXXXXXXX XXXXXXXXXXXXXX XXXXX XXXXXXXXXX XXXXXXXXXXXXXX XXXXX XXXXXXXXXX XXXXXXXXXXXXXX</code> --%></p>
                                <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">点击查看</a></p> -->
                            </div>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            
            <h3 class="page-header">1F 新书速递</h3>
            <div class="row">
                <c:forEach items="${floorBooksList1}" var="book">
	                <div class="col-sm-3">
	                    <div class="thumbnail">
	                    	<a href="BookServlet?doing=showItem&bid=${book.getBid()}"><img src="${book.getURL()}" alt=""></a>
	                        <div class="caption">
	                            <h5><a href="BookServlet?doing=showItem&bid=${book.getBid()}">${book.getName()}</a></h5>
	                            <p><label class="label label-danger">¥<fmt:formatNumber type="number" value="${book.getPrice()* book.getDiscount() * 0.1 }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></label></p>
	                            <span>${book.getAuthor()}</span><br>
	                            <span>${book.getPublishing()}</span><br>
	                            <span>${book.getPublishTime()}</span>
	                        </div>
	                    </div>
	                </div>
	            </c:forEach>
            </div>
          
            <h3 class="page-header">2F 丛书推荐</h3>
            <div class="row">
                <c:forEach items="${floorBooksList2}" var="book">
	                <div class="col-sm-3">
	                    <div class="thumbnail">
	                    	<a href="BookServlet?doing=showItem&bid=${book.getBid()}"><img src="${book.getURL()}" alt=""></a>
	                        <div class="caption">
	                            <h5><a href="BookServlet?doing=showItem&bid=${book.getBid()}">${book.getName()}</a></h5>
	                            <p><label class="label label-danger">¥<fmt:formatNumber type="number" value="${book.getPrice()* book.getDiscount() * 0.1 }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></label></p>
	                            <span>${book.getAuthor()}</span><br>
	                            <span>${book.getPublishing()}</span><br>
	                            <span>${book.getPublishTime()}</span>
	                        </div>
	                    </div>
	                </div>
	            </c:forEach>
            </div>
       
            <h3 class="page-header">3F 畅销经典</h3>
            <div class="row">
            	<c:forEach items="${floorBooksList3}" var="book">
	                <div class="col-sm-3">
	                    <div class="thumbnail">
	                    	<a href="BookServlet?doing=showItem&bid=${book.getBid()}"><img src="${book.getURL()}" alt="loading"></a>
	                        <div class="caption">
	                            <h5><a href="BookServlet?doing=showItem&bid=${book.getBid()}">${book.getName()}</a></h5>
	                            <p><label class="label label-danger">¥<fmt:formatNumber type="number" value="${book.getPrice()* book.getDiscount() * 0.1 }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></label></p>
	                            <span>${book.getAuthor()}</span><br>
	                            <span>${book.getPublishing()}</span><br>
	                            <span>${book.getPublishTime()}</span>
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
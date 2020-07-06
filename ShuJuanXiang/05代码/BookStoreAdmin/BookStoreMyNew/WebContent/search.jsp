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
            <ol class="breadcrumb" style="margin-top: 10px;">
                <li><a href="BookServlet?doing=queryAll">全部</a></li>
            </ol>
        </div>
        <div class="col-sm-12 col-md-12">
            <div class="panel panel-success">
                <div class="panel-heading">　</div>
                <!-- <div class="panel-body">
                    <div class="bs-callout bs-callout-info" id="callout-scrollspy-needs-nav"></div>
                </div> -->
                <div class="table table-responsive">
                    <table class="table table-striped table-condensed">
                        <tr>
                            <th><label class="label label-success">出版社：</label></th>
                            <c:forEach items="${publishs}" var="publish">
                            	<td align="left"><label class="control-label"><a href="BookServlet?doing=queryByPublish&publish=${publish}">${publish}</a></label></td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th><label class="label label-success">价格：</label></th>
                            <c:forEach items="${prices}" var="price">
                            	<td align="left"><label class="control-label"><a href="BookServlet?doing=queryByPrice&price=${price}">${price}</a></label></td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th><label class="label label-success">折扣：</label></th>
                            <c:forEach items="${discounts}" var="discount">
                            	<td align="left"><label class="control-label"><a href="BookServlet?doing=queryByDiscount&discount=${discount}">${discount}折</a></label></td>
                            </c:forEach>
                            <td align="left" colspan="2"></td>
                        </tr>
                    </table>
                     <div style="text-align:center;margin-right:10px;border-top:1px dashed  #999;line-height:30px;">
                        <div class="text-warning">对书籍进行进一步筛选</div>
                    </div>
                </div>
            </div>
        </div><div class="col-sm-12 col-md-12">
        	<c:forEach items="${booksCidSidList}" var="book">
	            <div class="col-sm-3">
	                <div class="thumbnail">
	                    <a href="BookServlet?doing=showItem&bid=${book.getBid()}"><img src="${book.getURL() }" alt="${book.getName()}"></a>
	                    <div class="caption">
	                        <h4 style="color: #c9302c">¥<fmt:formatNumber type="number" value="${book.getPrice() * book.getDiscount() * 0.1}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></h4>
	                        <!-- <h6>已有评价 <label class="label label-info">9140个</label></h6> -->
	                        <h5><a href="BookServlet?doing=showItem&bid=${book.getBid()}">${book.getName()}</a></h5>
	                        <!-- <button class="btn btn-default" style="color: #c9302c"><span class="glyphicon glyphicon-star-empty"></span>关注</button> -->
	                        <a href="javascript:openUrl('CartServlet?doing=addCart&bookId=${book.getBid()}&bookNum=1')" class="btn btn-default" style="color: #c9302c"><span class="glyphicon glyphicon-shopping-cart"></span> 加入购物车</a><br>
	                        <script>
	                        	function openUrl(path){
	                        		window.location.href = path;
	                        	}
	                        </script>
	                        <span>${book.getAuthor()}</span><br>
	                        <span>${book.getPublishing()}</span><br>
	                        <span>${book.getPublishTime()}</span>
	                    </div>
	                </div>
	            </div>
            </c:forEach>
        </div>
        <c:if test="${empty booksCidSidList}">
        	<c:set var="haveBooks" value="no"></c:set>
        </c:if>
        <c:if test="${not empty booksCidSidList}">
        	<c:set var="haveBooks" value="yes"></c:set>
        </c:if>
        <div class="col-sm-offset-4 col-sm-6">
            <ul class="pagination pagination-sm">
                <li class="first"><a onclick="splitPage('firstPage','no','${haveBooks}')" href="javascript:void(0)"><span class="glyphicon glyphicon-step-backward"></span>首页</a></li>
                <li class="prev"><a onclick="splitPage('prePage','no','${haveBooks}')" href="javascript:void(0)"><span class="glyphicon glyphicon-chevron-left"></span> 上一页</a></li>
                <c:forEach items="${linksValue }" var="linkValue" varStatus="status">
                	<!-- <script>alert("status="+status+" currPage="+currPage);</script> -->
                	<c:if test="${not empty linkValue }">
                		<c:if test="${status.count==blueIndex }">
                			<li class="active"><a href="javascript:void(0)" onclick="splitPage('fivePage',this,'${haveBooks}')">${linkValue }</a></li>
                		</c:if>
                		<c:if test="${status.count!=blueIndex }">
                			<li><a href="javascript:void(0)" onclick="splitPage('fivePage',this,'${haveBooks}')">${linkValue }</a></li>
                		</c:if>
                	</c:if>
                </c:forEach>
                <li onclick="splitPage('nextPage','no','${haveBooks}')" class="next"><a href="javascript:void(0)">下一页 <span class="glyphicon glyphicon-chevron-right"></span></a></li>
                <li onclick="splitPage('lastPage','no','${haveBooks}')" class="last"><a href="javascript:void(0)">尾页 <span class="glyphicon glyphicon-step-forward"></span></a></li>
            </ul>
        </div>
       
        <form action="${pageContext.request.contextPath }/${splitFormAction}" name="splitPageForm" method="post">
        	<!-- 要传到后台查询的当前页 -->
        	<input type="hidden" name="currPage" id="currPageId" value="1"/>
        	<!-- 从后台得到的当前页。没有name，不会传到后台，方便splitPage方法得到value -->
        	<input type="hidden" id="currPage2Id" value="${currPage}"/>
        	<!-- 总页码 -->
        	<input type="hidden" id="totalPageId" value="${totalPage}"/>
        </form>
        <script type="text/javascript">
        	function splitPage(doPage,obj,havebooks){
        		//alert(document.getElementsByName("splitPageForm")[0].action);
    			//alert("have="+havebooks);
        		if(havebooks=='no'){
    				return false;
    			}
        		//要传到后台的当前页
        		var currPage = document.getElementById("currPageId");
    			//当前页
    			var currPage2Value = document.getElementById("currPage2Id").value;//alert("now="+currPage2Value);
    			//总页码
    			var totalPageValue = document.getElementById("totalPageId").value;
    			if(currPage.value==""||currPage.value==null||currPage2Value==""||currPage2Value==null||totalPageValue==""||totalPageValue==null){
    				return false;
    			}
        		if(doPage=="firstPage"){
        			if(currPage2Value=="1"){
        				currPage.value = "1";
        				return false;
        			}else{
 		       			currPage.value = '1';
        				document.splitPageForm.submit();			
        			}
        		}else if(doPage=="prePage"){
        			if(currPage2Value=="1"){
        				currPage.value = "1";
        				return false;
        			}else{
        				currPage.value = parseInt(currPage2Value)-1;//alert(currPage.value);
        				document.splitPageForm.submit();
        			}
        		}else if(doPage=="nextPage"){
        			if(currPage2Value==totalPageValue){
        				return false;
        			}else{
    	    			currPage.value = parseInt(currPage2Value)+1;
	        			document.splitPageForm.submit();				
        			}
        		}else if(doPage=="lastPage"){
        			if(currPage2Value==totalPageValue){
        				return false;
        			}else{
        				currPage.value = totalPageValue;
        				document.splitPageForm.submit();
        			}
        		}else{
        			//中间5个链接
        			var innerValue = obj.innerHTML;//alert("innerValue="+innerValue);
        			currPage.value = innerValue;//alert("houtai="+currPage.value);
    				document.splitPageForm.submit();
        		}
        	}
        </script>
        <jsp:include page="public/footer.jsp" />
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 通过动态标签引入公共jsp页面 -->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>


 <!-- 引入函数 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品首页</title>


</head>


<body>
	<!-- 横幅导航条开始 -->
	<jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>


	<!-- 横幅导航条结束 -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<!-- 侧边导航开始 -->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="list-group">
					<!-- 遍历所有的一级物品类型 -->
					<c:forEach items="${articleTypes}" var="articleType">

						<c:choose>
							<c:when test="${firstCode eq articleType.code}">
								<a
									href="${pageContext.request.contextPath}/article/index.do?typeCode=${articleType.code}"
									class="list-group-item active">${articleType.name}</a>
							</c:when>
							<c:otherwise>
								<a
									href="${pageContext.request.contextPath}/article/index.do?typeCode=${articleType.code}"
									class="list-group-item">${articleType.name}</a>
							</c:otherwise>

						</c:choose>


					</c:forEach>

				</div>
			</div>
			<!--  侧边导航结束 -->
			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">显示导航条</button>
				</p>
				<div class="alert alert-info" role="alert">

					<c:if test="${not empty secondArticleTypes}">
						<c:forEach items="${secondArticleTypes}" var="secondArticleType">
							<c:choose>
							   <c:when test="${secondArticleType.code eq secondCode}">
							      <a href="${pageContext.request.contextPath}/article/index.do?typeCode=${secondArticleType.code}"
								class="btn btn-default active">${fn:replace(secondArticleType.name,'-','')}</a>
							   </c:when>
							   <c:otherwise>
							      <a href="${pageContext.request.contextPath}/article/index.do?typeCode=${secondArticleType.code}"
								class="btn btn-default">${fn:replace(secondArticleType.name,'-','')}</a>
							   </c:otherwise>
							    
							 </c:choose>
							
						
						</c:forEach>
					</c:if>



					<div>
						<form
							action="${pageContext.request.contextPath}/article/index.do"
							method="get">
							<!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
							<input type="hidden" name="typeCode" value="${typeCode}" /> <input
								name="keyword" value="${keyword}" />
							<button type="submit">搜索</button>
						</form>
					</div>
				</div>

				<div class="row">

					<c:forEach items="${articles}" var="article">
						<div class="col-xs-6 col-lg-4">
							<span class="thumbnail"> <a
								href="${pageContext.request.contextPath}/article/detail?id=${article.id}">

									<img
									src="${pageContext.request.contextPath}/image/article/${article.image}"
									alt="...">
									<p style="height: 20px; overflow: hidden;">${article.title}
										${article.description}</p>
							</a>
								<p>
									<span class="price">${article.price}</span>&nbsp;<font color='red'>折后价: <%-- <fmt:formatNumber value="${article.discountPrice}" pattern="0.00"></fmt:formatNumber> --%></font>

								</p>
							</span>
						</div>
					</c:forEach>

					<!--/.col-xs-6.col-lg-4-->

				</div>
				<!--/row-->


				<!-- p2是url前段部分pageNumber=之前 -->


				<!--  分页开始 -->

				<div class="row">
					<nav>
						<ul class="pagination">
							
							 <longIt:pager submitUrl="${pageContext.request.contextPath}/article/index" pageModel="${pageModel}"></longIt:pager>
							  
							
						</ul>
					</nav>
				</div>
				<!-- 分页结束 -->
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->
		<hr>
		<footer>
			<p>&copy;欢迎借鉴</p>
		</footer>
	</div>
</body>
</html>
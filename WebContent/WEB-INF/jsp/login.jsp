<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<!-- 通过动态标签引入公共jsp页面 -->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

 <!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录页面</title>


</head>
<body>
	<!-- 横幅导航条开始 -->
	
   <jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>
    
	<!-- /.navbar -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<!-- 入门-->
		<!-- 登录界面 -->
		<div class="page-header">
			<h1>用户登录</h1>&nbsp; <font color="red">${error_message}</font><font color="red">${message}</font>
		</div>
		<form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/user/userLogin">
			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" value="" placeholder="用户名/邮箱"
						type="text" id="loginName" name="loginName" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" placeholder="密码" id="passWord"
						type="password" name="password" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
					<span style="color: red;"></span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="submit" id="loginBtn" class="btn btn-success">
								<span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
							</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-danger">
								<span class="glyphicon glyphicon-edit"></span>注册
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<hr>

		<!-- 彈出框-->
		<div id="myModal" class="modal bs-example-modal-sm fade">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">登录提示</h4>
					</div>
					<div class="modal-body">
						<p id="tip"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="sure" type="button" class="btn btn-primary"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>
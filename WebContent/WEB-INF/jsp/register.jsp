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
<title>注册页面</title>

     <script type="text/javascript">
       	
    	function validLoginName(obj){
            
            if(obj.value!=null && obj.value!=""){
            	
            	//开始发送异步请求校验登录名是否存在
            	$.ajax({
             	   type: "POST",//请求的方式
             	   url: "${pageContext.request.contextPath}/user/validLoginName",//请求的地址
             	   data: "loginName="+obj.value,//需要传递至后台的参数
             	   success: function(msg){ //如果后台正常处理数据，没有出现异常则会自动回调 success中的函数，后台会将消息赋给  msg 
             	      if(msg!=""){
             	    	  alert(msg);
             	    	  obj.value = "";
             	      }
             		   
             	   },error:function(){ //如果后台出现异常
             		   alert("网络出现异常！");
             	   }
             	});
            	
            }
    		
    	}
     
     
     </script>

</head>
<body>
	<!-- 横幅导航条开始 -->
	
	<jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>

	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row info-content">
			<form id="registerForm" class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/user/userRegister" style="margin-top: 20px;">
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名称</label>
					<div class="col-sm-3">
						<input type="text" value="" id="loginName" name="loginName"
							class="form-control" placeholder="请输入您的登陆名称"  onblur = "validLoginName(this)">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-3">
						<input type="text" id="name" value="" name="name"
							class="form-control" placeholder="请输入您的真实姓名">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登陆密码</label>
					<div class="col-sm-3">
						<input type="password" id="password" name="password"
							class="form-control" placeholder="请输入您的密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-3">
						<input type="password" id="okPassword" name="okpassword"
							class="form-control" placeholder="请输入您的确认密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-3">
						<label>男:</label><input name="sex" value="1" type="radio"
							checked="checked"> <label>女:</label><input
							name="user.sex" value="2" type="radio">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-3">
						<input type="text" id="phone" value="" name="phone"
							class="form-control" placeholder="请输入您的电话">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-8">
						<textarea id="address" name="address" rows="4" cols="30"
							class="form-control" placeholder="请输入您的地址信息"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱:</label>
					<div class="col-sm-3">
						<input type="text" id="email" value="" name="email"
							class="form-control" placeholder="请输入您的邮箱">
					</div>
					<div class="col-sm-3">
						<span style="color: red;"></span>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-3">
						<button type="submit" id="btn_submit" class="btn btn-success">
							<span class="glyphicon glyphicon-user">注册
						</button>
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-edit">重置
						</button>
					</div>
				</div>
			</form>
		</div>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

		<script>
			/*  $('#registerForm').bootstrapValidator({
				//       live: 'disabled',
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					"user.name" : {
						validators : {
							notEmpty : {
								message : '真实姓名不能为空'
							}
						}
					},
					"user.loginName" : {
						message : '用户名验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},
							stringLength : {
								min : 5,
								max : 30,
								message : '用户名长度5到30位'
							}
						}
					},
					"user.email" : {
						validators : {
							notEmpty : {
								message : '邮箱不能为空'
							},
							emailAddress : {
								message : '邮箱格式不正确'
							}
						}
					},
					"user.phone" : {
						validators : {
							notEmpty : {
								message : '电话不能为空'
							},
							phone : {
								message : '电话号码错误',
								country : 'CN'
							}
						}
					},
					"user.password" : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							different : {
								field : 'user.loginName',
								message : '用户名和密码不能相同'
							}
						}
					},
					"user.okpassword" : {
						validators : {
							notEmpty : {
								message : '确认密码不能为空'
							},
							identical : {
								field : 'user.password',
								message : '两次密码不相同'
							},
							different : {
								field : 'user.loginName',
								message : '用户名和密码不能相同'
							}
						}
					},
					"user.address" : {
						validators : {
							notEmpty : {
								message : '地址不能为空'
							}
						}
					}
				}
			}); 

			$("#btn_submit").click(function() {
				alert(1);
				$('#registerForm').bootstrapValidator('validate');
			});
			 */
			
		</script>
	</div>
	<!--  横幅下方的主体结束 -->
</body>
</html>

 
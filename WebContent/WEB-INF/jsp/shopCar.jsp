<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- 通过动态标签引入公共jsp页面 -->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>


<!-- 引入格式化标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
 <!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>购物车</title>

	


<script type="text/javascript">

// 为 ’-‘ 半丁事件，用户点击后，将购买的数量减一，但是必须保证购买的数量为大于等于1的正整数 
function minuFun(articleId){
	   
	   var  obj = document.getElementById(articleId);

	   //获取输入款中的值
	   var num = obj.value;
	   
	   if(num>1){
		   //重新给输入框中的value赋值
		   obj.value = num - 1; 
		   
		   //发送请求更新购物车中购买的数量 
		   window.location = "${pageContext.request.contextPath}/shopCar/updateShopcar.action?id="+articleId+"&number="+obj.value;
		   
	   }
}


// 为 ’+‘ 半丁事件，用户点击后，将购买的数量减一，但是必须保证购买的数量为大于等于1的正整数 
function addFun(articleId){
	   
	   //获取输入框对应的dom对象
	   var  obj = document.getElementById(articleId);
	   //获取输入款中的值
	   var num = parseInt(obj.value);//parseInt:将字符窜转成整数
	   
	   //获取库存数
	   var kucun = parseInt("${article.storage}")
	   //重新给输入框中的value赋值
		obj.value = (num + 1) > kucun ?  kucun : num + 1; 
	   
		//发送请求更新购物车中购买的数量 
		window.location = "${pageContext.request.contextPath}/shopCar/updateShopcar.action?id="+articleId+"&number="+obj.value;
		   
}

 //为购买的数量对应的输入框  绑定失去焦点事件
 function validNum(obj,articleId){
 	//用户输入的数字 必须为 大于等于1的正整数，并且不能大于库存
 	
 	//1、判断用户输入的是否是数字   isNaN: is Not a Number
 	if(isNaN(obj.value)||obj.value < 1){
 		//用户输入的不是数字或者小于1的数字 都不合法,则 使用默认购买数量
 		obj.value = 1;
 	}else if(obj.value > parseInt("${article.storage}")){
 		obj.value = 1;
 		alert("购买数量已超过库存数，请核实");
 	}else{
 		//防止用户用户输入小数    假设用户输入3 == 》3  用户输入  3.5 == 》4   对用户输入的值 进行向上取整
 		obj.value = Math.ceil(obj.value);
 		//发送请求更新购物车中购买的数量 
		window.location = "${pageContext.request.contextPath}/shopCar/updateShopcar.action?id="+articleId+"&number="+obj.value;
		   
 		
 	}
 	
 }
 
 //为全选绑定单击事件
 function checkAll(checkObj){
	
	
	 //获取所有的子 checkbox 
	 var boxes = document.getElementsByName("box");
	 //alert(boxes.length);
	 
	  //将订单相关信息存放在 隐藏输入框中
	 var orderInfo = document.getElementById("orderInfo");
	  
	 orderInfo.value = "";
     
	 //遍历所有的子checkbox 实现全选或取消全选功能
	 for(var i=0;i<boxes.length;i++){
		 
		 //获取页面中元素对应的dom对象
		 var obj = boxes[i];
		 //checkObj：代表全选checkbox   checkObj.checked：获取全选的checked属性  true：代表全选checkbox是选中状态 
		 obj.checked = checkObj.checked;
		 
		 if(checkObj.checked){
			//将隐藏输入框中之前的信息 和  现在用户选中的新的商品信息 进行拼接
			 orderInfo.value = orderInfo.value +"#"+obj.value;
		 }
		
	 }
 }
 
 
 //为子  checkbox绑定事件，该函数的作用，用于判断全选是否需要选中
 function checkboxFun(info){
	 
	 //获取所有的子 checkbox 
	 var boxes = document.getElementsByName("box");
     //变量num用于计算选中的个数
	 var num = 0;
	 //遍历所有的子checkbox 如果子checkbox选中的个数  等于 子checkbox的总个数，全选应该被选中
	 for(var i=0;i<boxes.length;i++){
		 if(boxes[i].checked){
			 //选中后 num自增
			 num++;
		 }
	 }
	 
	
	 //控制全选 的状态
	 var boxAll = document.getElementById("checkAll");
	 boxAll.checked = (boxes.length == num);
	 
	
	 //获取所有的子 checkbox 
	 var boxes = document.getElementsByName("box");
	 
	  //将订单相关信息存放在 隐藏输入框中
	 var orderInfo = document.getElementById("orderInfo");
	  
	 orderInfo.value = "";
     
	 //遍历所有的子checkbox 实现全选或取消全选功能
	 for(var i=0;i<boxes.length;i++){
		 
		 //获取页面中元素对应的dom对象
		 var obj = boxes[i];
		
		 
		 if(obj.checked){
			//将隐藏输入框中之前的信息 和  现在用户选中的新的商品信息 进行拼接
			 orderInfo.value = orderInfo.value +"#"+obj.value;
		 }
		
	 }
	 
	 
	 
 }
 
 
 //提交订单
 function submitOrder(){
	 //获取用户选中的checkbox，如果一个都没有选中，则给用户提示
	 //获取所有的子 checkbox 
	 var boxes = document.getElementsByName("box");
	
	 
	 var num = 0;
	 //遍历所有的子checkbox 如果子checkbox选中的个数  等于 子checkbox的总个数，全选应该被选中
	 for(var i=0;i<boxes.length;i++){
		 if(boxes[i].checked){
			 //选中后 num自增
			 num++;
		 }
	 }
	 
	 if(num == 0){
		 alert("请选择需要购买的商品信息！");
	 }else{
		
		 //提交表单
		 document.getElementById("form").submit();
		 
	 }
	 
 }
 
	
</script>

</head>
<body>
	<!-- 横幅导航条开始 -->
	
	<jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>

	<!-- /.navbar -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
				<div>当前位置：我的购物车</div>
				<div class="table-responsive">
					<table class="table table-hover table-bordered table-striped">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll" onclick="checkAll(this)"></th>
								<th style="width: 15%">图片</th>
								<th style="width: 50%">名称</th>
								<th style="width: 10%">价格</th>
								<th style="width: 10%">数量</th>
								<th style="width: 8%">小计</th>
								<th style="width: 7%">操作</th>
							</tr>
						</thead>
						<tbody>
						
						<c:forEach items="${shopCars}" var="shopCar">
						     <tr>
								<td style="vertical-align: middle;"><input type="checkbox"
									 name="box" value="${shopCar.article.id}_${shopCar.buynum}_${shopCar.article.discountPrice* shopCar.buynum}"  onclick="checkboxFun(this.value)"/> <span id="total_1"
									style="display: none;">${shopCar.article.price}</span></td>
								<td><img alt="商品图片"
									src="${pageContext.request.contextPath}/image/article/${shopCar.article.image}"></td>
								<td>${shopCar.article.title}</td>
								<td>
								
								   价格:<span class="${shopCar.article.discount!=0.0 ? 'price':''}">${shopCar.article.price}</span>
								   
								   <c:if test="${shopCar.article.discount != 0.0}">
								        折后价:<span class="discountPrice"><fmt:formatNumber value="${shopCar.article.discountPrice}" pattern="0.00"></fmt:formatNumber></span>
								  </c:if>
								</td>
								<td><span class="glyphicon glyphicon-minus" onclick="minuFun('${shopCar.article.id}')"></span>
									
									<input id="${shopCar.article.id}" value="${shopCar.buynum}"
									style="width: 40px; text-align: center;" onblur="validNum(this,'${shopCar.article.id}')"/> 
									
									<span class="glyphicon glyphicon-plus"
									onclick="addFun('${shopCar.article.id}')"></span>
								</td>
								<td><fmt:formatNumber value="${shopCar.article.discountPrice* shopCar.buynum}" pattern="0.00"></fmt:formatNumber></td>
								<td><a href="${pageContext.request.contextPath}/shopCar/deleteShopCar.action?id=${shopCar.article.id}">删除</a>
									<a>收藏</a></td>
							</tr>
						</c:forEach>
						

					
						</tbody>
					</table>
				</div>
			</div>
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->
        <form action="${pageContext.request.contextPath}/order/orderSubmit.action" method="post" id="form">
            <!-- 通过隐藏输入框 将订单相关信息传递至后台 -->
            <input type="text" name="orderInfo" id="orderInfo"/>
            <div align="right">
			购物车商品总金额:<span id="totalMoney" style="color: red;"><fmt:formatNumber value="${totalPrice}" pattern="0.00"></fmt:formatNumber> 元</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="commitOrder" class="btn btn-danger" type="button" onclick="submitOrder()">
				提交订单 <span class="badge">0</span>
			</button>
		</div>
        </form>
		
		<hr>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>
	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.shopping.entity.Product"%>
<%@ page import="com.shopping.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="productService"
	class="com.shopping.service.impl.ProductServiceImpl" />
<c:set value="static/img" var="prefix" />
<c:set value="${pageContext.request.contextPath}" var="cp" />
<c:set value="${sessionScope.currentUser}" var="user" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<script src="${cp}/static/js/jquery-3.3.1.min.js"></script>
<link href="${cp}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${cp}/static/css/font-awesome.css" rel="stylesheet">
<script src="${cp}/static/js/bootstrap.bundle.min.js"
	type="text/javascript"></script>
<script src="${cp}/static/js/function.js" type="text/javascript"></script>
<!-- <script
	src="https://keycdn.layerjs.org/libs/layerjs/layerjs-0.6.1.min.js"></script>
<link href="https://keycdn.layerjs.org/libs/layerjs/layerjs-0.6.1.css"
	type="text/css" rel="stylesheet" /> -->


<style>
body {
	background: #00b09b;
	background: -webkit-linear-gradient(to right, #00b09b, #96c93d);
	background: linear-gradient(to right, #00b09b, #96c93d);
	min-height: 100vh;
}

.text-gray {
	color: #aaa;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand mx-5" href="#">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end mx-4"
			id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="${cp}/view/shoppingcart.jsp">Shopping
					Cart</a> <a class="nav-item nav-link" href="#"
					onclick="checkWishList()">Wish List</a>
				<c:if test="${user eq null}">
					<a class="nav-item nav-link" href="#" data-toggle="modal"
						data-target="#modalRegisterForm">Sign Up</a>
					<a class="nav-item nav-link" href="#" data-toggle="modal"
						data-target="#modalLoginForm">Sign In</a>
				</c:if>
				<c:if test="${user ne null}">
					<a class="nav-item nav-link" href="#" onclick="signout()">Sign
						Out</a>
					<a class="nav-item nav-link disabled" href="#">Hello, <c:out
							value="${user.userName}" />!
					</a>
				</c:if>
			</div>
		</div>
	</nav>
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-8 mx-auto">
				<ul class="list-group shadow">
					<c:forEach items="${productService.selectAllLaptop()}" var="laptop">
						<c:set value="${laptop.getProductName()}" var="name" />
						<c:set value="${laptop.getDescription()}" var="desc" />
						<c:set value="${laptop.getPrice()}" var="price" />
						<c:set value="${laptop.getImage()}" var="image" />
						<c:set value="${laptop.getProductId()}" var="id" />
						<!-- list group item-->
						<li class="list-group-item">
							<!-- Custom content-->
							<div
								class="media align-items-lg-center flex-column flex-lg-row p-3">
								<div class="media-body order-2 order-lg-1">
									<h5 class="mt-0 font-weight-bold mb-2">${name}</h5>
									<p class="font-italic text-muted mb-0 small">${desc}</p>
									<div
										class="d-flex align-items-center justify-content-between mt-1">
										<h6 class="font-weight-bold my-2">
											<fmt:formatNumber value="${price}" type="currency" />
										</h6>
										<ul class="list-inline medium">
											<li class="list-inline-item m-0"><a href="#"
												onclick="addLaptop(${id})" class="link-color"><i
													class="fas fa-cart-plus text-grey"></i></a></li>
											<li class="list-inline-item m-0"><a href="#"
												onclick="addToWishListFromHome(${id})"><i
													class="fas fa-star"></i></a></li>
										</ul>
									</div>
								</div>
								<img src="${cp}/${prefix}/${image}"
									alt="Generic placeholder image" width="200"
									class="ml-lg-5 order-1 order-lg-2">
							</div> <!-- End -->
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-lg-3 mx-auto checkbox ">
				<form>
					<ul class="list-group shadow">
						<c:forEach items="${productService.selectAllStorage()}"
							var="storage">
							<c:set value="${storage.getProductName()}" var="name" />
							<c:set value="${storage.getPrice()}" var="price" />
							<c:set value="${storage.getImage()}" var="image" />
							<c:set value="${storage.getProductId()}" var="id" />
							<!-- list group item-->
							<li class="list-group-item">
								<!-- Custom content-->
								<div
									class="media align-items-lg-center flex-column flex-lg-row p-2">
									<div class="media-body order-2 order-lg-1">
										<h6 class="mt-0 font-weight-bold mb-2">${name}</h6>
										<h6 class="font-weight-bold">
											<label> <input type="checkbox" name="storageId"
												value="${id}"> <fmt:formatNumber value="${price}"
													type="currency" />
											</label>
										</h6>
									</div>
									<img src="${cp}/${prefix}/${image}"
										alt="Generic placeholder image" width="50"
										class="ml-lg-5 order-1 order-lg-2">
								</div> <!-- End -->
							</li>
						</c:forEach>
					</ul>
					<div
						class="media align-items-lg-center flex-column flex-lg-row p-4 m-2">
						<button type="button" style="margin: auto"
							class="center btn btn-primary btn-md" onclick="addStorage()">Add
							to cart</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">
					<div class="md-form m-2 p-3">
						<i class="fas fa-user"></i> <input type="text" id="signin-user"
							class="form-control validate" placeholder="Your username"
							style="width: 90%; display: inline">
					</div>

					<div class="md-form m-2 p-3">
						<i class="fas fa-lock prefix grey-text"></i> <input
							type="password" id="signin-pass" class="form-control validate"
							placeholder="Your password" style="width: 90%; display: inline">
					</div>

				</div>
				<div class="modal-footer d-flex justify-content-center">
					<button class="btn btn-secondary" onclick="signin()">SIGN
						IN</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalRegisterForm" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Sign up</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">
					<div class="md-form m-2 p-3">
						<i class="fas fa-user"></i> <input type="text" id="signup-user"
							class="form-control validate" placeholder="Your username"
							style="width: 90%; display: inline">
					</div>

					<div class="md-form m-2 p-3">
						<i class="fas fa-lock prefix grey-text"></i> <input
							type="password" id="signup-pass" class="form-control validate"
							placeholder="Your password" style="width: 90%; display: inline">
					</div>

				</div>
				<div class="modal-footer d-flex justify-content-center">
					<button class="btn btn-secondary" onclick="signup()">SIGN
						UP</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.shopping.entity.Product"%>
<%@ page import="com.shopping.entity.User"%>
<%@ page import="com.shopping.entity.CartItem"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="productService"
	class="com.shopping.service.impl.ProductServiceImpl" />
<jsp:useBean id="wishListService"
	class="com.shopping.service.impl.WishListServiceImpl" />
<c:set value="static/img" var="prefix" />
<c:set value="${pageContext.request.contextPath}" var="cp" />
<c:set value="${pageContext.session.id}" var="sid" />
<c:set value="${sessionScope.currentUser}" var="user" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wish List</title>
<script src="${cp}/static/js/jquery-3.3.1.min.js"></script>
<link href="${cp}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${cp}/static/css/font-awesome.css" rel="stylesheet">
<script src="${cp}/static/js/bootstrap.bundle.min.js"></script>
<script src="${cp}/static/js/function.js" type="text/javascript"></script>

<style>
body {
	background: #74ebd5;
    background: -webkit-linear-gradient(to right, #74ebd5, #ACB6E5);
    background: linear-gradient(to right, #74ebd5, #ACB6E5);
    min-height: 100vh;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand mx-5" href="index.jsp">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end mx-4"
			id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="shoppingcart.jsp">Shopping
					Cart</a> <a class="nav-item nav-link active" href="#"
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
	<div>
		<div class="pb-5 m-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col" class="border-0 bg-light">
											<div class="p-2 px-3 text-uppercase">Product</div>
										</th>
										<th scope="col" class="border-0 bg-light text-center">
											<div class="py-2 text-uppercase">Price</div>
										</th>
										<th scope="col" class="border-0 bg-light text-center">
											<div class="py-2 text-uppercase">Quantity</div>
										</th>
										<th scope="col" class="border-0 bg-light text-center">
											<div class="py-2 text-uppercase text-center">Move to
												Cart</div>
										</th>
										<th scope="col" class="border-0 bg-light text-center">
											<div class="py-2 text-uppercase">Remove</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<c:set
										value="${wishListService.selectAllWishListItem(user.id)}"
										var="list" scope="session" />
									<c:forEach items="${list}" var="item">
										<c:set value="${item.product.productId}" var="productId" />
										<c:set value="${productService.selectProductById(productId)}"
											var="product" />
										<c:set value="${product.productName}" var="name" />
										<c:set value="${product.category}" var="category" />
										<c:set value="${product.price}" var="price" />
										<c:set value="${product.image}" var="image" />
										<c:set value="${item.quantity}" var="quantity" />
										<tr>
											<th scope="row" class="border-0">
												<div class="p-2">
													<img src="${cp}/${prefix}/${image}" alt="" width="70"
														class="img-fluid rounded shadow-sm">
													<div class="ml-3 d-inline-block align-middle">
														<h5 class="mb-0">
															<a href="#" class="text-dark d-inline-block align-middle">${name}</a>
														</h5>
														<span
															class="text-muted font-weight-normal font-italic d-block">Category:
															${category}</span>
													</div>
												</div>
											</th>
											<td class="border-0 align-middle text-center"><strong><fmt:formatNumber
														value="${price}" type="currency" /></strong></td>
											<td class="border-0 align-middle text-center"><input
												type="text" id="${productId}" class="form-control"
												style="display: inline; width: 30%"
												placeholder="${quantity}"> <a href="#"
												onclick="refreshWishListItem(${productId})"><i
													class="fas fa-sync-alt"></i></a></td>
											<td class="border-0 text-center align-middle"><a
												href="#" class="text-dark"
												onclick="moveToCart(${productId})"><i
													class="fas fa-cart-plus text-grey"></i></a></td>
											<td class="border-0 align-middle text-center"><a
												href="#" class="text-dark"
												onclick="removeWishListItem(${productId})"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
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
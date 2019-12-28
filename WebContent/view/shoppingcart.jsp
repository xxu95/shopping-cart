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
<jsp:useBean id="cartService"
	class="com.shopping.service.impl.CartServiceImpl" />
<c:set value="static/img" var="prefix" />
<c:set value="${pageContext.request.contextPath}" var="cp" />
<c:set value="${sessionScope.tempCart}" var="tCart" />
<c:set value="${sessionScope.currentUser}" var="user" />
<c:set value="${0}" var="subtotal" />
<c:set value="${10}" var="shipping" />
<c:set value="${subtotal + shipping}" var="total" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<script src="${cp}/static/js/jquery-3.3.1.min.js"></script>
<link href="${cp}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${cp}/static/css/font-awesome.css" rel="stylesheet">
<script src="${cp}/static/js/bootstrap.bundle.min.js"></script>
<script src="${cp}/static/js/function.js" type="text/javascript"></script>

<style>
body {
	background: #eecda3;
	background: -webkit-linear-gradient(to right, #eecda3, #ef629f);
	background: linear-gradient(to right, #eecda3, #ef629f);
	min-height: 100vh;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand mx-5" href="${cp}/view/index.jsp">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end mx-4"
			id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link active"
					href="${cp}/view/shoppingcart.jsp">Shopping Cart</a> <a
					class="nav-item nav-link" href="#" onclick="checkWishList()">Wish
					List</a>
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
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase text-center">Price</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase text-center">Quantity</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase text-center">Move to
												Wishlist</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase text-center">Remove</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${user eq null}">
										<c:forEach items="${tCart}" var="entry">
											<c:set value="${entry.key}" var="productId" />
											<c:set value="${productService.selectProductById(productId)}"
												var="product" />
											<c:set value="${product.productName}" var="name" />
											<c:set value="${product.category}" var="category" />
											<c:set value="${product.price}" var="price" />
											<c:set value="${product.image}" var="image" />
											<c:set value="${entry.value}" var="quantity" />
											<c:set value="${subtotal+price}" var="subtotal" />
											<tr>
												<th scope="row" class="border-0">
													<div class="p-2">
														<img src="${cp}/${prefix}/${image}" alt="" width="70"
															class="img-fluid rounded shadow-sm">
														<div class="ml-3 d-inline-block align-middle">
															<h5 class="mb-0">
																<a href="#"
																	class="text-dark d-inline-block align-middle">${name}</a>
															</h5>
															<span
																class="text-muted font-weight-normal font-italic d-block">Category:
																${category}</span>
														</div>
													</div>
												</th>
												<td class="border-0 text-center align-middle"><strong><fmt:formatNumber
															value="${price}" type="currency" /></strong></td>
												<td class="border-0 text-center align-middle"><input
													type="text" id="${productId}" class="form-control"
													style="display: inline; width: 30%;"
													placeholder="${quantity}"> <a href="#"
													onclick="refreshCartItem(${productId})"><i
														class="fas fa-sync-alt"></i></a></td>
												<td class="border-0 text-center align-middle"><a
													href="#" class="text-dark"
													onclick="addToWishListFromCart(${productId})"><i
														class="fas fa-star"></i></a></td>
												<td class="border-0 text-center align-middle"><a
													href="#" class="text-dark"
													onclick="removeCartItem(${productId})"><i
														class="fa fa-trash"></i></a></td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${user ne null}">
										<c:set value="${cartService.selectAllCartItem(user.id)}"
											var="list" />
										<c:forEach items="${list}" var="cartItem">
											<c:set value="${cartItem.product.productId}" var="productId" />
											<c:set value="${productService.selectProductById(productId)}"
												var="product" />
											<c:set value="${product.productName}" var="name" />
											<c:set value="${product.category}" var="category" />
											<c:set value="${product.price}" var="price" />
											<c:set value="${product.image}" var="image" />
											<c:set value="${cartItem.quantity}" var="quantity" />
											<c:set value="${subtotal+price}" var="subtotal" />
											<tr>
												<th scope="row" class="border-0">
													<div class="p-2">
														<img src="${cp}/${prefix}/${image}" alt="" width="70"
															class="img-fluid rounded shadow-sm">
														<div class="ml-3 d-inline-block align-middle">
															<h5 class="mb-0">
																<a href="#"
																	class="text-dark d-inline-block align-middle">${name}</a>
															</h5>
															<span
																class="text-muted font-weight-normal font-italic d-block">Category:
																${category}</span>
														</div>
													</div>
												</th>
												<td class="border-0 text-center align-middle"><strong><fmt:formatNumber
															value="${price}" type="currency" /></strong></td>
												<td class="border-0 text-center align-middle"><input
													type="text" id="${productId}" class="form-control"
													style="display: inline; width: 30%;"
													placeholder="${quantity}"> <a href="#"
													onclick="refreshCartItem(${productId})"><i
														class="fas fa-sync-alt"></i></a></td>
												<td class="border-0 text-center align-middle"><a
													href="#" class="text-dark"
													onclick="addToWishListFromCart(${productId})"><i
														class="fas fa-star"></i></a></td>
												<td class="border-0 text-center align-middle"><a
													href="#" class="text-dark"
													onclick="removeCartItem(${productId})"><i
														class="fa fa-trash"></i></a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="row py-5 p-4 bg-white rounded shadow-sm">
					<div class="col-lg-6">
						<div
							class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Coupon
							code</div>
						<div class="p-4">
							<p class="font-italic mb-4">If you have a coupon code, please
								enter it in the box below</p>
							<div class="input-group mb-4 border rounded-pill p-2">
								<input type="text" placeholder="Apply coupon"
									aria-describedby="button-addon3" class="form-control border-0">
								<div class="input-group-append border-0">
									<button id="button-addon3" type="button"
										class="btn btn-dark px-4 rounded-pill">
										<i class="fa fa-gift mr-2"></i>Apply coupon
									</button>
								</div>
							</div>
						</div>
						<div
							class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Instructions
							for seller</div>
						<div class="p-4">
							<p class="font-italic mb-4">If you have some information for
								the seller you can leave them in the box below</p>
							<textarea name="" cols="30" rows="2" class="form-control"></textarea>
						</div>
					</div>
					<div class="col-lg-6">
						<div
							class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order
							summary</div>
						<div class="p-4">
							<p class="font-italic mb-4">Shipping and additional costs are
								calculated based on values you have entered.</p>
							<c:set value="${subtotal + shipping}" var="total" />
							<ul class="list-unstyled mb-4">
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">Order Subtotal </strong><strong><fmt:formatNumber value="${subtotal}" type="currency" /></strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">Shipping and handling</strong><strong><fmt:formatNumber value="${shipping}" type="currency" /></strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">Tax</strong><strong>$0.00</strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">Total</strong>
									<h5 class="font-weight-bold"><fmt:formatNumber value="${total}" type="currency" /></h5></li>
							</ul>
							<a href="#" class="btn btn-dark rounded-pill py-2 btn-block">Procceed
								to checkout</a>
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
//get the context path and save it as cp
var pathName = document.location.pathname;
var index = pathName.substr(1).indexOf("/");
var cp = pathName.substr(0,index+1);

// add laptop from product list to shopping cart
function addLaptop(productId) {
	var url = "/AddLaptopServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		success:function(){
			alert("Added to cart!");
		},
		error:function(){
			alert("Failed, please try again!");
		}
	});
}



// add storage from product list to shopping cart
function addStorage() {
// 			$('input:checkbox').each(function() { 
// 			    if ($(this).attr('checked') ==true) { 
// 			    	alert($(this).val());
// 			    } 
// 			});
	var productIds = document.getElementsByName('storageId');
	var url = "/AddStorageServlet";
	var productId = '';
	for (var i = 0; i < productIds.length; i++) {
		if (productIds[i].checked) {
			if (productId != '') {
				productId += ',';
			}
			productId += productIds[i].value;
		}
	}
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		success:function(){
			alert("Added to cart!");
		},
		error:function(){
			alert("Failed, please try again!");
		}
	});
}

// 
function moveToCart(productId) {
	var url = "/MovedToCartServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		success:function(){
			alert("Moved to cart!");
			location.reload(true);
		},
		error:function(){
			alert("Failed, please try again!");
		}
	});
}

// check whether logged in before jumping to wishlist.jsp
function checkWishList() {
	var url = "/CheckLoginServlet";
	
	$.ajax({
		async:false,
		type:"get",
		url:cp+url,
		dataType:"text",
		success:function(result){
			/* The servlet will return true if there's a user
			 and will return false if there's no user*/
			if (result=="true") {
				window.location.assign(cp+"/view/wishlist.jsp");
			} else {
				alert("Sorry, you need to login!");
			}
		}
	});
}

// add product into wish list from home
function addToWishListFromHome(productId) {
	var url = "/AddToWishListFromHomeServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		success:function(result){
			if (result=="true") {
				alert("Added to wish list!");
			} else {
				alert("Sorry, you need to login!");
			}
		}
	});
}

//add product into wish list from cart
function addToWishListFromCart(productId) {
	var url = "/AddToWishListFromCartServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		success:function(result){
			if (result=="true") {
				alert("Moved to wish list!");
			} else {
				alert("Sorry, you need to login!");
			}
			location.reload(true);	
		}
	});
}

// remove item from wish list
function removeWishListItem(productId) {
	var url = "/RemoveWishListServlet";

	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		dataType:"text",
		success:function(){
			alert("Removed from wish list!");
			location.reload(true);
		}
	});
}

// refresh the wish list item quantity
function refreshWishListItem(productId) {
	var url = "/ModifyWishListQuantityServlet";
	var quantity=document.getElementById(productId.toString()).value;
	if (quantity==null) {
		alert("Please enter the quantity");
	} else {
		$.ajax({
			async:false,
			type:"post",
			url:cp+url,
			data:{
				"productId":productId,
				"quantity":quantity
			},
			dataType:"text",
			success:function(){
				alert("Modified!");
				location.reload(true);	
			}
		});
	}
}

function removeCartItem(productId) {
	var url = "/RemoveCartItemServlet";

	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId
		},
		success:function(){
			alert("Removed from cart!");
			location.reload(true);	
		}
	});
}

function refreshCartItem(productId) {
	var url = "/ModifyCartItemQuantityServlet";

	var quantity=document.getElementById(productId.toString()).value;
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"productId":productId,
			"quantity":quantity
		},
		success:function(){
			alert("Modified!");
			location.reload(true);	
		}
	});
}


// sign in
function signin() {
	var userName = document.getElementById('signin-user').value;
	var password = document.getElementById('signin-pass').value;
	var element = document.getElementById("userinfo");
	
	var url = "/SignInServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"userName":userName,
			"password":password
		},
		dataType:"text",
		success:function(data){
			if (data=="false") {
				alert("Invalid username or password, try again!")
			} else {
				$('#modalLoginForm').modal('hide');
				$("#userinfo").html("hello");
				location.reload(true);
			}
		}
	});
}

// sign up
function signup() {
	var userName = document.getElementById('signup-user').value;
	var password = document.getElementById('signup-pass').value;

	var url = "/SignUpServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		data:{
			"userName":userName,
			"password":password
		},
		dataType:"text",
		success:function(){
			$('#modalRegisterForm').modal('hide');
			alert("Sign up successfully!");
			location.reload(true);	
		}
	});
}

// sign out
function signout() {
	var url = "/SignOutServlet";
	
	$.ajax({
		async:false,
		type:"post",
		url:cp+url,
		dataType:"text",
		success:function(){
			alert("Signed out!");
			location.reload(true);	
		}
	});
}
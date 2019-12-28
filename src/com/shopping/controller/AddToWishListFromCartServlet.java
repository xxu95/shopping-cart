package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.entity.CartItem;
import com.shopping.entity.User;
import com.shopping.service.ICartService;
import com.shopping.service.IWishListService;
import com.shopping.service.impl.CartServiceImpl;
import com.shopping.service.impl.WishListServiceImpl;

/**
 * Servlet implementation class AddToWishListServlet
 */
@WebServlet("/AddToWishListFromCartServlet")
public class AddToWishListFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToWishListFromCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check whether logged in
		User user = (User) request.getSession().getAttribute("currentUser");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (user == null) {
			out.print("false");
			out.flush();
			out.close();
			return;
		} else {
			out.print("true");
		}

		// remove from the cart list
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		ICartService cartService = new CartServiceImpl();
		CartItem cartItem = cartService.selectCartItem(productId, user.getId());
		cartService.removeCartItem(productId, user.getId());
		// add into the wish list
		Integer userId = user.getId();
		IWishListService wishListService = new WishListServiceImpl();
		wishListService.addToWishList(productId, userId, cartItem.getQuantity());

		out.flush();
		out.close();
	}

}

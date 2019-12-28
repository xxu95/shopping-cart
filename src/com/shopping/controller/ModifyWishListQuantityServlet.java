package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.entity.User;
import com.shopping.service.IWishListService;
import com.shopping.service.impl.WishListServiceImpl;

/**
 * Servlet implementation class ModifyWishListQuantityServlet
 */
@WebServlet("/ModifyWishListQuantityServlet")
public class ModifyWishListQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyWishListQuantityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		User user = (User) request.getSession().getAttribute("currentUser");
		if (user != null) {
			IWishListService wishListService = new WishListServiceImpl();
			wishListService.modifyCartItemQuantity(productId, user.getId(), quantity);
		}
	}

}

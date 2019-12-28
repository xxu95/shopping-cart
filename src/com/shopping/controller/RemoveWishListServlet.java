package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.entity.User;
import com.shopping.service.IWishListService;
import com.shopping.service.impl.WishListServiceImpl;

/**
 * Servlet implementation class RemoveWishListServlet
 */
@WebServlet("/RemoveWishListServlet")
public class RemoveWishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveWishListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer productId = Integer.parseInt(request.getParameter("productId"));
		HttpSession session = request.getSession();
		IWishListService wishListService = new WishListServiceImpl();
		User user = (User) (session.getAttribute("currentUser"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (user == null) {
			out.print("User has logged out, please sign in again!");
		} else {
			out.print("Removed!");
			wishListService.removeCartItem(productId, user.getId());
		}
	}

}

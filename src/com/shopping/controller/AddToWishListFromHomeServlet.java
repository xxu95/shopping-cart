package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.entity.User;
import com.shopping.service.IWishListService;
import com.shopping.service.impl.WishListServiceImpl;

/**
 * Servlet implementation class AddToWishListServlet
 */
@WebServlet("/AddToWishListFromHomeServlet")
public class AddToWishListFromHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToWishListFromHomeServlet() {
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

		// add into the wish list
		Integer productId = Integer.parseInt(request.getParameter("productId"));

		Integer userId = user.getId();
		IWishListService wishListService = new WishListServiceImpl();
		wishListService.addToWishList(productId, userId, 1);

		out.flush();
		out.close();
	}

}

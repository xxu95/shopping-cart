package com.shopping.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.entity.User;
import com.shopping.service.ICartService;
import com.shopping.service.impl.CartServiceImpl;

/**
 * Servlet implementation class ModifyQuantityServlet
 */
@WebServlet("/ModifyCartItemQuantityServlet")
public class ModifyCartItemQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyCartItemQuantityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		ICartService cartService = new CartServiceImpl();
		User user = (User) session.getAttribute("currentUser");
		if (user == null) {
			Map<Integer, Integer> tempCart = (Map<Integer, Integer>) session.getAttribute("tempCart");
			if (tempCart == null) {
				tempCart = new HashMap<>();
				System.out.println("No map exception!!");
			}
			tempCart.put(productId, quantity);
			session.setAttribute("tempCart", tempCart);
		} else {
			cartService.modifyCartItemQuantity(productId, user.getId(), quantity);
		}

	}

}

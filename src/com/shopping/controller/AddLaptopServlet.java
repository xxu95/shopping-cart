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

import com.shopping.entity.CartItem;
import com.shopping.entity.User;
import com.shopping.service.ICartService;
import com.shopping.service.impl.CartServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/AddLaptopServlet")
public class AddLaptopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLaptopServlet() {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		ICartService cartService = new CartServiceImpl();

		if (user == null) {
			// add to session
			Map<Integer, Integer> tempCart = (Map<Integer, Integer>) session.getAttribute("tempCart");
			if (tempCart == null) {
				tempCart = new HashMap<>();
			}
			tempCart.put(productId, tempCart.getOrDefault(productId, 0) + 1);
			session.setAttribute("tempCart", tempCart);
		} else {
			cartService.addCartItem(productId, user.getId(), 1);
		}
	}
}

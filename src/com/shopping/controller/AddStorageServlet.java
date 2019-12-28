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
 * Servlet implementation class AddStorageServlet
 */
@WebServlet("/AddStorageServlet")
public class AddStorageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStorageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idStr = request.getParameter("productId");
		String[] productIds = idStr.split(",");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");

		if (user == null) {
			// add to session

			Map<Integer, Integer> tempCart = (Map<Integer, Integer>) session.getAttribute("tempCart");
			if (tempCart == null) {
				tempCart = new HashMap<>();
			}
			for (String productStr : productIds) {
				Integer productId = Integer.parseInt(productStr);
				tempCart.put(productId, tempCart.getOrDefault(productId, 0) + 1);
			}
			session.setAttribute("tempCart", tempCart);

		} else {
			ICartService cartService = new CartServiceImpl();
			CartItem cartItem = null;

			for (String productStr : productIds) {
				Integer productId = Integer.parseInt(productStr);
				cartService.addCartItem(productId, user.getId(), 1);
			}

		}
	}

}

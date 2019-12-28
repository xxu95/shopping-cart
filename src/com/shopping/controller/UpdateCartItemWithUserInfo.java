package com.shopping.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.service.ICartService;
import com.shopping.service.impl.CartServiceImpl;

/**
 * Servlet implementation class UpdateCartItemWithUserInfo
 */
@WebServlet("/UpdateCartItemWithUserInfo")
public class UpdateCartItemWithUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCartItemWithUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ICartService cartService = new CartServiceImpl();
		HttpSession session = request.getSession();
		Integer userId = (Integer) (request.getAttribute("userId"));

		/**
		 * Whenever you log in, there are 2 steps you need to do 1. get all the cart
		 * items from the map in the session 2. add thenm to the cart 3. remove cart
		 */
		Map<Integer, Integer> tempCart = (Map<Integer, Integer>) session.getAttribute("tempCart");
		if (tempCart == null) {
			return;
		}
		for (Integer productId : tempCart.keySet()) {
			Integer quantity = tempCart.get(productId);

			cartService.addCartItem(productId, userId, quantity);
		}
		session.removeAttribute("tempCart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

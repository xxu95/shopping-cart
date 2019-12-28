package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.shopping.service.IWishListService;
import com.shopping.service.impl.CartServiceImpl;
import com.shopping.service.impl.WishListServiceImpl;

/**
 * Servlet implementation class MovedToCartServlet
 */
@WebServlet("/MovedToCartServlet")
public class MovedToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovedToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		ICartService cartService = new CartServiceImpl();
		cartService.addCartItem(productId, user.getId(), 1);
		
		IWishListService wishListService = new WishListServiceImpl();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("Removed!");
		wishListService.removeCartItem(productId, user.getId());
	}

}

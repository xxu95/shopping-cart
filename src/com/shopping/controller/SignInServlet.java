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
import com.shopping.service.IUserService;
import com.shopping.service.impl.UserServiceImpl;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		IUserService userServiceImpl = new UserServiceImpl();
		User user = userServiceImpl.checkUser(userName, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);

			// update all cart item info
			request.setAttribute("userId", user.getId());
			request.getRequestDispatcher("UpdateCartItemWithUserInfo").forward(request, response);
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("false");
			out.flush();
			out.close();
		}
	}

}

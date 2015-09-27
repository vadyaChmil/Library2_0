package com.rhcloud.vadyazakusylo.library.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.library.entity.User;

public class LoginServlet extends HttpServletLibrary {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String nickname = request.getParameter(USER_LOGIN);
			String password = request.getParameter(USER_PASSWORD);
			User user = userDao.getUser(nickname);
			if (user == null || !user.getPassword().equals(password)) {
				String message = "Unable to log in. Please check that you have entered your login and password correctly.";
				request.setAttribute(MESSAGE, message);
				request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
			} else if (user.isRegistered() != true) {
				String message = "Your account is on approval.";
				request.setAttribute(MESSAGE, message);
				request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
			} else {
				request.getSession().setAttribute(REGISTERED, true);
				request.getSession().setAttribute(USER, user);
				request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			}
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}

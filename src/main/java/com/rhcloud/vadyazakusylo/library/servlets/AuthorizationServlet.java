package com.rhcloud.vadyazakusylo.library.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.library.entity.User;

public class AuthorizationServlet extends HttpServletLibrary {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String login = request.getParameter("login");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User newUser = new User(login, name, surname, password, email);

			List<User> userList = userDao.getUserList();
			for (User user : userList) {
				if (user.getLogin().equals(login)) {
					String message = "\"" + login + "\" already exists, please choose another login.";
					request.setAttribute(MESSAGE, message);
					request.setAttribute(USER, newUser);
					request.getRequestDispatcher(AUTHORIZATION_PAGE).forward(request, response);
				}
			}
			userDao.addUser(newUser);
			String message = "You have created your account.";
			request.setAttribute(MESSAGE, message);
			request.getRequestDispatcher(RESULT_PAGE).forward(request, response);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}

package com.rhcloud.vadyazakusylo.library.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.library.entity.Genre;

public class AddBookServlet extends HttpServletLibrary {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Genre> genreList = genreDao.getGenreList();
			request.setAttribute(GENRE_LIST, genreList);
			request.getRequestDispatcher(ADD_BOOK_PAGE).forward(request, response);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}

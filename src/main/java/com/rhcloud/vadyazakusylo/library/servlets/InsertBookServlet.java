package com.rhcloud.vadyazakusylo.library.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.library.entity.Book;

public class InsertBookServlet extends HttpServletLibrary {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int code = Integer.valueOf(request.getParameter("code"));
			String autor = request.getParameter("author");
			String title = request.getParameter("title");
			int year_edition = Integer.valueOf(request.getParameter("year_edition"));
			int pages = Integer.valueOf(request.getParameter("pages"));
			Book book = new Book(code, autor, title, year_edition, pages);

			int[] genreId = null;
			if (request.getParameterValues(GENRE) != null) {
				String[] genres = request.getParameterValues(GENRE);
				genreId = new int[genres.length];
				for (int index = 0; index < genreId.length; index++) {
					genreId[index] = Integer.valueOf(genres[index]);
				}
			}
			bookDao.addBook(book, genreId);

			String message = "Book have loaded to the library";
			request.setAttribute(MESSAGE, message);
			request.getRequestDispatcher(INSERT_BOOK_PAGE).forward(request, response);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}

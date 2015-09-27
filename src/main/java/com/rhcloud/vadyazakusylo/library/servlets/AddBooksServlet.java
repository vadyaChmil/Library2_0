package com.rhcloud.vadyazakusylo.library.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.library.entity.Book;
import com.rhcloud.vadyazakusylo.library.entity.Genre;
import com.rhcloud.vadyazakusylo.library.exception.DataFormatException;
import com.rhcloud.vadyazakusylo.library.exception.DataSizeException;
import com.rhcloud.vadyazakusylo.library.exception.UploadException;

public class AddBooksServlet extends HttpServletLibrary {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Book> uploadBookList = bookBrowser.uploadBooks(request, response);
			request.getSession().setAttribute(UPLOAD_BOOK_LIST, uploadBookList);
			List<Genre> genreList = genreDao.getGenreList();
			request.setAttribute(GENRE_LIST, genreList);
			request.getRequestDispatcher(ADD_BOOKS_PAGE).forward(request, response);
		} catch (DataFormatException | UploadException | DataSizeException e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}

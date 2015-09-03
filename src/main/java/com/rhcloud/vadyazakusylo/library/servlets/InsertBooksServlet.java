package com.rhcloud.vadyazakusylo.library.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.library.entity.Book;

public class InsertBooksServlet extends HttpServletLibrary {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if (request.getParameterValues("bookCode") == null) {
				String message = "You haven't chosen books for loading";
				request.setAttribute(MESSAGE, message);
				request.getRequestDispatcher(INSERT_BOOKS_PAGE).forward(request, response);
			} else {
				String[] bookCodeString = request.getParameterValues("bookCode");
				int[] bookCodeInt = new int[bookCodeString.length];
				for (int index = 0; index < bookCodeString.length; index++) {
					bookCodeInt[index] = Integer.valueOf(bookCodeString[index]);
				}

				@SuppressWarnings("unchecked")
				List<Book> uploadBookList = (List<Book>) request.getSession().getAttribute(UPLOAD_BOOK_LIST);
				List<Book> booksList = new ArrayList<>();
				List<int[]> genreIdList = new ArrayList<>();
				for (int code : bookCodeInt) {
					for (Book book : uploadBookList) {
						if (book.getCode() == code) {
							booksList.add(book);
							
							int[] genreId = null;
							if (request.getParameterValues(Integer.toString(code)) != null) {
								String[] genre = request.getParameterValues(Integer.toString(code));
								genreId = new int[genre.length];
								for (int index = 0; index < genreId.length; index++) {
									genreId[index] = Integer.valueOf(genre[index]);
								}
							}
							genreIdList.add(genreId);	
						}
					}
				}
				bookDao.addBookList(booksList, genreIdList);
				
				String message = "Chosen books have loaded to the library";
				// delete uploadedBooksList from memory of session
				uploadBookList = new ArrayList<>();
				request.getSession().setAttribute(UPLOAD_BOOK_LIST, uploadBookList);
				request.setAttribute(MESSAGE, message);
				request.getRequestDispatcher(INSERT_BOOKS_PAGE).forward(request, response);
			}
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}

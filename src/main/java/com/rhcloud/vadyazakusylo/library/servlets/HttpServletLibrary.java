package com.rhcloud.vadyazakusylo.library.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.rhcloud.vadyazakusylo.library.dao.BookDao;
import com.rhcloud.vadyazakusylo.library.dao.GenreDao;
import com.rhcloud.vadyazakusylo.library.dao_sql.BookBrowser;

@Controller
public abstract class HttpServletLibrary extends HttpServlet {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	public static final String BOOK_LIST = "bookList";
	public static final String UPLOAD_BOOK_LIST = "uploadBookList";
	public static final String GENRE = "genre";
	public static final String GENRE_LIST ="genreList";
	public static final String MESSAGE = "message";
	public static final String ERROR_MESSAGE = "errorMessage";

	public static final String LIBRARY_PAGE = "library.jsp";
	public static final String SELECT_BOOKS_PAGE = "select_books.jsp";
	public static final String INSERT_BOOKS_PAGE = "insert_books.jsp";
	public static final String ADD_BOOK_PAGE = "add_book.jsp";
	public static final String INSERT_BOOK_PAGE = "insert_book.jsp";
	public static final String ERROR_PAGE = "error.jsp";

	@Autowired
	BookDao bookDao;

	@Autowired
	GenreDao genreDao;

	@Autowired
	BookBrowser bookBrowser;

	@Override
	public void init(ServletConfig config) throws javax.servlet.ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}

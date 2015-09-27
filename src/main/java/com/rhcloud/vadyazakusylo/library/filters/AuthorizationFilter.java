package com.rhcloud.vadyazakusylo.library.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.rhcloud.vadyazakusylo.library.entity.User;

public class AuthorizationFilter implements Filter {

	private List<String> pathFilters = Arrays
			.asList(new String[] { "upload.jsp", "add_book.jsp", "add_book", "add_books.jsp", "add_books" });

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		doFilter((HttpServletRequest) request, (HttpServletResponse) response, filterChain);

	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		String uri = request.getRequestURI();
		String path = StringUtils.substringAfterLast(uri, "/");
		if (!pathFilters.contains(path)) {
			filterChain.doFilter(request, response);
			return;
		}

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			filterChain.doFilter(request, response);
			return;
		}

		String message = "Don't try to get around the system :)";
		request.setAttribute("message", message);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
	}

}

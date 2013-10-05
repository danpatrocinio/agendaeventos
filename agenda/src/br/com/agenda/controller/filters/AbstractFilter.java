package br.com.agenda.controller.filters;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AbstractFilter {

	public AbstractFilter() {
		super();
	}

	protected void accessDenied(ServletRequest request, ServletResponse response,
	        HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/accessDenied.xhtml");
		rd.forward(request, response);
	}

	protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req)
	        throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/index.xhtml");
		rd.forward(request, response);
	}

}

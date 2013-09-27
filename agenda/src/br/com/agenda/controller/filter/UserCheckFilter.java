package br.com.agenda.controller.filter;

import java.io.IOException;

import javax.servlet.*;

public class UserCheckFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("detroy...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

		System.out.println("filter working...");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Init...");
	}
}
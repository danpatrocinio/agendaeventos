package br.com.agenda.controller.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.agenda.entity.Pessoa;

public class LoginCheckFilter extends AbstractFilter implements Filter {

	public final static String AGENDA_CURRENT_USER = "agendaCurrentUser";

	private static List<String> allowedURIs;

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.isNew()) {
			doLogin(request, response, req);
			return;
		}

		Pessoa p = (Pessoa) session.getAttribute(LoginCheckFilter.AGENDA_CURRENT_USER);

		if (p == null && !LoginCheckFilter.allowedURIs.contains(req.getRequestURI())) {
			System.out.println("URL: " + req.getRequestURI());
			doLogin(request, response, req);
			return;
		}
		System.out.println(p != null ? p.getNome() : "null" + " está logado...");

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		if (LoginCheckFilter.allowedURIs == null) {
			LoginCheckFilter.allowedURIs = new ArrayList<String>();
			LoginCheckFilter.allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
			LoginCheckFilter.allowedURIs.add("/agenda/index.xhtml");
			LoginCheckFilter.allowedURIs.add("/agenda/home.xhtml");
			LoginCheckFilter.allowedURIs.add("/agenda/rest/pessoas/count");
			LoginCheckFilter.allowedURIs.add("/agenda/bootstrap/css/style.css");
			LoginCheckFilter.allowedURIs.add("/agenda/bootstrap/css/bootstrap.css");
			LoginCheckFilter.allowedURIs.add("/agenda/WEB-INF/reports/agendas.jasper");
			LoginCheckFilter.allowedURIs.add("/agenda/WEB-INF/reports/pessoas.jasper");
			LoginCheckFilter.allowedURIs.add("/agenda/rest/pessoas/bootstrap/css/bootstrap.css");
			LoginCheckFilter.allowedURIs.add("/agenda/bootstrap/img/glyphicons-halflings.png");
			LoginCheckFilter.allowedURIs.add("/agenda/javax.faces.resource/theme.css.xhtml");
			LoginCheckFilter.allowedURIs.add("/agenda/javax.faces.resource/primefaces.css.xhtml");
			LoginCheckFilter.allowedURIs.add("/agenda/javax.faces.resource/primefaces.js.xhtml");
			LoginCheckFilter.allowedURIs.add("/agenda/bootstrap/js/bootstrap.min.js");
			LoginCheckFilter.allowedURIs.add("/agenda/javax.faces.resource/jquery/jquery.js.xhtml");
			LoginCheckFilter.allowedURIs
			        .add("/agenda/javax.faces.resource/images/ui-icons_333333_256x240.png.xhtml");
			LoginCheckFilter.allowedURIs
			        .add("/agenda/javax.faces.resource/jquery/jquery-plugins.js.xhtml");
			LoginCheckFilter.allowedURIs
			        .add("/agenda/bootstrap/img/glyphicons-halflings-white.png");
		}
	}

}
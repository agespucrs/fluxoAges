package br.ages.crud.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private static final String[] URLS_TO_EXCLUDE = {".css", ".js", ".jpg", ".png", ".gif","login.jsp" };

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Filtro de Login Finalizado " + new Date());
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);

		/*
		 * if (httpRequest.getRequestURI().endsWith("login.jsp")){
		 * System.out.println("É uma jsp: " + httpRequest.getRequestURI()); }
		 * else { System.out.println("NÃO é uma JSP: " +
		 * httpRequest.getRequestURL()); }
		 */

		String uri = httpRequest.getRequestURI();
		
		// trocado pelo método isURLToExclusao(uri) (!uri.endsWith("login.jsp") && !uri.endsWith(".css") 
	
		if (!isURLToExclusao(uri, httpRequest)) {
			HttpSession session = httpRequest.getSession();
			if (session.getAttribute("usuario") == null) {
				request.setAttribute("msgErro", "Acesso negado! Você precisa logar primeiro");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filtro de Login Inicializado " + new Date());
	}
	
	private boolean isURLToExclusao(String uri, HttpServletRequest request) {
		boolean retorno = false;
		String acao = request.getParameter("acao");

		for (String url : URLS_TO_EXCLUDE) {
			if(uri != null && uri.endsWith(url)){
				retorno = true; 
			}
			
			if(uri != null && uri.endsWith("main") 
					&& (acao != null 
					&& acao.equals("login"))){
				retorno = true;
			}
		}
		return retorno;
	}
	

}

package br.ages.crud.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Teste
 */
@WebServlet("/Teste")
public class Teste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    StringBuilder sb = new StringBuilder();
    Enumeration e = request.getHeaderNames();
    while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        String value = request.getHeader(name);
        sb.append("name: "+name + " = " + value + "<br>" );
    }

    sb.append("getServletInfo: " + getServletInfo() + "<br>" );
    sb.append("getServletName: " + getServletName() + "<br>" );
    sb.append("getContextPath: " + request.getContextPath() + "<br>" );
    sb.append("getLocalAddr: " + request.getLocalAddr() + "<br>" );
    sb.append("getLocalName: " + request.getLocalName()+ "<br>" );
    sb.append("getPathInfo: " + request.getPathInfo()+ "<br>" );
    sb.append("getServletPath: " + request.getServletPath()+ "<br>" );
    sb.append("getParameterNames: " + request.getParameterNames()+ "<br>" );
    
    StringBuffer requestURL = request.getRequestURL();
    if (request.getQueryString() != null) {
    		sb.append("getQueryString: " + request.getQueryString() + "<br>");
        requestURL.append("?").append(request.getQueryString());
    }
    sb.append("completeURL: " + requestURL.toString()+ "<br>" );
    
    out.print(sb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

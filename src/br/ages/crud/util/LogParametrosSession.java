package br.ages.crud.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogParametrosSession {

	public static void logParametros(HttpServletRequest request) throws ServletException, IOException {
		// Set response content type
		
		Enumeration paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			System.out.println("Nome Parametro: " + paramName );
			String[] paramValues = request.getParameterValues(paramName);
			// Read single valued data
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					System.out.println("Sem Valor");
				else
					System.out.println("valor: " + paramValue);
			} else {
				// Read multiple valued data
					for (int i = 0; i < paramValues.length; i++) {
						System.out.println("valor("+i+"): "  + paramValues[i]);
				}
			}
		}
		}
}

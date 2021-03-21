package org.schlibbuz.sa723.web.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.schlibbuz.sa723.web.components.ComponentType;
import org.schlibbuz.sa723.web.components.factory.SimpleComponentFactory;


public class HtmlEncapsulationFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		
		if (request.getRequestURL().toString().endsWith("/") || request.getRequestURL().toString().endsWith(".do")) {
            System.out.println("html-header needed");
            res.getWriter().println(new SimpleComponentFactory().createComponent(ComponentType.HEADER).readAsString());
        }
		
        chain.doFilter(req, res);

        HttpServletResponse response = (HttpServletResponse) res;
        if (response.getContentType().startsWith("text/html")) {
            System.out.println("html-footer needed");
            try(PrintWriter out = response.getWriter()) {
                out.println(new SimpleComponentFactory().createComponent(ComponentType.FOOTER).readAsString());
            }
        }

	}

	public void init(FilterConfig config) throws ServletException {
		
	}

	public void destroy() {

	}
}

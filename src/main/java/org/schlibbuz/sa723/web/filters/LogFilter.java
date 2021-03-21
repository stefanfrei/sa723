package org.schlibbuz.sa723.web.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		
		
		// Log the IP address, current timestamp and requested url.
		System.out.println(
            "IP "+ request.getRemoteAddr() + ", Time " 
			+ new Date().toString()
            + " requested URL -> "
            + request.getRequestURL()
        );
		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

	public void destroy() {

	}
}

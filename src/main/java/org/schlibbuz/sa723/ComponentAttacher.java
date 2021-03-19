/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefan
 */
@WebFilter("/filterisbuggy")
public class ComponentAttacher implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, wrapper);
        System.out.println("Filter passed");

        PrintWriter responseWriter = response.getWriter();

        if (wrapper.getContentType().startsWith("text/html")) {
            CharArrayWriter charWriter = new CharArrayWriter();
            String originalContent = wrapper.toString();
            System.out.println(originalContent);

            int indexOfCloseBodyTag = originalContent.indexOf("</body>") - 1;

            charWriter.write(originalContent.substring(0, indexOfCloseBodyTag));

            String copyrightInfo = "<p>&copy Schlibbuz.org</p>";
            String closeHTMLTags = "</body></html>";

            charWriter.write(copyrightInfo);
            charWriter.write(closeHTMLTags);

            String alteredContent = charWriter.toString();
            System.out.println(alteredContent);
            response.setContentLength(alteredContent.length());
            responseWriter.write(alteredContent);
        }

    }

    @Override
    public void destroy() {

    }

}

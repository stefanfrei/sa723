/*
 * The MIT License
 * Copyright © 2014-2021 Stefan Frei
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package org.schlibbuz.sa723.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.schlibbuz.sa723.web.components.ComponentType;
import org.schlibbuz.sa723.web.components.factory.ComponentFactory;




/**
 *
 * @author Stefan
 */
public class WelcomeServlet extends HttpServlet {

    static final long serialVersionUID = 42L;

    private static final Logger w = LogManager.getLogger(WelcomeServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        w.trace("entering Welcome-Servlet with method -> " + request.getMethod());
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()) {
            ServletContext ctx = getServletContext();
            ComponentFactory fax = (ComponentFactory)ctx.getAttribute("template.factory");

            out.println(fax.createComponent(ComponentType.HEADER).getData());
            out.println(fax.createComponent(ComponentType.SANDBOX).getData());
            
            try {
                // get jndi-context
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                // Look up our data source
                DataSource ds = (DataSource) envCtx.lookup("jdbc/Sandbox");

                // Allocate and use a connection from the pool
                try (
                    Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement("select * from article");
                    ResultSet rs = ps.executeQuery()
                ) {
                    while(rs.next()) {
                        out.println(rs.getString("title"));
                    }
                } catch (SQLException e) {
                    out.println(e.getMessage());
                }

            } catch (NamingException e) {
                out.println(e.getMessage());
            }

            out.println(fax.createComponent(ComponentType.FOOTER).getData());
        }

        w.trace("Exiting WelcomeServlet");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

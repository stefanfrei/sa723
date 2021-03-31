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

package org.schlibbuz.sa723.web.listeners;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.schlibbuz.sa723.tools.PropsLoader;
import org.schlibbuz.sa723.web.components.factory.ComponentFactory;
import org.schlibbuz.sa723.web.components.factory.SimpleComponentFactory;



public class AppInitListener implements ServletContextListener {


    private static final Logger w = LogManager.getLogger(AppInitListener.class);

    private ComponentFactory fax;



    // Webapp startup-hook
    @Override
    public void contextInitialized(ServletContextEvent event) {

        w.trace("initializing ...");

        ServletContext ctx = event.getServletContext();

        // String appRoot = System.getProperty("catalina.base") + "/webapps/ROOT";

        ctx.setAttribute(
            "app.props",
            PropsLoader.loadProps()
        );

        try {
            ctx.setAttribute(
                "app.db",
                (DataSource) (
                    (Context) new InitialContext().lookup("java:comp/env")
                ).lookup("jdbc/Sandbox")
            );
        } catch(NamingException e) {
            w.error(e.getMessage());
        }

        // need instance to manage cleanup
        fax = SimpleComponentFactory.getInstance();
        ctx.setAttribute("app.template.factory", fax);

        ctx.setAttribute("app.sql.dateformat", "yyyy-MM-dd");

        w.trace("initialized!");

    }


    // Webapp shutdown-hook
    @Override
    public void contextDestroyed(ServletContextEvent event) {

        w.trace("destroying ...");
        fax.cleanup();
        w.trace("destroyed!");

    }

}

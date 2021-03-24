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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.schlibbuz.sa723.web.components.factory.CachedComponentFactory;
import org.schlibbuz.sa723.web.components.factory.ComponentFactory;



public class AppInitListener implements ServletContextListener {


    private ComponentFactory fax;



    // Webapp startup-hook
    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext ctx = event.getServletContext();

        String appRoot = System.getProperty("catalina.base") + "/webapps/ROOT";

        Properties props = loadProps(appRoot);
        ctx.setAttribute("app.props", props);

        fax = CachedComponentFactory.getInstance(props);
        ctx.setAttribute("template.factory", fax);

    }


    // Webapp shutdown-hook
    @Override
    public void contextDestroyed(ServletContextEvent event) {

        System.out.println("ServletContextListener destroyed");
        fax.cleanup();

    }



    private Properties loadProps(String appRoot) {

        try(InputStream input = new FileInputStream("ROOT/WEB-INF/app.props")) {

            Properties props = new Properties();
            props.load(input);
            props.setProperty("app.root", appRoot);
            props.forEach((key, val) -> {
                System.out.println(key + " -> " + val);
            });
            return props;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}

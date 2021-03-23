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

/**
 *
 * @author Stefan
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitListener
               implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }

        //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        String appPath = System.getProperty("catalina.base") + "/webapps/ROOT";

        System.setProperty("sandbox.app.root", appPath);
        System.setProperty("sandbox.app.charset", "UTF-8");
        System.setProperty("sandbox.app.templates.folder", appPath + "/WEB-INF/templates");
        System.setProperty("sandbox.app.templates.suffix", ".html");
    }
}

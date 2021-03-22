/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

        System.setProperty(
            "sandbox.app.templates.folder",
            appPath + "/WEB-INF/templates"
        );

        System.setProperty("sandbox.app.charset", "UTF-8");

        System.setProperty("sandbox.app.templates.suffix", ".html");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723;

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
        System.out.println("ServletContextListener started");
        System.setProperty(
            "sandbox.app.root",
            new StringBuilder(System.getProperty("catalina.base")).append("/webapps/ROOT").toString()
        );
        System.out.println(System.getProperty("sandbox.app.root"));
    }
}

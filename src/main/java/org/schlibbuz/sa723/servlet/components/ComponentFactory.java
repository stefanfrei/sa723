/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723.servlet.components;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stefan
 */
public class ComponentFactory {

    public static String getComponentToString(String filename) {
        try {
            return FileUtils.readFileToString(new File(
                    new StringBuilder(System.getProperty("sandbox.app.root")).append("/WEB-INF/templates/").append(filename).toString()
            ), "UTF-8");
        } catch(IOException e) {
            System.err.println(new StringBuilder("File error -> ").append(e.getMessage()));
        }
        return "";
    }
}

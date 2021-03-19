/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723.servlet.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class Header {

    private static final List<String> footer = new ArrayList<>(
            Arrays.asList(
                    "<!DOCTYPE html>",
                    "<html>",
                    "<head>",
                    "<title>Sandbox − Home</title>",
                    "<link rel=\"stylesheet\" href=\"/css/base.css\">",
                    "</head>",
                    "<body>",
                    "<div class=\"container\">",
                    "<section id=\"header\">",
                    new StringBuilder("<h1>Home@").append("localhost").append("</h1>").toString(),
                    "</section>",
                    "<section id=\"body\">"
            )
    );

    public static List<String> getHeaderAsList() {
        return footer;
    }

}

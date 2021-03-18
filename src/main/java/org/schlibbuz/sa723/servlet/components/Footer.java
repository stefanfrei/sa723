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
public class Footer {

    private static final List<String> footer = new ArrayList<>(
            Arrays.asList(
                    "<section id=\"footer\">",
                    "<div>",
                    "Footer",
                    "</div>",
                    "</section>"
            )
    );

    public static List<String> getFooterAsList() {
        return footer;
    }

}

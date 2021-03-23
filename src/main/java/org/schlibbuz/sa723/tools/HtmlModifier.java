/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stefan
 */
public class HtmlModifier {

    /*
    *  Just outputs formatted html to console which can be used in servlets.
    */
    void htmlToSevletWriterCode(String filename) {
        try {
            List<String> lines = FileUtils.readLines(new File(filename), "utf8");

            for (String line : lines) {
                System.out.println(
                        encapsulateLine(
                                escapeDoubleQuote(line)
                        )
                );
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    String escapeDoubleQuote(final String line) {
        return line.replace("\"", "\\\"");
    }

    String encapsulateLine(final String line) {
        return new StringBuilder("out.writeln(\"").append(line).append("\");").toString();
    }
}

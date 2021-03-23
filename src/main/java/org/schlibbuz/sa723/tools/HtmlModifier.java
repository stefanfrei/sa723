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

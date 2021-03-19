/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Stefan
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter writer;

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        writer = new CharArrayWriter();
    }

    public PrintWriter getWriter() {
        return new PrintWriter(writer);
    }

    public String toString() {
        return writer.toString();
    }

}

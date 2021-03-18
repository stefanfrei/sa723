/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723.tools;

/**
 *
 * @author Stefan
 */
public class ToolRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HtmlModifier mod = new HtmlModifier();
        mod.htmlToSevletWriterCode("data/test.html");
    }

}

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


import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 *
 * @author Stefan
 */
public class HtmlModifierTest {

    public HtmlModifierTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }


    /**
     * Test of htmlToSevletWriter method, of class HtmlModifier.
     */
    @DisplayName("Testing escapeDoubleQuotes (passing case)")
    @ParameterizedTest
    @MethodSource("testEscapeDoubleQuotesShouldPassData")
    public void testEscapeDoubleQuotesShouldPass(final String source, final String expected) {
        HtmlModifier instance = new HtmlModifier();
        assertEquals(expected, instance.escapeDoubleQuote(source));
    }
    private static Stream<Arguments> testEscapeDoubleQuotesShouldPassData() {
        return Stream.of(
                arguments("<html>", "<html>"),
                arguments("<div class=\"example\">", "<div class=\\\"example\\\">"),
                arguments("<div class='example'>", "<div class='example'>")
        );
    }


    @DisplayName("Testing escapeDoubleQuotes (failing case)")
    @ParameterizedTest
    @MethodSource("testEscapeDoubleQuotesShouldFailData")
    public void testEscapeDoubleQuotesShouldFail(final String source, final String expected) {
        HtmlModifier instance = new HtmlModifier();
        assertNotEquals(expected, instance.escapeDoubleQuote(source));
    }

    private static Stream<Arguments> testEscapeDoubleQuotesShouldFailData() {
        return Stream.of(
                arguments("<html>", "<htmlx>"),
                arguments("<div class=\"example\">", "<div class=\\\"examplex\\\">"),
                arguments("<div class='example'>", "<div class='examplex'>")
        );
    }


    @DisplayName("Testing encapsulateLine (passing case)")
    @ParameterizedTest
    @MethodSource("testEncapsulateLineShouldPassData")
    public void testEncapsulateLineShouldPass(final String source, final String expected) {
        HtmlModifier instance = new HtmlModifier();
        assertEquals(expected, instance.encapsulateLine(source));
    }

    private static Stream<Arguments> testEncapsulateLineShouldPassData() {
        return Stream.of(
                arguments("<body>", "out.writeln(\"<body>\");"),
                arguments("<div class=\"blaa\">", "out.writeln(\"<div class=\"blaa\">\");"),
                arguments("<table class=\"abc def\" id=\"raw\" attr=\"xyz\">", "out.writeln(\"<table class=\"abc def\" id=\"raw\" attr=\"xyz\">\");")
        );
    }

    @DisplayName("Testing encapsulateLine (failing case)")
    @ParameterizedTest
    @MethodSource("testEncapsulateLineShouldFailData")
    public void testEncapsulateLineShouldFail(final String source, final String expected) {
        HtmlModifier instance = new HtmlModifier();
        assertNotEquals(expected, instance.encapsulateLine(source));
    }

    private static Stream<Arguments> testEncapsulateLineShouldFailData() {
        return Stream.of(
                arguments("<body>", "out.writeln(\"<body\");"),
                arguments("<div class=\"blaa\">", "out.writeln(\"<div clas=\"blaa\">\");"),
                arguments("<table class=\"abc def\" id=\"raw\" attr=\"xyz\">", "out.writeln(\"<table class=\"abc def\" id=\"raw\" attr=\"xyz\">\")")
        );
    }

}

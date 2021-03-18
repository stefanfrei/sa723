/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.sa723.tools;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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

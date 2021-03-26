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

package org.schlibbuz.sa723.web.components;


import java.nio.charset.Charset;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class BasicComponent extends AComponent {


    private static final Logger w = LogManager.getLogger(BasicComponent.class);

    private final ComponentType componentType;
    private final Charset encoding;

    private String data;

    public BasicComponent(ComponentType componentType, String data, Charset encoding) {
        this.componentType = componentType;
        this.encoding = encoding;
        this.data = data;
    }


    @Override
    public ComponentType getComponentType() {
        return componentType;
    }


    @Override
    public Charset getEncoding() {
        return encoding;
    }


    @Override
    public String getData() {
        w.trace("Component created");
        return data;
    }

}

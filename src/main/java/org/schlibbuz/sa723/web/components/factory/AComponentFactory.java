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

package org.schlibbuz.sa723.web.components.factory;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.schlibbuz.sa723.tools.PropsLoader;
import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;



abstract class AComponentFactory implements ComponentFactory {


    final Properties props;
    final Charset encoding;
    final String templatesFolder;
    final String templatesSuffix;


    protected AComponentFactory() {
        props = PropsLoader.loadProps();
        encoding = Charset.forName(props.getProperty("app.encoding"));
        templatesFolder = props.getProperty("app.templates.folder");
        templatesSuffix = props.getProperty("app.templates.suffix");
    }


    public Component createComponent(final ComponentType componentType) {
        return null;
    }


    public Component createComponent(final ComponentType componentType, List<String> params) {
        return null;
    }


    public void cleanup() {
        this.cleanup(
            Long.valueOf(
                props.getProperty("app.cleanup.maxwait"),
                10 //radix
            )
        );
    }


    public void cleanup(long maxWait) {

    }

}

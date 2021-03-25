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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.schlibbuz.sa723.web.components.BasicComponent;
import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class SimpleComponentFactory extends AComponentFactory {


    private static SimpleComponentFactory instance;
    // Constructor part
    private SimpleComponentFactory() {
        super();
    }


    // get factory via this method
    public static ComponentFactory getInstance() {
        if(instance == null) {
            instance = new SimpleComponentFactory();
        }
        return instance;
    }
    // Constructor part end


    @Override
    public Component createComponent(ComponentType componentType) {
        File file = new File(
            templatesFolder
            + "/"
            + componentType.getName()
            + templatesSuffix // access to root-dir would be ugly
        );
        try {
            return new BasicComponent(
                componentType,
                FileUtils.readFileToString(file, encoding),
                encoding
            );
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    @Override
    public Component createComponent(ComponentType componentType, List<String> params) {
        // TODO Auto-generated method stub
        return null;
    }

}

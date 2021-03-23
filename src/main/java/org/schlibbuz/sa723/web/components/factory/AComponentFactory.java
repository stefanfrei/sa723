package org.schlibbuz.sa723.web.components.factory;

import java.nio.charset.Charset;
import java.util.List;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

abstract class AComponentFactory implements ComponentFactory {


    static AComponentFactory instance;

    static final Charset CHARSET = Charset.forName(System.getProperty("sandbox.app.charset"));
    static final String TEMPLATES_FOLDER = System.getProperty("sandbox.app.templates.folder");
    static final String TEMPLATES_SUFFIX = System.getProperty("sandbox.app.templates.suffix");


    public Component createComponent(final ComponentType componentType) {
        return null;
    }

    public Component createComponent(final ComponentType componentType, List<String> params) {
        return null;
    }
}

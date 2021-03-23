package org.schlibbuz.sa723.web.components.factory;


import java.nio.charset.Charset;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;


public class BasicComponent implements Component {
    private final ComponentType componentType;
    private final Charset encoding;

    private String data;

    public BasicComponent(ComponentType componentType, String data) {
        this.componentType = componentType;
        encoding = Charset.forName(System.getProperty("sandbox.app.encoding"));
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
        return data;
    }

}

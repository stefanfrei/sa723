package org.schlibbuz.sa723.web.components;


import java.nio.charset.Charset;



public class BasicComponent extends AComponent {


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

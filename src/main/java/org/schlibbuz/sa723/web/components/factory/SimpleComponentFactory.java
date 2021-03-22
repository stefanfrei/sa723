package org.schlibbuz.sa723.web.components.factory;

import java.util.List;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class SimpleComponentFactory implements ComponentFactory {


    private static SimpleComponentFactory instance = null;

    private SimpleComponentFactory() {}

    public static SimpleComponentFactory getInstance() {
        if(instance == null) {
            instance = new SimpleComponentFactory();
        }
        return instance;
    }

    @Override
    public Component createComponent(ComponentType componentType) {
        return new BasicComponent(componentType);
    }

    @Override
    public Component createComponent(ComponentType componentType, List<String> params) {
        // TODO Auto-generated method stub
        return null;
    }

}

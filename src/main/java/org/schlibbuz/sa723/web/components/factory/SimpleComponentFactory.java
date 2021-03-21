package org.schlibbuz.sa723.web.components.factory;

import java.util.List;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class SimpleComponentFactory implements ComponentFactory {

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

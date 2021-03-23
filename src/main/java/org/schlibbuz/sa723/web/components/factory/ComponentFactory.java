package org.schlibbuz.sa723.web.components.factory;


import java.util.List;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;


public interface ComponentFactory {
    public Component createComponent(final ComponentType componentType);
    public Component createComponent(final ComponentType componentType, List<String> params);
}

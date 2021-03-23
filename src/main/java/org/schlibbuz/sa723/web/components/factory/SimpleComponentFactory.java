package org.schlibbuz.sa723.web.components.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.schlibbuz.sa723.web.components.BasicComponent;
import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class SimpleComponentFactory extends AComponentFactory {


    // Constructor part
    private SimpleComponentFactory() {}


    // get factory via this method
    public static SimpleComponentFactory getInstance() {
        if(instance == null) {
            instance = new SimpleComponentFactory();
        }
        return (SimpleComponentFactory)instance; // Smells, try to avoid hardcast.
    }
    // Constructor part end


    @Override
    public Component createComponent(ComponentType componentType) {
        File file = new File(
            TEMPLATES_FOLDER + "/" + componentType.getName() + TEMPLATES_SUFFIX // access to root-dir would be ugly
        );
        try {
            return new BasicComponent(
                componentType,
                FileUtils.readFileToString(file, CHARSET)
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

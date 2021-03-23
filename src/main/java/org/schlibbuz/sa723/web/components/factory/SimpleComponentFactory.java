package org.schlibbuz.sa723.web.components.factory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class SimpleComponentFactory implements ComponentFactory {


    private static SimpleComponentFactory instance = null;

    private static final Charset CHARSET = Charset.forName(System.getProperty("sandbox.app.charset"));
    private static final String TEMPLATES_FOLDER = System.getProperty("sandbox.app.templates.folder");
    private static final String TEMPLATES_SUFFIX = System.getProperty("sandbox.app.templates.suffix");


    // Constructor part
    private SimpleComponentFactory() {}


    // get factory via this method
    public static SimpleComponentFactory getInstance() {
        if(instance == null) {
            instance = new SimpleComponentFactory();
        }
        return instance;
    }
    // Constructor part end


    @Override
    public Component createComponent(ComponentType componentType) {
        File file = new File(
            TEMPLATES_FOLDER + "/" + TEMPLATES_SUFFIX
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

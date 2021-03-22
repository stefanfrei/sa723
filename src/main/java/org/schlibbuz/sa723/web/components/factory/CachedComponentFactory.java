package org.schlibbuz.sa723.web.components.factory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class CachedComponentFactory implements ComponentFactory {


    private static CachedComponentFactory instance = null;
    private final DirectoryObserver directoryObserver;

    private CachedComponentFactory() {
        directoryObserver = initCompFax();
        directoryObserver.processEvents();
    }

    private static DirectoryObserver initCompFax() {
        try {
            return new DirectoryObserver(
                Paths.get(
                    System.getProperty("sandbox.app.templates.folder")
                )
            );
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CachedComponentFactory getInstance() {
        if(instance == null) {
            instance = new CachedComponentFactory();
        }
        return instance;
    }


    @Override
    public Component createComponent(ComponentType componentType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component createComponent(ComponentType componentType, List<String> params) {
        // TODO Auto-generated method stub
        return null;
    }

}

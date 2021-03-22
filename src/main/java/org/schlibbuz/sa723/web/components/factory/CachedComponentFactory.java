package org.schlibbuz.sa723.web.components.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class CachedComponentFactory implements ComponentFactory {


    private static CachedComponentFactory instance = null;
    private List<String> changedFiles;
    private Timer timer;
    FileSystemPoll fsp;

    private CachedComponentFactory() {
        changedFiles = new ArrayList<>();
        timer = new Timer();
        fsp = new FileSystemPoll();
        timer.scheduleAtFixedRate(fsp, 10000, 5000);
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

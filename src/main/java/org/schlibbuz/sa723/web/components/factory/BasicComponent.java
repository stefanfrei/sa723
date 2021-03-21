package org.schlibbuz.sa723.web.components.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;


public class BasicComponent implements Component {
    private final ComponentType componentType;
    private final String encoding;
    private final String absoluteFile;

    public BasicComponent(final ComponentType componentType) {
        this.componentType = componentType;
        encoding = "UTF-8";
        absoluteFile = System.getProperty("sandbox.app.templates.folder")
                     + "/" + this.componentType.toString()
                     + System.getProperty("sandbox.app.templates.suffix");
    }

    private File getFile() {
        return new File(absoluteFile);
    }

    @Override
    public String readAsString() {
        try {
            return FileUtils.readFileToString(getFile(), encoding);
        } catch(IOException | java.nio.charset.UnsupportedCharsetException e) {
            System.err.println("File error -> " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> readAsList() {
        try {
            return FileUtils.readLines(getFile(), encoding);
        } catch(IOException | java.nio.charset.UnsupportedCharsetException e) {
            System.err.println("File error -> " + e.getMessage());
        }
        return null;
    }
    
}

package org.schlibbuz.sa723.web.components.factory;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class CachedComponentFactory implements ComponentFactory {


    private static CachedComponentFactory instance = null;
    private static final String templateDir = System.getProperty("sandbox.app.templates.folder");

    private final DirectoryObserver directoryObserver;
    private final Map<String, String> templateCache;

    // Constructor part
    private CachedComponentFactory() {
        directoryObserver = initCompFax();
        directoryObserver.processEvents();
        templateCache = new HashMap<>();
        initTemplateCache();
    }

    // used in constructor to support final var
    private static DirectoryObserver initCompFax() {
        try {
            return new DirectoryObserver(
                Paths.get(templateDir)
            );
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // get factory via this method
    public static CachedComponentFactory getInstance() {
        if(instance == null) {
            instance = new CachedComponentFactory();
        }
        return instance;
    }
    // Constructor Part end


    private void initTemplateCache() {

        String basePath = CachedComponentFactory.templateDir;

        try (Stream<Path> walk = Files.walk(
            Paths.get(basePath)
        )) {

            List<String> fileNames = walk.filter(Files::isRegularFile).map(
                filename -> filename.toString()
            ).collect(Collectors.toList());

            fileNames.forEach(filename -> {
                System.out.println(filename);
                String relativeFile = filename.replace(basePath, ""); // rel filename as hash-key
                System.out.println(relativeFile);
                try {
                    String fileContent = FileUtils.readFileToString(
                        new File(filename),
                        System.getProperty("sandbox.app.charset")
                    );
                    templateCache.put(relativeFile, fileContent);
                } catch(IOException e) {
                    System.err.println(e.getMessage());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
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

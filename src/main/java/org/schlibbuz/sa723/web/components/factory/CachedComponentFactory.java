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
import org.schlibbuz.sa723.web.components.BasicComponent;
import org.schlibbuz.sa723.web.components.Component;
import org.schlibbuz.sa723.web.components.ComponentType;

public final class CachedComponentFactory extends AComponentFactory {


    private final DirectoryObserver directoryObserver;
    private final Map<String, Component> templateCache;

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
                Paths.get(TEMPLATES_FOLDER)
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
        return (CachedComponentFactory)instance; // Smells, try to avoid hardcast.
    }
    // Constructor Part end


    private void initTemplateCache() {
        System.out.println("Initializing template-cache...");
        String basePath = TEMPLATES_FOLDER;

        try (Stream<Path> walk = Files.walk(
            Paths.get(basePath)
        )) {

            List<String> fileNames = walk.filter(Files::isRegularFile).map(
                filename -> filename.toString()
            ).collect(Collectors.toList());

            fileNames.forEach(filename -> {
                String hashKey = getHashKeyFromFileName(filename);
                System.out.print("adding file " + filename + " with key -> " + hashKey + "...");
                try {
                    String fileContent = FileUtils.readFileToString(
                        new File(filename),
                        CHARSET
                    );
                    templateCache.put(
                        hashKey,
                        new BasicComponent(
                            ComponentType.fromName(hashKey),
                            fileContent
                        )
                    );
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("done!");
            });
            printCache();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private void printCache() {
        System.out.println("Cache populated!");
        templateCache.forEach(
            (key, value)
                -> System.out.println(key + " = " + value));
        System.out.println("-------------------");
    }


    private String getHashKeyFromFileName(final String filename) {

        if (filename.startsWith(TEMPLATES_FOLDER) && filename.endsWith(TEMPLATES_SUFFIX)) {
            int startIndex = TEMPLATES_FOLDER.length();
            int length = filename.length() - (TEMPLATES_FOLDER.length() + TEMPLATES_SUFFIX.length());
            return filename.substring(startIndex, length).toLowerCase();
        }
        return null;
    }


    @Override
    public Component createComponent(final ComponentType componentType) {
        return templateCache.get(componentType.getName());
    }


    @Override
    public Component createComponent(ComponentType componentType, List<String> params) {
        // TODO Auto-generated method stub
        return null;
    }


    public static void shutdown() {
        // ? how
    }

}

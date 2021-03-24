/*
 * The MIT License
 * Copyright © 2014-2021 Stefan Frei
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
        super();
        directoryObserver = initCompFax(templatesFolder);
        directoryObserver.processEvents();
        templateCache = new HashMap<>();
        initTemplateCache();
    }


    // used in constructor to support final var
    private static DirectoryObserver initCompFax(final String templatesFolder) {
        try {
            return new DirectoryObserver(
                Paths.get(templatesFolder)
            );
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    // get factory via this method
    public static ComponentFactory getInstance() {
        if(instance == null) {
            instance = new CachedComponentFactory();
        }
        return instance;
    }
    // Constructor Part end


    private void initTemplateCache() {
        System.out.println("Initializing template-cache...");
        String basePath = templatesFolder;

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
                        charset
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

        if (filename.startsWith(templatesFolder) && filename.endsWith(templatesSuffix)) {
            int startIndex = templatesFolder.length();
            int length = filename.length() - (templatesFolder.length() + templatesSuffix.length());
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
        return null; // implement!
    }


    @Override
    public void cleanup() {
        directoryObserver.disableProcessEvents();
    }


    @Override
    public void cleanup(long maxWait) {

    }

}

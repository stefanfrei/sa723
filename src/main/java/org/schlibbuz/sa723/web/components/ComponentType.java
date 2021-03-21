package org.schlibbuz.sa723.web.components;

public enum ComponentType {
    // types
    FOOTER("footer"),
    HEADER("header"),
    SANDBOX("sandbox");
    // end

    String name;


    ComponentType(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

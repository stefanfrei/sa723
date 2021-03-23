package org.schlibbuz.sa723.web.components;


public enum ComponentType {
    // types
    ADMIN_FOOTER("admin/footer"),
    ADMIN_HEADER("admin/header"),
    ADMIN_SANDBOX("admin/sandbox"),
    FOOTER("footer"),
    HEADER("header"),
    SANDBOX("sandbox");
    // end


    private final String name;


    ComponentType(String name) {
        this.name = name;
    }
    

    public String getName() {
        return name;
    }


    public static ComponentType fromName(String name) {
        for(ComponentType t : values()) {
            if(t.name.equals(name)) return t;
        }
        return null;
    }
}

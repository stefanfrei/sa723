package org.schlibbuz.sa723.web.components;

import java.nio.charset.Charset;

public interface Component {
    public ComponentType getComponentType();
    public Charset getEncoding();
    public String getData();
}

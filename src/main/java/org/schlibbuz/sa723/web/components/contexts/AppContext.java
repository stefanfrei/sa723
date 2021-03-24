package org.schlibbuz.sa723.web.components.contexts;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class AppContext implements AutoCloseable {

    private static ThreadLocal<AppContext> instance = new ThreadLocal<>();

    private HttpServletRequest request;
    @SuppressWarnings("unused")
    private HttpServletResponse response;

    private AppContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static AppContext create(HttpServletRequest request, HttpServletResponse response) {
        AppContext context = new AppContext(request, response);
        instance.set(context);
        return context;
    }

    public static AppContext getCurrentInstance() {
        return instance.get();
    }

    @Override    
    public void close() {
        instance.remove();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public ServletContext getServletContext() {
        return request.getServletContext();
    }

}

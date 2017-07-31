package com.vbashur.actuator;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String ROOT_SERVLET = "proof";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = createWebApplicationContext(servletContext);
        configureSpringMvc(servletContext, context);

    }


    private AnnotationConfigWebApplicationContext createWebApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class);
        context.setAllowBeanDefinitionOverriding(false);
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("charEncoding", "UTF-8");
        servletContext.addFilter("characterEncodingFilter", createCharacterEncodingFilter());
        context.setServletContext(servletContext);

        return context;
    }

    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext context) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(ROOT_SERVLET, new DispatcherServlet(context));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }

    private CharacterEncodingFilter createCharacterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        return filter;
    }

}

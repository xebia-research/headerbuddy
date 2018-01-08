package com.xebia.headerbuddy;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                ignoreAcceptHeader(true).
                favorParameter(true).
                parameterName("output").
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        //Log files registry
        String logPath = "classpath:/Logs/";
        registry.addResourceHandler("Debug-Log.log")
                .addResourceLocations(logPath);
        registry.addResourceHandler("Error-Warn-Log.log")
                .addResourceLocations(logPath);
        registry.addResourceHandler("Info-Log.log")
                .addResourceLocations(logPath);
        registry.addResourceHandler("Trace-Log.log")
                .addResourceLocations(logPath);
    }
}

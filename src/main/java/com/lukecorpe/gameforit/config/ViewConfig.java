package com.lukecorpe.gameforit.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;


@org.springframework.context.annotation.Configuration
public class ViewConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("classpath:/templates/");
        Properties properties = new Properties();
        properties.setProperty("auto_include", "/common/container.ftl");
        freeMarkerConfigurer.setFreemarkerSettings(properties);
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }
}

package com.lukecorpe.gameforit.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({Config.class})
public class SiteApplicationConfig extends SpringBootServletInitializer{
    
    public static void main(String[] args) {
        SpringApplication.run(SiteApplicationConfig.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SiteApplicationConfig.class);
    }
}

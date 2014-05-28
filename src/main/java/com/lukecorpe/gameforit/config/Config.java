package com.lukecorpe.gameforit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.lukecorpe.gameforit"})
@Import({ViewConfig.class})
public class Config {
}

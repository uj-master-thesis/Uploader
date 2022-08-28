package com.edu.uj.uploader.uploader.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfiguration {

    @Bean
    ApplicationStop applicationStop(ApplicationContext applicationContext) {
        return new ApplicationCloser(applicationContext);
    }
}

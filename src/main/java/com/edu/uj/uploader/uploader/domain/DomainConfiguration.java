package com.edu.uj.uploader.uploader.domain;

import com.edu.uj.uploader.uploader.domain.processing.ConcurrentProcessor;
import com.edu.uj.uploader.uploader.domain.processing.Processor;
import com.edu.uj.uploader.uploader.domain.processing.PublishingProcessor;
import com.edu.uj.uploader.uploader.utils.ApplicationStop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    Processor processor(ApplicationStop applicationStop){
        return new ConcurrentProcessor(new PublishingProcessor(), applicationStop);
    }


}

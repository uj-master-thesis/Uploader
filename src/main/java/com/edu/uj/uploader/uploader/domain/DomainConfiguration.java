package com.edu.uj.uploader.uploader.domain;

import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.domain.event.EventPublisher;
import com.edu.uj.uploader.uploader.domain.event.Publisher;
import com.edu.uj.uploader.uploader.domain.observers.EventObserver;
import com.edu.uj.uploader.uploader.domain.observers.ObserversBinder;
import com.edu.uj.uploader.uploader.domain.processing.ConcurrentProcessor;
import com.edu.uj.uploader.uploader.domain.processing.Processor;
import com.edu.uj.uploader.uploader.domain.processing.PublishingProcessor;
import com.edu.uj.uploader.uploader.kafka.NewsBusCommunication;
import com.edu.uj.uploader.uploader.utils.ApplicationStop;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class DomainConfiguration {

    @Bean
    Publisher publisher() {
        return new EventPublisher();
    }

    @Bean("publishingProcessor")
    Processor processor(Publisher publisher) {
        return new PublishingProcessor(publisher);
    }

    @Bean("concurrentProcessor")
    Processor processor(@Qualifier("publishingProcessor") Processor publishingProcessor, ApplicationStop applicationStop) {
        return new ConcurrentProcessor(publishingProcessor, applicationStop);
    }

    @Bean(initMethod = "bind")
    ObserversBinder wireObservers(Publisher publisher, NewsBusCommunication newsBusCommunication) {
        List<EventObserver<? extends Event>> observers = new LinkedList<>();
        observers.addAll(newsBusCommunication.eventsObservers());
        return new ObserversBinder(publisher, observers);
    }

//    AddThreadEventObserver addThreadEventObserver(@Qualifier("concurrentProcessor") Processor processor){
//        return new AddThreadEventObserver(processor);
//    }
}

package com.edu.uj.uploader.uploader.domain;

import com.edu.uj.uploader.uploader.domain.commands.CommandContext;
import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.domain.event.EventPublisher;
import com.edu.uj.uploader.uploader.domain.event.Publisher;
import com.edu.uj.uploader.uploader.domain.observers.EventObserver;
import com.edu.uj.uploader.uploader.domain.observers.ObserversBeans;
import com.edu.uj.uploader.uploader.domain.observers.ObserversBinder;
import com.edu.uj.uploader.uploader.domain.processing.ConcurrentProcessor;
import com.edu.uj.uploader.uploader.domain.processing.Processor;
import com.edu.uj.uploader.uploader.domain.processing.PublishingProcessor;
import com.edu.uj.uploader.uploader.email.EmailService;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.persistence.datastore.DatabaseTypesConverter;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;
import com.edu.uj.uploader.uploader.persistence.repositories.DbSubscribedUserDao;
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

    @Bean
    NewsDataConverter nodeDataConverter() {
        return new NewsDataConverter();
    }

    @Bean
    ObserversBeans databaseObservers(Datastore datastore, EmailService emailService) {
        return new ObserversBeans(datastore, emailService);
    }

    @Bean(initMethod = "bind")
    ObserversBinder wireObservers(Publisher publisher, ObserversBeans observersBeans) {
        List<EventObserver<? extends Event>> observers = new LinkedList<>();
        observers.addAll(observersBeans.dbObservers());
        return new ObserversBinder(publisher, observers);
    }

    @Bean
    Datastore datastore(DbSubscribedUserDao dbSubscribedUserDao) {
        return new Datastore(dbSubscribedUserDao, new DatabaseTypesConverter());
    }

    @Bean
    CommandContext commandContext(OutboundMessageHandler outboundMessageHandler, Datastore datastore) {
        return new CommandContext(outboundMessageHandler, datastore);
    }
}

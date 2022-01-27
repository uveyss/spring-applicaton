package com.lns.n11loanapplication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Configuration
@Slf4j
public class ConsumerConfig {
  /*  @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ObjectProvider<ConsumerFactory<Object, Object>> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, (ConsumerFactory<Object, Object>) kafkaConsumerFactory);
        factory.setRetryTemplate(retryTemplate());
        factory.setRecoveryCallback((context -> {
            if(context.getLastThrowable().getCause() instanceof RecoverableDataAccessException){
                //here you can do your recovery mechanism where you can put back on to the topic using a Kafka producer
            } else{
                // here you can log things and throw some custom exception that Error handler will take care of ..
                throw new RuntimeException(context.getLastThrowable().getMessage());
            }
            return null;
        }));
        factory.setErrorHandler(((exception, data) -> {
           /* here you can do you custom handling, I am just logging it same as default Error handler does
          If you just want to log. you need not configure the error handler here. The default handler does it for you.
          Generally, you will persist the failed records to DB for tracking the failed records.
            log.error("Error in process with Exception {} and the record is {}", exception, data);
        }));
        return factory;
    }
    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        /* here retry policy is used to set the number of attempts to retry and what exceptions you wanted to try and what you don't want to retry.
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        return retryTemplate;
    }
    private SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        exceptionMap.put(IllegalArgumentException.class, false);
        exceptionMap.put(TimeoutException.class, true);
        return new SimpleRetryPolicy(3,exceptionMap,true);
    }*/
}
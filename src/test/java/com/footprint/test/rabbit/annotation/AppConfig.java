package com.footprint.test.rabbit.annotation;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class AppConfig {

    private RabbitProperties props = new RabbitProperties();

    //private final String[] adminUris = { "http://host1:15672", "http://host2:15672" };

    //private final String[] nodes = { "rabbit@host1", "rabbit@host2" };

    @Bean
    public ConnectionFactory defaultConnectionFactory() {
        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setAddresses(this.props.getAddresses());
        cf.setUsername(this.props.getUsername());
        cf.setPassword(this.props.getPassword());
        cf.setVirtualHost(this.props.getVirtualHost());
        return cf;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(defaultConnectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
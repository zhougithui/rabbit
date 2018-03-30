package com.footprint.rabbit.consumer;

import org.springframework.amqp.support.ConsumerTagStrategy;

public class DefaultConsumerTagStrategy implements ConsumerTagStrategy {

    @Override
    public String createConsumerTag(String queue) {
        return queue + System.currentTimeMillis();
    }
}

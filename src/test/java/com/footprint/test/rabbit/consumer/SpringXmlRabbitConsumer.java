package com.footprint.test.rabbit.consumer;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlRabbitConsumer {
    public static void main(final String[] args) throws Exception {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-rabbit-consumer.xml");
        ctx.registerShutdownHook();

        System.in.read();
        ctx.close();
    }
}

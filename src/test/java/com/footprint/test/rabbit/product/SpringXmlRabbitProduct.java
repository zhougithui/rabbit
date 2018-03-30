package com.footprint.test.rabbit.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlRabbitProduct {
    public static void main(final String[] args) throws Exception {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-rabbit-publisher.xml");
        ctx.registerShutdownHook();

        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        template.convertAndSend("Hello, world!");
        System.in.read();
        ctx.close();
    }
}

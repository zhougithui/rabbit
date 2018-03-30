package com.footprint.test.rabbit.product;

import com.footprint.rabbit.vo.OrderMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.Executors;

public class SpringXmlRabbitProduct2 {
    public static void main(final String[] args) throws Exception {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-rabbit-publisher2.xml");
        ctx.registerShutdownHook();

        RabbitTemplate template = ctx.getBean("amqpTemplate", RabbitTemplate.class);
        Executors.newFixedThreadPool(10).submit(() -> {

            int i = 10000;
            while(i > 0){
                OrderMessage msg = new OrderMessage();
                msg.setAmount(BigDecimal.valueOf(10.1* i + 1));
                msg.setCustomerName("zmy" + i);
                msg.setOrderTime(new Date());
                msg.setRequestNo(System.currentTimeMillis() + "");
                template.convertAndSend(msg);
                i--;
            }
        });
        System.in.read();
        ctx.close();
    }
}

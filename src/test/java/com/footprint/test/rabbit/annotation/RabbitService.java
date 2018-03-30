package com.footprint.test.rabbit.annotation;

import com.footprint.rabbit.vo.OrderMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {

    /**
     * 队列和交换机会自动创建
     * @param order
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "myQueue", durable = "true"),
                    exchange = @Exchange(value = "myExchange", ignoreDeclarationExceptions = "true"),
                    key = "foo.*"
            )
    )
    public void processOrder(OrderMessage order){

    }


    /**
     * 匿名队列
     * @param invoice
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "auto.exch"),
            key = "invoiceRoutingKey")
    )
    public void processInvoice(Object invoice) {

    }

    /**
     * 绑定queue到默认的交换机
     * @param data
     * @return
     */
    @RabbitListener(queuesToDeclare = @Queue(name = "${my.queue}", durable = "true"))
    public String handleWithSimpleDeclare(String data) {
        return null;
    }

    /**
     * 设置参数
     *  x-message-ttl：消息过期时间
     *  x-match：接受消息的条件匹配方式
     *      all：表示全部条件匹配才接受消息
     *      any：表示只要一个条件匹配就接受消息
     * @param foo
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "auto.headers", autoDelete = "true",
                    arguments = @Argument(name = "x-message-ttl", value = "10000",
                            type = "java.lang.Integer")),
            exchange = @Exchange(value = "auto.headers", type = ExchangeTypes.HEADERS, autoDelete = "true"),
            arguments = {
                    @Argument(name = "x-match", value = "all"),
                    @Argument(name = "foo", value = "bar"),
                    @Argument(name = "baz")
            })
    )
    public String handleWithHeadersExchange(String foo) {
        return null;
    }
}

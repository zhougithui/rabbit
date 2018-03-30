package com.footprint.rabbit.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class DefaultReturnCallBack implements RabbitTemplate.ReturnCallback {
    private static final Logger logger = LoggerFactory.getLogger(DefaultReturnCallBack.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("收到return callback-message:").append(message)
                .append("replyCode").append(replyCode)
                .append("replyText").append(replyText)
                .append("exchange").append(exchange)
                .append("routingKey").append(routingKey);
        logger.error(sb.toString());
    }
}

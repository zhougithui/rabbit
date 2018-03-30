package com.footprint.rabbit.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class DefaultConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(DefaultConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        StringBuilder sb = new StringBuilder();
        sb.append("收到confirm callback：")
                .append("data").append(correlationData)
                .append("ack").append(ack)
                .append("cause").append(cause);
        logger.error(sb.toString());
    }
}

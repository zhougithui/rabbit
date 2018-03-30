package com.footprint.rabbit.consumer;

import com.footprint.utils.GsonUtils;
import com.rabbitmq.client.Channel;
import com.footprint.rabbit.consumer.listener.OrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;

import java.io.IOException;

public class DefaultMessageListener extends AbstractAdaptableMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(DefaultMessageListener.class);

    @Override
    public void onMessage(Message message, Channel channel) {
        logger.info("received message：" + message.toString());
        try {
            OrderListener listener = new OrderListener();
            listener.onMessage(
                    GsonUtils.buildGson().fromJson(new String(message.getBody(), "UTF-8"), listener.getEntityClass()),
                    message.getMessageProperties()
            );

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            logger.info("消息队列确认消息失败", e);
            try {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                logger.error("消息队列拒绝消息失败", e);
            }
        }
    }

}
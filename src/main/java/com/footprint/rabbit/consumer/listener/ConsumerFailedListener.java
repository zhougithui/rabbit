package com.footprint.rabbit.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Several other events are published at various stages of the container lifecycle:
 *      AsyncConsumerStartedEvent (when the consumer is started)
 *      AsyncConsumerRestartedEvent (when the consumer is restarted after a failure - SimpleMessageListenerContainer only)
 *      AsyncConsumerTerminatedEvent (when a consumer is stopped normally)
 *      AsyncConsumerStoppedEvent (when the consumer is stopped - SimpleMessageListenerContainer only)
 *      ConsumeOkEvent (when a consumeOk is received from the broker, contains the queue name and consumerTag)
 *      ListenerContainerIdleEvent (see the section called “Detecting Idle Asynchronous Consumers”)
 *
 */
public class ConsumerFailedListener implements SmartApplicationListener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerFailedListener.class);

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ListenerContainerConsumerFailedEvent.class == eventType;
    }

    @Override
    public boolean supportsSourceType(@Nullable Class<?> sourceType) {
        if(Objects.isNull(sourceType)){
            return true;
        }
        if(sourceType.isAssignableFrom(AbstractMessageListenerContainer.class)){
            return true;
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.error("消费失败事件：", event.getSource());
        ListenerContainerConsumerFailedEvent consumerFailedEvent = (ListenerContainerConsumerFailedEvent) event;
        logger.error(consumerFailedEvent.toString());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

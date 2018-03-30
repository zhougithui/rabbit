package com.footprint.rabbit.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractListener<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public AbstractListener(){
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
        }
    }

    /**
     * 接受消息抽象方法
     * @param message
     * @param messageProperties
     */
    public abstract void onMessage(T message, MessageProperties messageProperties);
}

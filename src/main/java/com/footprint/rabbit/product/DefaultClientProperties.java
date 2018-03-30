package com.footprint.rabbit.product;

import com.rabbitmq.client.impl.AMQConnection;

import java.util.Map;

public class DefaultClientProperties {

    public Map<String, Object> defaultClientProperties(){
        Map<String, Object> props = AMQConnection.defaultClientProperties();
        props.put("specialPro", "specialzmy");
        return props;
    }
}

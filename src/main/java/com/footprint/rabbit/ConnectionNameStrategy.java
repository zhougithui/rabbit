package com.footprint.rabbit;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import java.util.Random;

/**
 * @author hui.zhou 14:03 2018/1/5
 */
public class ConnectionNameStrategy implements org.springframework.amqp.rabbit.connection.ConnectionNameStrategy {

    private CachingConnectionFactory connectionFactory;
    private String connName;


    public void setConnectionFactory(CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public String obtainNewConnectionName(ConnectionFactory connectionFactory) {
        return this.connName;
    }

    public void setConnName(String connName) {
        this.connName = connName + new Random(10).nextInt();
    }
}

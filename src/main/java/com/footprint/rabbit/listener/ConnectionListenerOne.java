package com.footprint.rabbit.listener;

import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

public class ConnectionListenerOne implements ConnectionListener {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionListenerOne.class);

    @Override
    public void onCreate(Connection connection) {
        logger.info("connection create.....");
    }

    @Override
    public void onClose(Connection connection) {
        logger.info("connection close.....");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        logger.info("connection shutdown.....");
    }
}

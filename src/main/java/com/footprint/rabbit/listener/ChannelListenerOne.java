package com.footprint.rabbit.listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;

public class ChannelListenerOne implements ChannelListener {
    private static final Logger logger = LoggerFactory.getLogger(ChannelListenerOne.class);

    @Override
    public void onCreate(Channel channel, boolean transactional) {
        logger.info("create channel....");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        logger.info("close channel....");
    }
}

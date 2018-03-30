package com.footprint.rabbit.listener;

import com.rabbitmq.client.Recoverable;
import com.rabbitmq.client.RecoveryListener;

public class RecoverListenerOne implements RecoveryListener {

    @Override
    public void handleRecovery(Recoverable recoverable) {

    }

    @Override
    public void handleRecoveryStarted(Recoverable recoverable) {

    }
}

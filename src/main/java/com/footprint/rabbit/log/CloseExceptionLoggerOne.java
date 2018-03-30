package com.footprint.rabbit.log;

import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.logging.Log;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.amqp.support.ConditionalExceptionLogger;

/**
 * 记录日志规则
 * The CachingConnectionFactory uses a default strategy to log channel closures as follows:
 * 1、Normal channel closes (200 OK) are not logged.
 * 2、If a channel is closed due to a failed passive queue declaration, it is logged at debug level.
 * 3、If a channel is closed because the basic.consume is refused due to an exclusive consumer condition, it is logged at INFO level.
 * 4、All others are logged at ERROR level.
 */
public class CloseExceptionLoggerOne implements ConditionalExceptionLogger {

    @Override
    public void log(Log logger, String message, Throwable t) {
        if (t instanceof ShutdownSignalException) {
            ShutdownSignalException cause = (ShutdownSignalException) t;
            if (RabbitUtils.isPassiveDeclarationChannelClose(cause)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(message + ": " + cause.getMessage());
                }
            }
            else if (RabbitUtils.isExclusiveUseChannelClose(cause)) {
                if (logger.isInfoEnabled()) {
                    logger.info(message + ": " + cause.getMessage());
                }
            }
            else if (!RabbitUtils.isNormalChannelClose(cause)) {
                logger.error(message + ": " + cause.getMessage());
            }
        }
        else {
            logger.error("Unexpected invocation of " + this.getClass() + ", with message: " + message, t);
        }
    }
}

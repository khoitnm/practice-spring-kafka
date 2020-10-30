package org.tnmk.pro02transaction.pro02multipleproducers.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class LogHelper {
    private static final Logger logger = LoggerFactory.getLogger(LogHelper.class);

    public static void logResult(ListenableFuture<SendResult<String, String>> listenableFuture) {
        try {
            SendResult<String, String> sendResult = listenableFuture.get();
            logger.info("[KAFKA PUBLISHER] sent success " + sendResult.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("[KAFKA PUBLISHER] sent fail " + e.getMessage(), e);
        }
    }
}

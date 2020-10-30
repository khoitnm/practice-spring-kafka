package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.producer.ProducerWithTransactionalAnnotation;
import org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.storageservice.MessageAssertionService;

@Service
public class Initiation {

    @Autowired
    private ProducerWithTransactionalAnnotation producerWithTransactionalAnnotation;

    @Autowired
    private MessageAssertionService messageAssertionService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        sendSuccessMessages();
        sendFailMessages();
    }

    private void sendSuccessMessages() {
        String successMessage = "TransactionalAnnotation_Success_" + System.nanoTime();
        producerWithTransactionalAnnotation.sendMultiTopicsSuccessfully(successMessage);
        messageAssertionService.assertExistMessage(successMessage);
    }

    private void sendFailMessages() {
        String failMessage = "TransactionalAnnotation_Fail_" + System.nanoTime();
        try {
            producerWithTransactionalAnnotation.sendMultiTopicsFailAndRollback(failMessage);
        } finally {
            messageAssertionService.assertExistMessage(failMessage);
        }
    }
}

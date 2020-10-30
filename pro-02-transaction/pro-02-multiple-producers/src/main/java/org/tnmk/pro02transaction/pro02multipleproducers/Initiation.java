package org.tnmk.pro02transaction.pro02multipleproducers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro02transaction.pro02multipleproducers.producer.ProducerWithLocalTransaction;
import org.tnmk.pro02transaction.pro02multipleproducers.producer.ProducerWithPureApacheKafka;
import org.tnmk.pro02transaction.pro02multipleproducers.producer.ProducerWithTransactionalAnnotation;

@Service
public class Initiation {

    @Autowired
    private ProducerWithLocalTransaction producerWithLocalTransaction;

    @Autowired
    private ProducerWithTransactionalAnnotation producerWithTransactionalAnnotation;

    @Autowired
    private ProducerWithPureApacheKafka producerWithPureApacheKafka;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        sendSuccessMessages();
        sendFailMessages();
    }

    private void sendSuccessMessages() {
        String successMessage = "PureApacheKafka_Success_" + System.nanoTime();
        producerWithPureApacheKafka.sendMultiTopicsSuccessfully(successMessage);

        successMessage = "LocalTransaction_Success_" + System.nanoTime();
        producerWithLocalTransaction.sendMultiTopicsSuccessfully(successMessage);

        successMessage = "TransactionalAnnotation_Success_" + System.nanoTime();
        producerWithTransactionalAnnotation.sendMultiTopicsSuccessfully(successMessage);
    }

    private void sendFailMessages() {
        String failMessage = "LocalTransaction_Fail_" + System.nanoTime();
        producerWithLocalTransaction.sendMultiTopicsFailAndRollback(failMessage);

        failMessage = "TransactionalAnnotation_Fail_" + System.nanoTime();
        producerWithTransactionalAnnotation.sendMultiTopicsFailAndRollback(failMessage);
    }
}

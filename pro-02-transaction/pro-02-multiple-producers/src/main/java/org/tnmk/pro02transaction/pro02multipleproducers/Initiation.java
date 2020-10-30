package org.tnmk.pro02transaction.pro02multipleproducers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro02transaction.pro02multipleproducers.producer.ProducerWithLocalTransaction;
import org.tnmk.pro02transaction.pro02multipleproducers.producer.ProducerWithTransactionalAnnotation;

@Service
public class Initiation {

    @Autowired
    private ProducerWithLocalTransaction producerWithLocalTransaction;

    @Autowired
    private ProducerWithTransactionalAnnotation producerWithTransactionalAnnotation;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String successMessage = "Success_" + System.nanoTime();
        producerWithTransactionalAnnotation.sendSuccessfully(successMessage);

        String failMessage = "Fail_" + System.nanoTime();
        producerWithTransactionalAnnotation.sendFailAndRollback(failMessage);
    }
}

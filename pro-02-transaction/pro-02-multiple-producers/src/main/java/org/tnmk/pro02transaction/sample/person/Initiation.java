package org.tnmk.pro02transaction.sample.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro02transaction.sample.person.producer.MultipleTopicsProducer;

@Service
public class Initiation {

    @Autowired
    private MultipleTopicsProducer multipleTopicsProducer;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String successMessage = "Success_" + System.nanoTime();
        multipleTopicsProducer.sendSuccessfully(successMessage);

        String failMessage = "Fail_" + System.nanoTime();
        multipleTopicsProducer.sendFailAndRollback(failMessage);
    }
}

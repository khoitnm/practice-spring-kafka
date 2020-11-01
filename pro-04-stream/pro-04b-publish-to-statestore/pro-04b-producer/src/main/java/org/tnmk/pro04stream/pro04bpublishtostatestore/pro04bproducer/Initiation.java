package org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bproducer.producer.PersonProducer;
import org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bproducer.producer.model.Person;

@Service
public class Initiation {

    @Autowired
    private PersonProducer personProducer;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Person person = new Person(1L, "Name_"+System.nanoTime());
        personProducer.send(person);
    }
}

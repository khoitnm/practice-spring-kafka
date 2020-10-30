package org.tnmk.pro03jsonmessage.pro03producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro03jsonmessage.pro03producer.model.Person;
import org.tnmk.pro03jsonmessage.pro03producer.producer.PersonProducer;
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

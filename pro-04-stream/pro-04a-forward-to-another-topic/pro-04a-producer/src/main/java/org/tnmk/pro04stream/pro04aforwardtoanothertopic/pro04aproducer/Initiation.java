package org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aproducer.producer.PersonProducer;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aproducer.producer.model.Person;

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

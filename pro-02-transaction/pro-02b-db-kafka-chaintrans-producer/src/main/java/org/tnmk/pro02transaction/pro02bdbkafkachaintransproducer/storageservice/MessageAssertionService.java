package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.storageservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageAssertionService {
    private static final Logger logger = LoggerFactory.getLogger(MessageAssertionService.class);

    @Autowired
    private MessageRepository messageRepository;

    public boolean assertExistMessage(String messageBody){
        List<Message> messages = messageRepository.findByMessageBody(messageBody);
        boolean result = !messages.isEmpty();

        logger.info("Assert existing message {}: {}", messageBody, result);
        return result;
    }
}

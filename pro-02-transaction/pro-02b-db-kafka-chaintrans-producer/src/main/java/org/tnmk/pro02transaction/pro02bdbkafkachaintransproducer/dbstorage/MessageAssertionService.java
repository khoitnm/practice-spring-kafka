package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.dbstorage;

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
        boolean isExist = !messages.isEmpty();
        if (isExist){
            logger.info("Assert message in DB was committed: '{}'", messageBody);
        }else{
            logger.info("Assert message in DB was rollback: '{}'", messageBody);
        }
        return isExist;
    }
}

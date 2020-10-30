package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.dbstorage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByMessageBody(String messageBody);

    Message findById(long id);

}

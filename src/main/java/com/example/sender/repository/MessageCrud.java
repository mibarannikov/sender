package com.example.sender.repository;

import com.example.sender.entity.Message;
import com.example.sender.entity.enums.StatusSending;
import org.springframework.data.repository.CrudRepository;


public interface MessageCrud extends CrudRepository<Message, Long> {

    long countByStatusSending(StatusSending statusSending);

    long countBySendingListIdAndStatusSending(Long sendingListId, StatusSending statusSending);


}

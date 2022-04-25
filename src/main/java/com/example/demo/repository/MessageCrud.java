package com.example.demo.repository;

import com.example.demo.entity.Message;
import com.example.demo.entity.SendingList;
import com.example.demo.entity.enums.StatusSending;
import org.springframework.data.repository.CrudRepository;


public interface MessageCrud extends CrudRepository<Message, Long> {

    long countByStatusSending(StatusSending statusSending);

    long countBySendingListIdAndStatusSending(Long sendingListId, StatusSending statusSending);


}

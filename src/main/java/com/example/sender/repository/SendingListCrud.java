package com.example.sender.repository;

import com.example.sender.entity.SendingList;
import com.example.sender.entity.enums.SendingListStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
import java.util.List;


public interface SendingListCrud extends CrudRepository<SendingList, Long>{
    @RestResource(exported = false)
    List<SendingList> findByStartSendingBeforeAndEndSendingAfterAndSendingListStatus(LocalDateTime startSending,
                                                                                     LocalDateTime endSending,
                                                                                     SendingListStatus sendingListStatus);

    long countBySendingListStatus(SendingListStatus sendingListStatus);


}

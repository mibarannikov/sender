package com.example.demo.service;

import com.example.demo.entity.SendingList;
import com.example.demo.entity.enums.SendingListStatus;
import com.example.demo.repository.SendingListCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SendingListScheduler {

    private final SendingListCrud sendingListCrud;
    private final SendingListService sendingListService;

    @Scheduled(fixedDelay = 1000)
    public void sendingListCheck() {
        List<SendingList> sendingLists = sendingListCrud.findByStartSendingBeforeAndEndSendingAfterAndSendingListStatus(
                LocalDateTime.now(),
                LocalDateTime.now(),
                SendingListStatus.NEW);
        sendingLists = updateStatus(sendingLists);
        sendingLists.forEach(sendingListService::runSendingList);
    }

    @Transactional
    public List<SendingList> updateStatus(List<SendingList> sendingLists) {
        sendingLists.forEach(s -> {
            s.setSendingListStatus(SendingListStatus.RUNNING);
            sendingListCrud.save(s);
        });
    return sendingLists;
    }


}

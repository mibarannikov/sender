package com.example.sender.entity;

import com.example.sender.entity.enums.SendingListStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SendingList extends DateCreateUpdate {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(name = "sending_list_status", nullable = false)
    private SendingListStatus sendingListStatus;

    @Column(name = "start_sending")
    private LocalDateTime startSending;

    @Column(name = "end_sending")
    private LocalDateTime endSending;

    @Column(name = "message")
    private String message;

    @Column(name = "filter")
    private String filter;

}

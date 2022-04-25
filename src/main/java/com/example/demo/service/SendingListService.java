package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Message;
import com.example.demo.entity.SendingList;
import com.example.demo.entity.enums.StatusSending;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.Msg;
import com.example.demo.repository.ClientCrud;
import com.example.demo.repository.MessageCrud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendingListService {

    private final ClientCrud clientCrud;
    private final RestTemplate restTemplate = new RestTemplate();
    private final MessageCrud messageCrud;

    @Value("${api.url}")
    private String url;
    @Value("${api.token}")
    private String token;

    @Async
    public void runSendingList(SendingList sendingList) {
        List<Client> clients = clientCrud.findByCodeOrTag(sendingList.getFilter(), sendingList.getFilter());
        clients.forEach(client ->
                {
                    Message message = new Message();
                    message.setSendingList(sendingList);
                    message.setStatusSending(StatusSending.NOT_SENDING);
                    message.setClient(client);

                    message = messageCrud.save(message);
                    Msg msg = new Msg(message.getId(),
                            client.getPhoneNumber(),
                            sendingList.getMessage());
                    try {
                        ApiResponse apiResponse = sendMessage(msg);

                        log.info("Send message {}", apiResponse);
                        messageCrud.findById(message.getId()).ifPresent(m -> {
                            m.setStatusSending(StatusSending.SENDING);
                            messageCrud.save(m);
                        });

                    } catch (Exception ex) {

                        log.error(ex.getMessage());
                    }
                }
        );
    }

    private ApiResponse sendMessage(Msg msg) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Msg> request = new HttpEntity<>(msg, headers);
        return restTemplate.exchange(url,
                HttpMethod.POST,
                request,
                ApiResponse.class,
                msg.getId()

        ).getBody();
    }
}


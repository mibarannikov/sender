package com.example.sender.repository;

import com.example.sender.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface ClientCrud extends CrudRepository<Client, Long> {
    @RestResource(exported = false)
    List<Client> findByCodeOrTag(String code, String tag);



}


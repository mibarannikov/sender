package com.example.demo.repository;

import com.example.demo.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface ClientCrud extends CrudRepository<Client, Long> {
    @RestResource(exported = false)
    List<Client> findByCodeOrTag(String code, String tag);



}


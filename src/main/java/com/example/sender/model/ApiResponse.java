package com.example.sender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@ToString
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Integer code;
    private String message;
}

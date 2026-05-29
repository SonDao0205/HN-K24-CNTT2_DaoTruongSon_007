package com.btvn.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ApiDataResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private HttpStatus status;
}

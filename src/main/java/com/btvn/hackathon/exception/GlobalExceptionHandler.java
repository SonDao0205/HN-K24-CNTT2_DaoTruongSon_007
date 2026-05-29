package com.btvn.hackathon.exception;

import com.btvn.hackathon.dto.ApiDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(new ApiDataResponse<>(
                false, "Dữ liệu không hợp lệ", errors, HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false, ex.getMessage(), null, HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidValue.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNotValidValue(NotValidValue ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false, ex.getMessage(), null, HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
}
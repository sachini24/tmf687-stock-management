package com.example.TMF687.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now().toString(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}



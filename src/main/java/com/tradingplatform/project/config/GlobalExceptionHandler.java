package com.tradingplatform.project.config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(
        RuntimeException ex
    ){

        Map<String,Object> error =
            new HashMap<>();

        error.put(
            "timestamp",
            LocalDateTime.now()
        );

        error.put(
            "error",
            ex.getMessage()
        );

        return ResponseEntity
            .status(
                HttpStatus.BAD_REQUEST
            )
            .body(error);
    }
}
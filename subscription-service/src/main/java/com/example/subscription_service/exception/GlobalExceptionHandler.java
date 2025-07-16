package com.example.subscription_service.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExists(PlanNotFoundException ex) {
        log.warn("Plan is not Found  " ,ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler( TenantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExists(TenantNotFoundException ex) {
        log.warn("Tenant is not Found  " ,ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }




}

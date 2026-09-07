package com.example.demo.company.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>
    handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, Object> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                    errors.put(
                        error.getField(),
                        error.getDefaultMessage()
                    )
                );

        Map<String, Object> body =
                new HashMap<>();

        body.put(
                "status",
                HttpStatus.BAD_REQUEST.value()
        );

        body.put("errors", errors);

        return ResponseEntity
                .badRequest()
                .body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>>
    handleRuntimeException(
            RuntimeException ex) {

        Map<String, Object> body =
                new HashMap<>();

        body.put(
                "status",
                HttpStatus.NOT_FOUND.value()
        );

        body.put(
                "message",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    handleException(Exception ex) {

        Map<String, Object> body =
                new HashMap<>();

        body.put(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        body.put(
                "message",
                ex.getMessage()
        );

        return ResponseEntity
                .status(
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(body);
    }
}
package com.example.demo.agriculture.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

    @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleNotFound(FarmNotFoundException ex,HttpServletRequest request) 
    {

        return response(HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(FarmAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>
    handleAlreadyExists(FarmAlreadyExistsException ex,HttpServletRequest request) 
    {

        return response(HttpStatus.CONFLICT,ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponse>
    handleLogin(InvalidLoginException ex,HttpServletRequest request) 
    {

        return response(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                request.getRequestURI());
        }

    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>
    handleValidation(MethodArgumentNotValidException ex,HttpServletRequest request)
    {

        String message =ex.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(error -> {

                            if (error instanceof FieldError) 
                            {

                                FieldError field =(FieldError) error;

                                return field.getField()
                                        + ": "
                                        + field.getDefaultMessage();
                            }

                            return error
                                    .getDefaultMessage();

                        })
                        .collect(
                                Collectors.joining(", "));

        return response(
                HttpStatus.BAD_REQUEST,
                message,
                request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGeneral(Exception ex,
            HttpServletRequest request) 
    {

        return response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> response(
            HttpStatus status,
            String message,
            String path) 
    {

        ErrorResponse error =
                new ErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        message,
                        path);

        return ResponseEntity
                .status(status)
                .body(error);
    }
}
package com.maturanomartin.technicaltest.infrastructure.adapter.output.exception;

import com.maturanomartin.technicaltest.domain.exception.SuperHeroNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        com.maturanomartin.technicaltest.infrastructure.adapter.output.exception.ExceptionResponse exceptionResponse = new com.maturanomartin.technicaltest.infrastructure.adapter.output.exception.ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                Arrays.asList(request.getDescription(false)));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SuperHeroNotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundException(SuperHeroNotFound ex, WebRequest request) {
        com.maturanomartin.technicaltest.infrastructure.adapter.output.exception.ExceptionResponse exceptionResponse = new com.maturanomartin.technicaltest.infrastructure.adapter.output.exception.ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                Arrays.asList(request.getDescription(false)));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });

        com.maturanomartin.technicaltest.infrastructure.adapter.output.exception.ExceptionResponse exceptionResponse = new com.maturanomartin.technicaltest.infrastructure.adapter.output.exception.ExceptionResponse(LocalDateTime.now(), "Validation Failed", errors);

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

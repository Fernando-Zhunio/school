package com.fzhunio.school.config;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            IllegalAccessException.class,
            IllegalStateException.class,
            ConstraintViolationException.class,
            SQLIntegrityConstraintViolationException.class
    })
    protected ResponseEntity<?> handleConflict(
            RuntimeException ex, WebRequest req
    ) {
        String body = "Error";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, req);
    }
}

package com.abnamro.recipe.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomizeExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeExceptionHandler.class);

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundException(RecipeNotFoundException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException exception,
                                                                                WebRequest request) {
        LOGGER.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }
}
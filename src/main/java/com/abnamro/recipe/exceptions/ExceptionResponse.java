package com.abnamro.recipe.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExceptionResponse {

    private String message;
    private String details;
}
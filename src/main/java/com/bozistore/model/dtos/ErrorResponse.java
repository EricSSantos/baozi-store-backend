package com.bozistore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(int status, String message, Map<String, String> campos) {

    public ErrorResponse(int status, String message) {
        this(status, message, null);
    }
}

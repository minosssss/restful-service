package com.system.restfulservice.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidErrorResponse {
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ValidErrorResponse(String message, Map<String, String> validation) {
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

    ;

}

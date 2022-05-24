package com.modyo.pokemon.domain.exception;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiError {

    private Integer status;
    private String code;
    private LocalDateTime timestamp;
    private String message;
    private Type type;

    private List<ApiSubError> errors;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }


    ApiError(Integer status, String message, Type type) {
        this();
        this.status = status;
        this.message = message;
        this.type = type;
    }

    ApiError(Integer status, String message, Type type, List<ApiSubError> errors) {
        this();
        this.status = status;
        this.message = message;
        this.type = type;
        this.errors = errors;
    }

    public enum Type {
        BUSINESS, TECHNICAL
    }
}


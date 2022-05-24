package com.modyo.pokemon.domain.exception;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

interface ApiSubError {
    //Generic ApiSubError interface
}

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiValidationError implements ApiSubError {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String object;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String field;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Object rejectedValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;

    ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}

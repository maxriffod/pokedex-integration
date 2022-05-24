package com.modyo.pokemon.domain.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String VALIDATIONS_MESSAGE = "Validation error";
    private static final String EXTERNAL_API_ERROR = "External API error: ";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        String message = "Internal server error: " + ex.getLocalizedMessage();
        log.error(message, ex);
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, ApiError.Type.TECHNICAL), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<ApiSubError> errors = ex.getConstraintViolations().stream()
                .map(cv -> new ApiValidationError(null, cv.getPropertyPath().toString(), cv.getInvalidValue(), cv.getMessage()))
                .collect(Collectors.toList());
        int status = HttpStatus.BAD_REQUEST.value();
        log.error(errors.toString(), ex);
        return ResponseEntity.status(status).body(new ApiError(status, VALIDATIONS_MESSAGE, ApiError.Type.BUSINESS, errors));
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<Object> handleFeignException(FeignException ex) {
        String message = EXTERNAL_API_ERROR + ex.getLocalizedMessage();
        log.error(ex.getLocalizedMessage(), ex);
        return ResponseEntity.status(ex.status()).body(new ApiError(ex.status(), message, ApiError.Type.BUSINESS));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
        return new ResponseEntity<>(apiError, httpStatus);
    }


}

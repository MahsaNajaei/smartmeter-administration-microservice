package com.energymeter.administrationservice.exception.handlers;

import com.energymeter.administrationservice.exception.DataValidationException;
import com.energymeter.administrationservice.exception.EntityIdNotFoundException;
import com.energymeter.administrationservice.exception.messages.GeneralErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleExceptions(Exception e) {
        log.error("Unexpected Exception Occurred! ", e);
        return generateBaseApiErrorMessage(GeneralErrorMessages.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleEntityIdNotFoundException(EntityIdNotFoundException e) {
        log.warn(e.getMessage());
        return generateBaseApiErrorMessage(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        var responseBody = generateBaseApiErrorMessage(GeneralErrorMessages.DATA_INTEGRITY_VIOLATION);
        responseBody.put("cause", e.getMostSpecificCause().getMessage());
        return responseBody;
    }

    @ExceptionHandler(DataValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleDataValidationException(DataValidationException e) {
        log.warn(e.getMessage());
        return generateBaseApiErrorMessage(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private Map<String, String> generateBaseApiErrorMessage(String message) {
        var responseBody = new HashMap<String, String>();
        responseBody.put("message", message);
        return responseBody;
    }

}

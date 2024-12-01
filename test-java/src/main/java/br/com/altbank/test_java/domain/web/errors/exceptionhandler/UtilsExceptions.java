package br.com.altbank.test_java.domain.web.errors.exceptionhandler;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UtilsExceptions {

    public static Map<String,Object> createMessageErrorValidation(MethodArgumentNotValidException exception, int codeError) {

        Map<String, Object> errors = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            fieldErrors.put(field, message);
        }

        errors.put("status", codeError);
        errors.put("error", "Validation Error");
        errors.put("timestamp", LocalDateTime.now());
        errors.put("message", "One or more validation errors occurred.");
        errors.put("fields", fieldErrors);

        return errors;
    }

    public static Map<String,Object> createMessageError(Exception exception, int codeError) {

        Map<String, Object> errors = new HashMap<>();

        errors.put("status", codeError);
        errors.put("timestamp", LocalDateTime.now());
        errors.put("message", exception.getMessage());

        return errors;
    }

}

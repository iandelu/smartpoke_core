package com.smartpoke.api.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        Map map = new HashMap();
        map.put("message", resourceNotFoundException.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<Map<String, Object>> handlerEmailInUseException(EmailInUseException emailInUseException){
        Map map = new HashMap();
        map.put("message", emailInUseException.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.CONFLICT);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handlerInvalidPasswordException(InvalidPasswordException invalidPasswordException){
        Map map = new HashMap();
        map.put("message", invalidPasswordException.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}

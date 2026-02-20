package com.babunes.controller_jpa_practice.controllerAdvice;


import com.babunes.controller_jpa_practice.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserControllerAdvice {


    private DataIntegrityViolationException e;

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<String > handleException(DataIntegrityViolationException e, HttpServletRequest request,
                                                                HttpServletResponse response) {
        String name = "violation of an integrity constraint";
                Throwable cause  = e.getCause();
        if(cause instanceof org.hibernate.exception.ConstraintViolationException cve){
            name = cve.getKind().toString();
        }
        return ResponseEntity.badRequest().body(name);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String> > handleException(MethodArgumentNotValidException e, HttpServletRequest request,
                                                  HttpServletResponse response) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<String> handleException(CustomException e, HttpServletRequest request,
                                                  HttpServletResponse response) {
        if(e.getMessage().equalsIgnoreCase("User not found")) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

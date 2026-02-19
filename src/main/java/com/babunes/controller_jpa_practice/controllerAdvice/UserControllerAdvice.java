package com.babunes.controller_jpa_practice.controllerAdvice;


import com.babunes.controller_jpa_practice.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<String> handleException(Exception e, HttpServletRequest request,
                                                  HttpServletResponse response) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

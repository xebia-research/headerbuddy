package com.xebia.headerbuddy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandlingController {

    // Universal Exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, HttpServletResponse response) {
        return new ResponseEntity("ERROR: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

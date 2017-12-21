package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.models.CustomErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlingController {

    // Universal Exception handler
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception exeption, HttpServletResponse response) {
        return new ResponseEntity(new CustomErrorModel(exeption.getMessage()), HttpStatus.BAD_REQUEST);
    }

    //This is the exception handler for our validator annotations
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity handleConstraintException(ConstraintViolationException cve, HttpServletResponse response) {
        String errorMessage = "";
        for (ConstraintViolation cv : cve.getConstraintViolations()) {
            errorMessage = cv.getMessage();
            break;
        }
        return new ResponseEntity(new CustomErrorModel(errorMessage), HttpStatus.BAD_REQUEST);
    }
}
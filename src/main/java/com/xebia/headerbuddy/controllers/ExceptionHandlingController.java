package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.models.CustomErrorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // Universal Exception handler
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception exception, HttpServletRequest req, HttpServletResponse res) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new CustomErrorModel(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    //This is the exception handler for our validator annotations
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity handleConstraintException(ConstraintViolationException cve, HttpServletRequest req) {
        String errorMessage = "";
        for (ConstraintViolation cv : cve.getConstraintViolations()) {
            errorMessage = cv.getMessage();
        }
        logger.error(errorMessage);
        return new ResponseEntity(new CustomErrorModel(errorMessage), HttpStatus.BAD_REQUEST);
    }
}

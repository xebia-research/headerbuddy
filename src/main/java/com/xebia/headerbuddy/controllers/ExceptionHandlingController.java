package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.models.CustomErrorModel;
import org.rythmengine.Rythm;
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
import java.io.File;

@RestControllerAdvice
public class ExceptionHandlingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // Universal Exception handler
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception exception, HttpServletRequest req, HttpServletResponse res) {
        ResponseEntity responseEntity = getHtmlErrorIfApplicable(req, exception.getMessage());

        if (responseEntity == null) {
            responseEntity = new ResponseEntity(new CustomErrorModel(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }

        logger.error(exception.getMessage());
        return responseEntity;
    }

    //This is the exception handler for our validator annotations
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity handleConstraintException(ConstraintViolationException cve, HttpServletRequest req) {
        String errorMessage = "";
        for (ConstraintViolation cv : cve.getConstraintViolations()) {
            errorMessage = cv.getMessage();
        }

        ResponseEntity responseEntity = getHtmlErrorIfApplicable(req, errorMessage);

        if (responseEntity == null) {
            responseEntity = new ResponseEntity(new CustomErrorModel(errorMessage), HttpStatus.BAD_REQUEST);
        }

        logger.error(errorMessage);
        return responseEntity;
    }

    private ResponseEntity getHtmlErrorIfApplicable(HttpServletRequest req, String errorMessage) {
        if (req.getQueryString().contains("output=html")) {
            // Get html file from resources
            ClassLoader cl = getClass().getClassLoader();
            File htmlErr = new File(cl.getResource("error.html").getFile());

            return new ResponseEntity(Rythm.render(htmlErr, errorMessage), HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}

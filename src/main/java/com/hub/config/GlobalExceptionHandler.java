package com.hub.config;

import com.hub.exceptions.HubNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *  This class handles all the exceptions for the back-end application.
 *  @author Doug Heinbokel on 2/23/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  Handles NoHandlerFoundException
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 404.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    static ErrorInfo handleNoHandlerFoundException(NoHandlerFoundException e){

        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
    }

    /**
     * Handles the IllegalArgumentException
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 400.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static ErrorInfo handleIllegalArgException(IllegalArgumentException e){

        return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    /**
     * Handles the UsernameNotFoundException
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 404.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    static ErrorInfo handleUsernameNotFoundException(UsernameNotFoundException e){

        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
    }

    /**
     * Handles the HttpRequestMethodNotSupportedException
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 400.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static ErrorInfo handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){

        return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    /**
     * Handles the DataIntegrityViolationException
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 400.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static ErrorInfo handleDataIntegrityViolationException(DataIntegrityViolationException e){

        return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), "Value Taken", "Value must be unique.");
    }

    /**
     * Handles the HubNotFoundException
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 404.
     */
    @ExceptionHandler(HubNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    static ErrorInfo handleHubNotFoundException(HubNotFoundException e){

        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
    }

    /**
     * Handles all other exceptions.
     * @param e the exception being handled
     * @return The {@Link ErrorInfo} to return 500.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    static ErrorInfo handleException(Exception e){

        return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Request failed for some reason", "Request failed for some reason");
    }
}

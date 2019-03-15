package com.hub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HubFileNotFoundException extends RuntimeException{

    public HubFileNotFoundException(String message) {
        super(message);
    }

    public HubFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

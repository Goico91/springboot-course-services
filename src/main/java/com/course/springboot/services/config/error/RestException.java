package com.course.springboot.services.config.error;


import com.course.springboot.services.commons.enums.RestExceptionE;

public class RestException extends Exception {

    private final RestExceptionE info;

    public RestException(RestExceptionE pInfo) {
        super();
        this.info = pInfo;
    }

    public RestExceptionE getInfo() {
        return info;
    }
}
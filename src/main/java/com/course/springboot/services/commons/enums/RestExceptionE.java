package com.course.springboot.services.commons.enums;

import org.springframework.http.HttpStatus;

/**
 * Numerators for all Rest Exceptions that we are going to send
 */
public enum RestExceptionE {

    ERROR_EMPLOYEE_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "employee.not.exists"),
    ERROR_EMPLOYEE_ALREADY_EXISTS(1001, HttpStatus.BAD_REQUEST, "employee.already.exists"),
    UNAUTHORIZED(1002, HttpStatus.UNAUTHORIZED, "user.unauthorized"),
    ;

    private final Integer id;
    private final HttpStatus httpStatus;
    private final String message;

    RestExceptionE(final Integer pId, final HttpStatus pHttpStatus, final String pMessage) {
        this.id = pId;
        this.httpStatus = pHttpStatus;
        this.message = pMessage;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
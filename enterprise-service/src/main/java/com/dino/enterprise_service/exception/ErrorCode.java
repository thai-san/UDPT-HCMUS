package com.dino.enterprise_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    MISSING_REQUIRED_PARAM(1000, "Missing required param", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1003, "Invalid email", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 8 character", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1005, "User not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Unauthorized", HttpStatus.FORBIDDEN),
    CAN_NOT_CREATE_USER(1008, "Can not create this user", HttpStatus.INTERNAL_SERVER_ERROR),
    CUSTOMER_NOT_EXIST(2001, "Customer not exist", HttpStatus.NOT_FOUND),
    ENTERPRISE_NOT_EXIST(3001, "Enterprise not existed", HttpStatus.NOT_FOUND),
    NOT_ENOUGH_QUANTITY(3002, "Not enough ticket quantity", HttpStatus.BAD_REQUEST),
    EVENT_NOT_EXIST(4001, "Event not exist", HttpStatus.NOT_FOUND),
    BOOKING_NOT_EXIST(4001, "Booking not exist", HttpStatus.NOT_FOUND),
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}

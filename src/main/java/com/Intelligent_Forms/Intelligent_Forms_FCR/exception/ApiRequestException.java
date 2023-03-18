package com.Intelligent_Forms.Intelligent_Forms_FCR.exception;

public class ApiRequestException extends RuntimeException
{
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

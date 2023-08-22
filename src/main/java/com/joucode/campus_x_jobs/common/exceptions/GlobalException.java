package com.joucode.campus_x_jobs.common.exceptions;

public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }
}
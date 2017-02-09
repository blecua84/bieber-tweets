package com.blecua84.oauth.twitter.service.exception;

/**
 * Enum that define the error codes of the application.
 *
 * @author bleck84
 */
public enum TwitterStreamClientExceptionCode {

    KO_IO_EXCEPTION(1, "There was an error when the application access to a file: ");

    Integer code;

    String message;

    TwitterStreamClientExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.blecua84.oauth.twitter.service.exception;

/**
 * Exception that controls the events in the TwitterStreamClientImpl.
 *
 * @author bleck84
 */
public class TwitterStreamClientException extends Exception {

    /** Code of the exception */
    private Integer codeException;

    /** Message of the exception */
    private String msgException;

    public TwitterStreamClientException(TwitterStreamClientExceptionCode code) {
        this.codeException = code.getCode();
        this.msgException = code.getMessage();
    }

    public TwitterStreamClientException(Integer codeException, String msgException) {
        super(msgException);
        this.codeException = codeException;
        this.msgException = msgException;
    }

    public TwitterStreamClientException(Throwable cause, Integer codeException, String msgException) {
        super(msgException, cause);
        this.codeException = codeException;
        this.msgException = msgException;
    }

    public Integer getCodeException() {
        return codeException;
    }

    public void setCodeException(Integer codeException) {
        this.codeException = codeException;
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }
}

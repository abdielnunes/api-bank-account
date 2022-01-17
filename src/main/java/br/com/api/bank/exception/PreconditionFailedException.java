package br.com.api.bank.exception;

import org.springframework.http.HttpStatus;

public class PreconditionFailedException extends RuntimeException {
    private final HttpStatus status;
    private final MessageError errors;

    public PreconditionFailedException(String error) {
        this.status = HttpStatus.PRECONDITION_FAILED;
        this.errors = new MessageError(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public MessageError getErrors() {
        return errors;
    }
}

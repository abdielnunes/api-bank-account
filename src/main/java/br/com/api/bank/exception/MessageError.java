package br.com.api.bank.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class MessageError {

    @Getter @Setter
    private String message;

    public MessageError() {}

    public MessageError(final String message) {
        this.message = message;
    }
}
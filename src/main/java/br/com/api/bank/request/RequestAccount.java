package br.com.api.bank.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Setter
@Getter
@Builder
public class RequestAccount {
    private String type;
    private String origin;
    private String destination;
    private double amount;

    public Integer getEvent() {
        int event = 0;
        if(isNull(destination)) {
            event = 1;
        }
        return event;
    }
}
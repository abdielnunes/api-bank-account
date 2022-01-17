package br.com.api.bank.response;

import br.com.api.bank.dto.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ResponseDestination {
    @Getter @Setter
    private Account destination;
}

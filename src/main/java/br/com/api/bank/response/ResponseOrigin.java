package br.com.api.bank.response;

import br.com.api.bank.dto.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ResponseOrigin {
    @Getter
    @Setter
    private Account origin;
}

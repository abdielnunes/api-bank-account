package br.com.api.bank.response;

import br.com.api.bank.dto.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.buf.StringUtils;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseTransfer {

    @Getter @Setter
    private Account origin;

    @Getter @Setter
    private Account destination;
}

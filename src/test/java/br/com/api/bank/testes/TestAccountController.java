package br.com.api.bank.testes;

import br.com.api.bank.controller.AccountController;
import br.com.api.bank.dto.Account;
import br.com.api.bank.service.BankService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
public class TestAccountController {

    @Autowired
    private MockMvc mock;

    @MockBean
    private BankService service;

    @Mock
    Account acc = new Account();
    List<Account> accounts = new ArrayList<>();

    @InjectMocks
    private AccountController accountController;

    @Before
    public void setUp() {
        mock = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testReset() throws Exception {
        mock.perform(post("/reset")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string("OK"))
                    .andDo(print());
    }

    @Test
    public void testNonExistingAccount() throws Exception {
        acc = new Account();
        accounts = new ArrayList<>();
        final String account_id = "200";

        acc = accounts.stream()
                .filter(account -> acc.equals(account.id))
                .findAny()
                .orElse(null);

        given(service.getAccountById(acc, account_id, accounts)).willReturn(acc);

        mock.perform(get("/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                        .param("account_id", account_id))
                .andExpect(content().string("0.0"))
                .andExpect((status().isNotFound()));
    }
}

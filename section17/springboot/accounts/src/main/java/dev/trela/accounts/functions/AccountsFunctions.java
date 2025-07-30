package dev.trela.accounts.functions;

import dev.trela.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class AccountsFunctions {

    @Bean
    public Consumer<Long> updateCommunication(AccountService accountService) {
        return accountNumber -> {
            log.info("Updating communication for the  account number {}", accountNumber);
            accountService.updateCommunicationStatus(accountNumber);
        };
    }

}

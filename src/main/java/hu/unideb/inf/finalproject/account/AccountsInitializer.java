package hu.unideb.inf.finalproject.account;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountsInitializer {

    private final AccountService accountService;

    private final Account adminAccount = Account.builder()
        .userName("admin")
        .password("admin")
        .role(Role.ADMIN)
        .build();

    private final Account userAccount = Account.builder()
        .userName("user")
        .password("user")
        .role(Role.USER)
        .build();

    @PostConstruct
    private void initialize() {
        accountService.addAccount(adminAccount);
        accountService.addAccount(userAccount);
    }
}
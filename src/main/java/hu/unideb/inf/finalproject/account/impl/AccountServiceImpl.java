package hu.unideb.inf.finalproject.account.impl;

import hu.unideb.inf.finalproject.account.Account;
import hu.unideb.inf.finalproject.account.AccountRepository;
import hu.unideb.inf.finalproject.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void addAccount(Account account) {
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        accountRepository.save(account);
    }
}
package hu.unideb.inf.finalproject.account;

import hu.unideb.inf.finalproject.account.exception.UsernameAlreadyExistsException;

public interface AccountService {

    void addAccount(Account account) throws UsernameAlreadyExistsException;
}

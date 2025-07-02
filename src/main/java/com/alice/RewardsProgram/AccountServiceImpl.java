package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    final private List<Account> accounts;

    long accountIdCounter;

    public AccountServiceImpl() {
        accountIdCounter = 0;
        this.accounts = new ArrayList<>();
    }

    public Optional<Account> addAccount(Account account) {
        if (account==null)
            return Optional.empty();
        Account newAccount = new Account();
        newAccount.setUserName(account.getUserName());
        newAccount.setPassword(account.getPassword()); // TODO: unit test
        newAccount.setAccountId(accountIdCounter++);
        this.accounts.add(newAccount);
        return Optional.of(newAccount);
    }

    public Optional<Account> getAccountById(long accountId) {
        return this.accounts.stream().filter(acc->acc.getAccountId()==accountId).findFirst();
    }

    public Optional<Account> getAccountByName(String username) {
        return this.accounts.stream().filter(acc->acc.getUserName().equals(username)).findFirst();
    }

    public Optional<Long> getSizeOfAccounts() {
        return Optional.of((long) accounts.size());
    }

    public boolean deleteAccount(long acctId) {
        return accounts.removeIf(acc -> acc.getAccountId()==acctId);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

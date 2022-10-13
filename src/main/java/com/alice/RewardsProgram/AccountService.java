package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    final private List<Account> accounts;

    long accountIdCounter;

    public AccountService() {
        accountIdCounter = 0;
        this.accounts = new ArrayList<>();
    }

    public Optional<Account> addAccount(Account newAccount) {
        if (newAccount==null)
            return Optional.empty();
        long newId = accountIdCounter++;
        newAccount.setAccountId(newId);
        this.accounts.add(newAccount);
        return Optional.of(newAccount);
    }

    public Optional<Account> getAccountById(long accountId) {
        return this.accounts.stream().filter(acc->acc.getAccountId()==accountId).findFirst();
    }

    public Optional<Long> getSizeOfAccounts() {
        return Optional.of((long) accounts.size());
    }

    public boolean deleteAccount(long acctId) {
        return accounts.removeIf(acc -> acc.getAccountId()==acctId);
    }
}

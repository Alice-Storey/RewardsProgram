package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

    public Optional<Account> addAccount(Account account);

    public Optional<Account> getAccountById(long accountId);

    public Optional<Account> getAccountByName(String username);

    public Optional<Long> getSizeOfAccounts();

    public boolean deleteAccount(long acctId);

    public List<Account> getAccounts();
}

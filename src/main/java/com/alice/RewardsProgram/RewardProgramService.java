package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Account;
import com.alice.RewardsProgram.model.Item;
import com.alice.RewardsProgram.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardProgramService {
    @Autowired
    AccountService accountService;

    @Autowired
    ItemService itemService;

    Map<Long,List<Transaction>> transactionsByAccount;


    public Optional<Account> createAccount(String username, String password) {
        return accountService.addAccount(new Account(username));
    }
    public Optional<Transaction> makeTransaction(long accountId, List<Item> items, Date timestamp) {
        if (accountService.getAccountById(accountId).isEmpty())
            return Optional.empty();
        if (transactionsByAccount.get(accountId).isEmpty())
            transactionsByAccount.put(accountId, new ArrayList<>());
        final Transaction t = new Transaction();
        t.setTransactionId(timestamp.hashCode());
        t.setDate(timestamp);
        t.setUserId(accountId);
        List<Item> verifiedItems = items.stream()
                .map(i-> itemService.getItemById(i.getItemId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (verifiedItems.size()<1)
            return Optional.empty();
        t.addItems(verifiedItems);
        transactionsByAccount.get(accountId).add(t);
        return Optional.of(t);
    }

    public List<Item> viewCatalog() {
        return itemService.getItemCatalog();
    }

    public Optional<Account> findAccountByName(String username) {
        return accountService.getAccountByName(username);
    }

    public List<Account> listAccounts() {
        return accountService.getAccounts();
    }

    public Optional<Long> getAccountRewardPoints(long accountId) {
        Account acct = accountService.getAccountById(accountId).orElse(null);
        if (acct==null)
            return Optional.empty();

        return Optional.of(transactionsByAccount.get(accountId).stream()
                .mapToLong(Transaction::getRewardPoints)
                .sum());
    }

    // month: integer which indicates month of the year (1-12)
    public Optional<Long> getAccountRewardPointsForMonth(long accountId, int month) {
        Account acct = accountService.getAccountById(accountId).orElse(null);
        if (acct==null)
            return Optional.empty();
        return Optional.of(transactionsByAccount.get(accountId).stream()
                .filter(t -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(t.getDate());
                    return (calendar.get(Calendar.MONTH)+1==month);
                }).mapToLong(Transaction::getRewardPoints)
                .sum());
    }
}

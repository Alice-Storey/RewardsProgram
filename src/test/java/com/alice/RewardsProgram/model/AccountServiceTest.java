package com.alice.RewardsProgram.model;

import com.alice.RewardsProgram.AccountService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    @Test
    public void testAddDeleteAccounts() {
        AccountService accountService = new AccountService();
        Account testAccount1 = new Account("jane.s","1234");
        Account testAccount2 = new Account("john.c", "5678");
        assertEquals((Long)0L, (Long)accountService.getSizeOfAccounts().orElseGet(()->0L));
        assertFalse(accountService.deleteAccount(1));
        assertTrue(accountService.getAccountById(2L).isEmpty());

        Account acc = accountService.addAccount(testAccount1).orElseGet(null);
        assertNotNull(acc);
        assertEquals((Long)1L, accountService.getSizeOfAccounts().orElseGet(()->0L));
        Account accGot = accountService.getAccountById(acc.getAccountId()).orElseGet(null);
        assertNotNull(accGot);
        assertEquals(acc.getUserName(), accGot.getUserName());
        assertEquals(acc.getPassword(), accGot.getPassword());

        Account acc2 = accountService.addAccount(testAccount2).orElseGet(null);
        assertNotNull(acc2);
        Account accGot2 = accountService.getAccountById(acc2.getAccountId()).orElseGet(null);
        assertNotNull(accGot2);
        assertEquals(acc2.getUserName(), accGot2.getUserName());
        assertEquals(acc2.getPassword(), accGot2.getPassword());

        assertEquals((Long)2L, accountService.getSizeOfAccounts().orElseGet(()->0L));
        accGot = accountService.getAccountById(acc.getAccountId()).orElseGet(null);
        assertEquals(acc.getUserName(), accGot.getUserName());
        assertEquals(acc.getPassword(), accGot.getPassword());

        assertTrue(accountService.deleteAccount(accGot.getAccountId()));
        assertEquals((Long)1L, accountService.getSizeOfAccounts().orElseGet(()->0L));
        accGot2 = accountService.getAccountById(acc2.getAccountId()).orElseGet(null);
        assertNotNull(accGot2);
        assertEquals(acc2.getUserName(), accGot2.getUserName());
        assertEquals(acc2.getPassword(), accGot2.getPassword());
    }
}

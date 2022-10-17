package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Account;
import com.alice.RewardsProgram.model.Item;
import com.alice.RewardsProgram.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RewardsProgramController {
    @Autowired
    RewardProgramService rewardProgramService;

    //make account
    //vew items
    //make purchase
    //stretch goal: login with temp token
    // stockAmt logic

    @GetMapping("/viewProducts")
    @ResponseBody
    public ResponseEntity<List<Item>> viewItems() {
        return ResponseEntity.ok()
                .body(rewardProgramService.viewCatalog());
    }

    @GetMapping("/viewAccounts")
    public ResponseEntity<List<Account>> viewAccounts() {
        return ResponseEntity.ok()
                .body(rewardProgramService.listAccounts());
    }

    @PostMapping("/createAccounts")
    public ResponseEntity<List<Account>> createAccountsAndGetListOfAccountsWithUserId( @RequestBody List<Account> accountList){
        return accountList.isEmpty()? ResponseEntity.badRequest().build():
                ResponseEntity.ok().body(
                        accountList.stream().map(acct->rewardProgramService.createAccount(acct.getUserName(),acct.getPassword()).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList()));
    }
    @PostMapping("/makeTransactions")
    public ResponseEntity<List<Transaction>> makeTransactions(@RequestBody List<Transaction> newTransactions) {
        return newTransactions.isEmpty()? ResponseEntity.badRequest().build():
                ResponseEntity.ok(
                        newTransactions.stream().filter(Objects::nonNull).peek(t-> {
                                    t.setDate(new Date(System.currentTimeMillis()));
                                }).map(t->rewardProgramService.makeTransaction(t.getUserId(),t.getCartItems(),t.getDate()).orElse(null)).filter(Objects::nonNull)

                                .collect(Collectors.toList()));
    }

    @GetMapping("/getRewardsTotal")
    public ResponseEntity<Long> getRewardsTotal(@RequestParam long userid) {
        return ResponseEntity.ok(
                rewardProgramService.getAccountRewardPoints(userid).orElse(0L));
    }

    @GetMapping("/getRewardsForMonth")
    public ResponseEntity<Long> getRewardsTotal(@RequestParam long userid, int month) {
        return ResponseEntity.ok(
                rewardProgramService.getAccountRewardPointsForMonth(userid, month).orElse(0L));
    }

    @PostMapping("/createItems")
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items) {
        List<Item> successItems = new ArrayList<>();
        for (Item i : items)
            rewardProgramService.putItem(i).ifPresent(successItems::add);
        return ResponseEntity.ok(successItems);
    }
}

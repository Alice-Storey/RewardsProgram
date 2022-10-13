package com.alice.RewardsProgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Comparable<Account> {
   private long accountId, rewardsPoints;
   private String userName;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Account && this.accountId == ((Account)o).accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public int compareTo(Account o) {
        return Integer.compare((int)o.getAccountId(),(int) this.getAccountId());
    }
}

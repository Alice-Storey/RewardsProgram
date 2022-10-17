package com.alice.RewardsProgram.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Account implements Comparable<Account> {

    @NonNull
    private String userName;
    private Long accountId;

    @NonNull
    private String password;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Account && this.accountId .equals (((Account)o).accountId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getAccountId(), this.getAccountId());
    }
}

package com.alice.RewardsProgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private long transactionId, userId;
    private List<Item> cartItems;
    private Date date;

    public double getTotalPrice() {
        if (this.cartItems == null)
            return (0.0);
        return cartItems.stream()
            .map(Item::getItemPrice)
            .reduce(Double::sum)
            .orElseGet(()->0.0);
    }

    public void addItem(Item item) {
        this.cartItems.add(item);
    }

    public void removeItem(Item item) {
        this.cartItems.remove(item);
    }

    public void removeItem(long itemId) {
        this.cartItems.removeIf(item->item.itemId==itemId);
    }
}

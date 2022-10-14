package com.alice.RewardsProgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private long transactionId, userId;
    private List<Item> cartItems;
    private Date date;

    final private double ONE_POINT_MINIMUM = 50.0;
    final private double TWO_POINT_MINIMUM = 100.00;

    public Transaction() {
        cartItems = new ArrayList<>();
    }

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

    public long getRewardPoints() {
        long rewardsPts = 0L;
        double price = this.getTotalPrice();
        if (price > TWO_POINT_MINIMUM) {
            rewardsPts += (price - TWO_POINT_MINIMUM) * 2;
            price = TWO_POINT_MINIMUM;
        }
        if (price > ONE_POINT_MINIMUM)
            rewardsPts += (price - ONE_POINT_MINIMUM);

        return rewardsPts;
    }
}

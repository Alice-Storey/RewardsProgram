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
public class Item implements Comparable<Item> {
    long itemId;
    double itemPrice;
    String itemName;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Item && this.itemId == ((Item)o).itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare((int)this.getItemId(), (int)o.getItemId());
    }
}

package com.alice.RewardsProgram.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ItemTest {

    @Test
    void pojoTest() {
        Item newItem = new Item();
        assertNotEquals(null, newItem);
        long itemId=1;
        double itemPrice=9.95;
        String itemName="Demo Item";
        newItem.setItemId(itemId);
        newItem.setItemPrice(itemPrice);
        newItem.setItemName(itemName);
        assertEquals(itemPrice, newItem.getItemPrice());
        assertEquals(itemId, newItem.getItemId());
        assertEquals(itemName, newItem.getItemName());
    }
}

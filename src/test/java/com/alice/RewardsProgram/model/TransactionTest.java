package com.alice.RewardsProgram.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    public void testPojo() {
        Transaction t = new Transaction();
        long tId = 1L, userId=2L;
        Date date = new  Date(System.currentTimeMillis());
        t.setTransactionId(1L);
        t.setUserId(userId);
        t.setDate(date);
        assertEquals(tId, t.getTransactionId());
        assertEquals(userId, t.getUserId());
        assertEquals(date, t.getDate());
    }

    @Test
    public void testItems() {
        Item item1 = new Item(), item2 = new Item();
        double p1 = 5.95, p2 = .50;
        item1.setItemName("Broom");
        item1.setItemId(1);
        item1.setItemPrice(p1);
        item2.setItemPrice(p2);
        item2.setItemId(2);
        item2.setItemName("Gum");

        Transaction t = new Transaction();
        t.setUserId(1);
        t.setDate(new  Date(System.currentTimeMillis()));
        t.setTransactionId(2);

        t.setCartItems(new ArrayList<>());
        assertEquals(0.0,t.getTotalPrice());

        t.addItem(item1);
        assertEquals(p1, t.getTotalPrice());
        t.addItem(item2);
        assertEquals(p1+p2, t.getTotalPrice());

        t.removeItem(item1);
        assertEquals(p2, t.getTotalPrice());

        t.removeItem(item2.getItemId());
        assertEquals(0.0, t.getTotalPrice());
    }
}

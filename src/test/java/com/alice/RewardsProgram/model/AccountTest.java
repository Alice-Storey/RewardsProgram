package com.alice.RewardsProgram.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testPojoId() {
        Account accA = new Account(), accB = new Account();
        long id1 = 1L, id2 = 2L, id3=1L;

        accA.setAccountId(id1);
        assertEquals(id1, accA.getAccountId());

        accB.setAccountId(id2);
        assertEquals(id2, accB.getAccountId());

        assertNotEquals(accA, accB);
        assertNotEquals(accA.hashCode(),accB.hashCode());
    }


    @Test
    public void testOverrides() {
        Account accA = new Account(), accB = new Account(), accC = new Account();
        long id1 = 1L, id2 = 2L;
        accA.setAccountId(id1);
        accB.setAccountId(id2);
        accC.setAccountId(id1);

        assertNotEquals(accA, accB);
        assertEquals(accA, accC);
        assertNotEquals(accA.hashCode(), accB.hashCode());
        assertEquals(accA.hashCode(), accC.hashCode());
        var temp = accA.compareTo((accB));
        assertTrue(accA.compareTo(accB)>0);
        assertTrue(accB.compareTo(accA)<0);
    }

    @Test
    public void testPojoName() {
        Account accA = new Account();
        String name="Foo";
        assertNull(accA.getUserName());
        accA.setUserName(name);
        assertEquals(name, accA.getUserName());
    }
}

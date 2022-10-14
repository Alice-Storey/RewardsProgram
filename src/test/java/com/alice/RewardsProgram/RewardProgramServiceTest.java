package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Item;
import com.alice.RewardsProgram.model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardProgramServiceTest {

    RewardProgramService rewardProgramService;

    @Test
    public void testRewardPoints() {
        rewardProgramService = new RewardProgramService();

        Transaction t = new Transaction();
        Item item1 = new Item(), item2 = new Item();
        double p1 = 50.0, p2 = 90.0;
        item1.setItemName("Broom");
        item1.setItemId(1);
        item1.setItemPrice(p1);
        item2.setItemPrice(p2);
        item2.setItemId(2);
        item2.setItemName("Gum");


        assertEquals(0L, rewardProgramService.getRewardPoints(t));
        t.addItem(item1);
        assertEquals(0L, rewardProgramService.getRewardPoints(t));
        t.removeItem(item1);
        t.addItem(item2);
        assertEquals(40L, rewardProgramService.getRewardPoints(t));
        t.addItem(item1);
        assertEquals(130L, rewardProgramService.getRewardPoints(t));
        t.removeItem(item2.getItemId());
        t.addItem(item1);
        assertEquals(50L, rewardProgramService.getRewardPoints(t));
    }
}

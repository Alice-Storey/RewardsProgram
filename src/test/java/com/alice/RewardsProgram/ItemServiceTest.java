package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemServiceTest {

    @Test
    public void itemCatalogTest() {
        ItemService itemService = new ItemService();
        Item item1 = new Item(1L, 3.99, "Broom");
        Item item2 = new Item(2L, 0.40, "Gum");

        assertEquals(0,itemService.getItemCatalog().size());
        assertEquals(Optional.empty(), itemService.getItemById(1L));
        assertEquals(Optional.empty(), itemService.getStockOfItemById(1L));

        Item addedItem1 = itemService.addItemToCatalog(item1).orElse(null);
        assertEquals(1,itemService.getItemCatalog().size());
        Item addedItem2 = itemService.addItemToCatalog(item2).orElse(null);
        assertEquals(2,itemService.getItemCatalog().size());

        assertNotNull(addedItem1);
        Item gotItem1 = itemService.getItemById(addedItem1.getItemId()).orElse(null);
        assertNotNull(gotItem1);
        assertEquals(item1.getItemPrice(), gotItem1.getItemPrice());
        assertEquals(item1.getItemName(), gotItem1.getItemName());
        assertEquals(0L, itemService.getStockOfItemById(gotItem1.getItemId()).orElse(0L));
        itemService.addItemStock(gotItem1.getItemId(), 5L);
        assertEquals(5L, itemService.getStockOfItemById(gotItem1.getItemId()).orElse(0L));
        itemService.addItemStock(gotItem1.getItemId(), 2L);
        assertEquals(7L, itemService.getStockOfItemById(gotItem1.getItemId()).orElse(0L));

        assertNotNull(addedItem2);
        Item gotItem2 = itemService.getItemById(addedItem2.getItemId()).orElse(null);
        assertNotNull(gotItem2);
        assertEquals(item2.getItemPrice(), gotItem2.getItemPrice());
        assertEquals(item2.getItemName(), gotItem2.getItemName());
        assertEquals(0L, itemService.getStockOfItemById(gotItem2.getItemId()).orElse(0L));
        itemService.addItemStock(gotItem2.getItemId(), 6L);
        assertEquals(6L, itemService.getStockOfItemById(gotItem2.getItemId()).orElse(0L));
        itemService.addItemStock(gotItem2.getItemId(), 4L);
        assertEquals(10L, itemService.getStockOfItemById(gotItem2.getItemId()).orElse(0L));

        Long clearedStockAmount = itemService.clearAllItemStock(gotItem1.getItemId()).orElse(null);
        assertEquals(7L, clearedStockAmount);
        assertEquals(0L, itemService.getStockOfItemById(gotItem1.getItemId()).orElse(0L));
        assertEquals(10L, itemService.getStockOfItemById(gotItem2.getItemId()).orElse(0L));

        itemService.addItemStock(gotItem1.getItemId(),2L);
        Long newCurrentStock = itemService.addItemStock(gotItem1.getItemId(), 1L).orElse(null);
        assertEquals(3L, newCurrentStock);
        newCurrentStock = itemService.removeItemStock(gotItem1.getItemId(), 1L).orElse(0L);
        assertEquals(newCurrentStock, 2L);




    }

    @Test
    public void itemStockTest() {

    }
}

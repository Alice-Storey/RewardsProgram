package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

    final private List<Item> itemCatalog;
    final private Map<Long,Long> itemStock;

    private int itemIdCounter;

    public ItemService() {
        itemCatalog = new ArrayList<>();
        itemStock = new HashMap<>();
        itemIdCounter = 0;
    }

    public List<Item> getItemCatalog() {
        return itemCatalog;
    }

    public Optional<Item> getItemById(Long id) {
        return itemCatalog.stream().filter(i->i.getItemId() == id).findFirst();
    }

    public Optional<Long> getStockOfItemById(Long id) {
        return itemStock.containsKey(id) ? Optional.of(itemStock.get(id)) : Optional.empty();
    }

    public Optional<Item> addItemToCatalog(Item item) {
        if (item==null)
            return Optional.empty();
        Item newItem = new Item();
        newItem.setItemName(item.getItemName());
        newItem.setItemPrice(item.getItemPrice());
        newItem.setItemId(itemIdCounter++);
        itemCatalog.add(newItem);
        itemStock.put(newItem.getItemId(), 0L);
        return Optional.of(newItem);
    }

    public Optional<Long> addItemStock(Long itemId, Long additionalStock) {
        if (additionalStock==null || itemId==null || additionalStock < 0)
            return Optional.empty();

        itemStock.put(itemId, itemStock.get(itemId)+additionalStock);
        return Optional.of(itemStock.get(itemId));
    }

    public Optional<Long> removeItemStock(Long itemId, Long removeStock) {
        if (itemId==null || removeStock==null || removeStock < 0)
            return Optional.empty();
        Long newAmt = itemStock.get(itemId)-removeStock;
        if (newAmt < 0)
            newAmt = 0L;
        itemStock.put(itemId, newAmt);
        return Optional.of(newAmt);

    }

    public Optional<Long> clearAllItemStock(Long itemId) {
        if (itemId==null)
            return Optional.empty();
        Long stock = itemStock.get(itemId);
        itemStock.put(itemId, 0L);
        return Optional.of(stock);
    }

}

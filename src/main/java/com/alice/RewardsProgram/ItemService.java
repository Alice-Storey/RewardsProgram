package com.alice.RewardsProgram;

import com.alice.RewardsProgram.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface ItemService {

    public List<Item> getItemCatalog();

    public Optional<Item> getItemById(Long id) ;


    public Optional<Long> getStockOfItemById(Long id) ;


    public Optional<Item> addItemToCatalog(Item item);

    public Optional<Long> addItemStock(Long itemId, Long additionalStock);

    public Optional<Long> removeItemStock(Long itemId, Long removeStock);

    public Optional<Long> clearAllItemStock(Long itemId);

}

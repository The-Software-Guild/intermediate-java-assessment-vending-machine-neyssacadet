package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    //get 1 item
    Item getItem(String itemID);

    //get item inventory
    int getItemInventory(String itemID) throws VendingMachinePersistenceException;

    //get all items from inventory
    List<Item> getAllItems() throws VendingMachinePersistenceException;

    //remove one item from inventory
    void removeOneItemFromInventory(String itemName) throws VendingMachinePersistenceException;

    Map<String, BigDecimal> getItemNamesInStockWithCosts() throws VendingMachinePersistenceException;

    Map<String, Item> loadItems() throws VendingMachinePersistenceException;

    void writeItems() throws VendingMachinePersistenceException;

    List<String> getAllItemIds();

    public Item updateItem(String itemId, Item item);
}

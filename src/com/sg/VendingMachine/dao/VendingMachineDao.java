package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    Item getItem(String itemID);
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    void removeOneItemFromInventory(String itemName) throws VendingMachinePersistenceException;
    Map<String, BigDecimal> getItemNamesInStockWithCosts() throws VendingMachinePersistenceException;
    Map<String, Item> loadItems() throws VendingMachinePersistenceException;
    List<String> getAllItemIds();
    Item updateItem(String itemID, Item item);
    Item addItem(String itemID, Item item);
    void writeItems() throws VendingMachinePersistenceException;
    Item removeItem(String itemID);

}

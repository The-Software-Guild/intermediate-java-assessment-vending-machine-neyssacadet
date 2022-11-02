package com.sg.VendingMachine.service;

import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineService {
    public Map<String, Item> loadItemsInStock() throws VendingMachinePersistenceException;

    public void saveItemList() throws VendingMachinePersistenceException;

    public Item getChosenItem(String itemId) throws VendingMachinePersistenceException;

    public void updateItemSale(Item item) throws VendingMachineNoItemInventoryException;

    Change calculateChange(BigDecimal amount, Item item);

    public void validateItemInStock(String itemID) throws VendingMachineNoItemInventoryException;

    public void checkSufficientMoneyToBuyItem(BigDecimal inputAmount, Item item) throws VendingMachineInsufficientFundsException;

}

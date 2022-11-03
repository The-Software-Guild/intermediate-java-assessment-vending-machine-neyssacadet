package com.sg.VendingMachine.test.service;

import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {
    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("1");
        onlyItem.setItemName("Snickers");
        BigDecimal cost = new BigDecimal(2.55);
        onlyItem.setItemCost(cost);
        onlyItem.setItemInventory(5);
    }

    public VendingMachineDaoStubImpl(Item testItem){
        this.onlyItem = testItem;
    }

    @Override
    public Item getItem(String itemID) {
        if (itemID.equals(onlyItem.getItemID())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void removeOneItemFromInventory(String itemName) throws VendingMachinePersistenceException {

    }

    @Override
    public Map<String, BigDecimal> getItemNamesInStockWithCosts() throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Map<String, Item> loadItems() throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public List<String> getAllItemIds() {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item updateItem(String itemID, Item item) {
        return null;
    }

    @Override
    public Item addItem(String itemID, Item item) {
        if(itemID.equals(onlyItem.getItemID())){
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void writeItems() throws VendingMachinePersistenceException {

    }

    @Override
    public Item removeItem(String itemID) {
        if (itemID.equals(onlyItem.getItemID())) {
            return onlyItem;
        } else {
            return null;
        }
    }
}

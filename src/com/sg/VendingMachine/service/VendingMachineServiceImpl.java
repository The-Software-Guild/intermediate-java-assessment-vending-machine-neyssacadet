package com.sg.VendingMachine.service;

import com.sg.VendingMachine.dao.VendingMachineAuditDao;
import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendingMachineServiceImpl implements VendingMachineService {
    private final VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;


    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Change calculateChange(BigDecimal amount, Item item) {
        BigDecimal change = amount.subtract(item.getItemCost()).multiply(new BigDecimal(100));
        return new Change(change);
    }

    @Override
    public void checkSufficientMoneyToBuyItem(BigDecimal inputAmount, Item item) throws VendingMachineInsufficientFundsException {
        if (inputAmount.compareTo(item.getItemCost()) < 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient funds to buy " + item.getItemName());
        }
    }

    @Override
    public Map<String, Item> loadItemsInStock() throws VendingMachinePersistenceException {
        Map<String, Item> itemsInStock = new HashMap<>();
        for (Item item : dao.loadItems().values()) {
            if (item.getItemInventory() < 1) {
                auditDao.writeAuditEntry("Item Id: " + item.getItemID() + "quanity in stock is low");
            } else {
                itemsInStock.put(item.getItemID(), item);
            }
        }
        return itemsInStock;
    }

    @Override
    public void saveItemList() throws VendingMachinePersistenceException {
        dao.writeItems();
        auditDao.writeAuditEntry("Item list saved to file.");
    }

    @Override
    public Item getChosenItem(String itemId) throws VendingMachinePersistenceException {
        try {
            validateItemInStock(itemId);
        } catch (VendingMachineNoItemInventoryException ex) {
            Logger.getLogger(VendingMachineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dao.getItem(itemId);
    }

    @Override
    public void updateItemSale(Item item) throws VendingMachineNoItemInventoryException {
        if (item.getItemInventory() > 0) {
            item.setItemInventory(item.getItemInventory() - 1);
        } else {
            throw new VendingMachineNoItemInventoryException(" Selected Item is not in stock");
        }
        dao.updateItem(item.getItemID(), item);
        try {
            auditDao.writeAuditEntry("Item Id: " + item.getItemID() + "is updated");
        } catch (VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void validateItemInStock(String itemID) throws VendingMachineNoItemInventoryException {
        List<String> ids = dao.getAllItemIds();
        Item item = dao.getItem(itemID);
        if (!ids.contains(itemID) || (item.getItemInventory() < 1)) {
            throw new VendingMachineNoItemInventoryException("Selected Item is not in stock");
        }
    }
}

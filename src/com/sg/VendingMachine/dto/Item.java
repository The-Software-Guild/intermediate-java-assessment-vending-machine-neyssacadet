package com.sg.VendingMachine.dto;

import java.math.BigDecimal;

public class Item {
    private String itemName;
    private BigDecimal itemCost;
    private int itemInventory;

    public Item(String itemName, BigDecimal itemCost, int itemInventory) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemInventory = itemInventory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public int getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(int itemInventory) {
        this.itemInventory = itemInventory;
    }
}

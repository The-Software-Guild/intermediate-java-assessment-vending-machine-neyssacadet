package com.sg.VendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String itemID;
    private String itemName;
    private BigDecimal itemCost;
    private int itemInventory;

    //CONSTRUCTOR
    public Item(String itemID) {
        this.itemID = itemID;
    }

    public Item (String itemID, String itemName, BigDecimal itemCost, int itemInventory){
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemInventory = itemInventory;
    }

    public String getItemID() {
        return itemID;
    }

    //GETTER AND SETTER METHODS
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.itemID);
        hash = 79 * hash + Objects.hashCode(this.itemName);
        hash = 79 * hash + Objects.hashCode(this.itemCost);
        hash = 79 * hash + Objects.hashCode(this.itemInventory);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemInventory != other.itemInventory) {
            return false;
        }
        if (!Objects.equals(this.itemID, other.itemID)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + itemName + ", price=" + itemCost + ", ItemsInStock=" + itemInventory + '}';
    }

    public String marshalItemAsText() {
        return itemID + "::" + itemName + "::" + itemCost + "::" + itemInventory;
    }

}




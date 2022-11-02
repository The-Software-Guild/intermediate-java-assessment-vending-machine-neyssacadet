package com.sg.VendingMachine.ui;

import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Coins;
import com.sg.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingMachineView {
    private VendingMachineUserIO io = new VendingMachineUserIOImpl();

    public int printMenuAndGetSelection() {
        io.print("===Main Menu===");
        io.print("1. Select Item");
        io.print("2. Exit");

        return io.readInt("Please select one of the choice above", 1, 2);
    }

    public BigDecimal addMoney() {
        BigDecimal money = io.readBigDecimal("Please enter your amount in dollars.");
        io.print("You have added $" + money + "!");
        return money;
    }

    //GET ALL ITEMS
    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            if(currentItem.getItemInventory()> 0){
                String itemInfo = String.format("ID: %s, %s - Price: $%s, Inventory: %s",
                        currentItem.getItemID(),
                        currentItem.getItemName(),
                        currentItem.getItemCost(),
                        currentItem.getItemInventory());
                io.print(itemInfo);
            } else {
                String outOfStock = String.format("%s - OUT OF STOCK", currentItem.getItemName());
                io.print(outOfStock);
            }

        }
    }

    public int getItemSelection() {
        return io.readInt("Please select the item you would like to purchase.", 1, 9);
    }

    public BigDecimal displayItem(Item item) {
        if (item != null) {
            io.print("ID: " + item.getItemID() + " | " + item.getItemName() + " was selected. Price: $" + String.valueOf(item.getItemCost()));
        } else {
            io.print("no such item.");
        }
        io.readString("Please hit enter to confirm.");
        return item.getItemCost();
    }

    public void purchaseComplete(Item item) {
        io.print(item.getItemName() + " purchased!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR===");
        io.print(errorMsg);
    }

    public void displayUserMoneyInput(BigDecimal amount) {
        io.print("Refund of $" + amount + ".");
    }

    public void displayChange(Change rest){
        io.print("You have a change of: ");
        io.print(rest.getQuarters() + " quarter(s).");
        io.print(rest.getDimes() + " dime(s).");
        io.print(rest.getNickel() + " nickel(s).");
        io.print(rest.getPennies() + " pennie(s).");
    }

    //ALL  THE BANNERS
    public void vendingMachineBanner() {
        io.print("======SMART VENDING MACHINE======");
    }

    public void selectedItemBanner() {
        io.print("===SELECT ITEM===");
    }
    public void purchaseCompletedBanner() {
        io.print("===PURCHASE COMPLETE===");
    }

    public void changeDueBanner(){
        io.print("===CHANGE===");
    }

    public void exitMessageBanner() {
        io.print("GOOD BYE!");
    }

    public void unknownCommandBanner() {
        io.print("UNKNOWN COMMAND");
    }
}




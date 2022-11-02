package com.sg.VendingMachine.Controller;

import com.sg.VendingMachine.dao.VendingMachineDaoImpl;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.service.VendingMachineInsufficientFundsException;
import com.sg.VendingMachine.service.VendingMachineNoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineService;
import com.sg.VendingMachine.ui.VendingMachineUserIO;
import com.sg.VendingMachine.ui.VendingMachineUserIOImpl;
import com.sg.VendingMachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class Controller {
    private VendingMachineUserIO io = new VendingMachineUserIOImpl();
    private VendingMachineView view;
    private VendingMachineDaoImpl dao = new VendingMachineDaoImpl();
    private VendingMachineService service;
    BigDecimal moneyDeposit = new BigDecimal(0);

    public Controller(VendingMachineView view, VendingMachineService service) {
        this.service = service;
        this.view = view;
    }

    public void run() throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            view.vendingMachineBanner();
            listItems();
            getMoney();

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    selectItem(moneyDeposit);
                    io.print("ENJOY!");
                    keepGoing = false;
                    break;
                case 2:
                    io.print("REFUND OF $" + moneyDeposit );
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void getMoney() {
        moneyDeposit = view.addMoney();
    }

    //GET ALL ITEMS
    private void listItems() throws VendingMachinePersistenceException {
        List<Item> itemList = dao.getAllItems();
        view.displayItemList(itemList);
    }

    //SELECT 1 ITEM
    private void selectItem(BigDecimal amount) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        String itemID = String.valueOf(view.getItemSelection());
        Item item = dao.getItem(itemID);
        view.selectedItemBanner();
        view.displayItem(item);
        try {
            service.checkSufficientMoneyToBuyItem(moneyDeposit, item);
            Change rest = service.calculateChange(moneyDeposit, item);
            //removes from item from inventory
            dao.removeOneItemFromInventory(itemID);
            view.purchaseCompletedBanner();
            view.purchaseComplete(item);
            view.changeDueBanner();
            view.displayChange(rest);
        } catch (VendingMachineInsufficientFundsException ex) {
            service.validateItemInStock(itemID);
            displayErrorMessage(ex.getMessage());
            displayUserMoneyInput(amount);
        }
    }

    //EXIT MESSAGE
    private void exitMessage() {
        view.exitMessageBanner();
    }

    //UNKNOWN COMMAND
    private void unknownCommand() {
        view.unknownCommandBanner();
    }


    void displayUserMoneyInput(BigDecimal amount) {
        view.displayUserMoneyInput(amount);
    }

    void displayErrorMessage(String message) {
        view.displayErrorMessage(message);
    }

}



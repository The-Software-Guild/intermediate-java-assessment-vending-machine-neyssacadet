package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineDaoImpl implements VendingMachineDao {
    VendingMachineAuditDao audit = new VendingMachineAuditDaoImpl();
    //map where items are stored
    private Map<String, Item> item = new HashMap<>();

    //We want to get our item info from our file, we create 2 variables
    public final String ITEMS_FILE;
    public final String DELIMITER = "::";

    public VendingMachineDaoImpl(){
        ITEMS_FILE = "VendingMachineItems.txt";
    }

    public VendingMachineDaoImpl(String itemTextFile){
        ITEMS_FILE = itemTextFile;
    }

    //UNMARSHALLING PROCESS: getting data from string and storing it in a variable
    //AKA getting each item, cost, inventory from file
    public Item unmarshallItem(String itemAsText) {
        //creating arrays with strings by splitting them with the delimiter, now each string has an index
        String[] fields = itemAsText.split(DELIMITER);
        String itemID = fields[0];
        Item itemFromFile = new Item(itemID);
        itemFromFile.setItemName(fields[1]);
        itemFromFile.setItemCost(new BigDecimal(fields[2]));
        itemFromFile.setItemInventory(Integer.parseInt(fields[3]));
        return itemFromFile;
    }

    //this loads file into memory
    public Map<String, Item> loadItems() throws VendingMachinePersistenceException {
        Scanner scan;
        try {
            //Create Scanner for reading the file
            scan = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- could not load items data into memory", e
            );
        }
        //most recent line read
        String currentLine;
        //current item read
        Item currentItem;

        while (scan.hasNextLine()) {
            //get the next line in the file
            currentLine = scan.nextLine();
            currentItem = unmarshallItem(currentLine);
            item.put(currentItem.getItemID(), currentItem);
        }
        //close scanner
        scan.close();
        return item;
    }

    //MARSHALLING PROCESS: in order to edit the inventory
    private String marshallItem(Item anItem) {
        String itemAsText = anItem.getItemID() + DELIMITER;
        itemAsText += anItem.getItemName() + DELIMITER;
        itemAsText += anItem.getItemCost() + DELIMITER;
        itemAsText += anItem.getItemInventory();
        return itemAsText;
    }

    public void writeItems() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        //Clean up
        out.close();
    }

    //DONE
    @Override
    public Item getItem(String itemID) {
        return item.get(itemID);
    }

    @Override
    public int getItemInventory(String name) throws VendingMachinePersistenceException {
        loadItems();
        return item.get(name).getItemInventory();
    }

    //DONE: GET ALL ITEMS
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems();
        return new ArrayList<>(item.values());
    }

    //DONE - REMOVE 1 ITEM FROM INVENTORY
    @Override
    public void removeOneItemFromInventory(String itemID) throws VendingMachinePersistenceException {
        loadItems();
        int prevInventory = item.get(itemID).getItemInventory();
        item.get(itemID).setItemInventory(prevInventory - 1);
        writeItems();
        audit.writeAuditEntry("one " + item.get(itemID).getItemName() + " removed from the inventory" );

    }

    @Override
    public Map<String, BigDecimal> getItemNamesInStockWithCosts() throws VendingMachinePersistenceException {
        loadItems();
        //Return a list of the items names where the item inventory
        //is greater than 0, i.e. get the keys where the inventory>0

        Map<String, BigDecimal> itemsInStockWithCosts = item.entrySet()
                .stream()
                .filter(map -> map.getValue().getItemInventory() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getItemCost()));

        return itemsInStockWithCosts;

    }

    @Override
    public List<String> getAllItemIds() {
        return new ArrayList<>(item.keySet());
    }

    //updates item from storage to memory
    @Override
    public Item updateItem(String itemID, Item item) {
        return null; //item.replace(itemID, item);
    }
}

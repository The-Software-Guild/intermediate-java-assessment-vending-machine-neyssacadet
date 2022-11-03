package com.sg.VendingMachine.test.dao;

import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoImpl;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Item;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoImplTest {
    VendingMachineDao testDao;
    @BeforeAll
    public static void setUpItem() {
    }

    @AfterAll
    public static void tearDownItem(){
    }

    @BeforeEach
    public void setUp() throws VendingMachinePersistenceException, IOException {
        String testFile = "testItems.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @AfterEach
    void tearDown() {
    }
//METHODS FOR TESTING
    @Test
    public void testAddGetItem() throws Exception {
        // Create our method test inputs
        String ItemID = "1";
        Item item = new Item(ItemID);
        item.setItemName("Snickers");
        BigDecimal cost = new BigDecimal(2.19);
        item.setItemCost(cost);
        item.setItemInventory(5);

        //  Add the student to the DAO
        testDao.addItem(ItemID, item);
        // Get the student from the DAO
        Item retrievedItem = testDao.getItem(ItemID);

        // Check the data is equal
        assertEquals(item.getItemID(),
                retrievedItem.getItemID(),
                "Checking item id.");
        assertEquals(item.getItemName(),
                retrievedItem.getItemName(),
                "Checking item name.");
        assertEquals(item.getItemCost(),
                retrievedItem.getItemCost(),
                "Checking item cost.");
        assertEquals(item.getItemInventory(),
                retrievedItem.getItemInventory(),
                "Checking item inventory.");
    }

    @Test
    public void testAddGetAllItems() throws Exception {
        // Create our first item
        Item I1 = new Item("1");
        BigDecimal cost = new BigDecimal(2.99);
        I1.setItemName("Snickers");
        I1.setItemCost(cost);
        I1.setItemInventory(5);

        // Create our second item
        Item I2 = new Item("2");
        BigDecimal price = new BigDecimal(1.99);
        I2.setItemName("Cheetos");
        I2.setItemCost(price);
        I2.setItemInventory(10);

        // Add both our items to the DAO
        testDao.addItem(I1.getItemID(), I1);
        testDao.addItem(I2.getItemID(), I2);

        // Retrieve the list of all items within the DAO
        List<Item> allItem = testDao.getAllItems();

        // First check the general contents of the list
        assertNotNull(allItem, "The list of Items must not null");
        assertEquals(2, allItem.size(),"List of Items should have 2 Items.");

        // Then the specifics
        assertTrue(testDao.getAllItems().contains(I1),
                "The list of items should include Snickers.");
        assertTrue(testDao.getAllItems().contains(I2),
                "The list of items should include Cheetos.");

    }


    @Test
    public void testRemoveItem() throws Exception {
        // Create two new Items
        Item I1 = new Item("1");
        BigDecimal cost = new BigDecimal(2.99);
        I1.setItemName("Snickers");
        I1.setItemCost(cost);
        I1.setItemInventory(5);

        Item I2 = new Item("2");
        BigDecimal price = new BigDecimal(1.99);
        I2.setItemName("Cheetos");
        I2.setItemCost(price);
        I2.setItemInventory(10);

        // Add both to the DAO
        testDao.addItem(I1.getItemID(), I1);
        testDao.addItem(I2.getItemID(), I2);

        // remove the first Item
        Item removedItem = testDao.removeItem(I1.getItemID());

        // Check that the correct object was removed.
        assertEquals(removedItem, I1, "The removed Item should be Snickers.");

        // Get all the students
        List<Item> allItems = testDao.getAllItems();

        // First check the general contents of the list
        assertNotNull( allItems, "All items list should be not null.");
        assertEquals( 1, allItems.size(), "All items should only have 1 item.");

        // Then the specifics
        assertFalse( allItems.contains(I1), "All items should NOT include Snickers.");
        assertTrue( allItems.contains(I2), "All items should NOT include Cheetos.");

        // Remove the second student
        removedItem = testDao.removeItem(I2.getItemID());
        // Check that the correct object was removed.
        assertEquals( removedItem, I2, "The removed item should be Cheetos.");

        // retrieve all of the items again, and check the list.
        allItems = testDao.getAllItems();

        // Check the contents of the list - it should be empty
        assertTrue( allItems.isEmpty(), "The retrieved list of items should be empty.");

        // they should be null!
        Item retrievedItem = testDao.getItem(I1.getItemID());
        assertNull(retrievedItem, "Snickers was removed, should be null.");

        retrievedItem = testDao.getItem(I2.getItemID());
        assertNull(retrievedItem, "Cheetos was removed, should be null.");

    }

    @Test
    public void testUpdateItem(){
        System.out.println("updateItem");
        BigDecimal bd = new BigDecimal("2.50");
        Item I1 = new Item("1", "cake", bd, 10);
        bd = new BigDecimal("1.05");
        testDao.addItem(I1.getItemID(), I1);
        I1.setItemName("water");
        I1.setItemCost(bd);
        I1.setItemInventory(12);
        testDao.updateItem(I1.getItemID(), I1);
        //result
        Item result = testDao.updateItem(I1.getItemID(), I1);
        //expected result
        Item expResult = new Item("1", "water", bd, 12);
        //assert
        assertEquals(expResult, result, "update item is water");
        assertEquals("1", result.getItemID(), "update product id is 1");
        assertEquals("water", result.getItemName(), "update product name");
        assertEquals(bd, result.getItemCost(), "updated item price is 1.05");
        assertEquals(12, result.getItemInventory(), "update items in stock");
    }

    @Test
    public void testGetAllItemIds(){
        System.out.println("getAllItemIds");
        BigDecimal bd = new BigDecimal("2.50");
        Item I1 = new Item("1", "cake", bd, 10);
        bd = new BigDecimal("1.05");
        Item I2 = new Item("2", "water", bd, 12);
        testDao.addItem(I1.getItemID(), I1);
        testDao.addItem(I2.getItemID(), I2);
        //result
        List<String> result = testDao.getAllItemIds();
        //excepted result
        List<String> expResult = new ArrayList();
        expResult.add("1");
        expResult.add("2");
        //assert
        assertNotNull(result, "The List of Items ids must not be null");
        assertEquals(2, result.size(), "List of item ids should be 2");
        assertTrue(result.contains(I1.getItemID()), "The list of items should include cake and water");
        assertTrue(result.contains(I2.getItemID()), "2 lists os items should be the same");
        assertEquals(expResult, result, "2 lists of items should be the same");
    }

}
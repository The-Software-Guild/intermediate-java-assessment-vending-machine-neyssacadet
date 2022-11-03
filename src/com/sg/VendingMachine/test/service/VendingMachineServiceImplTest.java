package com.sg.VendingMachine.test.service;

import com.sg.VendingMachine.dao.*;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.service.VendingMachineInsufficientFundsException;
import com.sg.VendingMachine.service.VendingMachineNoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineService;
import com.sg.VendingMachine.service.VendingMachineServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceImplTest {

    VendingMachineDao dao = new VendingMachineDaoImpl();
    VendingMachineAuditDao auditdao = new VendingMachineAuditDaoImpl();
    VendingMachineService testService = new VendingMachineServiceImpl(dao, auditdao);

    public VendingMachineServiceImplTest() {

    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCalculateChange() throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        System.out.println("calculateChange");
        Item I2 = new Item("2");
        BigDecimal price = new BigDecimal(5);
        I2.setItemName("Cheetos");
        I2.setItemCost(price);
        I2.setItemInventory(10);

        Change result = testService.calculateChange(I2.getItemCost(), I2);
        //assert
        assertEquals(0, result.getQuarters(), "Check number of quarters");
        assertEquals(0, result.getDimes(), "Check number of dimes");
        assertEquals(0, result.getNickel(), "Check number of nickels");
        assertEquals(0, result.getPennies(), "Check number of pennies");

    }

    @Test
    public void testCheckSufficientMoneyToBuyItem() {
        System.out.println("checkSufficientFunds");
        //ARRANGE
        Item hariboClone = new Item("2");
        hariboClone.setItemCost(new BigDecimal("1.60"));
        hariboClone.setItemInventory(9);

        BigDecimal enoughMoney = new BigDecimal("2.00");
        BigDecimal notEnoughMoney = new BigDecimal("1.59");

        //ACT - enough money
        try {
            testService.checkSufficientMoneyToBuyItem(enoughMoney, hariboClone);
        } catch (VendingMachineInsufficientFundsException e){
            fail("There is sufficient funds, exception should not have been thrown");
        }
        //ACT - not enough money
        try {
            testService.checkSufficientMoneyToBuyItem(notEnoughMoney, hariboClone);
            fail("There insufficient funds, exception should have been thrown");
        } catch (VendingMachineInsufficientFundsException e){
        }
    }

    @Test
    public void testGetChosenItem() throws VendingMachinePersistenceException {
        System.out.println("getChosenItem");
        Item expResult = new Item("1");
        Item result = new Item("1");
        assertEquals(expResult, result, "Check both items equal.");
        assertEquals(expResult.getItemName(), result.getItemName(), "Check both items");
    }

    @Test
    void testvalidateItemInStock() throws VendingMachineNoItemInventoryException {
        System.out.println("validateItemInStock");
        String itemID = "2";
        Item I2 = new Item(itemID);
        BigDecimal price = new BigDecimal(5);
        I2.setItemName("Cheetos");
        I2.setItemCost(price);
        I2.setItemInventory(0);

        try {
            testService.validateItemInStock(itemID);
            fail("Selected item is not in stock");
        } catch (VendingMachineNoItemInventoryException e){

        }
    }

}
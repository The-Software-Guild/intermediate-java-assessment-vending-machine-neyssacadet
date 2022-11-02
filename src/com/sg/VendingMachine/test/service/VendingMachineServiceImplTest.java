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

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceImplTest {

    public VendingMachineService testService;

    public VendingMachineServiceImplTest() {
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineAuditDao auditdao = new VendingMachineAuditDaoImpl();
        testService = new VendingMachineServiceImpl(dao, auditdao);
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
        BigDecimal amount = new BigDecimal(5);
        Item item = testService.getChosenItem("1");
        Change result = testService.calculateChange(amount, item);
        //assert
        assertEquals(10, result.getQuarters(), "Check number of quarters");
        assertEquals(0, result.getDimes(), "Check number of dimes");
        assertEquals(0, result.getNickel(), "Check number of nickels");
        assertEquals(0, result.getPennies(), "Check number of pennies");

    }

    @Test
    public void testCheckSufficientMoneyToBuyItem(BigDecimal inputAmount, Item item) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException, VendingMachinePersistenceException {
        System.out.println("checkSufficientMoneyToBuyItem");
        inputAmount = new BigDecimal(3.50);
        item = testService.getChosenItem("1");
        testService.checkSufficientMoneyToBuyItem(inputAmount, item);
    }

    @Test
    void loadItemsInStock() {
    }

    @Test
    void saveItemList() {
    }

    @Test
    void getChosenItem() {
    }

    @Test
    void updateItemSale() {
    }

    @Test
    void validateItemInStock() {
    }
}
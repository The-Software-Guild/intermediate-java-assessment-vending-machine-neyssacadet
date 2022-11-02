package com.sg.VendingMachine.test;

import com.sg.VendingMachine.dao.*;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.service.VendingMachineNoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineService;
import com.sg.VendingMachine.service.VendingMachineServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineServiceImplTest {
    public VendingMachineService testService;

    public VendingMachineServiceImplTest() {
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineAuditDao auditdao = new VendingMachineAuditDaoImpl();
        testService = new VendingMachineServiceImpl(dao, auditdao);
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
        BigDecimal amount = new BigDecimal("5");
        Item item = testService.getChosenItem(".1");
        Change result = testService.calculateChange(amount, item);
        //assert
        assertEquals(10, result.getQuarters(), "Check number of quarters");
        assertEquals(0, result.getDimes(), "Check number of dimes");
        assertEquals(0, result.getNickel(), "Check number of nickels");
        assertEquals(0, result.getPennies(), "Check number of pennies");

    }

    @Test
    void checkSufficientMoneyToBuyItem() {
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
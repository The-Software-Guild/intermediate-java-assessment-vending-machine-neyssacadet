package com.sg.VendingMachine.test;

import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoImpl;
import com.sg.VendingMachine.dto.Item;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoImplTest {

    VendingMachineDao testDao;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testitems.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }


}
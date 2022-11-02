package com.sg.VendingMachine.test;

import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoImpl;
import org.junit.jupiter.api.*;

import java.io.FileWriter;

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
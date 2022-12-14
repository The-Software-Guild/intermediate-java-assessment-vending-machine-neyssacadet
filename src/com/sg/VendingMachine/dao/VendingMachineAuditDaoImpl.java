package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dao.VendingMachinePersistenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {
    private final String AUDIT_FILE;

    //Default constructor
    public VendingMachineAuditDaoImpl() {
        this.AUDIT_FILE = "audit.txt";
    }

    //Constructor for testing
    public VendingMachineAuditDaoImpl(String auditTestFile) {
        this.AUDIT_FILE = auditTestFile;
    }


    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not persist audit information", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}

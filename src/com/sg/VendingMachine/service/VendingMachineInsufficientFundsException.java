package com.sg.VendingMachine.service;

public class VendingMachineInsufficientFundsException extends Exception {
    public VendingMachineInsufficientFundsException(String message) {
        super(message);
    }

    public VendingMachineInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

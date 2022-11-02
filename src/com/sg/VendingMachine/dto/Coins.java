package com.sg.VendingMachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Coins {
    PENNY(new BigDecimal(0.01)),
    NICKEL(new BigDecimal(0.05)),
    DIME(new BigDecimal(0.1)),
    QUARTERS(new BigDecimal(0.25));

    //we want to place final for this variable so no one can have the access to change the value
    private final BigDecimal value;

    //Constructor
    private Coins(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.DOWN);
    }
}

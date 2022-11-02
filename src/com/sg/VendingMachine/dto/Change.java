package com.sg.VendingMachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Change {
    private int quarters;
    private int dimes;
    private int nickel;
    private int pennies;

    public Change(BigDecimal amount) {
        this.quarters = amount.divide(new BigDecimal("25")).intValue();
        amount = amount.remainder(new BigDecimal("25"));
        this.dimes = amount.divide(new BigDecimal("10")).intValue();
        amount = amount.remainder(new BigDecimal("10"));
        this.nickel = amount.divide(new BigDecimal("5")).intValue();
        this.pennies = amount.divide(new BigDecimal("1")).intValue();

    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickel() {
        return nickel;
    }

    public int getPennies() {
        return pennies;
    }
}
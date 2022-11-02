package com.sg.VendingMachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineUserIOImpl implements VendingMachineUserIO {
    final private Scanner scan = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scan.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scan.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            System.out.println(prompt);
            num = Integer.parseInt(scan.nextLine());
        }
        while (num < min || num > max);

        return num;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(scan.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            System.out.println(prompt);
            num = scan.nextInt();
        }
        while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return scan.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            System.out.println(prompt);
            num = scan.nextFloat();
        }
        while (num < min || num > max);

        return num;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return scan.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            System.out.println(prompt);
            num = scan.nextLong();
        }
        while (num < min || num > max);

        return num;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return new BigDecimal(scan.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal num;
        do {
            System.out.println(prompt);
            num = new BigDecimal(scan.nextLine());
        }
        while (num.doubleValue() < min.doubleValue() || num.doubleValue() > max.doubleValue() || num == null);

        return num;
    }
}

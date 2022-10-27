package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOImpl implements UserIO{
    //scanner for User input
    final private Scanner console = new Scanner(System.in);

    //shortcut to print whatever we want in the console
    @Override
    public void print(String message) {
        System.out.println(message);
    }
    //shortcut to return the User's Input as a string
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine(); //returns user's input
    }
    //shortcut to convert the string to an integer and return the integer
    @Override
    public int readInt(String prompt) {
        //initializing some variables
        boolean invalidInput = true;
        int number = 0;
        while(invalidInput){
            try{
                String stringValue = this.readString(prompt); //stores user input into a string
                number = Integer.parseInt(stringValue); //converts string to integer
            } //what if the string is not able to convert into a integer?
            catch (NumberFormatException e){
                this.print("Error. Please try again.");
            }
        }
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do{
            result = readInt(prompt); //stores number into variable
        } while (result < min || result > max);
        return result;
    }

    @Override
    public double readDouble(String prompt) {
        boolean invalidInput = true;
        double number = 0.0;
        while (invalidInput){
            try{
                String stringValue = this.readString(prompt);
                number = Double.parseDouble(stringValue);
            } catch (NumberFormatException e){
                this.print("Error. Please try again.");
            }
        }
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        } while( result < min || result > max);
        return result;
    }

    @Override
    public float readFloat(String prompt) {
        boolean invalidInput = true;
        float number = 0.0f;
        while (invalidInput) {
            try {
                String stringValue = this.readString(prompt);
                number = Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                this.print("Error. Please try again.");
            }
        }
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float result;
        do {
            result = readFloat(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public long readLong(String prompt)
    {
        this.print(prompt);
        return console.nextLong();
    }
    @Override
    public long readLong(String prompt, long min, long max)
    {
        long num;
        do {
            this.print(prompt);
            num = console.nextLong();
        }
        while(num < min || num > max);

        return num;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        this.print(prompt);
        return new BigDecimal(console.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal num;
        do {
            this.print(prompt);
            num = new BigDecimal(console.nextLine());
        }
        while(num.doubleValue() < min.doubleValue() || num.doubleValue() > max.doubleValue() || num==null);

        return num;
    }
}

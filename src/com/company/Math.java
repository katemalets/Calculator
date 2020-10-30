package com.company;

public class Math {

    int firstNumber;
    int secondNumber;
    char operator;

    public Math(int firstNumber, int secondNumber, char operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    public int calculate() {
        if (operator == '/')
            return division();
        else if (operator == ('*'))
            return multiplication();
        else if (operator == ('+'))
            return sum();
        else if (operator == ('-'))
            return subtraction();
        else {
            System.out.println("Calculator does not support such operator: " + operator);
            System.exit(1);
            return -1;
        }
    }

    private int sum() {
        return firstNumber + secondNumber;
    }

    private int subtraction() {
        return firstNumber - secondNumber;
    }

    private int division() {
        return firstNumber / secondNumber;
    }

    private int multiplication() {
        return firstNumber * secondNumber;
    }
}

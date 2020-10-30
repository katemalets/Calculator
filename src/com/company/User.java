package com.company;

import java.util.List;
import java.util.Scanner;

public class User {

    String expression;

    public void inputExpression() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input an expression!");
        String expression = input.nextLine();
        input.close();
        this.expression = expression;
        System.out.println(calculate());
    }

    private String calculate() {
        String[] expressionSymbols = parseExpression();
        if (expressionSymbols.length != 3) throw new IllegalNumberFormatException("Incorrect entered values");
        int firstNumber;
        int secondNumber;
        char operator;
        if (expressionSymbols[0].chars().allMatch(Character::isDigit)
                && expressionSymbols[2].chars().allMatch(Character::isDigit)) {
            firstNumber = Integer.parseInt(expressionSymbols[0]);
            secondNumber = Integer.parseInt(expressionSymbols[2]);
            checkTheEnteredRange(firstNumber, secondNumber);
            operator = expressionSymbols[1].charAt(0);
            return String.valueOf(new Math(firstNumber, secondNumber, operator).calculate());
        } else {
            firstNumber = romanToArabic(expressionSymbols[0]);
            secondNumber = romanToArabic(expressionSymbols[2]);
            checkTheEnteredRange(firstNumber, secondNumber);
            operator = expressionSymbols[1].charAt(0);
            return arabicToRoman(new Math(firstNumber, secondNumber, operator).calculate());
        }
    }

    private String[] parseExpression() {
        String expression = this.expression;
        return expression.split(" ");
    }

    private void checkTheEnteredRange(int firstNumber, int secondNumber) {
        if ((firstNumber > 10 || secondNumber > 10 || firstNumber < 1 || secondNumber < 1)) {
            throw new IllegalNumberFormatException("You should put the value between 1 and 10");
        }
    }

    private int romanToArabic(String expression) {
        String romanNumeral = expression.toUpperCase();
        int number = 0;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                number += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return number;
    }

    private String arabicToRoman(int number) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}

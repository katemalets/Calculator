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
    }

    private String calculate() {

        String[] expressionSymbols = parseExpression();
        if (expressionSymbols.length != 3) throw new IllegalNumberFormatException("Incorrect entered values");

        if (expressionSymbols[0].chars().allMatch(Character::isDigit)
                && expressionSymbols[2].chars().allMatch(Character::isDigit)) return calculateArabic(expressionSymbols);
        else return calculateRoman(expressionSymbols);
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

    private String calculateArabic(String[] expressionSymbols){
        int firstNumber = Integer.parseInt(expressionSymbols[0]);
        int secondNumber = Integer.parseInt(expressionSymbols[2]);
        checkTheEnteredRange(firstNumber, secondNumber);
        char operator = expressionSymbols[1].charAt(0);
        return String.valueOf(new Math(firstNumber, secondNumber, operator).calculate());
    }

    private String calculateRoman(String[] expressionSymbols){
        int firstNumber = romanToArabic(expressionSymbols[0]);
        int secondNumber = romanToArabic(expressionSymbols[2]);
        checkTheEnteredRange(firstNumber, secondNumber);
        char operator = expressionSymbols[1].charAt(0);
        return arabicToRoman(new Math(firstNumber, secondNumber, operator).calculate());
    }

    private int romanToArabic(String romanNumber) {

        String romanNumeral = romanNumber.toUpperCase();
        int arabicNumber = 0;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                arabicNumber += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return arabicNumber;
    }

    private String arabicToRoman(int number) {

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        StringBuilder romanNumber = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                romanNumber.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return romanNumber.toString();
    }

    public String getResult() {
        return calculate();
    }
}

package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("_____CALCULATOR_____");
        User user = new User();
        user.inputExpression();
        System.out.println(user.getResult());
    }
}

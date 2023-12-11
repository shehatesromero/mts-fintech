package ru.mts;

public class Main {

    public static void main(String[] args) {
        Price milk = new Price(2, 90, 0.75);
        milk.calculateSum();

        Price crisps = new Price(6, 140.95, 42.575);
        crisps.calculateSum();

        Price chocolate = new Price(4, 79.99, 59.1);
        chocolate.calculateSum();
    }
}

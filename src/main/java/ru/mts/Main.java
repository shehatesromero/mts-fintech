package ru.mts;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Price milk = new Price(2, 90, 0.75);
        processingObject(milk);

        Price crisps = new Price(6, 140.95, 42.575);
        processingObject(crisps);

        Price chocolate = new Price(4, 79.99, 59.1);
        processingObject(chocolate);
    }

    public static void processingObject(Price product) {
        BigDecimal totalSumOfProducts = new BigDecimal(product.getSumOfProducts() * product.getNumberOfProducts());
        BigDecimal Discount =
                new BigDecimal(((product.getSumOfProducts() * product.getNumberOfProducts()) * product.getDiscountOnProduct()) / 100.0);
        BigDecimal totalSumWithDiscount = totalSumOfProducts.subtract(Discount);
        System.out.println("Сумма покупки: " + totalSumOfProducts.setScale(2, BigDecimal.ROUND_UP));
        System.out.println("Сумма покупки со скидкой: " + totalSumWithDiscount.setScale(2, BigDecimal.ROUND_UP));

    }
}

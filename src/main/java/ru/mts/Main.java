package ru.mts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

    public static void main(String[] args) {
        OrderLine milk = new OrderLine(2, new BigDecimal(90), new BigDecimal(0.75));
        processingObject(milk);

        OrderLine crisps = new OrderLine(6, new BigDecimal(140.95), new BigDecimal(42.575));
        processingObject(crisps);

        OrderLine chocolate = new OrderLine(4, new BigDecimal(79.99), new BigDecimal(59.1));
        processingObject(chocolate);
    }

    public static void processingObject(OrderLine product) {
        BigDecimal totalSumOfProducts = product.getSumOfProducts().multiply(new BigDecimal(product.getNumberOfProducts()));
        BigDecimal Discount = product.getSumOfProducts()
                .multiply(new BigDecimal(product.getNumberOfProducts()))
                .multiply(product.getDiscountOnProduct())
                .divide(new BigDecimal(100.0));
        BigDecimal totalSumWithDiscount = totalSumOfProducts.subtract(Discount).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Сумма покупки: " + totalSumOfProducts);
        System.out.println("Сумма покупки со скидкой: " + totalSumWithDiscount);
    }
}

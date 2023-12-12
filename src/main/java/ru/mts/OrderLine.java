package ru.mts;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс Price содержит 3 параметра:
 * 1) количество товаров
 * 2) сумма товара
 * 3) скидка на товар
 * А также имеет 2 конструктора, геттеры и сеттеры
 */
public class OrderLine {

    int numberOfProducts; // количество товаров
    BigDecimal sumOfProducts; // сумма товара
    BigDecimal discountOnProduct; // скидка на товар

    /**
     * @param numberOfProducts  Количество товаров
     * @param sumOfProducts     Сумма товаров
     * @param discountOnProduct Скидка на товар
     */
    public OrderLine(int numberOfProducts, BigDecimal sumOfProducts, BigDecimal discountOnProduct) {
        if (numberOfProducts <= 0 || sumOfProducts.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Количество товаров и сумма товаров должны быть больше 0");
        }
        this.numberOfProducts = numberOfProducts;
        this.sumOfProducts = sumOfProducts.setScale(2, RoundingMode.HALF_UP);
        this.discountOnProduct = discountOnProduct.setScale(2, RoundingMode.HALF_UP);
    }

    public OrderLine(int numberOfProducts, BigDecimal sumOfProducts) {
        if (numberOfProducts <= 0 || sumOfProducts.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Количество товаров и сумма товаров должны быть больше 0");
        }
        this.numberOfProducts = numberOfProducts;
        this.sumOfProducts = sumOfProducts.setScale(2, RoundingMode.HALF_UP);
    }

    //Геттеры
    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public BigDecimal getSumOfProducts() {
        return sumOfProducts;
    }

    public BigDecimal getDiscountOnProduct() {
        return discountOnProduct;
    }

    //Сеттеры
    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setSumOfProducts(BigDecimal sumOfProducts) {
        this.sumOfProducts = sumOfProducts.setScale(2, RoundingMode.HALF_UP);
    }

    public void setDiscountOnProduct(BigDecimal discountOnProduct) {
        this.discountOnProduct = discountOnProduct.setScale(2, RoundingMode.HALF_UP);
    }
}

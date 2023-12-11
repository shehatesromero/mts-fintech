package ru.mts;


/**
 * Класс Price содержит 3 параметра:
 * 1) количество товаров
 * 2) сумма товара
 * 3) скидка на товар
 * А также имеет 2 конструктора, геттеры и сеттеры
 */
public class Price {

    int numberOfProducts; // количество товаров
    double sumOfProducts; // сумма товара
    double discountOnProduct; // скидка на товар

    /**
     * @param numberOfProducts  Количество товаров
     * @param sumOfProducts     Сумма товаров
     * @param discountOnProduct Скидка на товар
     */
    public Price(int numberOfProducts, double sumOfProducts, double discountOnProduct) {
        if (numberOfProducts <= 0 || sumOfProducts <= 0) {
            throw new IllegalArgumentException("Количество товаров и сумма товаров должны быть больше 0");
        }
        this.numberOfProducts = numberOfProducts;
        this.sumOfProducts = sumOfProducts;
        this.discountOnProduct = discountOnProduct;
    }

    public Price(int numberOfProducts, double sumOfProducts) {
        if (numberOfProducts <= 0 || sumOfProducts <= 0) {
            throw new IllegalArgumentException("Количество товаров и сумма товаров должны быть больше 0");
        }
        this.numberOfProducts = numberOfProducts;
        this.sumOfProducts = sumOfProducts;
    }

    //Геттеры
    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public double getSumOfProducts() {
        return sumOfProducts;
    }

    public double getDiscountOnProduct() {
        return discountOnProduct;
    }

    //Сеттеры
    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setSumOfProducts(double sumOfProducts) {
        this.sumOfProducts = sumOfProducts;
    }

    public void setDiscountOnProduct(double discountOnProduct) {
        this.discountOnProduct = discountOnProduct;
    }
}

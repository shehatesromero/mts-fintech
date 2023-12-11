package ru.mts;

/**
 * Класс Price содержит 3 параметра:
 * 1) количество товаров
 * 2) сумма товара
 * 3) скидка на товар
 */
public class Price {

    int numberOfProducts; // количество товаров
    double sumOfProducts; // сумма товара
    double discountOnProduct; // скидка на товар

    /**
     *
     * @param numberOfProducts Количество товаров
     * @param sumOfProducts Сумма товаров
     * @param discountOnProduct на товар
     */
    public Price(int numberOfProducts, double sumOfProducts, double discountOnProduct) {
        this.numberOfProducts = numberOfProducts;
        this.sumOfProducts = sumOfProducts;
        this.discountOnProduct = discountOnProduct;
    }

    public void calculateSum() {
        double totalSumOfProducts = sumOfProducts*numberOfProducts;
        double totalSumWithDiscount =
                totalSumOfProducts - ((sumOfProducts*numberOfProducts)*discountOnProduct)/100.0;
        System.out.println("Сумма покупки: " + String.format("%.2f", totalSumOfProducts));
        System.out.println("Сумма покупки со скидкой: " + String.format("%.2f", totalSumWithDiscount));
    }
}

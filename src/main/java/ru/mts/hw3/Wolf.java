package ru.mts.hw3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс Wolf представляет собой конкретную реализацию хищного животного - волка,
 * и является подклассом класса Predator. Класс включает конструктор для инициализации
 * полей породы, имени и стоимости волка, а также переопределение метода toString()
 * для удобного вывода информации о нем.
 */
public class Wolf extends Predator {
    public Wolf(String breed, String name, BigDecimal cost) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}

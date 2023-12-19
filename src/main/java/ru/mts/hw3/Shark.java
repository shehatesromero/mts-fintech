package ru.mts.hw3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс Shark представляет собой конкретную реализацию хищного животного - акулы,
 * и является подклассом класса Predator. Класс включает конструктор для инициализации
 * полей породы, имени и стоимости акулы, а также переопределение метода toString()
 * для удобного вывода информации о ней.
 */
public class Shark extends Predator {
    @SuppressWarnings("unused")
    public Shark(String breed, String name, BigDecimal cost, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
    }

    public Shark(String breed, String name, BigDecimal cost) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}

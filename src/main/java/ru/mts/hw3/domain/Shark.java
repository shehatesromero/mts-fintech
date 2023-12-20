package ru.mts.hw3.domain;

import ru.mts.hw3.domain.abstraction.Predator;

import java.math.BigDecimal;

/**
 * Класс Shark представляет собой конкретную реализацию хищного животного - акулы,
 * и является подклассом класса Predator. Класс включает конструктор для инициализации
 * полей породы, имени и стоимости акулы, а также переопределение метода toString()
 * для удобного вывода информации о ней.
 */
public class Shark extends Predator {

    @SuppressWarnings("unused")
    public Shark(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    public Shark(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

}

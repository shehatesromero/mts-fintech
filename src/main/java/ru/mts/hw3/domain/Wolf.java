package ru.mts.hw3.domain;

import ru.mts.hw3.domain.abstraction.Predator;

import java.math.BigDecimal;

/**
 * Класс Wolf представляет собой конкретную реализацию хищного животного - волка,
 * и является подклассом класса Predator. Класс включает конструктор для инициализации
 * полей породы, имени и стоимости волка, а также переопределение метода toString()
 * для удобного вывода информации о нем.
 */
public class Wolf extends Predator {

    @SuppressWarnings("unused")
    public Wolf(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    public Wolf(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

}

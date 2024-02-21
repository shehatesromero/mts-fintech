package ru.mts.hw7.domain;

import ru.mts.hw7.domain.abstraction.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Wolf представляет собой конкретную реализацию хищного животного - волка,
 * и является подклассом класса Predator. Класс включает конструктор для инициализации
 * полей породы, имени и стоимости волка, а также переопределение метода toString()
 * для удобного вывода информации о нем.
 */
public class Wolf extends Predator {

    @SuppressWarnings("unused")
    public Wolf(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public Wolf(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    public Wolf() {
    }

    @Override
    public String toString() {
        return "Wolf{" +
                super.toString() +
                '}';
    }

}

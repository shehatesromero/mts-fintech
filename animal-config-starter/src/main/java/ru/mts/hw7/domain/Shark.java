package ru.mts.hw7.domain;

import ru.mts.hw7.domain.abstraction.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Shark представляет собой конкретную реализацию хищного животного - акулы,
 * и является подклассом класса Predator. Класс включает конструктор для инициализации
 * полей породы, имени и стоимости акулы, а также переопределение метода toString()
 * для удобного вывода информации о ней.
 */
public class Shark extends Predator {

    @SuppressWarnings("unused")
    public Shark(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public Shark(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    public Shark() {
    }

    @Override
    public String toString() {
        return "Shark{" +
                super.toString() +
                '}';
    }

}

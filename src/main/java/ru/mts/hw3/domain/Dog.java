package ru.mts.hw3.domain;

import ru.mts.hw3.domain.abstraction.Pet;

import java.math.BigDecimal;

/**
 * Класс Dog представляет собой конкретную реализацию домашнего питомца - собаки, и наследует общие характеристики
 * от класса Pet. Класс включает конструктор для инициализации полей породы, имени и стоимости собаки, а также
 * переопределение метода toString() для удобного вывода информации о собаке.
 */
public class Dog extends Pet {

    @SuppressWarnings("unused")
    public Dog(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    public Dog(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

}

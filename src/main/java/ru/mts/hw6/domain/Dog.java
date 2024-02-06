package ru.mts.hw6.domain;

import ru.mts.hw6.domain.abstraction.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Dog представляет собой конкретную реализацию домашнего питомца - собаки, и наследует общие характеристики
 * от класса Pet. Класс включает конструктор для инициализации полей породы, имени и стоимости собаки, а также дня
 * рождения.
 */
public class Dog extends Pet {

    @SuppressWarnings("unused")
    public Dog(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public Dog(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    public Dog() {
    }

    @Override
    public String toString() {
        return "Dog{" +
                super.toString() +
                '}';
    }

}

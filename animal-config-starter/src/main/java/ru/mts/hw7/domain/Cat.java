package ru.mts.hw7.domain;

import ru.mts.hw7.domain.abstraction.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Cat представляет собой конкретную реализацию животного, являющегося домашним питомцем,
 * наследующую общие характеристики от класса Pet. Класс добавляет свою специфичную логику
 * и параметры для представления кота в контексте домашнего животного.
 */
public class Cat extends Pet {

    @SuppressWarnings("unused")
    public Cat(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public Cat(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    public Cat() {
    }

    @Override
    public String toString() {
        return "Cat{" +
                super.toString() +
                '}';
    }

}

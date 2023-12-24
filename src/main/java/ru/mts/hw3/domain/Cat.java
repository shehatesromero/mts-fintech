package ru.mts.hw3.domain;

import ru.mts.hw3.domain.abstraction.Pet;

import java.math.BigDecimal;

/**
 * Класс Cat представляет собой конкретную реализацию животного, являющегося домашним питомцем,
 * наследующую общие характеристики от класса Pet. Класс добавляет свою специфичную логику
 * и параметры для представления кота в контексте домашнего животного.
 */
public class Cat extends Pet {

    @SuppressWarnings("unused")
    public Cat(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    public Cat(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

}

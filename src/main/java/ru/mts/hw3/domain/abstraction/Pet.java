package ru.mts.hw3.domain.abstraction;

import java.math.BigDecimal;

/**
 * Абстрактный класс Pet является подклассом класса AbstractAnimal и представляет общие характеристики
 * для домашних питомцев. Конструктор класса инициализирует характер питомца значением "Kind".
 * Класс является абстрактным, что означает, что он может содержать абстрактные методы, оставленные для
 * реализации в его конкретных подклассах.
 */
public abstract class Pet extends AbstractAnimal {

    protected Pet(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    protected Pet(String breed, String name, BigDecimal cost) {
        super(breed, name, cost, "Kind");
    }

}

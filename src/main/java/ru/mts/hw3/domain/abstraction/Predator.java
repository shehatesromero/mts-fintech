package ru.mts.hw3.domain.abstraction;

import java.math.BigDecimal;

/**
 * Абстрактный класс Predator является подклассом класса AbstractAnimal и представляет общие характеристики
 * для хищных животных. Конструктор класса инициализирует характер хищного животного значением "Angry".
 * Класс является абстрактным, что означает, что он может содержать абстрактные методы, оставленные для
 * реализации в его конкретных подклассах.
 */
public abstract class Predator extends AbstractAnimal {

    protected Predator(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    protected Predator(String breed, String name, BigDecimal cost) {
        super(breed, name, cost, "Angry");
    }

}

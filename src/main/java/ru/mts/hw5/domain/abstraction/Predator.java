package ru.mts.hw5.domain.abstraction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс Predator является подклассом класса AbstractAnimal и представляет общие характеристики
 * для хищных животных. Конструктор класса инициализирует характер хищного животного значением "Angry".
 * Класс является абстрактным, что означает, что он может содержать абстрактные методы, оставленные для
 * реализации в его конкретных подклассах.
 */
public abstract class Predator extends AbstractAnimal {

    protected Predator(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    protected Predator(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, "Angry", birthDate);
    }

    public Predator() {
    }

    @Override
    public String toString() {
        return "Predator{" +
                super.toString() +
                '}';
    }
}

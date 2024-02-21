package ru.mts.hw7.domain.abstraction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс Pet является подклассом класса AbstractAnimal и представляет общие характеристики
 * для домашних питомцев. Конструктор класса инициализирует характер питомца значением "Kind".
 * Класс является абстрактным, что означает, что он может содержать абстрактные методы, оставленные для
 * реализации в его конкретных подклассах.
 */
public abstract class Pet extends AbstractAnimal {

    protected Pet(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    protected Pet(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, "Kind", birthDate);
    }

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                super.toString() +
                '}';
    }
}

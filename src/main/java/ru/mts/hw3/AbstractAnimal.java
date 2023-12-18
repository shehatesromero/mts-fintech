package ru.mts.hw3;

import java.math.BigDecimal;

/**
 * Абстрактный класс AbstractAnimal предоставляет базовую реализацию интерфейса Animal.
 * Этот класс содержит общие поля и методы для представления животных, позволяя
 * конкретным подклассам реализовывать специфическую функциональность.
 */
public abstract class AbstractAnimal implements Animal {
    // Защищенные поля для хранения информации о породе, имени, стоимости и характере животного.
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }
}

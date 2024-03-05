package ru.mts.hw7.domain.abstraction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

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
    protected LocalDate birthDate;

    protected AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = Objects.isNull(cost) ? null : cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
        this.birthDate = birthDate;
    }

    protected AbstractAnimal() {
    }

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

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "breed='" + getBreed() + '\'' +
                ", name='" + getName() + '\'' +
                ", cost=" + (Objects.isNull(getCost()) ? "null" : getCost().toPlainString()) +
                ", character='" + getCharacter() + '\'' +
                ", birthDate=" + String.format("%1$td-%1$tm-%1$tY", getBirthDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed)
                && Objects.equals(name, that.name)
                && Objects.equals(cost, that.cost)
                && Objects.equals(character, that.character)
                && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }
}

package ru.mts.hw3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс Dog представляет собой конкретную реализацию домашнего питомца - собаки, и наследует общие характеристики
 * от класса Pet. Класс включает конструктор для инициализации полей породы, имени и стоимости собаки, а также
 * переопределение метода toString() для удобного вывода информации о собаке.
 */
public class Dog extends Pet {
    @SuppressWarnings("unused")
    public Dog(String breed, String name, BigDecimal cost, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
    }

    public Dog(String breed, String name, BigDecimal cost) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}

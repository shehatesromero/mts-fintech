package ru.mts.hw7.repository;

import ru.mts.hw7.domain.abstraction.Animal;

import java.util.Map;

public interface AnimalsRepository {

    String NAME = "mts_AnimalsRepository";

    /**
     * При помощи цикла находит всех животных,
     * которые родились в високосный год
     *
     * @return Массив из имен животных
     */
    String[] findLeapYearNames();

    /**
     * При помощи цикла for находит всех
     * животных, возраст которых больше N лет
     *
     * @param n Возраст, выше которого нужно найти
     * @return Массив подходящих животных
     */
    Animal[] findOlderAnimal(int n);

    //Ищет дубликаты животных
    Map<Animal, Integer> findDuplicate();

    //Печатает дубликаты животных
    void printDuplicate();

}

package ru.mts.hw7.repository;

import ru.mts.hw7.domain.abstraction.Animal;

import java.time.LocalDate;
import java.util.Map;

public interface AnimalsRepository {

    String NAME = "mts_AnimalsRepository";

    /**
     * При помощи цикла находит всех животных,
     * которые родились в високосный год
     *
     * @return Map. Ключ - тип + имя животного, значение - дата
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * При помощи цикла for находит всех
     * животных, возраст которых больше N лет
     *
     * @param n Возраст, выше которого нужно найти
     * @return Map. Ключ - животное, значение - возраст
     */
    Map<Animal, Integer> findOlderAnimal(int n);

    /**
     * Ищет дубликаты животных
     *
     * @return Мап. Ключ - тип животного, значение - количество дубликата
     */
    Map<String, Integer> findDuplicate();

    /**
     * Выводит результат работы findDuplicate()
     */
    void printDuplicate();

}

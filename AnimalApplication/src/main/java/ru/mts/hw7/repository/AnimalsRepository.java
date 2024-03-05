package ru.mts.hw7.repository;

import ru.mts.hw7.domain.abstraction.Animal;

import java.time.LocalDate;
import java.util.List;
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
    Map<String, List<Animal>> findDuplicate();

    /**
     * Выводит результат работы findDuplicate()
     */
    void printDuplicate();

    List<Animal> getAllAnimals();

    /**
     * Находит при помощи Stream API средний возраст всех животных.
     * Результат выводит на экран.
     *
     * @param animalsList Список животных.
     */
    void findAverageAge(List<Animal> animalsList);

    /**
     * Находит при помощи Stream API животных, возраст которых больше 5 лет
     * и стоимость которых больше средней стоимости всех животных.
     * Результатом работы метода является отсортированный по дате рождения (по возрастанию) список.
     *
     * @param animalsList Список животных.
     */
    List<Animal> findOldAndExpensive(List<Animal> animalsList);

    /**
     * Находит при помощи Stream API 3 животных с самой низкой ценой.
     * Результатом работы метода является список имен,
     * отсортированный в обратном алфавитном порядке.
     *
     * @param animalsList Список животных.
     * @return Список имен.
     */
    List<String> findMinCostAnimals(List<Animal> animalsList);

}

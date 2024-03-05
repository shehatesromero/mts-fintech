package ru.mts.hw7.service;

import ru.mts.hw7.domain.abstraction.Animal;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс CreateAnimalService предоставляет абстракцию для создания животных.
 * Метод createUniqueAnimals() включает логику по созданию уникальных животных
 * с использованием реализации интерфейса CreateOneUniqueAnimal.
 */
public interface CreateAnimalService {

    String NAME = "mts_CreateAnimalService";

    /**
     * Получить животное
     *
     * @return Animal
     */
    Animal createAnimal();

    /**
     * Проинициализировать поле
     */
    void initAnimalType();

    /**
     * Дефолтный метод генерации
     * десяти различных животных
     * циклом while
     */
    Map<String, List<Animal>> createUniqueAnimals();
}

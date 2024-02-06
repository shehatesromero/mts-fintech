package ru.mts.hw6.service;

import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.factory.AnimalSimpleFactory;

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

    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию.
    default Animal[] createUniqueAnimals() {
        int animalCounter = 0;
        Animal[] animals = new Animal[10];

        while (animalCounter < 10) {
            animals[animalCounter] = AnimalSimpleFactory.createRandomAnimal();
            animalCounter++;
        }

        return animals;
    }


}

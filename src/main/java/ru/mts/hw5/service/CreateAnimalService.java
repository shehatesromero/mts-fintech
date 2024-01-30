package ru.mts.hw5.service;

import ru.mts.hw5.domain.abstraction.Animal;
import ru.mts.hw5.factory.AnimalSimpleFactory;

/**
 * Интерфейс CreateAnimalService предоставляет абстракцию для создания животных.
 * Метод createUniqueAnimals() включает логику по созданию уникальных животных
 * с использованием реализации интерфейса CreateOneUniqueAnimal.
 */
public interface CreateAnimalService {

    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию.
    default Animal[] createUniqueAnimals() {
        int animalCounter = 0;
        Animal[] animals = new Animal[10];

        while (animalCounter < 10) {
            animals[animalCounter] = AnimalSimpleFactory.createReandomAnimal();
            animalCounter++;
        }

        return animals;
    }


}

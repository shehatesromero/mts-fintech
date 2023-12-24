package ru.mts.hw3.service;

import ru.mts.hw3.domain.abstraction.Animal;
import ru.mts.hw3.factory.AnimalSimpleFactory;

/**
 * Интерфейс CreateAnimalService предоставляет абстракцию для создания животных.
 * Метод createUniqueAnimals() включает логику по созданию уникальных животных
 * с использованием реализации интерфейса CreateOneUniqueAnimal.
 */
public interface CreateAnimalService {

    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию.
    default int createUniqueAnimals() {
        int animalCounter = 0;

        Animal animal;
        while (animalCounter < 10) {
            animal = AnimalSimpleFactory.createOneUniqueAnimal();
            System.out.println(animal);

            animalCounter++;
        }

        return animalCounter;
    }

}

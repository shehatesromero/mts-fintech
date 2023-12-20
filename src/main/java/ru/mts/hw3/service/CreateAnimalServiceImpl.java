package ru.mts.hw3.service;

import ru.mts.hw3.domain.abstraction.Animal;
import ru.mts.hw3.factory.AnimalSimpleFactory;

/**
 * Класс CreateAnimalServiceImpl предоставляет конкретную реализацию интерфейса CreateAnimalService.
 * Класс включает методы для создания уникальных животных с использованием реализации
 * интерфейса CreateOneUniqueAnimal. Предоставляется возможность создания уникальных
 * животных в количестве 10 штук через метод createUniqueAnimals() и заданного количества
 * через метод createUniqueAnimals(int n).
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию при помощи цикла do while.
    public int createUniqueAnimals() {
        int animalCounter = 0;

        do {
            animalCounter += CreateAnimalService.super.createUniqueAnimals();

        } while (animalCounter < 10);

        return animalCounter;
    }

    /**
     * Метод createUniqueAnimals(int n) создает заданное количество уникальных животных и выводит их информацию
     * при помощи цикла for
     */
    public void createUniqueAnimals(int n) {
        Animal animal;
        for (int i = 0; i < n; i++) {
            animal = AnimalSimpleFactory.createOneUniqueAnimal();

            System.out.println(animal);
        }

    }
}

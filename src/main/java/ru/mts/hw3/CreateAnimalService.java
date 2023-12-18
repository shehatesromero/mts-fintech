package ru.mts.hw3;

/**
 * Интерфейс CreateAnimalService предоставляет абстракцию для создания животных.
 * Метод createUniqueAnimals() включает логику по созданию уникальных животных
 * с использованием реализации интерфейса CreateOneUniqueAnimal.
 */
public interface CreateAnimalService {
    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию.
    default void createUniqueAnimals() {
        int animalCounter = 0;
        CreateOneUniqueAnimal a = new CreateOneUniqueAnimal();
        while (animalCounter < 10) {
            System.out.println(a.createOneUniqueAnimal());
            animalCounter++;
        }

    }
}

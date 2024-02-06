package ru.mts.hw6.service.impl;

import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.factory.AnimalFactory;
import ru.mts.hw6.service.CreateAnimalService;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Класс CreateAnimalServiceImpl предоставляет конкретную реализацию интерфейса CreateAnimalService.
 * Класс включает методы для создания уникальных животных с использованием реализации
 * интерфейса CreateOneUniqueAnimal. Предоставляется возможность создания уникальных
 * животных в количестве 10 штук через метод createUniqueAnimals() и заданного количества
 * через метод createUniqueAnimals(int n).
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final AnimalFactory animalFactory;

    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        checkArgument(Objects.nonNull(animalFactory), "'animalFactory' is null");
        this.animalFactory = animalFactory;
    }

    private Animal animal;

    /**
     * Получить животное из поля Animal
     *
     * @return Animal
     */
    @Override
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Проинициализировать поле
     */
    @Override
    public void initAnimal() {
        animal = animalFactory.createRandomAnimal();
    }

    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию при помощи цикла do while.
    @Override
    public Animal[] createUniqueAnimals() {
        int animalCounter = 0;
        Animal[] animals = new Animal[10];

        do {
            animals[animalCounter] = animalFactory.createRandomAnimal();
            animalCounter++;
        } while (animalCounter < 10);

        return animals; // Возвращаем массив созданных животных
    }

    /**
     * Метод createUniqueAnimals(int n) создает заданное количество уникальных животных и выводит их информацию
     * при помощи цикла for.
     */
    public Animal[] createUniqueAnimals(int n) {
        Animal[] animals = new Animal[n]; // Создаем массив для хранения животных
        for (int i = 0; i < n; i++) {
            animals[i] = animalFactory.createRandomAnimal();
        }

        return animals;
    }

}

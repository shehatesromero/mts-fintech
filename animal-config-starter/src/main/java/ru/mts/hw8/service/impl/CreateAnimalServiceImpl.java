package ru.mts.hw8.service.impl;

import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw8.service.CreateAnimalService;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Класс CreateAnimalServiceImpl предоставляет конкретную реализацию интерфейса CreateAnimalService.
 * Класс включает методы для создания уникальных животных с использованием реализации
 * интерфейса CreateOneUniqueAnimal. Предоставляется возможность создания уникальных
 * животных в количестве 10 штук через метод createUniqueAnimals() и заданного количества
 * через метод createUniqueAnimals(int n).
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final AbstractAnimalFactory abstractAnimalFactory;

    private AnimalType animalType;

    public CreateAnimalServiceImpl(AbstractAnimalFactory abstractAnimalFactory) {
        checkArgument(Objects.nonNull(abstractAnimalFactory), "'abstractAnimalFactory' is null");
        this.abstractAnimalFactory = abstractAnimalFactory;
    }

    /**
     * Получить животное из поля Animal
     *
     * @return Animal
     */
    @Override
    public Animal createAnimal() {
        var animalFactory = abstractAnimalFactory.createAnimalFactory(animalType);

        return animalFactory.createAnimal();
    }

    /**
     * Проинициализировать поле
     */
    @Override
    public void initAnimalType() {
        int randCoefficient = ThreadLocalRandom.current().nextInt(0, 3);
        animalType = AnimalType.values()[randCoefficient];
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    // Метод createUniqueAnimals() создает 10 уникальных животных и выводит их информацию при помощи цикла do while.
    @Override
    public Animal[] createUniqueAnimals() {
        var factory = abstractAnimalFactory.createAnimalFactory(animalType);
        int animalCounter = 0;
        Animal[] animals = new Animal[10];

        do {
            animals[animalCounter] = factory.createAnimal();
            animalCounter++;
        } while (animalCounter < 10);

        return animals; // Возвращаем массив созданных животных
    }

    /**
     * Метод createUniqueAnimals(int n) создает заданное количество уникальных животных и выводит их информацию
     * при помощи цикла for.
     */
    public Animal[] createUniqueAnimals(int n) {
        var factory = abstractAnimalFactory.createAnimalFactory(animalType);

        Animal[] animals = new Animal[n]; // Создаем массив для хранения животных
        for (int i = 0; i < n; i++) {
            animals[i] = factory.createAnimal();
        }

        return animals;
    }

}

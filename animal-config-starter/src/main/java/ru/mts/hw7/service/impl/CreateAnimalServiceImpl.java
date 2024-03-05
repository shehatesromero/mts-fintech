package ru.mts.hw7.service.impl;

import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.service.CreateAnimalService;

import java.util.*;
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

    /**
     * Метод createUniqueAnimals() создает 10 уникальных животных и возвращает их информацию в Map.
     *
     * @return Map, где ключ - тип животного, значение - список созданных животных
     */
    @Override
    public Map<String, List<Animal>> createUniqueAnimals() {
        var factory = abstractAnimalFactory.createAnimalFactory(animalType);
        int animalCounter = 0;
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        do {
            Animal animal = factory.createAnimal();

            animalsMap.computeIfAbsent(animalType.toString(), k -> new ArrayList<>()).add(animal);

            animalCounter++;
        } while (animalCounter < 10);

        return animalsMap;
    }

    /**
     * Метод createUniqueAnimals() создает n уникальных животных и возвращает их информацию в Map.
     *
     * @param n - количество животных
     * @return Map, где ключ - тип животного, значение - список созданных животных
     */
    public Map<String, List<Animal>> createUniqueAnimals(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Wrong n for createUniqueAnimals");
        }
        var factory = abstractAnimalFactory.createAnimalFactory(animalType);
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Animal animal = factory.createAnimal();

            animalsMap.computeIfAbsent(animalType.toString(), k -> new ArrayList<>()).add(animal);
        }

        return animalsMap;
    }
}

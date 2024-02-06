package ru.mts.hw6.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.ObjectProvider;
import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.service.CreateAnimalService;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class AnimalsRepositoryImpl implements AnimalsRepository {

    private static final int CAPACITY = (Integer.MAX_VALUE / 100_000);

    private final ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    private final List<Animal> animals = Lists.newArrayListWithCapacity(CAPACITY);

    private boolean initialized;

    public AnimalsRepositoryImpl(ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        this.createAnimalServicesBeanProvider = createAnimalServicesBeanProvider;
    }

    public void postConstruct() {
        if (!initialized) {
            Animal animal;
            CreateAnimalService prototype;
            for (int i = 0; i < CAPACITY; i++) {
                prototype = createAnimalServicesBeanProvider.getIfAvailable();
                if (Objects.isNull(prototype)) {
                    throw new RuntimeException("Caramba! 'prototype' is null");
                }

                animal = prototype.createAnimal();
                animals.add(animal);
            }

            initialized = true;
        }

    }

    @Override
    public String[] findLeapYearNames() {
        List<String> animalsReturn = new ArrayList<>();

        for (Animal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                animalsReturn.add(animal.getName());
            }
        }
        return animalsReturn.toArray(new String[0]);
    }

    @Override
    public Animal[] findOlderAnimal(int n) {
        List<Animal> animalsReturn = new ArrayList<>();

        for (Animal animal : animals) {
            if (Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > n) {
                animalsReturn.add(animal);
            }
        }

        return animalsReturn.toArray(new Animal[0]);
    }

    @Override
    public Map<Animal, Integer> findDuplicate() {
        Map<Animal, Integer> animalDuplicates = new HashMap<>();
        Set<Animal> uniqueAnimals = new HashSet<>();

        for (Animal animal : animals) {
            if (uniqueAnimals.contains(animal)) {
                if (animalDuplicates.containsKey(animal)) {
                    animalDuplicates.put(animal, animalDuplicates.get(animal) + 1);
                } else {
                    animalDuplicates.put(animal, 1);
                }

            } else {
                uniqueAnimals.add(animal);
            }
        }

        return animalDuplicates;
    }

    @Override
    public void printDuplicate() {
        Map<Animal, Integer> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }

        for (Map.Entry<Animal, Integer> entry : animalDuplicates.entrySet()) {
            System.out.println(entry);
        }

    }

}

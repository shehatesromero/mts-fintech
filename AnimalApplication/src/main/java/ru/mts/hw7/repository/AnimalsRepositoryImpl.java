package ru.mts.hw7.repository;

import org.springframework.beans.factory.ObjectProvider;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final int capacity;

    private final ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    private Map<String, List<Animal>> animals;

    private boolean initialized;

    public AnimalsRepositoryImpl(int repositoryCapacity, ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        capacity = repositoryCapacity;
        this.createAnimalServicesBeanProvider = createAnimalServicesBeanProvider;
    }

    @PostConstruct
    public void postConstruct() {
        if (!initialized) {
            var prototype = createAnimalServicesBeanProvider.getIfAvailable();
            animals = prototype.createUniqueAnimals();
        }

        initialized = true;
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        validateAnimals();
        Map<String, LocalDate> animalsReturn = new HashMap<>();

        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalsList = animals.get(key);
            for (Animal animal : animalsList) {
                if (animal.getBirthDate().isLeapYear()) {
                    animalsReturn.put(
                            animal.getBreed() + " " + animal.getName(),
                            animal.getBirthDate());
                }
            }
        }
        return animalsReturn;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        validateAnimals();
        Map<Animal, Integer> animalsReturn = new HashMap<>();
        int oldestYearsOld = 0;
        Animal oldestAnimal = null;

        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalList = animals.get(key);
            for (Animal animal : animalList) {
                int animalYearsOld = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();

                if (animalYearsOld > oldestYearsOld) {
                    oldestYearsOld = animalYearsOld;
                    oldestAnimal = animal;
                }
                if (animalYearsOld > n) {
                    animalsReturn.put(animal, animalYearsOld);
                }
            }
        }
        if (animalsReturn.isEmpty()) {
            animalsReturn.put(oldestAnimal, oldestYearsOld);
        }
        return animalsReturn;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        validateAnimals();
        Map<String, Integer> animalDuplicatesBreed = new HashMap<>();
        Set<String> uniqueAnimalsBreed = new HashSet<>();
        Map<String, Integer> animalsReturn = new HashMap<>();

        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalsList = animals.get(key);
            for (Animal animal : animalsList) {
                String animalBreed = animal.getBreed();
                if (uniqueAnimalsBreed.contains(animalBreed)) {
                    if (animalDuplicatesBreed.containsKey(animalBreed)) {
                        animalDuplicatesBreed.put(animalBreed, animalDuplicatesBreed.get(animalBreed) + 1);
                    } else {
                        animalDuplicatesBreed.put(animalBreed, 1);
                    }
                } else {
                    uniqueAnimalsBreed.add(animalBreed);
                }
            }
        }
        animalsReturn.putAll(animalDuplicatesBreed);
        return animalsReturn;
    }

    @Override
    public void printDuplicate() {
        Map<String, Integer> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        for (Map.Entry<String, Integer> entry : animalDuplicates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private void validateAnimals() {
        if (animals == null) {
            throw new IllegalArgumentException("Animals array cannot be null");
        }
        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalsList = animals.get(key);
            for (Animal animal : animalsList) {
                if (animal == null) {
                    throw new IllegalArgumentException("Some animal is null");
                }
            }
        }
    }
}


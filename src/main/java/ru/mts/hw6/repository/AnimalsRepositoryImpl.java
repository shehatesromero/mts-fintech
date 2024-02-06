package ru.mts.hw6.repository;

import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class AnimalsRepositoryImpl implements AnimalsRepository {
    protected List<Animal> animals;
    CreateAnimalService animalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animalService = createAnimalService;
    }

    @PostConstruct
    private void postConstruct() {
        animals = List.of(animalService.createUniqueAnimals());
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
    public Animal[] findOlderAnimal(int N) {
        List<Animal> animalsReturn = new ArrayList<>();

        for (Animal animal : animals) {
            if (Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > N) {
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

package ru.mts.hw7.repository;

import org.springframework.beans.factory.ObjectProvider;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

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
        return animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getBreed() + " " + animal.getName(),
                        Animal::getBirthDate,
                        (existing, replacement) -> existing.isAfter(replacement) ? existing : replacement
                ));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        validateAnimals();
        return animals.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears(),
                        Collectors.toList()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getKey() > n)
                .findFirst()
                .map(entry -> entry.getValue().stream()
                        .collect(Collectors.toMap(
                                animal -> animal,
                                animal -> entry.getKey()
                        )))
                .orElseGet(() -> {
                    Map<Animal, Integer> result = new HashMap<>();
                    animals.values().stream()
                            .flatMap(List::stream)
                            .max(Comparator.comparing(animal ->
                                    Period.between(animal.getBirthDate(), LocalDate.now()).getYears()))
                            .ifPresent(animal -> {
                                int oldestYearsOld = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
                                result.put(animal, oldestYearsOld);
                            });
                    return result;
                });
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        validateAnimals();

        // Используем Stream API и groupingBy для поиска дубликатов
        Map<String, List<Animal>> animalDuplicates = animals.values().stream()
                .flatMap(Collection::stream)  // Вместо List::stream
                .collect(Collectors.groupingBy(Animal::toString));

        // Фильтруем только те записи, у которых количество больше 1
        Map<String, List<Animal>> animalsReturn = animalDuplicates.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return animalsReturn;
    }

    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        } else {
            animalDuplicates.forEach((breed, animalsList) -> {
                System.out.println(breed + " duplicates:");
                //при желании можно вывести animal.getNames
                animalsList.forEach(animal -> System.out.println("  " + animal));
            });
        }
    }


    public void findAverageAge(List<Animal> animalsList) {
        double averageAge = animalsList.stream()
                .mapToDouble(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElse(0.0);

        System.out.println("Average Age of Animals: " + averageAge);
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalsList) {
        BigDecimal averageCost = animalsList.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animalsList.size()), 2, RoundingMode.HALF_UP);

        return animalsList.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5
                        && animal.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animalsList) {
        return animalsList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
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

    @Override
    public List<Animal> getAllAnimals() {
        validateAnimals();

        return animals.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}


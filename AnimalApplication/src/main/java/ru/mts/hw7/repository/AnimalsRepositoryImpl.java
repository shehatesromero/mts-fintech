package ru.mts.hw7.repository;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.ObjectProvider;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.exception.InsufficientArraySizeException;
import ru.mts.hw7.exception.InvalidParameterException;
import ru.mts.hw7.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AnimalsRepositoryImpl implements AnimalsRepository {

    // Добавляем блокировки для обеспечения потокобезопасности (но только когда необходимо)
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private final int capacity;

    private final ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    // тоже просто как пример
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    private Map<String, List<Animal>> animals;

    public AnimalsRepositoryImpl(int repositoryCapacity, ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        capacity = repositoryCapacity;
        this.createAnimalServicesBeanProvider = createAnimalServicesBeanProvider;
    }

    @PostConstruct
    public void postConstruct() {
        if (!initialized.get()) {
            var prototype = createAnimalServicesBeanProvider.getIfAvailable();
            Preconditions.checkArgument(Objects.nonNull(prototype), "'prototype' is null");
            // Используем ConcurrentHashMap для потокобезопасной мапы
            animals = new ConcurrentHashMap<>(prototype.createUniqueAnimals());
        }

        initialized.set(true);
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        validateAnimals();

        return animals.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(a -> a.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getBreed() + " " + animal.getName(),
                        Animal::getBirthDate,
                        (existing, replacement) -> existing.isAfter(replacement) ? existing : replacement
                ));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        // Проверка на некорректное значение аргумента
        if (n < 0) {
            throw new IllegalArgumentException("The value of the argument n cannot be negative");
        }
        validateAnimals();

        var map = animals.values()
                .parallelStream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears(),
                        Collectors.toList()
                ));

        final var now = LocalDate.now();

        return map.entrySet()
                .stream()
                .filter(entry -> entry.getKey() > n)
                .findFirst()
                .map(entry -> entry.getValue()
                        .stream()
                        .collect(Collectors.toMap(
                                Function.identity(), animal -> entry.getKey()
                        )))
                .orElseGet(() -> {
                    final Map<Animal, Integer> result = new ConcurrentHashMap<>();
                    animals.values()
                            .stream()
                            .flatMap(Collection::stream)
                            .max(Comparator.comparing(a -> Period.between(a.getBirthDate(), now).getYears()))
                            .ifPresent(a -> {
                                int oldestYearsOld = Period.between(a.getBirthDate(), now).getYears();
                                result.put(a, oldestYearsOld);
                            });

                    return result;
                });

    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        validateAnimals();

        // Используем Stream API и groupingBy для поиска дубликатов
        Map<String, List<Animal>> animalDuplicates = animals.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(String::valueOf));

        // Фильтруем только те записи, у которых количество больше 1
        return animalDuplicates.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        } else {
            animalDuplicates.forEach((breed, animals) -> {
                if (animals != null && !animals.isEmpty()) {
                    String msg = breed + " duplicates:" +
                            animals.stream()
                                    .filter(Objects::nonNull)
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(" "));

                    //при желании можно вывести animal.getNames
                    System.out.println(msg);
                }
            });
        }
    }

    public void findAverageAge(List<Animal> animalsList) {
        // Проверка на то, что List не null
        if (animalsList == null) {
            throw new InvalidParameterException("The list of animals cannot be null");
        }

        // Проверка на то, что элементы в List не null
        if (animalsList.contains(null)) {
            throw new InvalidParameterException("The list of animals cannot contain null elements");
        }

        final var now = LocalDate.now();

        double averageAge = animalsList.stream()
                .mapToDouble(a -> Period.between(a.getBirthDate(), now).getYears())
                .average()
                .orElse(0.0);

        System.out.println("Average Age of Animals: " + averageAge);
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalsList) {
        // Проверка на то, что List не null
        if (animalsList == null) {
            throw new InvalidParameterException("The list of animals cannot be null");
        }

        // Проверка на то, что элементы в List не null
        if (animalsList.contains(null)) {
            throw new InvalidParameterException("The list of animals cannot contain null elements");
        }

        var averageCost = animalsList.stream()
                .filter(Objects::nonNull)
                .map(Animal::getCost)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animalsList.size()), 2, RoundingMode.HALF_UP);

        final var now = LocalDate.now();

        return animalsList.stream()
                .filter(a -> (Period.between(a.getBirthDate(), now).getYears() > 5)
                        && Objects.nonNull(a.getCost())
                        && (a.getCost().compareTo(averageCost) > 0))
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animalsList) throws InsufficientArraySizeException {
        // Проверка на то, что массив больше 3
        if (animalsList.size() < 3) {
            throw new InsufficientArraySizeException("The size of the array must be at least three");
        }

        return animalsList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateAnimals() {
        if (animals == null) {
            throw new IllegalArgumentException("Animals array cannot be null");
        }

        for (var animal : animals.values()) {
            if (animal == null) {
                throw new IllegalArgumentException("Some animal is null");
            }
        }

    }

    @Override
    public List<Animal> getAllAnimals() {
        validateAnimals();

        return animals.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

}


package ru.mts.hw5.service;

import org.apache.commons.lang3.ArrayUtils;
import ru.mts.hw5.domain.abstraction.Animal;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class SearchServiceImpl implements SearchService {

    /**
     * Метод findLeapYearNames осуществляет поиск и возвращает имена животных,
     * которые родились в високосные годы. Для определения високосного года
     * используется информация о дате рождения животных.
     *
     * @param animals массив объектов типа Animal, представляющих животных для анализа
     * @return список строк с именами животных, родившихся в високосные годы
     */
    @Override
    public List<String> findLeapYearNames(Animal[] animals) {
        checkArgument(ArrayUtils.isNotEmpty(animals), "Input array should not be null or empty");

        List<String> result = new ArrayList<>();
        System.out.println("Created animals: ");
        for (Animal animal : animals) {
            if (Objects.nonNull(animal)) {
                System.out.println(animal);

                if (Objects.nonNull(animal.getBirthDate())
                        && animal.getBirthDate().isLeapYear()) {

                    result.add(String.valueOf(animal.getName()));
                }

            }

        }

        System.out.println("-----------------------------");
        System.out.println("Leap Year Names: ");

        return result;
    }

    /**
     * Метод findOlderAnimal осуществляет поиск и возвращает имена животных, которые
     * старше заданного возраста. Возраст определяется с использованием информации
     * о дате рождения животных.
     *
     * @param animals массив объектов типа Animal, представляющих животных для анализа
     * @param age     возраст, старше которого нужно искать животных
     * @return список строк с именами животных, старше заданного возраста
     */
    @Override
    public List<String> findOlderAnimal(Animal[] animals, int age) {
        checkArgument(ArrayUtils.isNotEmpty(animals), "Input array should not be null or empty");

        var now = LocalDate.now();

        List<String> result = new ArrayList<>();
        for (var animal : animals) {
            if (Objects.nonNull(animal)
                    && Objects.nonNull(animal.getBirthDate())) {

                var birthDate = Period.between(animal.getBirthDate(), now);
                if (birthDate.getYears() > age) {
                    result.add(String.valueOf(animal));
                }
            }

        }

        System.out.printf("Animals over %d years old:%n", age);

        return result;
    }

    /**
     * Метод findDuplicate осуществляет поиск и выводит наличие дубликатов животных
     * в переданном массиве. Предполагается, что каждое животное имеет уникальный
     * идентификатор, который используется для выявления дубликатов.
     *
     * @param animals массив объектов типа Animal, представляющих животных для анализа
     */
    @Override
    public List<String> findDuplicate(Animal[] animals) {
        checkArgument(ArrayUtils.isNotEmpty(animals), "Input array should not be null or empty");

        Map<String, Animal> animalMap = new HashMap<>();
        Set<String> duplicates = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (var animal : animals) {
            if (Objects.nonNull(animal)) {
                // Идентификатор (в данном случае, имя) животного
                var identifier = animal.getName();
                checkState(isNotBlank(identifier), "'identifier' is blank");

                // Если такой идентификатор уже встречался, добавляем в дубликаты
                if (animalMap.containsKey(identifier)) {
                    duplicates.add(identifier);
                } else {
                    // Иначе добавляем в карту для дальнейшей проверки
                    animalMap.put(identifier, animal);
                }
            }

        }

        // Выводим дубликаты
        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found.");
        } else {
            for (String duplicate : duplicates) {
                result.add(duplicate);
            }
        }
        return result;
    }

    public void printDuplicate(Animal[] animals) {
        var animalDuplicates = findDuplicate(animals);
        if (animalDuplicates.isEmpty()) {
            System.out.println("No duplicates found.");
        } else {
            System.out.println("Duplicates found: ");
            for (String duplicate : animalDuplicates) {
                System.out.println(duplicate);
            }
        }
    }
}

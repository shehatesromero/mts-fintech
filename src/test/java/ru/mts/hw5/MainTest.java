package ru.mts.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.hw5.domain.Dog;
import ru.mts.hw5.domain.Wolf;
import ru.mts.hw5.domain.abstraction.AbstractAnimal;
import ru.mts.hw5.domain.abstraction.Animal;
import ru.mts.hw5.service.SearchServiceImpl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    @Nested
    class AbstractAnimalTest {
        private final Dog dog1 = new Dog("Mops", "Sharik", BigDecimal.valueOf(205.0), LocalDate.of(2020, 2, 1));
        private final Dog dog2 = new Dog("Mops", "Sharik", BigDecimal.valueOf(205.0), LocalDate.of(2020, 2, 1));
        private final Dog dog3 = new Dog("Mops", "Sharik", BigDecimal.valueOf(205.0), LocalDate.of(2020, 2, 1));

        @Test
        @DisplayName("Checking the correct redefinition of the equals method")
        void testEqualsOverridden() throws NoSuchMethodException {
            Method equalsMethodFromAbstractAnimal = AbstractAnimal.class.getMethod("equals", Object.class);
            Method equalsMethodFromObject = Object.class.getMethod("equals", Object.class);

            Assertions.assertNotEquals(equalsMethodFromAbstractAnimal, equalsMethodFromObject);
        }

        @Test
        @DisplayName("Checking the Reflexivity of equals")
        void testReflexiveEqulas() {
            Assertions.assertEquals(dog1, dog1);
        }

        @Test
        @DisplayName("Checking the Symmetry of equals")
        void testSymmetricEquals() {
            Assertions.assertEquals(dog1, dog2);
            Assertions.assertEquals(dog2, dog1);
        }

        @Test
        @DisplayName("Checking the Transitivity of equals")
        void testTransitiveEquals() {
            Assertions.assertEquals(dog1, dog2);
            Assertions.assertEquals(dog2, dog3);
            Assertions.assertEquals(dog1, dog3);
        }

        @Test
        @DisplayName("Checking the Consistency of equals")
        void testConsistenceEquals() {
            Assertions.assertEquals(dog1, dog2);
            Assertions.assertEquals(dog2, dog3);
            Assertions.assertEquals(dog1, dog3);
        }

        @Test
        @DisplayName("Checking a non-empty equals object")
        void testNullComparison() {
            Assertions.assertNotEquals(null, dog1);
        }
    }

    @Nested
    class SearchServiceImplTest {
        SearchServiceImpl searchService = new SearchServiceImpl();

        @Test
        @DisplayName("Checking the search for animals born in a leap year")
        void testFindLeapYearNames() {
            Animal[] animals = {new Wolf("Tundra", "Bob", BigDecimal.valueOf(20.0), LocalDate.of(2020, 1, 1)),
                    new Wolf("Forest", "Jake", BigDecimal.valueOf(20.0), LocalDate.of(2024, 1, 1))};
            List<String> expectedAnimalsNames = List.of("Bob", "Jake");
            List<String> result = searchService.findLeapYearNames(animals);
            Assertions.assertIterableEquals(expectedAnimalsNames, result);

            Animal[] emptyAnimals = {new Wolf("Tundra", "Bob", BigDecimal.valueOf(20.0), LocalDate.of(2023, 1, 1)),
                    new Wolf("Forest", "Jake", BigDecimal.valueOf(20.0), LocalDate.of(2021, 1, 1))};
            List<String> expectedEmptyAnimals = List.of();
            List<String> resultEmpty = searchService.findLeapYearNames(emptyAnimals);
            Assertions.assertIterableEquals(expectedEmptyAnimals, resultEmpty);
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 10, 19})
        @DisplayName("Checking animals over the age of n")
        void testFindOlderAnimal(int age) {
            Animal[] animals = {new Wolf("Tundra", "Bob", BigDecimal.valueOf(20.0), LocalDate.of(2003, 1, 1)),
                    new Wolf("Forest", "Jake", BigDecimal.valueOf(20.0), LocalDate.of(2004, 1, 1))};
            List<String> expectedAnimals = Arrays.asList(
                    "Wolf{Predator{breed='Tundra', name='Bob', cost=20.00, character='Angry', birthDate=01-01-2003}}",
                    "Wolf{Predator{breed='Forest', name='Jake', cost=20.00, character='Angry', birthDate=01-01-2004}}");
            List<String> result = searchService.findOlderAnimal(animals, age);
            Assertions.assertIterableEquals(expectedAnimals, result);
        }

        @Test
        @DisplayName("Checking for duplicate animals")
        void testFindDuplicate() {
            Animal[] animals = {new Wolf("Tundra", "Bob", BigDecimal.valueOf(20.0), LocalDate.of(2003, 1, 1)),
                    new Wolf("Forest", "Bob", BigDecimal.valueOf(20.0), LocalDate.of(2004, 1, 1))};
            List<String> expectedAnimalsNames = List.of("Bob");
            List<String> result = searchService.findDuplicate(animals);
            Assertions.assertIterableEquals(expectedAnimalsNames, result);
        }

    }

}

package ru.mts.hw7.repositoryTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw7.config.AnimalDataAutoConfiguration;
import ru.mts.hw7.config.AppConfig;
import ru.mts.hw7.config.TestConfigApp;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.repository.AnimalsRepository;
import ru.mts.hw7.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {AnimalDataAutoConfiguration.class, AppConfig.class, TestConfigApp.class})
public class AnimalsRepositoryImplTest {

    @Autowired
    private ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    @Autowired
    private AnimalsRepository animalsRepository;

    @Test
    @DisplayName("Positive Test: Find Leap Year Names")
    void testFindLeapYearNames() {
        // Act
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();

        // Assert
        assertNotNull(leapYearNames);
        assertTrue(leapYearNames.size() > 0, "Expected non-empty array of names for leap years");
    }


    //может не проходить тесты из-за наших моков - будет исправлено позже

    /***
     * Ошибка указывает на то, что в методе findOlderAnimal происходит попытка собрать Map с
     * дублирующимися ключами (животными), и ошибка возникает при попытке объединения значений для
     * одного и того же ключа.
     */
    @Test
    @DisplayName("Positive Test: Find Older Animals")
    void testFindOlderAnimals() {
        // Arrange
        int n = 1;

        // Act
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(n);

        // Assert
        assertNotNull(olderAnimals);
    }


    @Test
    @DisplayName("Negative Test: Find Duplicate - Expect Duplicates")
    void testFindDuplicateWithDuplicates() {
        // Act
        Map<String, List<Animal>> duplicates = animalsRepository.findDuplicate();

        // Assert
        assertNotNull(duplicates);
        assertFalse(duplicates.isEmpty(), "Expected duplicates");
    }

    @Test
    @DisplayName("Тест метода findDuplicate, но с пустой Map")
    void findDuplicateNotExpectedTest() {
        Map<String, Integer> expectedDuplicates = Map.ofEntries();
        Map<String, List<Animal>> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertNotEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Тест метода findLeapYearNames")
    void findLeapYearNamesTest() {
        Map<String, LocalDate> expectedAnimals = Map.ofEntries(
                entry("White Mock_Bars", LocalDate.of(2020, 1, 8))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Тест метода findLeapYearNames, но с пустой Map")
    void findLeapYearNamesNotExpectedTest() {
        Map<String, LocalDate> expectedAnimals = new HashMap<>();
        Assertions.assertNotEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

}

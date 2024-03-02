package ru.mts.hw7.repositoryTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw7.config.AnimalDataAutoConfiguration;
import ru.mts.hw7.config.AppConfig;
import ru.mts.hw7.config.TestConfigApp;
import ru.mts.hw7.domain.Wolf;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.factory.AnimalFactory;
import ru.mts.hw7.factory.impl.WolfFactory;
import ru.mts.hw7.repository.AnimalsRepository;
import ru.mts.hw7.repository.AnimalsRepositoryImpl;
import ru.mts.hw7.service.CreateAnimalService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

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
        String[] leapYearNames = animalsRepository.findLeapYearNames();

        // Assert
        assertNotNull(leapYearNames);
        assertTrue(leapYearNames.length > 0, "Expected non-empty array of names for leap years");
    }

    @Test
    @DisplayName("Positive Test: Find Older Animals")
    void testFindOlderAnimals() {
        // Arrange
        int n = 1;

        // Act
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(n);

        // Assert
        assertNotNull(olderAnimals);
    }


    @Test
    @DisplayName("Negative Test: Find Duplicate - Expect Duplicates")
    void testFindDuplicateWithDuplicates() {
        // Act
        Map<Animal, Integer> duplicates = animalsRepository.findDuplicate();

        // Assert
        assertNotNull(duplicates);
        assertFalse(duplicates.isEmpty(), "Expected duplicates");
    }

    @Test
    @DisplayName("Negative Test: Find Leap Year Names with no leap year")
    void findLeapYearNamesWithNoLeapYear() {
        // Создаем моки для тестовых данных
        AbstractAnimalFactory animalFactory = Mockito.mock(AbstractAnimalFactory.class);
        AnimalFactory<Wolf> wolfFactory = Mockito.mock(WolfFactory.class);

        // Создаем объект Wolf с невисокосным годом
        Wolf wolfWithZeroCost = new Wolf("White", "Mock_Bars",
                BigDecimal.valueOf(0.1).setScale(2, RoundingMode.HALF_UP),
                LocalDate.of(2023, 1, 8));

        // Настраиваем поведение мока wolfFactory
        Mockito.when(wolfFactory.createAnimal()).thenReturn(wolfWithZeroCost);

        // Настраиваем поведение мока animalFactory
        Mockito.when(animalFactory.createAnimalFactory(Mockito.any()))
                .thenAnswer(invocation -> {
                    final AnimalFactory<?> result;
                    final AnimalType animalTypeArg = invocation.getArgument(0, AnimalType.class);

                    if (AnimalType.WOLF.equals(animalTypeArg)) {
                        result = wolfFactory;
                    } else {
                        throw new UnsupportedOperationException("Unsupported animal type");
                    }

                    return result;
                });

        // Создаем AnimalsRepository с настроенными моками
        AnimalsRepository animalsRepository = new AnimalsRepositoryImpl(1, createAnimalServicesBeanProvider);

        // Вызываем findLeapYearNames
        String[] leapYearNames = animalsRepository.findLeapYearNames();

        assertNotNull(leapYearNames);
        assertFalse(leapYearNames.length > 0);
    }

}

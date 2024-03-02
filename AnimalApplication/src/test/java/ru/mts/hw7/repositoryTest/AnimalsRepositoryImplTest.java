package ru.mts.hw7.repositoryTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw7.config.AnimalDataAutoConfiguration;
import ru.mts.hw7.config.AppConfig;
import ru.mts.hw7.config.TestConfigApp;
import ru.mts.hw7.repository.AnimalsRepository;
import ru.mts.hw8.service.CreateAnimalService;
import ru.mts.hw8.service.impl.CreateAnimalServiceImpl;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(classes = {AnimalDataAutoConfiguration.class, AppConfig.class, TestConfigApp.class})
public class AnimalsRepositoryImplTest {

    @Autowired
    private ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;
    @Autowired
    private ObjectProvider<CreateAnimalServiceImpl> createAnimalServicesImplBeanProvider;
    @Autowired
    private AnimalsRepository animalsRepository;

    //Проверка работы тестового класса
    //java.lang.IllegalStateException: Failed to load ApplicationContext
    //Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'animalsRepository':
    // Invocation of init method failed; nested exception is java.lang.UnsupportedOperationException: Unsupported animal type
    @DisplayName("Checking the initialization of the service after beanPostProcessor")
    @Test
    void testInitAfterBeanPostProcessor() {
        var prototype = createAnimalServicesBeanProvider.getIfAvailable();

        assertNotNull(prototype);

        try {
            var animal = prototype.createAnimal();
            assertNotNull(animal);
        } catch (UnsupportedOperationException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    /*@Test
    @DisplayName("Positive Test: Find Leap Year Names")
    void testFindLeapYearNames() {
        // Arrange

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
        int n = 5;

        // Act
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(n);

        // Assert
        assertNotNull(olderAnimals);
    }

    @Test
    @DisplayName("Negative Test: Find Duplicate - Expect No Duplicates")
    void testFindDuplicateNoDuplicates() {
        // Arrange

        // Act
        Map<Animal, Integer> duplicates = animalsRepository.findDuplicate();

        // Assert
        assertNotNull(duplicates);
        assertTrue(duplicates.isEmpty(), "Expected no duplicates");
    }

    @Test
    @DisplayName("Negative Test: Print Duplicate - Expect No Duplicates")
    void testPrintDuplicateNoDuplicates(@Autowired CapturedOutput output) {

        // Act
        animalsRepository.printDuplicate();

        // Assert
        // Проверяем, что в выводе содержится ожидаемое сообщение
        assertThat(output).contains("There is no duplicates");
    }*/
}

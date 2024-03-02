package ru.mts.hw8.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw7.config.AnimalDataAutoConfiguration;
import ru.mts.hw7.config.AppConfig;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.service.CreateAnimalService;
import ru.mts.hw7.service.impl.CreateAnimalServiceImpl;
import ru.mts.hw8.config.TestConfig;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {AnimalDataAutoConfiguration.class, AppConfig.class, TestConfig.class})
class AnimalServiceTest {

    @Autowired
    private ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;
    @Autowired
    private ObjectProvider<CreateAnimalServiceImpl> createAnimalServicesImplBeanProvider;
    @Autowired
    private AbstractAnimalFactory abstractAnimalFactory;

    @DisplayName("Checking the initialization of the service after beanPostProcessor")
    @Test
    void testInitAfterBeanPostProcessor() {
        var prototype = createAnimalServicesBeanProvider.getIfAvailable();

        assertNotNull(prototype);

        var animal = prototype.createAnimal();

        assertNotNull(animal);

    }

    @DisplayName("Checking the creation of an animal array with correct n")
    @Test
    void testCreateAnimalArrayWithCorrectN() {
        // Arrange
        int n = 5;
        var prototype = createAnimalServicesImplBeanProvider.getIfAvailable();
        assertNotNull(prototype);

        // Act
        Animal[] animals = prototype.createUniqueAnimals(n);

        // Assert
        assertNotNull(animals);
        assertEquals(n, animals.length);
        for (Animal animal : animals) {
            assertNotNull(animal);
        }
    }

    @DisplayName("Checking exception in createUniqueAnimals with negative N")
    @Test
    void testExceptionInCreateUniqueAnimalsWithNegativeN() {
        // Arrange
        int n = -3;
        var prototype = createAnimalServicesImplBeanProvider.getIfAvailable();

        // Act & Assert
        assertThrows(NegativeArraySizeException.class, () -> prototype.createUniqueAnimals(n));
    }

    @DisplayName("Test Animal Creation via AbstractAnimalFactory")
    @Test
    void testCreateAnimal() {
        // Arrange
        AnimalType animalType = AnimalType.DOG;

        // Act
        var animalFactory = abstractAnimalFactory.createAnimalFactory(animalType);
        Animal animal = animalFactory.createAnimal();

        // Assert
        assertNotNull(animal);
    }

    @DisplayName("Animal Creation test for an unknown type via AbstractAnimalFactory")
    @Test
    void testCreateAnimalWithUnsupportedType() {
        // Arrange
        AnimalType unsupportedType = AnimalType.UNKNOWN;

        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            abstractAnimalFactory.createAnimalFactory(unsupportedType);
        });
    }
}

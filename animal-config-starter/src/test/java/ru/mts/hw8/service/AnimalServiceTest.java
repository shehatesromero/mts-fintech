package ru.mts.hw8.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw7.config.AnimalDataAutoConfiguration;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw8.config.TestConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = AnimalDataAutoConfiguration.class)
@ActiveProfiles("test")
@Import(TestConfiguration.class)
class AnimalServiceTest {
    @Autowired
    ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;
    CreateAnimalService prototype;
    Animal animal;
    @DisplayName("Проверка инициализации сервиса после beanPostProcessor")
    @Test
    void testInitAfterBeanPostProcessor() {

        prototype = createAnimalServicesBeanProvider.getIfAvailable();
        animal = prototype.createAnimal();
        assertNotNull(animal);

    }
}

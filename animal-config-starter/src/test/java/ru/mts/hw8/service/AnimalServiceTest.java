package ru.mts.hw8.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw7.config.AnimalDataAutoConfiguration;
import ru.mts.hw7.config.AppConfig;
import ru.mts.hw8.config.TestConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(classes = {AnimalDataAutoConfiguration.class, AppConfig.class, TestConfig.class})
class AnimalServiceTest {

    @Autowired
    private ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    @DisplayName("Проверка инициализации сервиса после beanPostProcessor")
    @Test
    void testInitAfterBeanPostProcessor() {
        var prototype = createAnimalServicesBeanProvider.getIfAvailable();

        assertNotNull(prototype);

        var animal = prototype.createAnimal();

        assertNotNull(animal);
    }

}

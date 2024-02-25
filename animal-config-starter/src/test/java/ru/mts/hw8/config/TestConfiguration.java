package ru.mts.hw8.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.mts.hw7.domain.Cat;
import ru.mts.hw7.domain.Dog;
import ru.mts.hw7.domain.Shark;
import ru.mts.hw7.domain.Wolf;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.factory.AnimalFactory;
import ru.mts.hw7.factory.impl.CatFactory;
import ru.mts.hw7.factory.impl.DogFactory;
import ru.mts.hw7.factory.impl.SharkFactory;
import ru.mts.hw7.factory.impl.WolfFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@Profile("test")
public class TestConfiguration {
    @Bean
    public AbstractAnimalFactory animalFactory() {
        AbstractAnimalFactory animalFactory = Mockito.mock(AbstractAnimalFactory.class);

        // Для каждого типа животного создаем соответствующий мок AnimalFactory
        AnimalFactory<Dog> dogFactory = Mockito.mock(DogFactory.class);
        AnimalFactory<Cat> catFactory = Mockito.mock(CatFactory.class);
        AnimalFactory<Wolf> wolfFactory = Mockito.mock(WolfFactory.class);
        AnimalFactory<Shark> sharkFactory = Mockito.mock(SharkFactory.class);

        // Задаем поведение моков при вызове createAnimal()
        Mockito.when(dogFactory.createAnimal()).thenReturn(
                new Dog("White", "Bars", BigDecimal.valueOf(0.1), LocalDate.of(2020, 1, 8))
        );
        Mockito.when(catFactory.createAnimal()).thenReturn(
                new Cat("White", "Bars", BigDecimal.valueOf(0.1), LocalDate.of(2020, 1, 8))
        );
        Mockito.when(wolfFactory.createAnimal()).thenReturn(
                new Wolf("White", "Bars", BigDecimal.valueOf(0.1), LocalDate.of(2020, 1, 8))
        );
        Mockito.when(sharkFactory.createAnimal()).thenReturn(
                new Shark("White", "Bars", BigDecimal.valueOf(0.1), LocalDate.of(2020, 1, 8))
        );

        // Задаем поведение мока AbstractAnimalFactory при вызове createAnimalFactory()
        Mockito.when(animalFactory.createAnimalFactory(Mockito.any())).thenAnswer(invocation -> {
            AnimalFactory<?> createdFactory = null;
            AnimalFactory<?> argFactory = invocation.getArgument(0);

            if (argFactory instanceof DogFactory) {
                createdFactory = dogFactory;
            } else if (argFactory instanceof CatFactory) {
                createdFactory = catFactory;
            } else if (argFactory instanceof WolfFactory) {
                createdFactory = wolfFactory;
            } else if (argFactory instanceof SharkFactory) {
                createdFactory = sharkFactory;
            }

            return createdFactory;
        });

        return animalFactory;
    }
}

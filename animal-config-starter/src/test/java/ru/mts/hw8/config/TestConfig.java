package ru.mts.hw8.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.mts.hw7.domain.Cat;
import ru.mts.hw7.domain.Dog;
import ru.mts.hw7.domain.Shark;
import ru.mts.hw7.domain.Wolf;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.factory.AnimalFactory;
import ru.mts.hw7.factory.impl.CatFactory;
import ru.mts.hw7.factory.impl.DogFactory;
import ru.mts.hw7.factory.impl.SharkFactory;
import ru.mts.hw7.factory.impl.WolfFactory;
import ru.mts.hw8.config.bpp.MyTestBeanPostProcessor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static ru.mts.hw7.domain.enums.AnimalType.*;

@Profile("test")
@TestConfiguration
public class TestConfig {

    @Primary
    @Bean(name = "mts_MyTestBeanPostProcessor")
    public MyTestBeanPostProcessor myTestBeanPostProcessor() {
        return new MyTestBeanPostProcessor();
    }

    @Primary
    @Bean(name = AbstractAnimalFactory.NAME + "Test")
    public AbstractAnimalFactory animalFactory() {
        var animalFactory = Mockito.mock(AbstractAnimalFactory.class);

        // Для каждого типа животного создаем соответствующий мок AnimalFactory
        var dogFactory = Mockito.mock(DogFactory.class);
        var catFactory = Mockito.mock(CatFactory.class);
        var wolfFactory = Mockito.mock(WolfFactory.class);
        var sharkFactory = Mockito.mock(SharkFactory.class);

        // Задаем поведение моков при вызове createAnimal()
        Mockito.when(dogFactory.createAnimal())
                .thenReturn(
                        new Dog("White", "Mock_Bars",
                                BigDecimal.valueOf(0.1).setScale(2, RoundingMode.HALF_UP),
                                LocalDate.of(2020, 1, 8)
                        )
                );

        Mockito.when(catFactory.createAnimal())
                .thenReturn(
                        new Cat("White", "Mock_Bars",
                                BigDecimal.valueOf(0.1).setScale(2, RoundingMode.HALF_UP),
                                LocalDate.of(2020, 1, 8)
                        )
                );

        Mockito.when(wolfFactory.createAnimal())
                .thenReturn(
                        new Wolf("White", "Mock_Bars",
                                BigDecimal.valueOf(0.1).setScale(2, RoundingMode.HALF_UP),
                                LocalDate.of(2020, 1, 8))

                );

        Mockito.when(sharkFactory.createAnimal())
                .thenReturn(
                        new Shark("White", "Mock_Bars",
                                BigDecimal.valueOf(0.1).setScale(2, RoundingMode.HALF_UP),
                                LocalDate.of(2020, 1, 8)
                        )
                );

        // Задаем поведение мока AbstractAnimalFactory при вызове createAnimalFactory()
        Mockito.when(animalFactory.createAnimalFactory(Mockito.any()))
                .thenAnswer(invocation -> {
                    final AnimalFactory<?> result;
                    final AnimalType animalTypeArg = invocation.getArgument(0, AnimalType.class);

                    if (DOG.equals(animalTypeArg)) {
                        result = dogFactory;
                    } else if (CAT.equals(animalTypeArg)) {
                        result = catFactory;
                    } else if (WOLF.equals(animalTypeArg)) {
                        result = wolfFactory;
                    } else if (SHARK.equals(animalTypeArg)) {
                        result = sharkFactory;
                    } else {
                        throw new UnsupportedOperationException("Un");
                    }

                    return result;
                });

        return animalFactory;
    }

}

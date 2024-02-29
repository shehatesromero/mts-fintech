package ru.mts.hw7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw7.domain.Cat;
import ru.mts.hw7.domain.Dog;
import ru.mts.hw7.domain.Shark;
import ru.mts.hw7.domain.Wolf;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.factory.impl.*;
import ru.mts.hw8.service.CreateAnimalService;
import ru.mts.hw8.service.impl.CreateAnimalServiceImpl;

/**
 * @author Vladislav Gruzdov
 */
@EnableConfigurationProperties(AnimalDataProperties.class)
@Configuration
public class AppConfig {

    @Bean(name = RandomAnimalFactory.NAME)
    public RandomAnimalFactory<? extends Animal> randomAnimalFactory() {
        return new RandomAnimalFactory<>();
    }

    @Bean(name = CatFactory.NAME)
    public CatFactory<? extends Cat> catFactory(@Autowired AnimalDataProperties animalDataProperties) {
        return new CatFactory<>(animalDataProperties);
    }

    @Bean(name = DogFactory.NAME)
    public DogFactory<? extends Dog> dogFactory(@Autowired AnimalDataProperties animalDataProperties) {
        return new DogFactory<>(animalDataProperties);
    }

    @Bean(name = SharkFactory.NAME)
    public SharkFactory<? extends Shark> sharkFactory(@Autowired AnimalDataProperties animalDataProperties) {
        return new SharkFactory<>(animalDataProperties);
    }

    @Bean(name = WolfFactory.NAME)
    public WolfFactory<? extends Wolf> wolfFactory(@Autowired AnimalDataProperties animalDataProperties) {
        return new WolfFactory<>(animalDataProperties);
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME)
    public CreateAnimalService createAnimalService(@Autowired AbstractAnimalFactory abstractAnimalFactory) {
        return new CreateAnimalServiceImpl(abstractAnimalFactory);
    }

}

package ru.mts.hw7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.factory.AnimalFactory;
import ru.mts.hw7.factory.impl.AbstractAnimalFactoryImpl;
import ru.mts.hw7.provider.AnimalDataProvider;

import java.util.Map;

@Configuration
@ConditionalOnClass(AnimalDataProvider.class)
@EnableConfigurationProperties(AnimalDataProperties.class)
public class AnimalDataAutoConfiguration {

    private final AnimalDataProperties animalDataProperties;

    @Autowired
    public AnimalDataAutoConfiguration(AnimalDataProperties animalDataProperties) {
        this.animalDataProperties = animalDataProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public AnimalDataConfig animalDataConfig() {
        return AnimalDataConfig.createInstance(animalDataProperties.getAnimalData());
    }

    @Bean
    @ConditionalOnMissingBean
    public AnimalDataProvider animalData(@Autowired AnimalDataConfig config) {
        return new AnimalDataProvider(config);
    }

    @ConditionalOnMissingBean
    @Bean(name = AbstractAnimalFactory.NAME)
    public AbstractAnimalFactory abstractAnimalFactory(@Autowired Map<String, AnimalFactory<? extends Animal>> animalFactorys) {
        return new AbstractAnimalFactoryImpl(animalFactorys);
    }

}


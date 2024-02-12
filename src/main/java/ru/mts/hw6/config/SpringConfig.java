package ru.mts.hw6.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw6.factory.AnimalFactory;
import ru.mts.hw6.factory.impl.AnimalFactoryImpl;
import ru.mts.hw6.repository.AnimalsRepository;
import ru.mts.hw6.repository.AnimalsRepositoryImpl;
import ru.mts.hw6.service.CreateAnimalService;
import ru.mts.hw6.service.impl.CreateAnimalServiceImpl;

@Configuration
public class SpringConfig {

    @Bean
    public static MyBeanPostProcessor addPostProcessorImpl() {
        return new MyBeanPostProcessor();
    }

    @Bean(name = AnimalFactory.NAME)
    public AnimalFactory animalFactory() {
        return new AnimalFactoryImpl();
    }

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME)
    public CreateAnimalService createAnimalService(@Autowired AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }

    @Bean(name = AnimalsRepository.NAME, initMethod = "postConstruct")
    public AnimalsRepository animalsRepository(@Autowired ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        return new AnimalsRepositoryImpl(createAnimalServicesBeanProvider);
    }

}

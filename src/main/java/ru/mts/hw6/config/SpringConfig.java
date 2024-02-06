package ru.mts.hw6.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService(){
        return new CreateAnimalServiceImpl(new AnimalFactoryImpl());
    }


    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService){
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}

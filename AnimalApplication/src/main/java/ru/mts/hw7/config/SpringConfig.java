package ru.mts.hw7.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw7.config.bpp.MyBeanPostProcessor;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.repository.AnimalsRepository;
import ru.mts.hw7.repository.AnimalsRepositoryImpl;
import ru.mts.hw7.service.CreateAnimalService;
import ru.mts.hw7.service.impl.CreateAnimalServiceImpl;

@EnableConfigurationProperties(AppConfigProperties.class)
@Configuration
public class SpringConfig {

    private final AppConfigProperties appConfigProperties;

    @Autowired
    public SpringConfig(AppConfigProperties appConfigProperties) {
        this.appConfigProperties = appConfigProperties;
    }

    @Bean
    public static MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME)
    public CreateAnimalService createAnimalService(@Autowired AbstractAnimalFactory abstractAnimalFactory) {
        return new CreateAnimalServiceImpl(abstractAnimalFactory);
    }

    @Bean(name = AnimalsRepository.NAME, initMethod = "postConstruct")
    public AnimalsRepository animalsRepository(@Autowired ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        return new AnimalsRepositoryImpl(appConfigProperties.getRepositoryCapacity(), createAnimalServicesBeanProvider);
    }

}

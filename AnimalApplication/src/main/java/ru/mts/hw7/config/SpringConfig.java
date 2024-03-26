package ru.mts.hw7.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.mts.hw7.config.bpp.MyBeanPostProcessor;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.repository.AnimalsRepository;
import ru.mts.hw7.repository.AnimalsRepositoryImpl;
import ru.mts.hw7.service.CreateAnimalService;
import ru.mts.hw7.service.impl.CreateAnimalServiceImpl;

import java.util.concurrent.ScheduledExecutorService;

@EnableConfigurationProperties(AppConfigProperties.class)
@Configuration
@EnableAsync
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

    @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.initialize(); // Инициализация ThreadPoolTaskScheduler
        return scheduler;
    }

    @Bean(name = "taskExecutor")
    public ScheduledExecutorService taskExecutor(@Qualifier("taskScheduler") ThreadPoolTaskScheduler scheduler) {
        return scheduler.getScheduledExecutor();
    }

}


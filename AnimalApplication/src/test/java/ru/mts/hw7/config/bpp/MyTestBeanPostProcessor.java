package ru.mts.hw7.config.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.mts.hw8.service.CreateAnimalService;

import javax.annotation.Nullable;

/**
 * @author Vladislav Gruzdov
 */
public class MyTestBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessAfterInitialization(@SuppressWarnings("NullableProblems") Object bean,
                                                 @SuppressWarnings("NullableProblems") String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            ((CreateAnimalService) bean).initAnimalType();
        }

        return bean;
    }

}

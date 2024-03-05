package ru.mts.hw7.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw7.config.AppConfigProperties;
import ru.mts.hw7.repository.AnimalsRepository;

@Component(AnimalsSchedulerMBean.NAME)
public class AnimalsScheduler implements AnimalsSchedulerMBean {


    private final AnimalsRepository animalsRepository;

    private final int animalCount;

    @Autowired
    public AnimalsScheduler(AppConfigProperties appConfigProperties, AnimalsRepository animalsRepository) {
        animalCount = appConfigProperties.getAnimalCount();
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 60 * 1000)
    @Override
    public void printAllRepositoryMethods() {
        System.out.println("Names in a leap year: ");
        System.out.println((animalsRepository.findLeapYearNames()));
        System.out.println("Animals older " + animalCount + " years: ");
        System.out.println(animalsRepository.findOlderAnimal(animalCount));
        System.out.println("Duplicates: ");
        System.out.println(animalsRepository.findDuplicate());
    }

}

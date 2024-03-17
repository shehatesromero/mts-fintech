package ru.mts.hw7.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw7.config.AppConfigProperties;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.repository.AnimalsRepository;

import java.util.List;


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

        animalsRepository.printDuplicate();

        animalsRepository.findAverageAge(animalsRepository.getAllAnimals());

        System.out.println("Old and Expensive Animals: ");
        List<Animal> oldAndExpensiveAnimals = animalsRepository.findOldAndExpensive(animalsRepository.getAllAnimals());
        oldAndExpensiveAnimals.forEach(System.out::println);

        System.out.println("Top 3 Animals with Min Cost, sorted in reverse order: ");
        List<String> minCostAnimalNames = animalsRepository.findMinCostAnimals(animalsRepository.getAllAnimals());
        minCostAnimalNames.forEach(System.out::println);

    }

}

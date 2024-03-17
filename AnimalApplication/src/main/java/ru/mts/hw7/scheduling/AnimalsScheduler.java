package ru.mts.hw7.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw7.config.AppConfigProperties;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.exception.InsufficientArraySizeException;
import ru.mts.hw7.exception.InvalidParameterException;
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

        try {
            System.out.println("Names in a leap year: ");
            System.out.println((animalsRepository.findLeapYearNames()));
        } catch (Exception e) {
            System.out.println("Error occurred while finding leap year names: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Animals older " + animalCount + " years: ");
            System.out.println(animalsRepository.findOlderAnimal(animalCount));
        } catch (IllegalArgumentException e) {
            System.out.println("Error occurred while finding older animals: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            animalsRepository.printDuplicate();
        } catch (Exception e) {
            System.out.println("Error occurred while printing duplicates: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            List<Animal> nullAnimals = null;
            animalsRepository.findAverageAge(nullAnimals);
        } catch (InvalidParameterException e) {
            System.out.println("Error occurred while finding average age: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Old and Expensive Animals: ");
            List<Animal> nullAnimals = null;
            List<Animal> oldAndExpensiveAnimals = animalsRepository.findOldAndExpensive(nullAnimals);
            oldAndExpensiveAnimals.forEach(System.out::println);
        } catch (InvalidParameterException e) {
            System.out.println("Error occurred while finding old and expensive animals: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Top 3 Animals with Min Cost, sorted in reverse order: ");
            List<String> minCostAnimalNames = animalsRepository.findMinCostAnimals(animalsRepository.getAllAnimals().subList(0, 2));
            minCostAnimalNames.forEach(System.out::println);
        } catch (InsufficientArraySizeException e) {
            System.out.println("InsufficientArraySizeException occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

}

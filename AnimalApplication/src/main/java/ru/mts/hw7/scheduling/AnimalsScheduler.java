package ru.mts.hw7.scheduling;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(AnimalsScheduler.class);

    private final AnimalsRepository animalsRepository;

    private final int animalCount;

    @Autowired
    public AnimalsScheduler(AppConfigProperties appConfigProperties, AnimalsRepository animalsRepository) {
        animalCount = appConfigProperties.getAnimalCount();
        this.animalsRepository = animalsRepository;
    }


    @Scheduled(fixedRate = 60 * 1_000)
    @Override
    public void printAllRepositoryMethods() {

        try {
            System.out.println("Names in a leap year: ");
            System.out.println((animalsRepository.findLeapYearNames()));
        } catch (Exception e) {
            logError("Error occurred while finding leap year names", e);
        }

        try {
            System.out.println("Animals older " + animalCount + " years: ");
            System.out.println(animalsRepository.findOlderAnimal(animalCount));
        } catch (IllegalArgumentException e) {
            logError("Error occurred while finding older animals", e);
        }

        try {
            animalsRepository.printDuplicate();
        } catch (Exception e) {
            logError("Error occurred while printing duplicates", e);
        }

        try {
            animalsRepository.findAverageAge(animalsRepository.getAllAnimals());
        } catch (InvalidParameterException e) {
            logError("Error occurred while finding average age", e);
        }

        try {
            System.out.println("Old and Expensive Animals: ");
            List<Animal> oldAndExpensiveAnimals = animalsRepository.findOldAndExpensive(animalsRepository.getAllAnimals());
            oldAndExpensiveAnimals.forEach(System.out::println);
        } catch (InvalidParameterException e) {
            logError("Error occurred while finding old and expensive animals", e);
        }

        try {
            System.out.println("Top 3 Animals with Min Cost, sorted in reverse order: ");
            List<String> minCostAnimalNames = animalsRepository.findMinCostAnimals(animalsRepository.getAllAnimals());
            minCostAnimalNames.forEach(System.out::println);
        } catch (InsufficientArraySizeException e) {
            logError("InsufficientArraySizeException occurred", e);
        }

    }

    private void logError(String infoMessage, Exception e) {
        log.error("{}: {}", infoMessage, e.getMessage());
        log.error(ExceptionUtils.getStackTrace(e));
    }

}

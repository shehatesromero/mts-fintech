package ru.mts.hw6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.hw6.config.SpringConfig;
import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.factory.impl.AnimalFactoryImpl;
import ru.mts.hw6.repository.AnimalsRepository;
import ru.mts.hw6.service.CreateAnimalService;
import ru.mts.hw6.service.SearchService;
import ru.mts.hw6.service.impl.CreateAnimalServiceImpl;
import ru.mts.hw6.service.impl.SearchServiceImpl;

public class Main {

    private static final SearchService searchService;
    private static final CreateAnimalService createAnimalService;

    static {
        searchService = new SearchServiceImpl();
        createAnimalService = new CreateAnimalServiceImpl(new AnimalFactoryImpl());
    }


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);

        System.out.println("---   Demonstration for prototype bean   ---");
        CreateAnimalService animalService = context.getBean(CreateAnimalServiceImpl.class);
        System.out.println(animalService.getAnimal());
        animalService = context.getBean(CreateAnimalServiceImpl.class);
        System.out.println(animalService.getAnimal());

        System.out.println("---   Demonstration for findOlderAnimal method   ---");
        Animal[] agedOlderAnimals = animalsRepository.findOlderAnimal(3);
        for (Animal animal : agedOlderAnimals) {
            System.out.println(animal);
        }
        System.out.println("---   Demonstration for findLeapYearNames method   ---");
        String[] leapYearAnimalNames = animalsRepository.findLeapYearNames();
        if(leapYearAnimalNames.length == 0){
            System.out.println("There is no leap year animal");
        }
        for (String animalName : leapYearAnimalNames) {
            System.out.println(animalName);
        }
        System.out.println("---   Demonstration for findDuplicate method   ---");
        animalsRepository.printDuplicate();
    }

}
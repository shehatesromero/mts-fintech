package ru.mts.hw4;

import ru.mts.hw4.domain.abstraction.Animal;
import ru.mts.hw4.factory.AnimalFactoryImpl;
import ru.mts.hw4.service.CreateAnimalService;
import ru.mts.hw4.service.CreateAnimalServiceImpl;
import ru.mts.hw4.service.SearchService;
import ru.mts.hw4.service.SearchServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final SearchService searchService;
    private static final CreateAnimalService createAnimalService;

    static {
        searchService = new SearchServiceImpl();
        createAnimalService = new CreateAnimalServiceImpl(new AnimalFactoryImpl());
    }

    public static void main(String[] args) {
        //use default method
        Animal[] defaultMethodResult = new CreateAnimalService() {
        }.createUniqueAnimals();
        System.out.println(Arrays.toString(defaultMethodResult));

        Animal[] generatedAnimals = ((CreateAnimalServiceImpl) createAnimalService).createUniqueAnimals(12);

        List<String> leapYearNames = searchService.findLeapYearNames(generatedAnimals);
        // Выводим только имена животных с високосным годом рождения
        System.out.println(leapYearNames);

        List<String> olderAnimalsList = searchService.findOlderAnimal(generatedAnimals, 20);
        // Выводим животных старше N лет
        System.out.println(olderAnimalsList);

        // Выводим дубликаты животных
        searchService.findDuplicate(generatedAnimals);
    }

}
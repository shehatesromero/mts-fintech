package ru.mts.hw3;

import ru.mts.hw3.service.CreateAnimalService;
import ru.mts.hw3.service.CreateAnimalServiceImpl;

public class Main {

    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();

        System.out.println("Создание 10 уникальных животных по умолчанию: ");
        createAnimalService.createUniqueAnimals();
        if (createAnimalService instanceof CreateAnimalServiceImpl) {
            System.out.println("-------------------------------");
            System.out.println("Создание 2 уникальных животных:");
            ((CreateAnimalServiceImpl) createAnimalService).createUniqueAnimals(2);
        }

    }

}
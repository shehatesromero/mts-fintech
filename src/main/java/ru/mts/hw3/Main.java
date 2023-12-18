package ru.mts.hw3;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        System.out.println("Создание 10 уникальных животных по умолчанию: ");
        createAnimalService.createUniqueAnimals();
        System.out.println("-------------------------------");
        System.out.println("Создание 2 уникальных животных:");
        createAnimalService.createUniqueAnimals(2);

    }
}
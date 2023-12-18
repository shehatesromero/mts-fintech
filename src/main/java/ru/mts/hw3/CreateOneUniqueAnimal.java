package ru.mts.hw3;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Класс CreateOneUniqueAnimal предоставляет функциональность для создания одного уникального животного
 * с использованием случайной генерации имени, породы и стоимости. Класс обеспечивает создание экземпляров
 * различных подклассов Animal (Dog, Cat, Shark, Wolf) в зависимости от случайно сгенерированного числа.
 */
public class CreateOneUniqueAnimal {
    // Метод createOneUniqueAnimal() создает одно уникальное животное с случайно сгенерированными параметрами.
    public Animal createOneUniqueAnimal() {
        Random random = new Random();
        int randomNum = random.nextInt(4);
        String breed = generateRandomBreed(randomNum);
        String name = generateRandomName();
        BigDecimal cost = generateRandomCost();
        switch (randomNum) {
            case 0:
                return new Dog(breed, name, cost);
            case 1:
                return new Cat(breed, name, cost);
            case 2:
                return new Shark(breed, name, cost);
            case 3:
                return new Wolf(breed, name, cost);
            default:
                return null;
        }
    }

    // Метод generateRandomBreed(int randomNum) генерирует случайную породу в зависимости от переданного числа.
    private String generateRandomBreed(int randomNum) {
        Random random = new Random();
        switch (randomNum) {
            case 0: // Для собаки
                String[] dogBreeds = {"Terrier", "Boxer", "Bolognese", "Bulldog", "Chihuahua", "Collie", "Mops",
                        "Spitz", "Labrador", "Mastiff"};
                return dogBreeds[random.nextInt(dogBreeds.length)];
            case 1: // Для кошки
                String[] catBreeds = {"Siamese", "Persian", "Sphynx", "Asian", "Bengal", "Burmilla", "Foldex",
                        "German Rex", "Kinkalow", "Minskin"};
                return catBreeds[random.nextInt(catBreeds.length)];
            case 2: // Для акулы
                String[] sharkBreeds = {"Dogfish", "Angel", "Sawsharks", "Bullhead", "Carpet", "Ground", "Bali catshark",
                        "Blind", "Blue", "Borneo"};
                return sharkBreeds[random.nextInt(sharkBreeds.length)];
            case 3: // Для волка
                String[] wolfBreeds = {"Tundra", "Arabian", "Steppe", "Himalayan", "Mongolian", "Eurasian", "Indian",
                        "Arctic", "Mexican", "Eastern"};
                return wolfBreeds[random.nextInt(wolfBreeds.length)];
            default:
                return "Unknown Breed";
        }
    }

    // Метод generateRandomName() генерирует случайное имя для животного.
    private String generateRandomName() {
        Random random = new Random();
        String[] possibleNames = {"Buddy", "Whiskers", "Fang", "Shadow", "Luna", "Rex", "Misty", "Rocky", "Cleo", "Thor"};
        return possibleNames[random.nextInt(possibleNames.length)];
    }

    // Метод generateRandomCost() генерирует случайную стоимость для животного.
    private BigDecimal generateRandomCost() {
        Random random = new Random();
        return BigDecimal.valueOf(random.nextDouble() * 50000.0);
    }
}

package ru.mts.hw3.factory;

import ru.mts.hw3.domain.Cat;
import ru.mts.hw3.domain.Dog;
import ru.mts.hw3.domain.Shark;
import ru.mts.hw3.domain.Wolf;
import ru.mts.hw3.domain.abstraction.Animal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Класс CreateOneUniqueAnimal предоставляет функциональность для создания одного уникального животного
 * с использованием случайной генерации имени, породы и стоимости. Класс обеспечивает создание экземпляров
 * различных подклассов Animal (Dog, Cat, Shark, Wolf) в зависимости от случайно сгенерированного числа.
 */
public final class AnimalSimpleFactory {

    private static final Map<Integer, List<String>> BREEDS_DATASET = Map.of(
            0, List.of("Terrier", "Boxer", "Bolognese", "Bulldog", "Chihuahua", "Collie", "Mops", "Spitz", "Labrador", "Mastiff"),
            1, List.of("Siamese", "Persian", "Sphynx", "Asian", "Bengal", "Burmilla", "Foldex", "German Rex", "Kinkalow", "Minskin"),
            2, List.of("Dogfish", "Angel", "Sawsharks", "Bullhead", "Carpet", "Ground", "Bali catshark", "Blind", "Blue", "Borneo"),
            3, List.of("Tundra", "Arabian", "Steppe", "Himalayan", "Mongolian", "Eurasian", "Indian", "Arctic", "Mexican", "Eastern")
    );

    private AnimalSimpleFactory() {
    }

    // Метод createOneUniqueAnimal() создает одно уникальное животное с случайно сгенерированными параметрами.
    public static Animal createOneUniqueAnimal() {
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
                throw new UnsupportedOperationException("Unsupported animal type");
        }

    }

    // Метод generateRandomBreed(int randomNum) генерирует случайную породу в зависимости от переданного числа.
    private static String generateRandomBreed(int randomNum) {
        List<String> list = BREEDS_DATASET.get(randomNum);
        if (list == null) {
            return "Unknown Breed";
        }

        Random random = new Random();

        return list.get(random.nextInt(list.size()));
    }

    // Метод generateRandomName() генерирует случайное имя для животного.
    private static String generateRandomName() {
        List<String> possibleNames = List.of(
                "Buddy", "Whiskers", "Fang", "Shadow", "Luna", "Rex", "Misty", "Rocky", "Cleo", "Thor"
        );

        Random random = new Random();

        return possibleNames.get(random.nextInt(possibleNames.size()));
    }

    // Метод generateRandomCost() генерирует случайную стоимость для животного.
    private static BigDecimal generateRandomCost() {
        Random random = new Random();

        return BigDecimal.valueOf(random.nextDouble() * 50000.0);
    }

}

package ru.mts.hw5.factory;

import ru.mts.hw5.domain.Cat;
import ru.mts.hw5.domain.Dog;
import ru.mts.hw5.domain.Shark;
import ru.mts.hw5.domain.Wolf;
import ru.mts.hw5.domain.abstraction.Animal;
import ru.mts.hw5.domain.enums.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Класс CreateOneUniqueAnimal предоставляет собой фабрику животных по типам, а также добавляет функциональность для
 * создания одного уникального животного с использованием случайной генерации имени, породы и стоимости. Класс
 * обеспечивает создание экземпляров различных подклассов Animal (Dog, Cat, Shark, Wolf) в зависимости от типа
 * животного.
 */
// 'SimpleFactory'
public final class AnimalSimpleFactory {

    private static final Map<Integer, List<String>> BREEDS_DATASET = Map.of(
            0, List.of("Terrier", "Boxer", "Bolognese", "Bulldog", "Chihuahua", "Collie", "Mops", "Spitz", "Labrador", "Mastiff"),
            1, List.of("Siamese", "Persian", "Sphynx", "Asian", "Bengal", "Burmilla", "Foldex", "German Rex", "Kinkalow", "Minskin"),
            2, List.of("Dogfish", "Angel", "Sawsharks", "Bullhead", "Carpet", "Ground", "Bali catshark", "Blind", "Blue", "Borneo"),
            3, List.of("Tundra", "Arabian", "Steppe", "Himalayan", "Mongolian", "Eurasian", "Indian", "Arctic", "Mexican", "Eastern")
    );

    private AnimalSimpleFactory() {
    }

    // Метод createOneUniqueAnimal() создает одно уникальное животное основываясь на типе животного (CAT, DOG..).
    public static Animal createReandomAnimal() {
        var random = new Random();

        var types = AnimalType.values();
        int index = random.nextInt(types.length);

        var breed = generateRandomBreed(index);
        var name = generateRandomName();
        var cost = generateRandomCost();
        var birthDate = randomBirthday();

        switch (types[index]) {
            case DOG:
                return new Dog(breed, name, cost, birthDate);
            case CAT:
                return new Cat(breed, name, cost, birthDate);
            case SHARK:
                return new Shark(breed, name, cost, birthDate);
            case WOLF:
                return new Wolf(breed, name, cost, birthDate);
            default:
                throw new UnsupportedOperationException("Unsupported animal type");
        }

    }

    // Метод generateRandomBreed(int index) генерирует случайную породу в зависимости от переданного индекса типа.
    private static String generateRandomBreed(int index) {
        List<String> list = BREEDS_DATASET.get(index);
        if (list == null) {
            return "Unknown Breed";
        }

        var random = new Random();

        return list.get(random.nextInt(list.size()));
    }

    // Метод generateRandomName() генерирует случайное имя для животного.
    private static String generateRandomName() {
        List<String> possibleNames = List.of(
                "Buddy", "Whiskers", "Fang", "Shadow", "Luna", "Rex", "Misty", "Rocky", "Cleo", "Thor"
        );

        var random = new Random();

        return possibleNames.get(random.nextInt(possibleNames.size()));
    }

    // Метод generateRandomCost() генерирует случайную стоимость для животного.
    private static BigDecimal generateRandomCost() {
        var random = new Random();

        return BigDecimal.valueOf(random.nextDouble() * 50000.0);
    }

    // Метод randomBirthday() генерирует случайный день рождения, вычитая из нынешнего времени период 30 лет
    private static LocalDate randomBirthday() {
        var now = LocalDate.now();
        var period = Period.ofDays((new Random().nextInt(365 * 30)));

        return now.minus(period);
    }

}
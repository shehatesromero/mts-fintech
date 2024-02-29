package ru.mts.hw7.factory.impl;

import org.apache.commons.lang3.StringUtils;
import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.factory.AnimalFactory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Vladislav Gruzdov
 */
public abstract class BaseAnimalFactory<T extends Animal> implements AnimalFactory<T> {

    protected final List<String> names;
    protected final List<String> breeds;
    protected final List<String> characters;

    public BaseAnimalFactory(Map<String, List<String>> props) {
        names = props.getOrDefault("names", List.of("TOM", "JERRY"));
        breeds = initBreeds(props);
        characters = props.getOrDefault("characters", List.of("LAZY, EAGER"));
    }

    private List<String> initBreeds(Map<String, List<String>> props) {
        final String animalTypeStr =
                StringUtils.substringBefore(getClass().getSimpleName(), "Factory").toLowerCase();

        return props.entrySet()
                .stream()
                .filter(entry -> StringUtils.endsWith(entry.getKey(), "-breeds"))
                .filter(entry -> StringUtils.startsWith(entry.getKey(), animalTypeStr))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public T createAnimal() {
        var breed = generateRandomBreed();
        var name = generateRandomName();
        var cost = generateRandomCost();
        var character = generateRandomCharacter();
        var birthDate = generateRandomBirthday();

        var createdClass = getCreatedClass();

        try {
            var constructors = createdClass.getDeclaredConstructors();
            var targetConstructor = Arrays.stream(constructors)
                    .filter(c -> c.getParameterCount() == 5)
                    .findFirst()
                    .orElseThrow();

            //noinspection unchecked
            return (T) targetConstructor.newInstance(breed, name, cost, character, birthDate);

        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    // Метод generateRandomBreed(int index) генерирует случайную породу в зависимости от переданного индекса типа.
    protected String generateRandomBreed() {
        var random = ThreadLocalRandom.current();

        return breeds.get(random.nextInt(breeds.size()));
    }

    // Метод generateRandomName() генерирует случайное имя для животного.
    protected String generateRandomName() {
        var random = ThreadLocalRandom.current();

        return names.get(random.nextInt(names.size()));
    }

    // Метод generateRandomCost() генерирует случайную стоимость для животного.
    private BigDecimal generateRandomCost() {
        var random = ThreadLocalRandom.current();

        return BigDecimal.valueOf(random.nextDouble() * 50000.0);
    }

    // Метод randomBirthday() генерирует случайный день рождения, вычитая из нынешнего времени период 30 лет
    private LocalDate generateRandomBirthday() {
        var now = LocalDate.now();
        var random = ThreadLocalRandom.current();

        var period = Period.ofDays(random.nextInt(365 * 30));

        return now.minus(period);
    }

    protected String generateRandomCharacter() {
        var random = ThreadLocalRandom.current();

        return characters.get(random.nextInt(characters.size()));
    }

}

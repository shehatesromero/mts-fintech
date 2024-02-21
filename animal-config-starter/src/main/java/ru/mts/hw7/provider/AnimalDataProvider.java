package ru.mts.hw7.provider;

import ru.mts.hw7.config.AnimalDataConfig;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AnimalDataProvider {

    private final AnimalDataConfig config;

    public AnimalDataProvider(AnimalDataConfig config) {
        this.config = config;
    }

    private String getRandomStringFromConfig(String key) {
        List<String> values = config.getOrDefault(key, Collections.emptyList());
        if (values.isEmpty()) {
            return EMPTY;
        }

        var random = ThreadLocalRandom.current();

        return values.get(random.nextInt(values.size()));
    }

    public String getRandomName() {
        return getRandomStringFromConfig("names");
    }

    public String getRandomDogBreed() {
        return getRandomStringFromConfig("dogBreeds");
    }

    public String getRandomCatBreed() {
        return getRandomStringFromConfig("catBreeds");
    }

    public String getRandomSharkBreed() {
        return getRandomStringFromConfig("sharkBreeds");
    }

    public String getRandomWolfBreed() {
        return getRandomStringFromConfig("wolfBreeds");
    }

    public String getRandomCharacter() {
        return getRandomStringFromConfig("characters");
    }

}


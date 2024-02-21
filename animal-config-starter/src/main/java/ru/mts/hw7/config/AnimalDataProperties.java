package ru.mts.hw7.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "application")
public class AnimalDataProperties {

    private Map<String, List<String>> animalData;

    public Map<String, List<String>> getAnimalData() {
        return animalData;
    }

    public void setAnimalData(Map<String, List<String>> animalData) {
        this.animalData = animalData;
    }

}


package ru.mts.hw7.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Vladislav Gruzdov
 */
@ConfigurationProperties(prefix = "application")
public class AppConfigProperties {

    private int repositoryCapacity;
    private int animalCount;

    public int getRepositoryCapacity() {
        return repositoryCapacity;
    }

    public void setRepositoryCapacity(int repositoryCapacity) {
        this.repositoryCapacity = repositoryCapacity;
    }

    public int getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(int animalCount) {
        this.animalCount = animalCount;
    }

}

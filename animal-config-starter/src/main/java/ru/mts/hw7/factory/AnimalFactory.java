package ru.mts.hw7.factory;

import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;

public interface AnimalFactory<T extends Animal> extends Factory {

    T createAnimal();

    boolean isApplicable(AnimalType animalType);

    Class<? extends Animal> getCreatedClass();

}

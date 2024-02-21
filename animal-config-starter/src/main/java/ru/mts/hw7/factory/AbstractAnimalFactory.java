package ru.mts.hw7.factory;

import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;

/**
 * @author Vladislav Gruzdov
 */
public interface AbstractAnimalFactory extends Factory {

    String NAME = "mts_AbstractAnimalFactory";

    AnimalFactory<? extends Animal> createAnimalFactory(final AnimalType animalType);

}

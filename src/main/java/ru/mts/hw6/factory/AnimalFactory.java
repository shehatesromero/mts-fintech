package ru.mts.hw6.factory;

import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.domain.enums.AnimalType;

public interface AnimalFactory extends Factory {

    String NAME = "mts_AnimalFactory";

    Animal createRandomAnimal();

    Animal createAnimal(AnimalType animalType);

}

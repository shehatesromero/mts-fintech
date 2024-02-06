package ru.mts.hw6.factory;

import ru.mts.hw6.domain.abstraction.Animal;

public interface AnimalFactory extends Factory {

    Animal createRandomAnimal();

}

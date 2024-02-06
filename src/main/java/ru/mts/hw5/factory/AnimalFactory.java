package ru.mts.hw5.factory;

import ru.mts.hw5.domain.abstraction.Animal;

public interface AnimalFactory extends Factory {

    Animal createReandomAnimal();

}

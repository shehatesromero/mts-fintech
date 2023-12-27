package ru.mts.hw4.factory;

import ru.mts.hw4.domain.abstraction.Animal;

public interface AnimalFactory extends Factory {

    Animal createReandomAnimal();

}

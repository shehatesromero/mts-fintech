package ru.mts.hw6.factory.impl;

import ru.mts.hw6.domain.abstraction.Animal;
import ru.mts.hw6.domain.abstraction.Instance;
import ru.mts.hw6.domain.enums.AnimalType;
import ru.mts.hw6.factory.AnimalFactory;
import ru.mts.hw6.factory.AnimalSimpleFactory;

//'Factory' pattern
public class AnimalFactoryImpl implements AnimalFactory {

    @Override
    public Instance createRandomInstance() {
        return createRandomAnimal();
    }

    @Override
    public Animal createRandomAnimal() {
        return AnimalSimpleFactory.createRandomAnimal();
    }

    @Override
    public Animal createAnimal(AnimalType animalType) {
        return AnimalSimpleFactory.createRandomAnimal();
    }

}

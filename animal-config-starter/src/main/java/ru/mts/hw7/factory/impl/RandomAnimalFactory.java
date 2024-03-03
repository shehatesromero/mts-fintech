package ru.mts.hw7.factory.impl;

import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AnimalFactory;
import ru.mts.hw7.factory.AnimalSimpleFactory;
import ru.mts.hw7.factory.RandomInstanceFactory;

/**
 * @author Vladislav Gruzdov
 */
public class RandomAnimalFactory<T extends Animal> implements AnimalFactory<T>, RandomInstanceFactory {

    public static final String NAME = "mts_RandomAnimalFactory";

    @Override
    public T createRandomInstance() {
        //noinspection unchecked
        return (T) AnimalSimpleFactory.createRandomAnimal();
    }

    @Override
    public T createAnimal() {
        return createRandomInstance();
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return false;
    }

    @Override
    public Class<? extends Animal> getCreatedClass() {
        return Animal.class;
    }

}

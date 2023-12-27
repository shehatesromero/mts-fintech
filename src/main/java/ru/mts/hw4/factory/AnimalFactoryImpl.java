package ru.mts.hw4.factory;

import ru.mts.hw4.domain.abstraction.Animal;
import ru.mts.hw4.domain.abstraction.Instance;

//'Factory' pattern
public class AnimalFactoryImpl implements AnimalFactory {

    @Override
    public Instance createRandomInstance() {
        return createReandomAnimal();
    }

    @Override
    public Animal createReandomAnimal() {
        return AnimalSimpleFactory.createReandomAnimal();
    }

}

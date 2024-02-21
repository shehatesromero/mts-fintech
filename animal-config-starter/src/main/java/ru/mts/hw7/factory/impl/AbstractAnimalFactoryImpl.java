package ru.mts.hw7.factory.impl;

import ru.mts.hw7.domain.abstraction.Animal;
import ru.mts.hw7.domain.enums.AnimalType;
import ru.mts.hw7.factory.AbstractAnimalFactory;
import ru.mts.hw7.factory.AnimalFactory;

import java.util.Map;

/**
 * @author Vladislav Gruzdov
 */
public class AbstractAnimalFactoryImpl implements AbstractAnimalFactory {

    private final Map<String, AnimalFactory<? extends Animal>> animalFactorys;

    public AbstractAnimalFactoryImpl(Map<String, AnimalFactory<? extends Animal>> animalFactorys) {
        this.animalFactorys = animalFactorys;
    }

    @Override
    public AnimalFactory<? extends Animal> createAnimalFactory(final AnimalType animalType) {
        return animalFactorys.values()
                .stream()
                .filter(f -> f.isApplicable(animalType))
                .findFirst()
                .orElseThrow();
    }

}

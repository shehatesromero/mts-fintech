package ru.mts.hw7.factory.impl;

import ru.mts.hw7.config.AnimalDataProperties;
import ru.mts.hw7.domain.Cat;
import ru.mts.hw7.domain.enums.AnimalType;

import static ru.mts.hw7.domain.enums.AnimalType.CAT;

public class CatFactory<T extends Cat> extends BaseAnimalFactory<T> {

    public static final String NAME = "mts_CatFactory";

    public CatFactory(AnimalDataProperties animalDataProperties) {
        super(animalDataProperties.getAnimalData());
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return CAT.equals(animalType);
    }

    @Override
    public Class<? extends Cat> getCreatedClass() {
        return Cat.class;
    }

}

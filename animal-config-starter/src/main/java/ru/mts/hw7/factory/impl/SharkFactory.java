package ru.mts.hw7.factory.impl;

import ru.mts.hw7.config.AnimalDataProperties;
import ru.mts.hw7.domain.Shark;
import ru.mts.hw7.domain.enums.AnimalType;

import static ru.mts.hw7.domain.enums.AnimalType.SHARK;

public class SharkFactory<T extends Shark> extends BaseAnimalFactory<T> {

    public static final String NAME = "mts_SharkFactory";

    public SharkFactory(AnimalDataProperties animalDataProperties) {
        super(animalDataProperties.getAnimalData());
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return SHARK.equals(animalType);
    }

    @Override
    public Class<? extends Shark> getCreatedClass() {
        return Shark.class;
    }

}

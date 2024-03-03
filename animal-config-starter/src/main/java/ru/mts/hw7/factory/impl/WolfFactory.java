package ru.mts.hw7.factory.impl;

import ru.mts.hw7.config.AnimalDataProperties;
import ru.mts.hw7.domain.Wolf;
import ru.mts.hw7.domain.enums.AnimalType;

import static ru.mts.hw7.domain.enums.AnimalType.WOLF;

public class WolfFactory<T extends Wolf> extends BaseAnimalFactory<T> {

    public static final String NAME = "mts_WolfFactory";

    public WolfFactory(AnimalDataProperties animalDataProperties) {
        super(animalDataProperties.getAnimalData());
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return WOLF.equals(animalType);
    }

    @Override
    public Class<? extends Wolf> getCreatedClass() {
        return Wolf.class;
    }

}

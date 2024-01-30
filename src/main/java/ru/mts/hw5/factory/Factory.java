package ru.mts.hw5.factory;

import ru.mts.hw5.domain.abstraction.Instance;

public interface Factory {

    //'Factory method' pattern
    Instance createRandomInstance();

}

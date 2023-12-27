package ru.mts.hw4.factory;

import ru.mts.hw4.domain.abstraction.Instance;

public interface Factory {

    //'Factory method' pattern
    Instance createRandomInstance();

}

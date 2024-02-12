package ru.mts.hw6.factory;

import ru.mts.hw6.domain.abstraction.Instance;

public interface Factory {

    //'Factory method' pattern
    Instance createRandomInstance();

}

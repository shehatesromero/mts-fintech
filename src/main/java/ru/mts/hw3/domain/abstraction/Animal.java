package ru.mts.hw3.domain.abstraction;

import java.math.BigDecimal;

/**
 * Интерфейс Animal представляет абстракцию для описания животных.
 * Животные, реализующие этот интерфейс, должны предоставлять информацию
 * о своей породе, имени, стоимости и характере.
 */
public interface Animal {

    String getBreed();

    String getName();

    BigDecimal getCost();

    String getCharacter();

}

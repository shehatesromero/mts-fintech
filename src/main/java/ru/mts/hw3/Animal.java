package ru.mts.hw3;

import java.math.BigDecimal;

/**
 * Интерфейс Animal представляет абстракцию для описания животных.
 * Животные, реализующие этот интерфейс, должны предоставлять информацию
 * о своей породе, имени, стоимости и характере.
 */
public interface Animal {
    @SuppressWarnings("unused")
    String getBreed();

    @SuppressWarnings("unused")
    String getName();

    @SuppressWarnings("unused")
    BigDecimal getCost();

    @SuppressWarnings("unused")
    String getCharacter();
}

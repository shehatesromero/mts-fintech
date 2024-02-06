package ru.mts.hw6.service;

import ru.mts.hw6.domain.abstraction.Animal;

import java.util.List;

/**
 * Интерфейс SearchService предоставляет методы для поиска и анализа животных
 * в массиве объектов типа Animal. Эти методы обеспечивают функциональность,
 * связанную с определением перескока високосных годов, поиском животных старше
 * заданного возраста и выявлением дубликатов по уникальному идентификатору.
 */
public interface SearchService {

    List<String> findLeapYearNames(Animal[] animals);

    List<String> findOlderAnimal(Animal[] animals, int age);

    List<String> findDuplicate(Animal[] animals);

    void printDuplicate(Animal[] animals);
}

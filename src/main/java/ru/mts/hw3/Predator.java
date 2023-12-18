package ru.mts.hw3;

/**
 * Абстрактный класс Predator является подклассом класса AbstractAnimal и представляет общие характеристики
 * для хищных животных. Конструктор класса инициализирует характер хищного животного значением "Angry".
 * Класс является абстрактным, что означает, что он может содержать абстрактные методы, оставленные для
 * реализации в его конкретных подклассах.
 */
public abstract class Predator extends AbstractAnimal {
    Predator() {
        this.character = "Angry";
    }
}
package by.bsu.lab4.factory;

import by.bsu.lab4.entity.MusicComposition;

public abstract class CompositionFactory {

    public abstract MusicComposition create(String[] fields);
}
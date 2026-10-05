package by.bsu.lab4.entity;

import java.util.ArrayList;
import java.util.List;

public class Disc {
    private final String name;
    private final List<MusicComposition> compositions = new ArrayList<>();

    public Disc(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<MusicComposition> getCompositions() {
        return compositions;
    }

    public void addComposition(MusicComposition composition) {
        compositions.add(composition);
    }

    @Override
    public String toString() {
        return "Disc[name=" + name + ", compositions=" + compositions + "]";
    }
}
package by.bsu.lab4.comparator;

import by.bsu.lab4.entity.MusicComposition;

import java.util.Comparator;

public class StyleComparator implements Comparator<MusicComposition> {

    @Override
    public int compare(MusicComposition first, MusicComposition second) {
        return first.getStyle().compareTo(second.getStyle());
    }
}
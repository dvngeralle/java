package by.bsu.lab4.service;

import by.bsu.lab4.comparator.StyleComparator;
import by.bsu.lab4.entity.Disc;
import by.bsu.lab4.entity.MusicComposition;

import java.util.ArrayList;
import java.util.List;

public class DiscService {

    public int calculateTotalDuration(Disc disc) {
        int total = 0;
        for (MusicComposition composition : disc.getCompositions()) {
            total += composition.getDurationSeconds();
        }
        return total;
    }

    public void sortByStyle(Disc disc) {
        disc.getCompositions().sort(new StyleComparator());
    }

    public List<MusicComposition> findByDuration(Disc disc, int minSeconds, int maxSeconds) {
        if (minSeconds > maxSeconds) {
            throw new IllegalArgumentException("minSeconds must not exceed maxSeconds");
        }
        List<MusicComposition> result = new ArrayList<>();
        for (MusicComposition composition : disc.getCompositions()) {
            int duration = composition.getDurationSeconds();
            if (duration >= minSeconds && duration <= maxSeconds) {
                result.add(composition);
            }
        }
        return result;
    }
}
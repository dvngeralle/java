package by.bsu.lab4.factory;

import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;
import by.bsu.lab4.entity.Song;

public class SongFactory extends CompositionFactory {

    @Override
    public MusicComposition create(String[] fields) {
        return new Song(fields[1].trim(), fields[2].trim(),
                Integer.parseInt(fields[3].trim()),
                MusicStyle.valueOf(fields[4].trim()), fields[5].trim());
    }
}
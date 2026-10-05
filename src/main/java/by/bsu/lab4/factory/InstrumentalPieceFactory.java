package by.bsu.lab4.factory;

import by.bsu.lab4.entity.InstrumentalPiece;
import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;

public class InstrumentalPieceFactory extends CompositionFactory {

    @Override
    public MusicComposition create(String[] fields) {
        return new InstrumentalPiece(fields[1].trim(), fields[2].trim(),
                Integer.parseInt(fields[3].trim()),
                MusicStyle.valueOf(fields[4].trim()), fields[5].trim());
    }
}
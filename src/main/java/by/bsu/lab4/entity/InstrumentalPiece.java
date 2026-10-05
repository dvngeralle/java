package by.bsu.lab4.entity;

public class InstrumentalPiece extends MusicComposition {
    private final String mainInstrument;
    public InstrumentalPiece(String title, String artist, int durationSeconds,
                MusicStyle style, String mainInstrument) {
        super(title, artist, durationSeconds, style);
        this.mainInstrument = mainInstrument ;
    }
    public String getMainInstrument() {
        return mainInstrument;
    }
    @Override
    public String toString(){
        return super.toString() + ", mainInstrument=" + mainInstrument + "]";
    }
}

package by.bsu.lab4.entity;

public class ClassicalWork extends MusicComposition{
    private final String composer;
    private final int opusNumber;
    public ClassicalWork(String title, String artist, int durationSeconds,
                MusicStyle style, String composer, int opusNumber) {
        super(title, artist, durationSeconds, style);
        this.composer = composer;
        this.opusNumber = opusNumber;
    }
    public String getComposer() {
        return composer;
    }
    public int getOpusNumber() {
        return opusNumber;
    }
    @Override
    public String toString(){
        return super.toString() + ", composer=" + composer + ", opusNumber=" + opusNumber + "]";
    }
}

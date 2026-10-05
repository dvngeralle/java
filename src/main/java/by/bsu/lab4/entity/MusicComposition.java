package by.bsu.lab4.entity;

public abstract class MusicComposition {
    private final String title;
    private final String artist;
    private final int durationSeconds;
    private final MusicStyle style;

    protected MusicComposition(String title, String artist, int durationSeconds, MusicStyle style) {
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public MusicStyle getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[title=" + title + ", artist=" + artist
                + ", duration=" + durationSeconds + "s, style=" + style;
    }
}
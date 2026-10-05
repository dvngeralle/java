package by.bsu.lab4.entity;

public class Song extends MusicComposition {
    private final String lyricsLanguage;

    public Song(String title, String artist, int durationSeconds,
                MusicStyle style, String lyricsLanguage) {
        super(title, artist, durationSeconds, style);
        this.lyricsLanguage = lyricsLanguage;
    }

    public String getLyricsLanguage() {
        return lyricsLanguage;
    }

    @Override
    public String toString() {
        return super.toString() + ", lyricsLanguage=" + lyricsLanguage + "]";
    }
}
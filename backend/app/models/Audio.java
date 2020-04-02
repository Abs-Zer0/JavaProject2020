package models;

import java.util.Collection;

public class Audio {
    private final long id;
    private String name;
    private final double duration;
    private String genres;

    public Audio(long ID, String Name, double Duration, String Genres) {
        this(ID, Name, Duration);
        this.genres = Genres;
    }

    public Audio(long ID, String Name, double Duration) {
        this.id = ID;
        this.name = Name;
        this.duration = Duration;
        this.genres = "";
    }

    public long getId() {
        return this.id;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public void addGenre(String genre) {
        this.genres += ", " + genre;
    }

    public void addGenres(Collection<String> genres) {
        for (String genre : genres) {
            this.addGenre(genre);
        }
    }

    public String getGenres() {
        return this.genres;
    }
}

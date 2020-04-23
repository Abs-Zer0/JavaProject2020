/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import java.sql.Time;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author illyasviel
 */
@Entity
@Table(name = "audios")
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;
    @Column(name = "name", nullable = false)
    @Size(min = 1)
    private String name;
    @Column(name = "duration", nullable = false)
    private Time duration;
    @Column(name = "artist", nullable = false)
    @Size(min = 1)
    private String artist;
    @Column(name = "path")
    private String path;
    @Column(name = "genres")
    private String genres;
    @ManyToMany(mappedBy = "audios")
    private List<Playlist> playlists;

    public Audio() {
    }

    public Audio(long id, String name, Time duration) {
        this.Id = id;
        this.name = name;
        this.duration = duration;
        this.path = "";
        this.artist = "";
        this.genres = "";
    }

    public Audio(long id, String name, Time duration, String path) {
        this(id, name, duration);
        this.path = path;
    }

    public Audio(long id, String name, String artists, Time duration, String path) {
        this(id, name, duration, path);
        this.artist = artists;
    }

    public Audio(long id, String name, Time duration, String path, String genres) {
        this(id, name, duration, path);
        this.genres = genres;
    }

    public Audio(long id, String name, String artists, Time duration, String path, String genres) {
        this(id, name, artists, duration, path);
        this.genres = genres;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Audio other = (Audio) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.genres, other.genres)) {
            return false;
        }
        return true;
    }

    public long getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getArtists() {
        return this.artist;
    }

    public void setArtists(String newArtists) {
        this.artist = newArtists;
    }

    public void addArtist(String artist) {
        this.artist += ", " + artist;
    }

    public Time getDuration() {
        return this.duration;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String newPath) {
        this.path = newPath;
    }

    public String getGenres() {
        return this.genres;
    }

    public void setGenres(String newGenres) {
        this.genres = newGenres;
    }

    public void addGenre(String genre) {
        this.genres += "; " + genre;
    }

}

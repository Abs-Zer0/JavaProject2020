/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import java.util.List;
import javax.persistence.*;

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
    private String name;
    @Column(name = "duration", nullable = false)
    private double duration;
    @Column(name = "genres")
    private String genres;
    @ManyToMany(mappedBy = "audios")
    private List<Playlist> playlists;

    public Audio(long id, String name, double duration) {
        this.Id = id;
        this.name = name;
        this.duration = duration;
        this.genres = new String();
    }

    public Audio(long id, String name, double duration, String genres) {
        this(id, name, duration);
        this.genres = genres;
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

    public double getDuration() {
        return this.duration;
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

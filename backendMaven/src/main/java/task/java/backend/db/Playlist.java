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
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;
    @ManyToMany
    @JoinTable(name = "AP",
            joinColumns = @JoinColumn(name = "playlists_id"),
            inverseJoinColumns = @JoinColumn(name = "audios_id"))
    private List<Audio> audios;

    public Playlist(long id, String name, User owner) {
        this.Id = id;
        this.name = name;
        this.owner = owner;
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

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User newOwner) {
        this.owner = newOwner;
    }

}

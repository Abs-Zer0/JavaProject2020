/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Size(min = 1)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;
    @ManyToMany/*(fetch = FetchType.EAGER)*/
    @JoinTable(name = "AP",
            joinColumns = @JoinColumn(name = "playlists_id"),
            inverseJoinColumns = @JoinColumn(name = "audios_id"))
    private List<Audio> audios;

    public Playlist() {
    }

    public Playlist(long id, String name, User owner) {
        this.Id = id;
        this.name = name;
        this.owner = owner;
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
        final Playlist other = (Playlist) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
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

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User newOwner) {
        this.owner = newOwner;
    }

}

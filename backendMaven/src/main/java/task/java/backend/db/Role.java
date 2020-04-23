/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author illyasviel
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    @Column(name = "name")
    @Size(min = 6)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.Id = id;
    }

    public Role(Long id, String name) {
        this.Id = id;
        this.name = name;
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
        final Role other = (Role) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(Collection<? extends User> newUsers) {
        this.users.clear();
        this.users.addAll(newUsers);
    }

    public void addUser(User newUser) {
        this.users.add(newUser);
    }

    @Override
    public String getAuthority() {
        return getName();
    }

}

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
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author illyasviel
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;
    @Column(name = "login", nullable = false)
    @Size(min = 4)
    private String username;
    @Column(name = "passwd", nullable = false)
    @Size(min = 8)
    private String password;
    @Transient
    private String passwdConfirm;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UR",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User() {
    }

    public User(long id, String login, String passwd, String name) {
        this.Id = id;
        this.username = login;
        this.password = passwd;
        this.passwdConfirm = passwd;
        this.name = name;
        this.roles = new ArrayList<>();
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
        final User other = (User) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    

    public long getId() {
        return this.Id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPasswd) {
        this.password = newPasswd;
    }

    public String getPasswordConfirm() {
        return this.passwdConfirm;
    }

    public void setPasswordConfirm(String newPasswdConfirm) {
        this.passwdConfirm = newPasswdConfirm;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<? extends Role> newRoles) {
        this.roles.clear();
        this.roles.addAll(newRoles);
    }

    public void addRole(Role newRole) {
        this.roles.add(newRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

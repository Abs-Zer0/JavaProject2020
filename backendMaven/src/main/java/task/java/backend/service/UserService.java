/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import task.java.backend.db.Role;
import task.java.backend.db.RoleRepository;
import task.java.backend.db.User;
import task.java.backend.db.UserRepository;

/**
 *
 * @author illyasviel
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository usrepo;
    @Autowired
    RoleRepository rorepo;
    @Autowired
    BCryptPasswordEncoder passwdenc;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usrepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User \"" + username + "\" not found");

        }

        return user;
    }

    public boolean addUser(User user) {
        User existUser = usrepo.findByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }

        User correct = new User(0, user.getUsername(), passwdenc.encode(user.getPassword()), user.getName());
        correct.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        usrepo.save(correct);

        return true;
    }

}

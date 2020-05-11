/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import task.java.backend.db.*;

/**
 *
 * @author illyasviel
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository usrepo;

    @Autowired
    private RoleRepository rorepo;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("allUsers", usrepo.findAll());

        return "users";
    }

    @GetMapping("/users/{id}/change_role/admin")
    public String userChangeRoleAdmin(@PathVariable long id, RedirectAttributes redirect, Model model) {
        String userMsg = "У пользователя (" + Long.toString(id) + ") изменились роли";
        Optional<User> op = usrepo.findById(id);
        if (op.isEmpty()) {
            userMsg = "Пользователя (" + Long.toString(id) + ") не существует";
        } else {
            User user = op.get();
            if (user.getRoles().contains(rorepo.Admin())) {
                user.getRoles().remove(rorepo.Admin());
            } else {
                user.addRole(rorepo.Admin());
            }
            usrepo.saveAndFlush(user);
        }

        redirect.addFlashAttribute("userMsg", userMsg);
        redirect.addFlashAttribute("allUsers", usrepo.findAll());

        return "redirect:/users";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable long id, RedirectAttributes redirect, Model model) {
        String userMsg = "Пользователь (" + Long.toString(id) + ") успешно удален";
        Optional<User> op = usrepo.findById(id);
        if (op.isEmpty()) {
            userMsg = "Пользователя (" + Long.toString(id) + ") не существует";
        } else {
            usrepo.deleteById(id);
        }

        redirect.addFlashAttribute("userMsg", userMsg);
        redirect.addFlashAttribute("allUsers", usrepo.findAll());

        return "redirect:/users";
    }
}

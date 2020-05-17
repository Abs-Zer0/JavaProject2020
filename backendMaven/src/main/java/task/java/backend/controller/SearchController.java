/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task.java.backend.db.*;

/**
 *
 * @author illyasviel
 */
@Controller
public class SearchController {

    @Autowired
    private AudioRepository aurepo;
    @Autowired
    private UserRepository usrepo;
    @Autowired
    private RoleRepository rorepo;

    @GetMapping("/audios/search")
    public String audiosSearch(@RequestParam("keyword") String keyword, Model model, Principal principal) {
        Boolean canChangeAudio = false;
        if (principal != null) {
            User logined = (User) ((Authentication) principal).getPrincipal();
            canChangeAudio = logined.getAuthorities().contains(rorepo.Admin());
        }

        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : aurepo.findAll()) {
            boolean name = audio.getName().toLowerCase().contains(keyword.toLowerCase());
            boolean artist = audio.getArtists().toLowerCase().contains(keyword.toLowerCase());
            if (name || artist) {
                audios.add(audio);
            }
        }

        String audioMsg = "";
        if (audios.isEmpty()) {
            audioMsg = "Не найдено аудиозаписей с таким названием/исполнителем";
        }

        model.addAttribute("canChangeAudio", canChangeAudio);
        model.addAttribute("allAudios", audios);
        model.addAttribute("searchWord", keyword);
        model.addAttribute("audioMsg", audioMsg);

        return "index";
    }

    @GetMapping("/users/search")
    public String usersSearch(@RequestParam("keyword") String keyword, Model model) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : usrepo.findAll()) {
            boolean name = user.getName().toLowerCase().contains(keyword.toLowerCase());
            boolean username = user.getUsername().toLowerCase().contains(keyword.toLowerCase());
            if (name || username) {
                users.add(user);
            }
        }

        String userMsg = "";
        if (users.isEmpty()) {
            userMsg = "Не найдено пользователей с таким именем/логином";
        }

        model.addAttribute("allUsers", users);
        model.addAttribute("searchWord", keyword);
        model.addAttribute("userMsg", userMsg);

        return "users";
    }
}

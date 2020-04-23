/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.java.backend.db.*;

/**
 *
 * @author illyasviel
 */
@Controller
public class MainController {

    @Autowired
    private AudioRepository aurep;

    @Autowired
    private RoleRepository rorep;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        Boolean canChangeAudio = false;
        List<Audio> audios = aurep.findAll();

        if (principal != null) {
            User logined = (User) ((Authentication) principal).getPrincipal();
            canChangeAudio = logined.getAuthorities().contains(rorep.Admin());
        }

        model.addAttribute("canChangeAudio", canChangeAudio);
        model.addAttribute("allAudios", audios);

        return "index";
    }
}

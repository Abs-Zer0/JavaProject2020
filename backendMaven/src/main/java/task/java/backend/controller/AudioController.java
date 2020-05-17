/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import task.java.backend.db.*;
import task.java.backend.service.StorageService;

/**
 *
 * @author illyasviel
 */
@Controller
public class AudioController {

    @Autowired
    private StorageService ss;

    @Autowired
    private AudioRepository aurepo;

    @Autowired
    private RoleRepository rorepo;

    @GetMapping("/add_audio")
    public String addAudioForm(Model model) {
        return "add_audio";
    }

    @PostMapping("/add_audio")
    public String addAudio(@RequestParam("file") MultipartFile file, RedirectAttributes redirect, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("fileError", "Файл пустой");
            return "add_audio";
        }

        try {
            Audio audio = ss.save(file);
            redirect.addFlashAttribute("audioForm", audio);
            return "redirect:/audios/" + audio.getId();

        } catch (Exception e) {
            model.addAttribute("fileError", e.getMessage());
            return "add_audio";
        }
    }

    @GetMapping("/audios/{id}")
    public String getAudio(@PathVariable long id, Model model) {
        Optional<Audio> op = aurepo.findById(id);
        if (op.isEmpty()) {
            return "redirect:/";
        }

        Audio audio = op.get();
        model.addAttribute("audioForm", audio);

        return "audios";
    }

    @PostMapping("/audios/{id}")
    public String changeAudio(@PathVariable long id, @ModelAttribute("audioForm") @Valid Audio audioForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "audios";
        }

        Audio audio = aurepo.getOne(id);
        audio.setName(audioForm.getName());
        audio.setArtists(audioForm.getArtists());
        audio.setGenres(audioForm.getGenres());
        aurepo.saveAndFlush(audio);

        model.addAttribute("msg", "Change Saved.");
        model.addAttribute("audioForm", audio);

        return "audios";
    }

    @GetMapping(value = "/audios/{id}/delete")
    public String deleteAudio(@PathVariable long id, RedirectAttributes redirect, Model model, Principal principal) {
        String deleteAudioMsg = "Запись (" + Long.toString(id) + ") успешно удалена";
        Optional<Audio> op = aurepo.findById(id);
        if (op.isEmpty()) {
            deleteAudioMsg = "Записи (" + Long.toString(id) + ") не существует";
        } else {
            aurepo.deleteById(id);
        }

        Boolean canChangeAudio = false;
        if (principal != null) {
            User logined = (User) ((Authentication) principal).getPrincipal();
            canChangeAudio = logined.getAuthorities().contains(rorepo.Admin());
        }

        redirect.addFlashAttribute("audioMsg", deleteAudioMsg);
        redirect.addFlashAttribute("canChangeAudio", canChangeAudio);
        redirect.addFlashAttribute("allAudios", aurepo.findAll());

        return "redirect:/";
    }
}

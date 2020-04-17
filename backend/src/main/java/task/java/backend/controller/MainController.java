/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import task.java.backend.db.Audio;
import task.java.backend.db.AudioRepository;

/**
 *
 * @author illyasviel
 */
@RestController
public class MainController {

    @Autowired
    private AudioRepository aurep;

    /*@GetMapping("/")
    public ResponseEntity<List<Audio>> index() {
        List<Audio> audios = (List<Audio>) aurep.findAll();
        return new ResponseEntity<>(audios, HttpStatus.OK);
    }*/
    /*@GetMapping("/")
    public ResponseEntity<String> index() {
        List<Audio> audios = (List<Audio>) aurep.findAll();
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Main</title>";
        html += "</head>";
        html += "<body>";
        Integer size = audios.size();
        html += "<h1>" + size.toString() + "</h1>";
        html += "</body>";
        return new ResponseEntity<>(html, HttpStatus.OK);
    }*/
}

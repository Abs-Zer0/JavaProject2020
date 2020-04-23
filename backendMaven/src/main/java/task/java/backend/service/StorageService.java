/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.service;

import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import task.java.backend.db.Audio;

/**
 *
 * @author illyasviel
 */
public interface StorageService {

    void init();

    Audio save(MultipartFile file);

    Path save(InputStream in, String filename);

    File createTmp(MultipartFile file);

    Audio createFromMp3(Mp3File file, String filename);

    Path load(String filename);

    Resource loadAsResource(String filename);

}

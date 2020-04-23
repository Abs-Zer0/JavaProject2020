/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.service;

import com.mpatric.mp3agic.*;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import task.java.backend.config.StorageProperties;
import task.java.backend.db.*;
import task.java.backend.exception.*;

/**
 *
 * @author illyasviel
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    @Autowired
    private AudioRepository aurep;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public Audio save(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException("Cannot store file with relative path outside current directory "
                        + filename);
            }

            File tmp = createTmp(file);
            Mp3File mp3 = new Mp3File(tmp);
            Audio audio = createFromMp3(mp3, filename);

            if (aurep.anyMatches(audio.getName(), audio.getArtists()) > 0) {
                throw new IOException("This audio is already exist");
            }
            Path newPath = save(file.getInputStream(), audio.getArtists() + " - " + audio.getName());
            audio.setPath(newPath.toAbsolutePath().toString());

            aurep.saveAndFlush(audio);

            return audio;
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public File createTmp(MultipartFile file) {
        try {
            File tmp = File.createTempFile("tmp", file.getName());
            Files.copy(file.getInputStream(), tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return tmp;
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Path save(InputStream in, String filename) {
        try {
            Path path = this.rootLocation.resolve(filename);
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException ex) {
            throw new StorageException("Failed to store file " + filename, ex);
        }
    }

    @Override
    public Audio createFromMp3(Mp3File file, String filename) {
        String name = filename, artist = "Неизвестный", path = "", genre = "";
        if (file.hasId3v2Tag()) {
            ID3v2 tags = file.getId3v2Tag();
            if (tags.getTrack() != null) {
                if (tags.getTrack().length() > 0) {
                    name = tags.getTrack();
                }
            }
            if (tags.getArtist() != null) {
                if (tags.getArtist().length() > 0) {
                    artist = tags.getArtist();
                }
            }
            if (tags.getGenreDescription() != null) {
                if (tags.getGenreDescription().length() > 0) {
                    genre = tags.getGenreDescription();
                }
            }
        } else if (file.hasId3v1Tag()) {
            ID3v1 tags = file.getId3v1Tag();
            if (tags.getTrack() != null) {
                if (tags.getTrack().length() > 0) {
                    name = tags.getTrack();
                }
            }
            if (tags.getArtist() != null) {
                if (tags.getArtist().length() > 0) {
                    artist = tags.getArtist();
                }
            }
            if (tags.getGenreDescription() != null) {
                if (tags.getGenreDescription().length() > 0) {
                    genre = tags.getGenreDescription();
                }
            }
        }

        return new Audio(0, name, artist, new Time(file.getLengthInMilliseconds()), path, genre);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author illyasviel
 */
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}

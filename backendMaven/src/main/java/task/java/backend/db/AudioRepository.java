/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author illyasviel
 */
public interface AudioRepository extends JpaRepository<Audio, Long> {

    @Query(value = "select count(*) "
            + "from audios tab "
            + "where tab.name = :name and tab.artist = :artist",
            nativeQuery = true)
    long anyMatches(@Param("name") String name, @Param("artist") String artist);
}

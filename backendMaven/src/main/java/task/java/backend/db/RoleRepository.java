/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.db;

import org.springframework.data.jpa.repository.*;

/**
 *
 * @author illyasviel
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select * from roles where roles.id = 2", nativeQuery = true)
    Role Admin();

    @Query(value = "select * from roles where roles.id = 1", nativeQuery = true)
    Role User();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.java.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author illyasviel
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "uploads";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

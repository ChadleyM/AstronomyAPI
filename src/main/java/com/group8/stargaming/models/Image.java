package com.group8.stargaming.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue
    private  Long id;
    private String name;
    private String constellationUrl;

    Image(String name, String constellationUrl) {
        this.name = name;
        this.constellationUrl = constellationUrl;
    }
}

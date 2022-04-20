package com.group8.stargaming.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "constellation_images")
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;

    Image(){}
}

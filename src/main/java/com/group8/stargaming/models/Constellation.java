package com.group8.stargaming.models;

import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Constellation {
    private @Id @GeneratedValue Long id;
    private String name;

    // Maybe linked list?
    private String starList;

    Constellation() {}

    Constellation(String name, String starList) {
        this.name = name;
        // make a copy before assignment?
        this.starList = starList;
    }
}

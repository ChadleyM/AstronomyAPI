package com.group8.stargaming.models;

import lombok.Data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Constellation {
    private @Id @GeneratedValue Long id;
    private String name;
    private List<ConstellationEdge> edgeList;

    public Constellation() {}

    public Constellation(String name, List<ConstellationEdge> edgeList) {
        this.name = name;
        this.edgeList = edgeList;
    }
}

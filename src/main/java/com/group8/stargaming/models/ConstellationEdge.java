package com.group8.stargaming.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ConstellationEdge {
    private @Id Constellation constellation;
    private CosmicObject vertex1;
    private CosmicObject vertex2;

    public ConstellationEdge() {}

    public ConstellationEdge(Constellation constellation, CosmicObject vertex1, CosmicObject vertex2) {
        this.constellation = constellation;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
}

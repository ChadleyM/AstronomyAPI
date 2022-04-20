package com.group8.stargaming.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.*;

@Data
@Table(name = "Constellations")
@Entity
public class Constellation {
    private @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(mappedBy = "constellation")
    @JsonIgnore
    private List<ConstellationEdge> edgeList;

    public Constellation() {}

    public Constellation(String name, List<ConstellationEdge> edgeList) {
        this.name = name;
        this.edgeList = edgeList;
    }

    public boolean starListContainsEdges(List<Long> starList) {
        return edgeList.stream()
                .flatMap((ce) -> Stream.of(ce.getStar1(), ce.getStar2()))
                .map(StarDetails::getId)
                .allMatch(starList::contains);
    }
}

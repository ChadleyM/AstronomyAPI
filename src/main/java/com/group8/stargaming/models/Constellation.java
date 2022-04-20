package com.group8.stargaming.models;

import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.*;

@Data
@Table(name = "Constellation")
@Entity
public class Constellation {
    private @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(mappedBy = "constellation")
    private List<ConstellationEdge> edgeList;

    public Constellation() {}

    public Constellation(String name, List<ConstellationEdge> edgeList) {
        this.name = name;
        this.edgeList = edgeList;
    }

    public boolean starListContainsEdges(List<StarDetails> starList) {
        return edgeList.stream()
                .flatMap((ce) -> Stream.of(ce.getStar1(), ce.getStar2()))
                .allMatch(starList::contains);
    }
}

package com.group8.stargaming.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Table(name = "Constellation_Star")
@Entity
public class ConstellationEdge implements Serializable {
    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name="constellation_id")
    private Constellation constellation;

    @ManyToOne
    private StarDetails star1;

    @ManyToOne
    private StarDetails star2;

    public ConstellationEdge() {}

    public ConstellationEdge(Constellation constellation, StarDetails star1, StarDetails star2) {
        this.constellation = constellation;
        this.star1 = star1;
        this.star2 = star2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstellationEdge edge = (ConstellationEdge) o;
        return Objects.equals(star1, edge.star1) && Objects.equals(star2, edge.star2)
                || Objects.equals(star1, edge.star2) && Objects.equals(star2, edge.star1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(star1, star2);
    }
}

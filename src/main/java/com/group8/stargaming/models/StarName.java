package com.group8.stargaming.models;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Table(name = "Star_Names")
@Entity
public class StarName {
    private @Id Long id;
    private String name;

    public StarName() {}

    public StarName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Star[ name='%s']",
                name);
    }
}

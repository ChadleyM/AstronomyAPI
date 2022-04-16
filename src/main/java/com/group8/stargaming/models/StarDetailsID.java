package com.group8.stargaming.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Embeddable
public class StarDetailsID implements Serializable {
    private static final long serialVersionUID = -6414032688628222148L;
    private String date;
    private String star_name;

//    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StarDetailsID() {}

    public StarDetailsID(String date, String starName) {
        this.date = date;
        this.star_name = starName;
    }

    public String getDate() {
        return date;
    }

    public String getStar_name() {
        return star_name;
    }

    @Override
    public boolean equals(Object candidate) {
        if (this == candidate) return true;
        if (candidate == null || getClass() != candidate.getClass()) return false;
        StarDetailsID castedCandidate = (StarDetailsID) candidate;
        return date.equals(castedCandidate.date) &&
                star_name.equals(castedCandidate.star_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, star_name);
    }
}

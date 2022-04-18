package com.group8.stargaming.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "Star_Details")
@Entity
public class StarRewind {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long starDateLocID;
    private int starID;
    private int dateID;
    private double gha;
    private double declination;

    public StarRewind() {}

    public Long getStarDateLocID() {return starDateLocID;}
    public void setStarDateLocID(Long starDateLocID) {this.starDateLocID = starDateLocID;}

    public int getStarID() {return starID;}
    public void setStarID(int starID) {this.starID = starID;}

    public int getDateID() {return dateID;}
    public void setDateID(int dateID) {this.dateID = dateID;}

    public double getGhaRewind() {return gha;}
    public void setGhaRewind(int gha) {this.gha = gha;}

    public double getDeclinationRewind() {return declination;}
    public void setDeclinationRewind(int declination) {this.dateID = declination;}
}

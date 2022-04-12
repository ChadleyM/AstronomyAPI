package com.group8.stargaming.models;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Table(name = "Star_Details")
@Entity
public class StarDetails {

	@EmbeddedId
	private StarDetailsID starDetailsID;
	private Double gha;
	private Double declination;

	public StarDetails() {};

	public StarDetailsID getStarDetailsID() {
		return starDetailsID;
	}

	public Double getGha() {
		return gha;
	}

	public Double getDeclination() {
		return declination;
	}
}



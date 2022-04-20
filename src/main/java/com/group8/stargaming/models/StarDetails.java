package com.group8.stargaming.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "Star_Details")
@Entity
public class StarDetails {

	private @Id @GeneratedValue Long id;
	private double gha;
	@Column(name = "dec")
	private double declination;
	@Transient
	private Double altitude;
	@Transient
	private Double azimuth;
	@Transient
	private Double azimuthCorrection;
	@Transient
	private Double altitudeCorrection;

	private String date;
	@Column(name = "name")
	private String star_name;

	public StarDetails() {
		this.altitude = null;
		this.azimuth = null;
	}
}
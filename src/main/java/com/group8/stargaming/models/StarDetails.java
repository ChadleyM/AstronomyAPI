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
	private double gha;
	private double declination;
	@Transient
	private Double altitude;
	@Transient
	private Double azimuth;
	@Transient
	private Double azimuthCorrection;
	@Transient
	private Double altitudeCorrection;

	public StarDetails() {
		this.altitude = null;
		this.azimuth = null;
	};

	public Double getAzimuthCorrection() {
		return azimuthCorrection;
	}

	public void setAzimuthCorrection(Double azimuthCorrection) {
		this.azimuthCorrection = azimuthCorrection;
	}

	public Double getAltitudeCorrection() {
		return altitudeCorrection;
	}

	public void setAltitudeCorrection(Double altitudeCorrection) {
		this.altitudeCorrection = altitudeCorrection;
	}

	public StarDetailsID getStarDetailsID() {
		return starDetailsID;
	}

	public Double getGha() {
		return gha;
	}

	public Double getDeclination() {
		return declination;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}
}

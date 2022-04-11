package com.group8.stargaming.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class StarAlmanac {
	@Id
	private long id;
	private double greenwichHourAngle;
	private double declination;
	private String starName;
	private String date;
	
	public StarAlmanac(String starName, String date) {
		this.starName = starName;
		this.date = date;
		this.greenwichHourAngle = 0;
		this.declination = 0;
	}
	
	public double getGreenwichHourAngle() {
		return greenwichHourAngle;
	}

	public long getId() {
		return id;
	}

	public String getStarName() {
		return starName;
	}

	public String getDate() {
		return date;
	}

	public double getDeclination() {
		return declination;
	}
}

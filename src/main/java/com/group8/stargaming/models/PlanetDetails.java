package com.group8.stargaming.models;

public class PlanetDetails {
    private Double rightAscension;
    private Double declination;
    private Double altitude;
    private Double azimuth;
    private Double azimuthCorrection;
    private Double altitudeCorrection;

    public PlanetDetails(Double rightAscension, Double declination, Double altitude, Double azimuth) {
        this.rightAscension = rightAscension;
        this.declination = declination;
        this.altitude = altitude;
        this.azimuth = azimuth;
    }

    public PlanetDetails() {
        this.rightAscension = null;
        this.declination = null;
        this.altitude = null;
        this.azimuth = null;
    }

    public double getRightAscension() {
        return rightAscension;
    }

    public void getRightAscension(double rightAscension) {
        this.rightAscension = rightAscension;
    }

    public double getDeclination() {
        return declination;
    }

    public void setDeclination(double declination) {
        this.declination = declination;
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
}

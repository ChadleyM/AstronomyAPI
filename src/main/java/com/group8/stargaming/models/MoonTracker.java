package com.group8.stargaming.models;


public class MoonTracker {

    private Double moon_phase_val;
    private  String moon_phase_name;

    MoonTracker(){
        this.moon_phase_val=null;
        this.moon_phase_name=null;
    }

    public  MoonTracker(Double moonPhaseVal, String moonPhaseName){
        this.moon_phase_val=moonPhaseVal;
        this.moon_phase_name= moonPhaseName;
    }

    public String getMoonPhaseName(){
        return moon_phase_name;
    }

    public Double getMoonPhase(){
        return moon_phase_val;
    }

    public void setMoonPhaseVal(Double moonVal) {
        this.moon_phase_val=moonVal;
    }

    public void setMoonPhaseName(String moonName){
        this.moon_phase_name=moonName;
    }


}

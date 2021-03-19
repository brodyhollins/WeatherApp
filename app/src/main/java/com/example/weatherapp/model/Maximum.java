package com.example.weatherapp.model;

public class Maximum {
    private float Value;

    private String Unit;

    private float UnitType;

    public void setValue(int Value){
        this.Value = Value;
    }
    public float getValue(){
        return this.Value;
    }
    public void setUnit(String Unit){
        this.Unit = Unit;
    }
    public String getUnit(){
        return this.Unit;
    }
    public void setUnitType(int UnitType){
        this.UnitType = UnitType;
    }
    public float getUnitType(){
        return this.UnitType;
    }
}

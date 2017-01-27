package com.example.marcneisser.quest;

import android.content.SharedPreferences;

/**
 * Created by marcneisser on 5/31/16.
 */
public class Multiplier {

    private String name;

    private double multiple;
    private double multiplierCurrent;
    private double multiplierMin;
    private double multiplierMax;

    private String multiNameKey=name+" Name";
    private String multiMultipleKey=name+" Multiple";
    private String multiMultiplierCurrentKey=name+" MultiplierCurrent";
    private String multiMultiplierMinKey=name+" MultiplierMin";
    private String multiMultiplierMaxKey=name+" MultiplierMax";

    public Multiplier(){}

    public Multiplier(String name, double multiple, double multiplierCurrent,
                      double multiplierMin, double multiplierMax){

        this.name=name;

        this.multiple=multiple;
        this.multiplierCurrent=multiplierCurrent;
        this.multiplierMin=multiplierMin;
        this.multiplierMax=multiplierMax;
    }
//used when loading existing user data. use in conjunction with get multiplier.
    public Multiplier(String name){
        this.name=name;
    }

    public void multiplierChange(boolean didComplete){

        if(didComplete==true){
            if (multiplierCurrent!=multiplierMax){
                multiplierCurrent=multiplierCurrent+multiple;
            }else{
                multiplierCurrent=multiplierMin;
            }
        }
    }

    public void storeMultiplier(SharedPreferences.Editor sharedEditor){

        sharedEditor.putString(multiNameKey, name);
        sharedEditor.putLong(multiMultipleKey, Double.doubleToRawLongBits(multiple));
        sharedEditor.putLong(multiMultiplierCurrentKey, Double.doubleToRawLongBits(multiplierCurrent));
        sharedEditor.putLong(multiMultiplierMinKey, Double.doubleToRawLongBits(multiplierMin));
        sharedEditor.putLong(multiMultiplierMaxKey, Double.doubleToRawLongBits(multiplierMax));

    }

    public void getMultiplier(SharedPreferences sharedPreferences){

        this.multiple=Double.longBitsToDouble(sharedPreferences.getLong(multiMultipleKey, -1));
        this.multiplierCurrent=Double.longBitsToDouble(sharedPreferences.getLong(multiMultiplierCurrentKey, -1));
        this.multiplierMin=Double.longBitsToDouble(sharedPreferences.getLong(multiMultiplierMinKey, -1));
        this.multiplierMax=Double.longBitsToDouble(sharedPreferences.getLong(multiMultiplierMaxKey, -1));
    }

    public void setMultiple(double multiple) {
        this.multiple = multiple;
    }

    public void setMultiplierCurrent(double multiplierCurrent) {
        this.multiplierCurrent = multiplierCurrent;
    }

    public void setMultiplierMax(double multiplierMax) {
        this.multiplierMax = multiplierMax;
    }

    public void setMultiplierMin(double multiplierMin) {
        this.multiplierMin = multiplierMin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMultiple() {
        return multiple;
    }

    public double getMultiplierCurrent() {
        return multiplierCurrent;
    }

    public double getMultiplierMax() {
        return multiplierMax;
    }

    public double getMultiplierMin() {
        return multiplierMin;
    }

    public String getName() {
        return name;
    }

}

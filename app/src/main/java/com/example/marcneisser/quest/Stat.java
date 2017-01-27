package com.example.marcneisser.quest;

import android.content.SharedPreferences;

/**
 * Created by marcneisser on 5/31/16.
 */
public class Stat {

    private String statName;

    private int statLevel;
    private int statExpCurrent;
    private int statExpNeed;

    private String statNameKey=statName+" Name";
    private String statLevelKey=statName+" Level";
    private String statExpCurrentKey=statName+" ExpCurrent";
    private String statExpNeedKey=statName+" ExpNeed";

    public Stat(){}

    public Stat(String statName, int statLevel, int statExpCurrent, int statExpNeed){

        this.statName=statName;

        this.statLevel=statLevel;
        this.statExpCurrent=statExpCurrent;
        this.statExpNeed=statExpNeed;
    }

    public Stat(String statName){
        this.statName=statName;
    }

    public void levelUp(){
        if (statExpCurrent==statExpNeed){
            statLevel=statLevel+1;
            statExpCurrent=statExpCurrent-statExpNeed;
            statExpNeed=(statExpNeed/2)+statExpNeed; //need to figure out a better exp adjustment algorithm
            levelUp();
        }
    }
    //used for quickly storing stat in a custom shared preference file
    /*FUCKING COOL. if it works this will make saving stat and skill info
     to shared preferences insanely fast!!! still need to make sure it works but
     so far no errors are showing...
     */
    public void storeStat(SharedPreferences.Editor sharedEditor){

        sharedEditor.putString(statNameKey, statName);
        sharedEditor.putInt(statLevelKey, statLevel);
        sharedEditor.putInt(statExpCurrentKey, statExpCurrent);
        sharedEditor.putInt(statExpNeedKey, statExpNeed);
        //no apply needed here. the object calling the stat object will choose when to apply.
    }

    public void getStat(SharedPreferences sharedPreferences){

        this.statLevel=sharedPreferences.getInt(statLevelKey, -1);
        this.statExpCurrent=sharedPreferences.getInt(statExpCurrentKey, -1);
        this.statExpNeed=sharedPreferences.getInt(statExpNeedKey, -1);
    }

    public void setStatExpCurrent(int statExpCurrent) {
        this.statExpCurrent = statExpCurrent;
    }

    public void setStatExpNeed(int statExpNeed) {
        this.statExpNeed = statExpNeed;
    }

    public void setStatLevel(int statLevel) {
        this.statLevel = statLevel;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public int getStatExpCurrent() {
        return statExpCurrent;
    }

    public int getStatExpNeed() {
        return statExpNeed;
    }

    public int getStatLevel() {
        return statLevel;
    }

    public String getStatName() {
        return statName;
    }

}

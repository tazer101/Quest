package com.example.marcneisser.quest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by marcneisser on 5/27/16.
 */
public class UserStats {

    private Stat level;

    private Stat strength;
    private Stat dexterity;
    private Stat wisdom;
    private Stat intelligence;
    private Stat charisma;

    private String lvlName="Level";
    private String strName="Strength";
    private String dexName="Dexterity";
    private String wisName="Wisdom";
    private String intName="Intelligence";
    private String charName="Charisma";

    private String constName="Constitution";

    private Multiplier constitution;//stat multiplier, not an actual level. goes by.5x

    Context context;

    private static final int PREFERENCE_MODE_PRIVATE=0;
    private static final String USER_STAT_FILE="userStatFile";
    private static final String USER_MULTIPLIER_FILE="userMultiplierFile";

    private SharedPreferences preferenceSettingsUserStats;
    private SharedPreferences.Editor preferenceEditorUserStats;

    private SharedPreferences preferenceSettingsMultiplier;
    private SharedPreferences.Editor preferenceEditorMultiplier;

    public static ArrayList<Stat> statsList = new ArrayList<Stat>();

    public UserStats(){}

    //use if new user is being made
    public UserStats(Activity activity){

        this.context=activity;
        this.preferenceSettingsUserStats=context.getSharedPreferences(USER_STAT_FILE, PREFERENCE_MODE_PRIVATE);
        this.preferenceEditorUserStats=preferenceSettingsUserStats.edit();

        this.preferenceSettingsMultiplier=context.getSharedPreferences(USER_MULTIPLIER_FILE, PREFERENCE_MODE_PRIVATE);
        this.preferenceEditorMultiplier=preferenceSettingsMultiplier.edit();

        this.level= new Stat(lvlName, 1, 0, 1000);

        this.strength=new Stat(strName, 1, 0, 100);
        this.dexterity=new Stat(dexName, 1, 0, 100);
        this.wisdom=new Stat(wisName, 1, 0, 100);
        this.intelligence=new Stat(intName, 1, 0, 100);
        this.charisma=new Stat(charName, 1, 0, 100);

        this.constitution=new Multiplier(constName, 0.5, 1, 1, 10);

        level.storeStat(preferenceEditorUserStats);
        strength.storeStat(preferenceEditorUserStats);
        dexterity.storeStat(preferenceEditorUserStats);
        wisdom.storeStat(preferenceEditorUserStats);
        intelligence.storeStat(preferenceEditorUserStats);
        charisma.storeStat(preferenceEditorUserStats);
        preferenceEditorUserStats.apply();

        constitution.storeMultiplier(preferenceEditorMultiplier);
        preferenceEditorMultiplier.apply();

        makeStatsList();
    }

    //use this for loading in existing user stat data
    public UserStats(Context context){

        this.context=context;
        this.preferenceSettingsUserStats=context.getSharedPreferences(USER_STAT_FILE, PREFERENCE_MODE_PRIVATE);
        this.preferenceEditorUserStats=preferenceSettingsUserStats.edit();

        this.preferenceSettingsMultiplier=context.getSharedPreferences(USER_MULTIPLIER_FILE, PREFERENCE_MODE_PRIVATE);
        this.preferenceEditorMultiplier=preferenceSettingsMultiplier.edit();

        this.level=new Stat(lvlName);
        this.strength=new Stat(strName);
        this.dexterity=new Stat(dexName);
        this.wisdom=new Stat(wisName);
        this.intelligence=new Stat(intName);
        this.charisma=new Stat(charName);

        this.constitution=new Multiplier(constName);

        level.getStat(preferenceSettingsUserStats);
        strength.getStat(preferenceSettingsUserStats);
        dexterity.getStat(preferenceSettingsUserStats);
        wisdom.getStat(preferenceSettingsUserStats);
        intelligence.getStat(preferenceSettingsUserStats);
        charisma.getStat(preferenceSettingsUserStats);

        constitution.getMultiplier(preferenceSettingsMultiplier);

        makeStatsList();
    }

    /*
    Still need to make functions enabling bulk level up checking
    but everything is looking pretty solid so far!
    Adding the capability for stats and multipliers to automatically grab
    data from custom shared preferences files was pure genius on my part!
     */

    public void checkLevelUp(){
        level.levelUp();
        strength.levelUp();
        dexterity.levelUp();
        wisdom.levelUp();
        intelligence.levelUp();
        charisma.levelUp();
        //when using this same sort of thing for skills you need to
    }

    private void makeStatsList(){
        statsList.add(this.level);
        statsList.add(this.strength);
        statsList.add(this.dexterity);
        statsList.add(this.wisdom);
        statsList.add(this.intelligence);
        statsList.add(this.charisma);
    }

    public ArrayList<Stat> getStatsList() {
        return statsList;
    }
}

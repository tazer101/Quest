package com.example.marcneisser.quest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by marcneisser on 5/27/16.
 */
public class UserInfo {

    private long id; //user id in relation to database

    private String firstName;
    private String lastName;

    private Date birthDate;

    public UserInfo(){}

    public UserInfo(String firstName, String lastName, Date birthDate){

    }

}

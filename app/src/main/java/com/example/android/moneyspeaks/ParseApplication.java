package com.example.android.moneyspeaks;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;
/**
 * Created by Sunny on 7/25/2015.
 */
public class ParseApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
	String AppId;
	String ClientKey;
        // Add your initialization code here
        Parse.initialize(this, AppID, ClientKey); //note I've  removed AppId and ClientKey for security purposes

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }


}

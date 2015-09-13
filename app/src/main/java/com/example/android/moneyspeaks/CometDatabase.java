package com.example.android.moneyspeaks;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sunny on 7/8/2015.
 */
public class CometDatabase extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "cometdatabase";
    private static final String TABLE_NAME= "CommitTable";
    private static final String UID = "_id";
    private static final String GOAL_NAME = "Goal Name";
    private static final String WAGER = "Wager";
    private static final String TIME_REQ_2 = "Time Period";
    private static final String GENERATED_CODE= "Generated Code";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = ("CREATE TABLE " +TABLE_NAME+ " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+GOAL_NAME+" VARCHAR(255), "+WAGER+" VARCHAR (255), "+TIME_REQ_2+" VARCHAR(255), "+GENERATED_CODE+" VARCHAR (255));");

    public CometDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try{
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}

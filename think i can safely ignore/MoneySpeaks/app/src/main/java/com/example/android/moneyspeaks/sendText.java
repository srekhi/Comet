package com.example.android.moneyspeaks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;

/**
 * Created by Sunny on 6/11/2015.
 */

public class sendText{
    String msg;
    String STRcode;
    CodeGenerator codeGenerator = new CodeGenerator();


   int code = codeGenerator.gen();

    public static final String SP_NAME = "userDetails";

    public String getNumber(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("refNumber", "");
    }
    public void sendMsg(Context context, String refNumber, String STRcode)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(refNumber, null, STRcode, null, null );
        //add to database here
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor dbeditor = sharedPreferences.edit();
        dbeditor.putString("code", STRcode); //this adds the string 'code' to the sharedPreferences file

    }

}












/*


    public SharedPreferences sendText(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return userLocalDatabase;
    }

    //IGNORE THE THOUGHTS:
    //send an intro message about how a person has selected this person as their referee.
    //need to add a name field and then send them an intro text saying hi this person has made you a referee for their goals.
    //every time they make a commitment, a code is gonna be sent to you.
    //FINISHED IGNORING

    public void sendcode()

    {

        SharedPreferences userLocalDatabase = getSharedPreferences("refNumber",0);
        //this method needs to be run everytime a commitment is renewed or made



    }


}
*/
package com.example.android.moneyspeaks;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

/**
 * Created by Sunny on 8/2/2015.
 */
public class Alarm extends BroadcastReceiver {
    AlarmManager am;
    SetTheStakes setStakes = new SetTheStakes();
    String cost;
    String cust_id;
    //AlarmManagers can be used along with Broadcast Receivers to start a service
    Checkout_Activity checkout = new Checkout_Activity();
    @Override
    public void onReceive(Context context, Intent intent) {   //invokes this receiver on a scheduled time
        //Call Parse code to charge customer

        cost = String.valueOf(setStakes.returnWager());
        cust_id = checkout.returnCustomerId();
        Toast.makeText(context, "HII", Toast.LENGTH_SHORT).show();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("amount", cost);
        params.put("customerID", cust_id);
        ParseCloud.callFunctionInBackground("chargeCustomer", params, new FunctionCallback<Object>() {
            @Override
            public void done(Object o, ParseException e) {
                Log.d("charge", String.valueOf(o));
                //nothing for now. just need to charge the bitch.
            }
        });


    }
    public void setAlarm(Context context)
    {
        //am.set
    }


}

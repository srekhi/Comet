package com.example.android.moneyspeaks;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class WhyRef extends DialogFragment
{
    @Nullable
    //@Override
    //Button gotIt;
    WhyRef whyref;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(true);
        View v =  inflater.inflate(R.layout.activity_why_ref, null);
       // gotIt= (Button)v.findViewById(R.id.gotIt);

        return v;
    }

   /* @Override
    public void onClick(View v) {
        if (v.getId()==R.id.gotIt)
        {
            whyref.dismiss();
        }
    }
    */
}
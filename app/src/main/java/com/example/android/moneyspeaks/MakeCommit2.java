package com.example.android.moneyspeaks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MakeCommit2 extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Spinner spinner2;
    EditText numberInput;
    EditText goalName;
    int timeReq;
    String timeClassification1; //from spinner, hours,minutes,seconds,etc. NEED TO SET THIS ON THE SPINNER
    String timeClassification2; //same as above
    String classification;
    int classification1;
    int classification2;
    String refNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_commit2);
        goalName = (EditText) findViewById(R.id.goalname);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        numberInput = (EditText) findViewById(R.id.number);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.MakeCommitOptions, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        Intent i2 = getIntent();
        Bundle bundle = i2.getExtras();
        //refNumber = bundle.getString("number");






    }
    @Override
    public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
        classification1= spinner.getSelectedItemPosition();

        classification2=spinner2.getSelectedItemPosition();

        // TextView classification = (TextView) view;
      /*  if (view.getId() == spinner.getId())
        {
            classification1= spinner.getSelectedItemPosition();
        }
        if (view.getId() == spinner2.getId())
        {
            classification2= spinner2.getSelectedItemPosition();
        }
        */


        timeClassification1 = spinner.getSelectedItem().toString();
        timeClassification2 = spinner2.getSelectedItem().toString();
    }

        public void setStakes(View v)
        {
            Log.d("Classification1", String.valueOf(classification1)); //comes out as zero
            Log.d("Classification2", String.valueOf(classification2)); //0
            if (goalName.getText().toString().isEmpty() || numberInput.getText().toString().isEmpty() || timeClassification1.isEmpty() || timeClassification2.isEmpty())
            {
                Toast toast = Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT);
                toast.show();
            }
            if (classification1<classification2 || classification1==classification2)
            {

                Toast toast = Toast.makeText(this, "Please make sure your time periods make sense", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                //I'm passing these to SetTheStakes,which I then pass to Commit_Summary
                Intent i1 = new Intent(this, SetTheStakes.class);

                i1.putExtra("GoalName", goalName.getText().toString());
                i1.putExtra("timeReq", Integer.parseInt(numberInput.getText().toString()));
                //the next putExtra is referring to the first value in the phrase 3 hours every day
                i1.putExtra("TimeClassification1", timeClassification1);
                //the next putExtra is referring to thes second string in the phrse 3 hours every week
                i1.putExtra("TimeClassification2", timeClassification2);
                //unsure about keeping finance related penalty--so not gonna implemenet functionality
                //i1.putExtra("RefereeNumber", refNumber);

                startActivity(i1);


            }

        }



        @Override
        public void onNothingSelected (AdapterView < ? > parent)
        {


        }




    //FAKE METHOD
    public void alarmTest(View v)
    {

        Intent fakeIntent = new Intent(MakeCommit2.this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MakeCommit2.this, 0, fakeIntent, 0);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 10, 100, pendingIntent);

    }


}






package com.example.android.moneyspeaks;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MakeCommit2 extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Spinner spinner2;
    EditText numberInput;
    EditText goalName;
    int timeReq;
    String timeClassification1; //from spinner, hours,minutes,seconds,etc.
    String timeClassification2; //same as above
    String classification;


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

        Intent i = new Intent(this, Commit_Summary.class);
        i.putExtra("GoalName", goalName.getText());
        i.putExtra("timeReq", numberInput.getText());
        //the next putExtra is referring to the first value in the phrase 3 hours every day
        i.putExtra("TimeClassification1", timeClassification1);
        //the next putExtra is referring to thes second string in the phrse 3 hours every week
        i.putExtra("TimeClassification2", timeClassification2);
    }

        public void sms()
        {

        }

        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
            TextView classification = (TextView) view;
            if (view.getId() == spinner.getId()) {
                timeClassification1 = (String) classification.getText();
            }
            if (view.getId() == spinner2.getId()) {
                timeClassification2 = (String) classification.getText();
            }
        }

        @Override
        public void onNothingSelected (AdapterView < ? > parent) {

        }
}






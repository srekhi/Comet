package com.example.android.moneyspeaks;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class SetTheStakes extends ActionBarActivity {
    TextView Cost;
    int stakes=10;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_the_stakes);
        Cost = (TextView) findViewById(R.id.cost);


    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_the_stakes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
    public void add(View view) {
        stakes = Integer.parseInt(Cost.getText().toString());
        if (stakes >= 5)
        {
            stakes-=5;
            String convertStakestoText = Integer.toString(stakes);
            Cost.setText(convertStakestoText);
        }
        else
        {

        }

    }
    public void subtract (View v)
    {
        stakes=Integer.parseInt(Cost.getText().toString());
        if (stakes>50)
        {

        }
        else
        {
            stakes+=5;
            String convertStakestoText = Integer.toString(stakes);
            Cost.setText(convertStakestoText);
        }

    }
    public void passToSummary(View v)
    {
        extras = getIntent().getExtras();

        Intent i = new Intent (this, Commit_Summary.class);

        //getExtra from bundle for Goal Name
        String goalNameSummary = extras.getString("GoalName");
        i.putExtra("GoalName", goalNameSummary);

        //timeReq
        Integer timeReq = extras.getInt("timeReq");
        i.putExtra("timeReq", timeReq);


        //TimeClassification1
        String timeClassification1 = extras.getString("TimeClassification1");
        i.putExtra("TimeClassification1", timeClassification1);

        //TimeClassification2
        String timeClassification2 = extras.getString("TimeClassification2");
        i.putExtra("TimeClassification2", timeClassification2);
        //done getting inputs from MakeCommit2, now to pass to CommitSummary

        //String refereeNumber = extras.getString("RefereeNumber");
        //i.putExtra("REFNUMBA", refereeNumber);


        i.putExtra("wager", stakes);
        startActivity(i);
    }
    public Integer returnWager ()
    {
        return stakes;
    }

}

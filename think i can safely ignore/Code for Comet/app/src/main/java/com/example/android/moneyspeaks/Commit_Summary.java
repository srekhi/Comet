package com.example.android.moneyspeaks;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class Commit_Summary extends ActionBarActivity {
    TextView goalNameSummary;
    TextView goalTimeSummary;
    TextView classify1Summary;
    TextView classify2Summary;
    EditText wager;
    //still have to get cost intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit__summary);
        Bundle bundle = getIntent().getExtras();

        //name of the goal
        goalNameSummary = (TextView) findViewById(R.id.goalID);
        goalNameSummary.setText((String) bundle.get("GoalName"));

        //Number time of goal
        goalTimeSummary = (TextView) findViewById(R.id.timeReqSummary);
        goalTimeSummary.setText((Integer) bundle.get("timeReq"));

        //classify1
        classify1Summary = (TextView) findViewById(R.id.classify1Summary);
        classify1Summary.setText((String)bundle.get("TimeClassification1"));

        //classify2
        classify2Summary=(TextView)findViewById(R.id.classify2Summary);
        classify2Summary.setText((String)bundle.get("TimeClassification2"));

        //wager amount
        wager = (EditText) findViewById(R.id.wager);
        wager.setText((Integer)bundle.get("wager"));







        /*
        goalReq = (int) bundle.get("timeReq");

        classify1Summary = (String) bundle.get("TimeClassification1");
        classify2Summary= (String) bundle.get("TimeClassification2");
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commit__summary, menu);
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
}

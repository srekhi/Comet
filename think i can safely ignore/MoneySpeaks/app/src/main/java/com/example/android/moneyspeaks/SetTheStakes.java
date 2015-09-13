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
    int stakes=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_the_stakes);
        Intent i = new Intent(this, Commit_Summary.class);
        i.putExtra("wager", stakes);



    }

    @Override
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
    public void subtract(View view) {
        stakes = Integer.parseInt(Cost.getText().toString());
        if (stakes > 5)
        {
            stakes-=5;
        }
        else
        {

        }

    }
    public void add (View v)
    {
        stakes=Integer.parseInt(Cost.getText().toString());
        if (stakes>50)
        {

        }
        else
        {
            stakes+=5;
        }

    }

}

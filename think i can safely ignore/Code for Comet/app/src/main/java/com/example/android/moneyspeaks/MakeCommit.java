package com.example.android.moneyspeaks;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MakeCommit extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_commit);

        TextView commit1 = (TextView) findViewById(R.id.commit1);
        TextView commit2 = (TextView) findViewById(R.id.commit2);

        String commitStyle1 = "I'm going to <font color = 'red'> study: 3 hours </font> every <font color = 'red'> day </font>";
        commit1.setText(Html.fromHtml(commitStyle1));
        String commitStyle2 = "I'm going to <font color = 'red'> exercise: 4 days </font> every <font color = 'red'> week </font>";
        commit2.setText(Html.fromHtml(commitStyle2));

        TextView makeyourown = (TextView) findViewById(R.id.Makeyourownbtn);
        makeyourown.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_make_commit, menu);
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
    public void formCommit() {
        //if the user presses either check box, then their form to make their commitment gets autopopulated
        //do checkmark1 first

    }
    public void passto2(){
        Intent i = new Intent(this, MakeCommit2.class );
        startActivity(i);
    }
}

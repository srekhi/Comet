package com.example.android.moneyspeaks;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class RefPage1 extends ActionBarActivity  {
    //INTENT REF NUMBER TO JAVA CLASS sendText()
    EditText cellph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ref_page1);

        cellph = (EditText) findViewById(R.id.cellNumber);

        Intent i = new Intent(this, sendText.class);
        i.putExtra("number", cellph.getText().toString());
    }


    public void refswitch(){
        FragmentManager manager = getFragmentManager();
        WhyRef whyRef = new WhyRef();
        whyRef.show(manager,"Whyref");
    }
    public void maketheCommit()
    {
        Intent i = new Intent(this, MakeCommit.class);
        startActivity(i);
    }

}

package com.example.android.moneyspeaks;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RefPage1 extends ActionBarActivity implements View.OnClickListener {
    //INTENT REF NUMBER TO JAVA CLASS sendText()
    EditText cellph;
    EditText refName;
    SharedPreferences userLocalDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ref_page1);

        cellph = (EditText) findViewById(R.id.cellNumber);
        refName=(EditText) findViewById(R.id.refName);





    }



    public void refswitch(View v){
        FragmentManager manager = getFragmentManager();
        WhyRef whyRef = new WhyRef();
        whyRef.show(manager, "Whyref");
        whyRef.isCancelable();

    }
    public void makeTheCommit(View v)
    {
        String CellPhString = cellph.getText().toString();
        String refNameString = refName.getText().toString();
        if (CellPhString.isEmpty() || refNameString.isEmpty())
        {
            Toast toast =Toast.makeText(this, "Please fill in both the name and cell number of the referee", Toast.LENGTH_SHORT);
            toast.show();

        }
        else
        {
            Log.d("RefNumber", CellPhString);
            Intent i1 = new Intent(this, MakeCommit2.class);
            i1.putExtra("number", CellPhString);

            SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("refereeNumber", CellPhString);
            editor.commit();

            Intent i = new Intent(this, MakeCommit.class);
            startActivity(i);

        }

    }


    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        WhyRef whyRef = new WhyRef();
        if (v.getId() == R.id.whyref)
        {

            whyRef.show(manager, "Whyref");
            whyRef.isCancelable();

        }

    }
}

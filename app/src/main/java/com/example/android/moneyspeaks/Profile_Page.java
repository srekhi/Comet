package com.example.android.moneyspeaks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class Profile_Page extends  ActionBarActivity implements CommitmentDataAdapter.ClickListener {
    private RecyclerView recyclerView;
    private CommitmentDataAdapter adapter;
    private Context context;


    final List<CommitmentData> data = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__page);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Commitment");
        query.whereEqualTo("author", ParseUser.getCurrentUser());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView); //find the recycler View on this page
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override

            public void done(List<ParseObject> list, ParseException e)
            { //returns actual Parse Object
                if (e==null) {
                    for (ParseObject getData : list)
                    {
                        CommitmentData current = new CommitmentData();
                        current.goalName = getData.getString("goalName");
                        current.wager = getData.getString("wager");
                        current.goalTime = getData.getString("GoalTime");
                        current.classify1 = getData.getString("timeClassification1");
                        current.classify2 = getData.getString("timeClassification2");
                        current.commitment = current.goalName + " for " + current.goalTime+" "+ current.classify1 + " per " + current.classify2;
                        //checked Log.d, query is working fine. ArrayList should have an object of all the commitments for the use
                        data.add(current);
                        //data correct contains list of commitment bojects
                    }
                }
                    else
                    {
                        //something went wrong
                    }

                adapter = new CommitmentDataAdapter(getApplicationContext(), data);
                recyclerView.setAdapter(adapter); //set recyclerView to this adapter



            }
        });

       // final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
       // TextView txtuser = (TextView) findViewById(R.id.UsernameTV);
        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();
        // Convert currentUser into String
        String struser = currentUser.getUsername();

       // txtuser.setText("You are logged in as " + struser);

        // Locate Button
       // Button logoutBtn = (Button) findViewById(R.id.logOutBtn);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    /*adapter = new CommitmentDataAdapter(getApplicationContext(), data);
        recyclerView.setAdapter(adapter); //set recyclerView to this adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));//setting the layoutManager to the recyclerView
        */
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //must define layout manager for RecycleView item positioning



   /* public static List<CommitmentData> getData()
    {
        //final might not be a good idea

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Commitment");
        query.whereEqualTo("author", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override

            public void done(List<ParseObject> list, ParseException e)
            { //returns actual Parse Object


                    for (ParseObject getData : list)
                    {
                        CommitmentData current = new CommitmentData();
                        current.goalName = getData.getString("goalName");
                        current.wager = getData.getString("wager");
                        current.goalTime = getData.getString("GoalTime");
                        current.classify1 = getData.getString("timeClassification1");
                        current.classify2 = getData.getString("timeClassification2");
                        current.commitment = current.goalName + " for " + current.goalTime + current.classify1 + " per " + current.classify2;
                        //checked Log.d, query is working fine. ArrayList should have an object of all the commitments for the use
                        data.add(current);
                        //data correct contains list of commitment bojects

                    }
                }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView); //find the recycler View on this page
        adapter = new CommitmentDataAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(adapter); //set recyclerView to this adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        return data;
    }
    */
     //   return data;
  //  }


    public void createCommit(View v)
    {
        Intent i = new Intent(this, MakeCommit2.class);
        startActivity(i);
    }
    public void logOut(View view)
    {
        // Logout current user
        ParseUser.logOut();
        finish();
    }

    @Override
    public void itemClicked(View view, int position) {
        if (view.getId() == R.id.submitTheCode)
        {

        }
    }



}

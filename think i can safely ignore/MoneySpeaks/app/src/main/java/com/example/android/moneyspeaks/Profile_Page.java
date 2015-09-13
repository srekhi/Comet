package com.example.android.moneyspeaks;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Profile_Page extends ActionBarActivity {
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__page);
    }

    private void displayUserDetails()
    {
        User user = userLocalStore.getLoggedInUser();
        //then set Textview to .setText(user.username) look at Tonikami tutorial
    }
}

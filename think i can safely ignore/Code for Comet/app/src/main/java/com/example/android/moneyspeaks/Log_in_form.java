package com.example.android.moneyspeaks;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Log_in_form extends ActionBarActivity implements View.OnClickListener {
    EditText ETusername;
    EditText ETpassword;
    UserLocalStore userLocalStore;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_form);

        ETusername = (EditText) findViewById(R.id.usernameET);
        ETpassword = (EditText) findViewById(R.id.passwordET);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        userLocalStore = new UserLocalStore(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                User user = new User (null,null,null);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                break;


        }
    }
}

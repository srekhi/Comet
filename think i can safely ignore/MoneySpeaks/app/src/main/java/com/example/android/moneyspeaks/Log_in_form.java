package com.example.android.moneyspeaks;

import android.content.Context;
import android.content.Intent;
import android.opengl.ETC1;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Log_in_form extends ActionBarActivity implements View.OnClickListener {
    EditText ETusername;
    EditText ETpassword;
    UserLocalStore userLocalStore;
    Button loginBtn;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_form);
        c= this;
        ETusername = (EditText) findViewById(R.id.usernameET);
        ETpassword = (EditText) findViewById(R.id.passwordET);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        userLocalStore = new UserLocalStore(c);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbutton:
                String username = ETusername.getText().toString();
                String password = ETpassword.getText().toString();

                User user = new User (username,password);
                authenticate(user);

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                break;

            /*case R.id.logout
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            */
        }
    }
    private void authenticate(User user)
    {
        ServerRequests serverRequests= new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {

            @Override
            public void done(User returnedUser) {
                if (returnedUser == null)
                {
                    showErrorMessage();
                }
                else
                {
                    logUserIn(returnedUser);
                }

            }
        });
    }
    private void showErrorMessage()
    {
        Toast.makeText(this, "Please double-check your login or password and try again", Toast.LENGTH_LONG);
    }
    private void logUserIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser); //unclear why we have to store the data here....need to have a better underestanding of whats going on with the db.
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, Profile_Page.class ));
    }
}

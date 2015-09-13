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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class Log_in_form extends ActionBarActivity{
    EditText ETusername;
    EditText ETpassword;
    UserLocalStore userLocalStore;
    Button loginBtn;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_form);
        ETusername = (EditText) findViewById(R.id.usernameET);
        ETpassword = (EditText) findViewById(R.id.passwordET);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        userLocalStore = new UserLocalStore(this);


    }


    public void logMeIn(View v) {

                String username = ETusername.getText().toString();
                String password = ETpassword.getText().toString();
                ParseUser.logInInBackground(username, password,
                    new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                // If user exist and authenticated, send user to Welcome.class
                                Intent intent = new Intent(
                                        Log_in_form.this,
                                        Profile_Page.class);

                                //startActivity(intent);


                                Intent iFAKE = new Intent (Log_in_form.this, MakeCommit2.class);
                                startActivity(iFAKE);

                                Toast.makeText(getApplicationContext(),
                                        "Successfully Logged in",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "No such user exist, please signup",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                //User user = new User (username,password); //creates a user based on the log in information entered by the user
                //authenticate(user); //authenticates the user
                //userLocalStore.storeUserData(user);
                //userLocalStore.setUserLoggedIn(true);

            /*case R.id.logout
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            */
        }

    private void authenticate(User user)
    {
        ServerRequests serverRequests= new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {

            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) //THIS IS CRASHING SOMETHING WRONG WITH SERVER REQUESTS
                {
                    showErrorMessage(); //returns Toast that says authentication failed
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
        Toast toast = Toast.makeText(this, "Please double-check your login or password and try again", Toast.LENGTH_LONG);
        toast.show();
    }
    private void logUserIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser); //so you use userLocalStore to be able to draw the user Details to display on the profile page.
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, Profile_Page.class));


    }
}

package com.example.android.moneyspeaks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;

//need to obtain a client token everytime app starts, so put request for client_token here in launcher activity
public class MoneySpeaks extends ActionBarActivity {
    private TextView mTextDetails;
    CallbackManager callbackManager;
    Button register;
    Button login;
    UserLocalStore userLocalStore;

    private boolean authenticate () {
        return userLocalStore.getUserLoggedin();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate()==true )
        {
            //if user is logged in, we go to their profile page
            Intent i = new Intent (this, Profile_Page.class);
            startActivity(i);
        }
        else
        {
            setContentView(R.layout.activity_money_speaks);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        register = (Button) findViewById(R.id.registerPage);
        login = (Button) findViewById(R.id.loginbutton);

    }
    public void registerPage() {
        Intent i = new Intent(this, SignUpPage.class);
        startActivity(i);

    }
    public void loginForm() {
        
        Intent i = new Intent (this, Log_in_form.class);
        startActivity(i);
    }


     /*   FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // App code
            }
        }; */
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_money_speaks, menu);
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

    public void introView (View view)
    {
        Intent i = new Intent(this, HomePage2.class);
        startActivity(i);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    */



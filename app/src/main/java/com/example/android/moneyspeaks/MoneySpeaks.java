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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import org.apache.http.Header;

//need to obtain a client token everytime app starts, so put request for client_token here in launcher activity
public class MoneySpeaks extends ActionBarActivity {
    private TextView mTextDetails;
    CallbackManager callbackManager;
    Button register;
    Button login;
    UserLocalStore userLocalStore;
    String AppID;
    String ClientKey;

    private boolean authenticate () {
        UserLocalStore userLocalStore = new UserLocalStore(this);

        return userLocalStore.getUserLoggedin();






    }


   /* @Override
    protected void onStart() {
        super.onStart();


    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserLocalStore userLocalStore = new UserLocalStore(this);
        setContentView(R.layout.activity_money_speaks);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, AppID, ClientKey); //Note I've replaced with AppID and ClientKey with null strings for security reasons.
        register = (Button) findViewById(R.id.registerPage);
        login = (Button) findViewById(R.id.loginbutton);
       // runBraintreeCode();
    }


        void postNonceToServer(String nonce) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("payment_method_nonce", nonce);
            client.post("http://your-server/payment-methods", params,
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                        // Your implementation here
                    }
            );
        }


      /*   if (authenticate())
        {
            //if user is logged in, we go to their profile pagee
            Intent i = new Intent (this, Profile_Page.class);
            startActivity(i);
        }
        else
        {
            setContentView(R.layout.activity_money_speaks);
        }
        */





    public void runBraintreeCode()
    {
         final String[] nonce = new String[1];
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://removed_url_security_reasons/client_token", new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
            {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString)
            {
               // nonce = responseString; //this is the payment method nonce

            }


        });


    }


    public void registerPage(View view) {
        Intent i = new Intent(this, SignUpPage.class);
        startActivity(i);

    }
    public void loginForm(View view) {
        
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



package com.example.android.moneyspeaks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpPage extends ActionBarActivity  {
    EditText ETemail, ETusername, ETpassword;
    Button registerBtn;
    //make sure you write some code to check if their internet is on to send data to database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        ETemail = (EditText) findViewById(R.id.email_field);
        ETusername = (EditText) findViewById(R.id.username_field);
        ETpassword = (EditText) findViewById(R.id.password_field);
        registerBtn = (Button) findViewById(R.id.registerbutton);




        }
    public void SignUpPage(Context context)
    {

    }






     public void registerThis(View view) {

        String username = ETusername.getText().toString();
        String password = ETpassword.getText().toString();
        String email = ETemail.getText().toString();

       /* if (!isEmailValid(email)) {
           Toast toast = Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT);
            toast.show();
        }
         */

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast toast = Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT);
            toast.show();;

        } else {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Processing...");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        progressDialog.dismiss();
                        // Show a simple Toast message upon successful registration

                        //Intent i = new Intent(SignUpPage.this, RefPage1.class);
                        //startActivity(i);


                        //DOING THIS INSTEAD
                        Intent iFake = new Intent(SignUpPage.this, Checkout_Activity.class);
                        startActivity(iFake);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "Sign up error, please try again", Toast.LENGTH_LONG)
                                .show();
                    }

            //User registeredData = new User(email, username, password);
            //registerUser(registeredData);
            }
             });
        }
     }

   /*
   this method is from when i did the other way of registration
   private void registerUser(User user)
    {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent i = new Intent(SignUpPage.this, RefPage1.class);
                startActivity(i);
            }
        });
    }*/

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}

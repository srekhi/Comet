package com.example.android.moneyspeaks;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUpPage extends ActionBarActivity implements View.OnClickListener {
    EditText ETemail, ETusername, ETpassword;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        ETemail = (EditText) findViewById(R.id.email_field);
        ETusername= (EditText) findViewById(R.id.username_field);
        ETpassword = (EditText) findViewById(R.id.password_field);
        registerBtn= (Button) findViewById(R.id.registerbutton);


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.registerbutton:
                String username = ETusername.getText().toString();
                String password = ETpassword.getText().toString();
                String email = ETemail.getText().toString();

                User registeredData = new User (email, username, password);

                registerUser(user);
                break;
        }
    }
    private void registerUser()
    {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(this, Log_in_form.class));
            }
        })
    }


}

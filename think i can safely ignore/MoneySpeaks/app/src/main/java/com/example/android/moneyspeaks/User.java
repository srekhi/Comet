package com.example.android.moneyspeaks;

/**
 * Created by Sunny on 6/30/2015.
 */
public class User {
    String username, password, email;

    public User (String email, String username, String password)
    {   this.email=email;
        this.password=password;
        this.username = username;

    }
    public User (String username, String password)
    {
        this.username = username;
        this.password = password;
        this.email="";
    }

}

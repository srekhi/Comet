package com.example.android.moneyspeaks;

/**
 * Created by Sunny on 7/3/2015.
 */
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15; //time in milliseconds that the server persists for before it stops
    public static final String SERVER_ADDRESS = "http://comet.site90.net/"; //unsure about this--might have to redefine
    public static int count = 0;
    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }
    public void storeUserDataInBackground(User user,
                                          GetUserCallback userCallBack) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();

    }
    public void storeUserCommitment(Commitment commitment, GetUserCallback userCallback)
    {
        progressDialog.show();
        new storeUserCommitmentAsyncTask(commitment, userCallback).execute();
    }



    public void fetchUserDataInBackground(User user, GetUserCallback userCallBack) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, userCallBack).execute();
    }


    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    //background Task = AsyncTask
    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        Toast toast;
        User user;
        GetUserCallback userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
            ;
        }

        @Override
        protected Void doInBackground(Void... params) {
           /* ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));
            */

            //NameValue pair is a way to put Key-value pairs in an ArrayList. This has been deprecated, so see the below code:
            ContentValues values = new ContentValues();
            values.put("email", user.email);
            values.put("username", user.username);
            values.put("password", user.password);
            HashMap<String, String> postDataParams = new HashMap<String, String>();
            postDataParams.put("email", user.email);
            postDataParams.put("username", user.username);
            postDataParams.put("password", user.password);


            try {
                URL url = new URL(SERVER_ADDRESS + "Register.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                urlConnection.setReadTimeout(CONNECTION_TIMEOUT);
                urlConnection.setDoOutput(true); //configure connection for output to post data to server. sets HTTP method to "post"
                urlConnection.setChunkedStreamingMode(0); //do this when body length not known in advance of data to be sent
                urlConnection.setRequestMethod("POST");
                OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());//returns an output stream for writing data to this URLConnection
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                writer.write(getPostDataString(postDataParams));
                //okay, so writer's sends string to server-->  "username=user&email=email&password=pass"
                writer.flush();
                writer.close();
                outputStream.close();
                urlConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) { //this is the catch for the openConnection() statement
                e.printStackTrace();
            }

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallBack.done(null);
        }
    }





        //repeat the process to retrieve data from server
        public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
            User user;
            GetUserCallback userCallBack;

            public fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
                this.user = user;
                this.userCallBack = userCallBack;
            }

            @Override
            protected User doInBackground(Void... params) {
                /*ArrayList<NameValuePair> dataToSend = new ArrayList<>();

                dataToSend.add(new BasicNameValuePair("username", user.username));
                dataToSend.add(new BasicNameValuePair("password", user.password));
                */
                ContentValues values2 = new ContentValues();
                values2.put("username", user.username);
                values2.put("password", user.password);

                HashMap<String, String> postDataParams2 = new HashMap<String, String>();
                postDataParams2.put("username", user.username);
                postDataParams2.put("password", user.password);
                User returnedUser = null;
                StringBuilder result = new StringBuilder();

                try {
                    URL url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                    urlConnection.setReadTimeout(CONNECTION_TIMEOUT);
                    urlConnection.setDoOutput(true);
                    urlConnection.setChunkedStreamingMode(0);
                    urlConnection.setRequestMethod("POST");
                    OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getPostDataString(postDataParams2));
                    writer.flush();
                    writer.close();
                    //we have to post the data to the server so it can process and return some shit


                    //now we have to read the input

                    InputStream response = urlConnection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line); //result is a StringBuilder class with the key/values of the JSON object

                    }
                    String Stringresult = result.toString();
                    try {
                        JSONObject ResponseObject = new JSONObject(Stringresult);
                        String email = ResponseObject.getString("email");
                        String username =  ResponseObject.getString("username");
                        String password = ResponseObject.getString("password");
                        returnedUser = new User (email, username, password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return returnedUser;



                /*HttpParams httpRequestParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                        CONNECTION_TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpRequestParams,
                        CONNECTION_TIMEOUT);

                HttpClient client = new DefaultHttpClient(httpRequestParams);
                HttpPost post = new HttpPost(SERVER_ADDRESS
                        + "FetchUserData.php");

                */

            }


            @Override
            protected void onPostExecute(User returnedUser) {
                super.onPostExecute(returnedUser);
                progressDialog.dismiss();
                userCallBack.done(returnedUser);
            }
        }

    public class storeUserCommitmentAsyncTask extends AsyncTask <Void, Void, Void>
    {
        Commitment commitment;
        GetUserCallback userCallBack;

        public storeUserCommitmentAsyncTask(Commitment commitment, GetUserCallback userCallBack) {
            this.commitment = commitment;
            this.userCallBack = userCallBack;
            ;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            HashMap<String, String> postDataParams = new HashMap<String, String>();
            postDataParams.put("goal_name", commitment.goal);
            postDataParams.put("wager", commitment.wager);
            postDataParams.put("time_classification", commitment.time_req_2);
            postDataParams.put("code", commitment.code);





            try {
                URL url = new URL(SERVER_ADDRESS +  "Commit.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                urlConnection.setReadTimeout(CONNECTION_TIMEOUT);
                urlConnection.setDoOutput(true); //configure connection for output to post data to server. sets HTTP method to "post"
                urlConnection.setChunkedStreamingMode(0); //do this when body length not known in advance of data to be sent
                urlConnection.setRequestMethod("POST");
                OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());//returns an output stream for writing data to this URLConnection
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                writer.write(getPostDataString(postDataParams));
                //okay, so writer's sends string to server-->  "username=user&email=email&password=pass"
                writer.flush();
                writer.close();
                outputStream.close();
                urlConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) { //this is the catch for the openConnection() statement
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallBack.done(null);
        }
    }


    //code to send data appropriately to server, taken from http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post
     private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException { //hashmap name = params
        StringBuilder result = new StringBuilder(); //just builds Strings..not diff than actual toString
        boolean first = true; //FIRST is a boolean
        for(Map.Entry<String, String> entry : params.entrySet()){ // Map.Entry is a key/value mapping contained in Map
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
         return result.toString();
    }

}


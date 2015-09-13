package com.example.android.moneyspeaks;

/**
 * Created by Sunny on 7/3/2015.
 */
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15; //time in milliseconds that the server persists for before it stops
    public static final String SERVER_ADDRESS = "http://comet.site90.net/"; //unsure about this--might have to redefine

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
        User user;
        GetUserCallback userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
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

            try {
                URL url = new URL(SERVER_ADDRESS + "Register.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                urlConnection.setReadTimeout(CONNECTION_TIMEOUT);
                urlConnection.setDoOutput(true);
                urlConnection.setChunkedStreamingMode(0);
                urlConnection.setRequestMethod("POST");
                OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(String.valueOf(values));
                writer.flush();
                writer.close();
                outputStream.close();
                urlConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) { //this is the catch for the openConnection() statement
                e.printStackTrace();
            }

           /* HttpParams httpRequestParams = getHttpRequestParams(); //allows us to modify the attributes of the HTTP request we're gonna make

            HttpClient client = new DefaultHttpClient(httpRequestParams); //client allows us to make requests to the server
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "Register.php"); //this is the data management system that will handle email/user/password that we send to it.

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend)); //Encodes the data to be sent and gives to post to send to server
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace(); //this prints if something goes wrong
            }

            return null;
        }
*/
       /* private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, //time we wanna wait before post is executed
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT); //time to wait to receive anything from server
            return httpRequestParams;
        }

*/          return null;

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
                    writer.write(String.valueOf(values2));
                    writer.flush();
                    writer.close();
                    urlConnection.connect();

                    InputStream response = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    //Map jsonJavaRootObject = new Gson().fromJson(result, Map.class); -->this was an attempt to read data from RESULT
                    String email = JsonPath.read(result, "$.email");
                    String username = JsonPath.read(result, "$.username");
                    String password = JsonPath.read(result, "$.password");

                    returnedUser = new User(email, username, password);
                    //now result contains the JSON String representation of the data passed back.
                    //take result, get user name password email out of it, then store in returnedUser



                   /* JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,SERVER_ADDRESS+"FetchUserData.php", null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String name = response.getString("name");
                                String password = response.getString("age");
                                String email = response.getString("email");
                                returnedUser = new User (email, name, password);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    */


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
    }


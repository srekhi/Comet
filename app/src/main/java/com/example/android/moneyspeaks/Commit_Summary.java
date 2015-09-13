package com.example.android.moneyspeaks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//import com.braintreepayments.api.dropin.BraintreePaymentActivity;
import com.braintreepayments.api.models.ClientToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.*;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.paypal.android.sdk.v;

import org.apache.http.Header;


public class Commit_Summary extends ActionBarActivity {
    TextView goalNameSummary;
    TextView goalTimeSummary;
    TextView classify1Summary;
    TextView classify2Summary;
    Commitment commitment;
    public static final String SP_NAME = "userDetails";
    TextView wager;
    public static final int REQUEST_CODE = 11876;
    ClientToken clientToken;
    private static final String BASE_URL = "https://damp-cliffs-3969.herokuapp.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();
    CodeGenerator codeGenerator = new CodeGenerator();
    int intCode = codeGenerator.gen();
    String codeToSend = String.valueOf(intCode);
    String refNumber;
    Checkout_Activity checkout = new Checkout_Activity();

    //get client token from server here|||| "https://damp-cliffs-3969.herokuapp.com/"

    //still have to get cost intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent i1 = getIntent();
        setContentView(R.layout.activity_commit__summary);
        Bundle bundle = i1.getExtras();

        //name of the goal
        if (bundle!=null) {
            goalNameSummary = (TextView) findViewById(R.id.goalID);
            goalNameSummary.setText(bundle.getString("GoalName"));
            Log.d("goalNameSummary", goalNameSummary.getText().toString());
            //Number time of goal
            goalTimeSummary = (TextView) findViewById(R.id.timeReqSummary);
            goalTimeSummary.setText(String.valueOf(bundle.getInt("timeReq")));

            //classify1
            classify1Summary = (TextView) findViewById(R.id.classify1Summary);
            classify1Summary.setText(bundle.getString("TimeClassification1"));

            //classify2
            classify2Summary = (TextView) findViewById(R.id.classify2Summary);
            classify2Summary.setText(bundle.getString("TimeClassification2"));

            //wager amount
            wager= (TextView) findViewById(R.id.wager);
            wager.setText(String.valueOf(bundle.getInt("wager"))); //this is the only setText that's working. it's also in a separate intent.

           // refNumber = bundle.getString("REFNUMBA");
            Intent iAlarm = new Intent(this,Alarm.class);
            iAlarm.putExtra("wager", String.valueOf(bundle.getInt("wager")));

        }
    }
        //now post this commitment to server

        private void storetheUserCommitment(Commitment commitment)

        {
            ServerRequests serverRequests = new ServerRequests(this);
            serverRequests.storeUserCommitment(commitment, new GetUserCallback() {
                @Override
                public void done(User returnedUser) {
                    Intent i = new Intent(Commit_Summary.this, Profile_Page.class);
                    startActivity(i);
                }
            });
        }



/*
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://damp-cliffs-3969.herokuapp.com/", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                clientToken = (ClientToken.fromString(responseString));
            }

        });

    /*

    /*public void onBraintreeSubmit(View v) {
        Intent intent = new Intent(this, BraintreePaymentActivity.class);
        intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, (Parcelable) clientToken); //unsure about the Parcelable addition--seems right tho
        // REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(intent, REQUEST_CODE);
    }




    //need a method to check if payment method has been entered-- if it has don't pass to payment page
    public void checkPay()
    {
        //wait until after Braintree to implement this
    }

*/
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
        // need a method to submit each commitment to the server

    /* @Override
     //as I underestand it, this should receive the payment_method_nonce from the payment form that the user will fill out
     //after they press the button saying take me there. then this activity receives that data and posts that bitch to the server
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == REQUEST_CODE) {
             switch (resultCode) {
                 case BraintreePaymentActivity.RESULT_OK:
                     String paymentMethodNonce = data
                             .getStringExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);
                     break;
                 case BraintreePaymentActivity.BRAINTREE_RESULT_DEVELOPER_ERROR:
                 case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_ERROR:
                 case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_UNAVAILABLE:
                     // handle errors here, a throwable may be available in
                     // data.getSerializableExtra(BraintreePaymentActivity.EXTRA_ERROR_MESSAGE)
                     break;
                 default:
                     break;
             }

         }
     }
     */
    //now, I pass the paymethodmethodnonce i just got to the server
    void postNonceToServer(String nonce) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("payment_method_nonce", nonce);
        client.post("https://damp-cliffs-3969.herokuapp.com/payment-methods", params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //understand what these parameters are to better use them
                        //this is how you convert responseBody to String--> String str= new String (responseBody, "UTF-8")

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                    // Your implementation here
                }
        );
    }

    //should make another method to post the wager to the server as that's gonna keep changing, but payment method stays constant
    // //now whenever
    // params.put("Time_Classification", classify2Summary.getText().toString());
    public void postData(View v)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String refNumber = sharedPreferences.getString("refereeNumber", ""); //NOW REF NUMBER IS CORRECT
        Log.d("refereeNumber", refNumber);

        //PARSE CODE and SMS MANANGING
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(refNumber, null, codeToSend, null, null);
        ParseObject commitment = new ParseObject("Commitment");
        commitment.put("goalName", goalNameSummary.getText().toString());
        commitment.put("wager",wager.getText().toString());
        commitment.put("GoalTime", goalTimeSummary.getText().toString());
        commitment.put("timeClassification1",classify1Summary.getText().toString());
        commitment.put("timeClassification2",classify2Summary.getText().toString());
        commitment.put("code", codeToSend);

        //create an author relationship with the current user
        commitment.put("author", ParseUser.getCurrentUser());
        commitment.saveInBackground();
        //END OF PARSE CODE AND SMS SHIT




        Commitment registerCommitment = new Commitment(goalNameSummary.toString(), wager.toString(), classify2Summary.toString(), codeToSend);
        storetheUserCommitment(registerCommitment);
        /*AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("wager", Integer.parseInt(wager.getText().toString()));
        params.put("Time_Classification", classify2Summary.getText().toString());
        client.post("https://damp-cliffs-3969.herokuapp.com/commit-data", params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //understand what these parameters are to better use them
                        //this is how you convert responseBody to String--> String str= new String (responseBody, "UTF-8")

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                    // Your implementation here
                }

        );
        */

    }


}

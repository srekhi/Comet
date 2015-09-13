package com.example.android.moneyspeaks;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.braintreepayments.api.dropin.BraintreePaymentActivity;
import com.braintreepayments.api.models.ClientToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.*;

import org.apache.http.Header;


public class Commit_Summary extends ActionBarActivity {
    TextView goalNameSummary;
    TextView goalTimeSummary;
    TextView classify1Summary;
    TextView classify2Summary;
    EditText wager;
    private static int REQUEST_CODE = 1900;
    ClientToken clientToken;

    //still have to get cost intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit__summary);
        Bundle bundle = getIntent().getExtras();

        //name of the goal
        goalNameSummary = (TextView) findViewById(R.id.goalID);
        goalNameSummary.setText((String) bundle.get("GoalName"));

        //Number time of goal
        goalTimeSummary = (TextView) findViewById(R.id.timeReqSummary);
        goalTimeSummary.setText((Integer) bundle.get("timeReq"));

        //classify1
        classify1Summary = (TextView) findViewById(R.id.classify1Summary);
        classify1Summary.setText((String) bundle.get("TimeClassification1"));

        //classify2
        classify2Summary = (TextView) findViewById(R.id.classify2Summary);
        classify2Summary.setText((String) bundle.get("TimeClassification2"));

        //wager amount
        wager = (EditText) findViewById(R.id.wager);
        wager.setText((Integer) bundle.get("wager"));

    }

    //need a method to check if payment method has been entered-- if it has don't pass to payment page
    public void checkPay()
    {
        //wait until after Braintree to implement this
    }





    //get client token from server here|||| https://gentle-crag-4137.herokuapp.com/
    AsyncHttpClient client = new AsyncHttpClient();
    client.get("https://your-server/client_token",new

    TextHttpResponseHandler() {
        @Override
        public void onSuccess (String clientToken){
            this.clientToken = clientToken;
        }
    }

    );

    public void onBraintreeSubmit(View v) {
        Intent intent = new Intent(this, BraintreePaymentActivity.class);
        intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, clientToken);
        // REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
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
    //now, I pass the paymethodmethodnonce i just got to the server
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
}

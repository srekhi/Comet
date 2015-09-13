package com.example.android.moneyspeaks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by Sunny on 7/26/2015.
 */
public class CommitmentDataAdapter extends RecyclerView.Adapter<CommitmentDataAdapter.MyViewHolder>
{
    List<CommitmentData> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;
    long millisCountDownTime;
    Intent intent;
    PendingIntent pendingIntent;



    public CommitmentDataAdapter(Context context, List<CommitmentData> data) //constructor, pass in List received from Parse here
    {
        inflater = LayoutInflater.from(context);
        this.data= data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)//called everytime a new row is to be displayed
    {
        View view = inflater.inflate(R.layout.recycler_view_layout, parent, false); //View of an item in recycleview
        MyViewHolder holder = new MyViewHolder(view); //holder is a quick way of rapidly finding all the elements by their IDs

        return holder;
    }
    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        CommitmentData current = data.get(position);
        holder.commitment.setText(current.commitment);
        holder.submitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "HELL YA", Toast.LENGTH_LONG).show();
                // if (holder.codeInputField.getText().toString() = QUERY PARSE FOR CODE)
                //then reset the time
                // else
                //make a Toast that says wrong code bruh.
            }
        });

        if (current.classify2.equals("day(s)") )
        {
            millisCountDownTime = 86400000;
        }
        else if (current.classify2.equals("week(s)"))
        {
            millisCountDownTime = 604800000;
        }
        else if (current.classify2.equals("hour(s)"))
        {
            millisCountDownTime = 3600000;
        }
        else if(current.classify2.equals("minute(s)"))
        {
            millisCountDownTime = 6000;
        }
        Log.d("millisCountDownTime", String.valueOf(millisCountDownTime));



        new CountDownTimer(millisCountDownTime, 1000)
        { // adjust the milli seconds here

            public void onTick(long millisUntilFinished)
            {
                holder.codeTimer.setText("" + String.format("%d min, %d sec", //not working,could be bc of final declaration
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            public void onFinish() {

            }
        }.start();


    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {    TextView commitment;
         TextView enterCode;
         TextView codeTimer;
         EditText codeInputField;
         Button submitCode;



        public MyViewHolder(View itemView) {
            super(itemView);
            commitment= (TextView) itemView.findViewById(R.id.commitmentSentence);
            codeTimer = (TextView) itemView.findViewById(R.id.codeTimer);
            submitCode=(Button) itemView.findViewById(R.id.submitTheCode);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.submitTheCode)
                    {
                        Intent i = new Intent(v.getContext(), WhyRef.class);
                        v.getContext().startActivity(i);

                    }
                }
            });


        }


    }
    public interface ClickListener
    {
        public void itemClicked(View view, int position);
    }
    public void startAlarm(Context context, long millisCountDownTimeInterval )
    {
        intent = new Intent(context, Alarm.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + millisCountDownTimeInterval, millisCountDownTimeInterval, pendingIntent);



    }

    public class DisplayTime extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {

        }
    }

}

package com.example.android.moneyspeaks;
import java.util.Random;
/**
 * Created by Sunny on 7/3/2015.
 */
public class CodeGenerator {


    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return (1 + r.nextInt(2)) * 10000 + r.nextInt(10000);

    }



    //goal is to generate a code for the ref to receive and add to database for authentication


}

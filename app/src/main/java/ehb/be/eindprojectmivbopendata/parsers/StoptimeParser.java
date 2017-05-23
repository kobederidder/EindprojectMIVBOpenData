package ehb.be.eindprojectmivbopendata.parsers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Stoptime;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class StoptimeParser {
    private static final StoptimeParser ourInstance = new StoptimeParser();

    public static StoptimeParser getInstance() {
        return ourInstance;
    }

    private StoptimeParser() {
    }
    //needed stuff
    private ArrayList<Stoptime> mStoptimeList = new ArrayList<> ();
    private final String TAG = "Stoptime";

    public void parseStoptime(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mStoptimeList.add(new Stoptime(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mStoptimeList.remove(0);


    }

    private void printStoptime() {
        for (Stoptime stoptime : mStoptimeList)
            Log.i(TAG, "name " + stoptime.getDeparture_time() + "\n");
    }
}

package ehb.be.eindprojectmivbopendata.parsers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Stop;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class StopParser {
    private static final StopParser ourInstance = new StopParser();

    public static StopParser getInstance() {
        return ourInstance;
    }

    private StopParser() {
    }
    //needed stuff
    public ArrayList<Stop> mStopList = new ArrayList<> ();
    public final String TAG = "Stop";

    public void parseStop(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mStopList.add(new Stop(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mStopList.remove(0);

        //printRoute();

    }

    private void printRoute() {
        for (Stop stop : mStopList)
            Log.i(TAG, "id " + stop.getStop_id() + "\n"
                    + "stop_name" + stop.getStop_name());
    }

    public ArrayList<Stop> getmStopList(){
        return mStopList;
    }
}

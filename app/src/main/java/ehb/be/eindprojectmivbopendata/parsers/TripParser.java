package ehb.be.eindprojectmivbopendata.parsers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Trip;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class TripParser {
    private static final TripParser ourInstance = new TripParser();

    public static TripParser getInstance() {
        return ourInstance;
    }

    private TripParser() {
    }
    //needed stuff
    private ArrayList<Trip> mTripList = new ArrayList<> ();
    private final String TAG = "Trip";

    public void parseTrip(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mTripList.add(new Trip(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mTripList.remove(0);

        printRoute();
    }

    private void printRoute() {
        for (Trip trip : mTripList)
            Log.i(TAG, "name " + trip.getTrip_id() + "\n");
    }
}

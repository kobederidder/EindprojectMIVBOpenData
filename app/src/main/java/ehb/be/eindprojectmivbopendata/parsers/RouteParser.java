package ehb.be.eindprojectmivbopendata.parsers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Route;

/**
 * Created by mobapp10 on 11/05/17.
 */

public class RouteParser {
    private static final RouteParser ourInstance = new RouteParser();

    public static RouteParser getInstance() {
        return ourInstance;
    }

    private RouteParser() {
    }
    //needed stuff
    private ArrayList<Route> mRouteList = new ArrayList<> ();
    private final String TAG = "Route";

    public void parseRoute(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mRouteList.add(new Route(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mRouteList.remove(0);


    }

    private void printRoute() {
        for (Route route : mRouteList)
            Log.i(TAG, "name " + route.getRoute_short_name() + "\n");
    }
}

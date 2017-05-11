package ehb.be.eindprojectmivbopendata.parsers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import ehb.be.eindprojectmivbopendata.source.Calendar;

/**
 * Created by mobapp10 on 11/05/17.
 */

public class CalendarParser {
    private static final CalendarParser ourInstance = new CalendarParser();

    public static CalendarParser getInstance() {
        return ourInstance;
    }

    private CalendarParser() {
    }

    private ArrayList<Calendar> mCalendarList = new ArrayList<>();
    private final String TAG = "GtfsDemo";

    public void parseCalendar(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mCalendarList.add(new Calendar(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mCalendarList.remove(0);


    }

    private void printAgency() {
        for (Calendar calendar : mCalendarList)
            Log.i(TAG, "name " + calendar.getService_id() + "\n");
    }
}

package ehb.be.eindprojectmivbopendata.parsers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Agency;


/**
 * Created by mobapp10 on 11/05/17.
 * Copied from David
 */

public class AgencyParser {
    private static final AgencyParser ourInstance = new AgencyParser();

    public static AgencyParser getInstance() {
        return ourInstance;
    }

    private AgencyParser() {
    }
    //needed stuff
    private ArrayList<Agency> mAgencyList = new ArrayList<Agency> ();
    private final String TAG = "GtfsDemo";

    public void parseAgency(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mAgencyList.add(new Agency(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mAgencyList.remove(0);


    }


}

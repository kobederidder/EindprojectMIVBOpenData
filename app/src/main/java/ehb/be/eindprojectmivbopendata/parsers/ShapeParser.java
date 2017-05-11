package ehb.be.eindprojectmivbopendata.parsers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Shape;

/**
 * Created by mobapp10 on 11/05/17.
 */

public class ShapeParser {
    private static final ShapeParser ourInstance = new ShapeParser();

    public static ShapeParser getInstance() {
        return ourInstance;
    }

    private ShapeParser() {
    }
    //needed stuff
    private ArrayList<Shape> mShapeList = new ArrayList<> ();
    private final String TAG = "Shape";

    public void parseShape(FileInputStream rid) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mShapeList.add(new Shape(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mShapeList.remove(0);


    }

    private void printShape() {
        for (Shape shape : mShapeList)
            Log.i(TAG, "id " + shape.getShape_id() + "\n");
    }
}

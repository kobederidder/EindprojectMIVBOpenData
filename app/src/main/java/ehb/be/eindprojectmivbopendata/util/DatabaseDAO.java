package ehb.be.eindprojectmivbopendata.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.source.Route;
import ehb.be.eindprojectmivbopendata.source.Stop;
import ehb.be.eindprojectmivbopendata.source.Stoptime;


/**
 * Created by mobapp10 on 17/05/17.
 */

public class DatabaseDAO {
    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseDAO(Context c) {
        dbHelper = new SQLiteHelper(c);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertEntry() {
        //TODO dit nog in orde brengen
        if(db == null) {
            ContentValues mValues = new ContentValues();

            return false;
        }
        return true;

    }

    //Alles van routes dao
    public ArrayList<Route> getAllRoutes() {
        ArrayList<Route> allRoutes = new ArrayList<>();

        Cursor cursor = db.query(SQLiteHelper.getTableRoutes(), SQLiteHelper.getColumnsRoutes(),
                null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Route temp = cursorToRoute(cursor);
            allRoutes.add(temp);
            cursor.moveToNext();
        }
        cursor.close();

        return allRoutes;
    }

    public ArrayList<Route> getDistinctRoutes(){
        ArrayList<Route> allDistinctRoutes = new ArrayList<>();

        Cursor cursorDistinctRoutes = db.query(SQLiteHelper.getTableRoutes(), SQLiteHelper.getColumnsRoutes(), null, null, SQLiteHelper.getColumnRouteId(), null, null);

        cursorDistinctRoutes.moveToFirst();
        while (!cursorDistinctRoutes.isAfterLast()) {
            Route temp = cursorToRoute(cursorDistinctRoutes);
            allDistinctRoutes.add(temp);
            cursorDistinctRoutes.moveToNext();
        }
        cursorDistinctRoutes.close();

        return allDistinctRoutes;
    }

    public boolean insertAllRoutes(ArrayList<Route> routeArrayList) {

        ContentValues mValues = new ContentValues();
        for(Route r : routeArrayList) {
            mValues.put(SQLiteHelper.getColumnRouteId(), r.getRoute_id());
            mValues.put(SQLiteHelper.getColumnRouteShortName(), r.getRoute_short_name());
            mValues.put(SQLiteHelper.getColumnRouteLongName(), r.getRoute_long_name());
            mValues.put(SQLiteHelper.getColumnRouteDesc(), r.getRoute_desc());
            mValues.put(SQLiteHelper.getColumnRouteType(), r.getRoute_type());
            mValues.put(SQLiteHelper.getColumnRouteUrl(), r.getRoute_url());
            mValues.put(SQLiteHelper.getColumnRouteColor(), r.getRoute_color());
            mValues.put(SQLiteHelper.getColumnRouteTextColor(), r.getRoute_text_color());

            long resultID = db.insert(SQLiteHelper.getTableRoutes(), null, mValues);

            if(resultID == -1)
                return false;
        }
            return true;
    }

    private Route cursorToRoute (Cursor cursor) {
        Route temp = new Route();

        temp.setRoute_id(cursor.getString(0));
        temp.setRoute_short_name(cursor.getString(1));
        temp.setRoute_long_name(cursor.getString(2));
        temp.setRoute_desc(cursor.getString(3));
        temp.setRoute_type(cursor.getString(4));
        temp.setRoute_url(cursor.getString(5));
        temp.setRoute_color(cursor.getString(6));
        temp.setRoute_text_color(cursor.getString(7));

        return temp;
    }


    //Alles van stops dao
    public ArrayList<Stop> getAllStops() {
        ArrayList<Stop> allStops = new ArrayList<>();

        Cursor cursorStop = db.query(SQLiteHelper.getTableStops(), SQLiteHelper.getColumnsStops(),
                null, null, null, null, null, null);
        cursorStop.moveToFirst();
        while (!cursorStop.isAfterLast()) {
            Stop temp = cursorToStop(cursorStop);
            allStops.add(temp);
            cursorStop.moveToNext();
        }
        cursorStop.close();

        return allStops;
    }

    public ArrayList<Stop> getDistinctStopNames(){
        ArrayList<Stop> allDistinctNames = new ArrayList<>();

        Cursor cursorDistinctStopNames = db.query(SQLiteHelper.getTableStops(), SQLiteHelper.getColumnsStops(), null, null, SQLiteHelper.getColumnStopName(), null, null);

        cursorDistinctStopNames.moveToFirst();
        while (!cursorDistinctStopNames.isAfterLast()) {
            Stop temp = cursorToStop(cursorDistinctStopNames);
            allDistinctNames.add(temp);
            cursorDistinctStopNames.moveToNext();
        }
        cursorDistinctStopNames.close();

        return allDistinctNames;
    }

    public boolean insertAllStops(ArrayList<Stop> stopArrayList) {

        ContentValues mValues = new ContentValues();
        for(Stop s : stopArrayList) {
            mValues.put(SQLiteHelper.getColumnStopId(), s.getStop_id());
            mValues.put(SQLiteHelper.getColumnStopCode(), s.getStop_code());
            mValues.put(SQLiteHelper.getColumnStopName(), s.getStop_name());
            mValues.put(SQLiteHelper.getColumnStopDesc(), s.getStop_desc());
            mValues.put(SQLiteHelper.getColumnStopLat(), s.getStop_lat());
            mValues.put(SQLiteHelper.getColumnStopLon(), s.getStop_lon());
            mValues.put(SQLiteHelper.getColumnZoneId(), s.getZone_id());
            mValues.put(SQLiteHelper.getColumnStopUrl(), s.getStop_url());
            mValues.put(SQLiteHelper.getColumnLocationType(), s.getLocation_type());

            long resultID = db.insert(SQLiteHelper.getTableStops(), null, mValues);

            if(resultID == -1)
                return false;
        }
        Log.i("Stops", "Stops zijn geladen");
        return true;
    }

    private Stop cursorToStop (Cursor cursor) {
        Stop temp = new Stop();

        temp.setStop_id(cursor.getString(0));
        temp.setStop_code(cursor.getString(1));
        temp.setStop_name(cursor.getString(2));
        temp.setStop_desc(cursor.getString(3));
        temp.setStop_lat(cursor.getString(4));
        temp.setStop_lon(cursor.getString(5));
        temp.setZone_id(cursor.getString(6));
        temp.setStop_url(cursor.getString(7));
        temp.setLocation_type(cursor.getString(8));

        return temp;
    }


    //Alles van stoptimes dao
    public ArrayList<Stoptime> getAllStoptimes() {
        ArrayList<Stoptime> allStoptimes = new ArrayList<>();

        Cursor cursor = db.query(SQLiteHelper.getTableStopTimes(), SQLiteHelper.getColumnsStopTimes(),
                null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Stoptime temp = cursorToStoptime(cursor);
            allStoptimes.add(temp);
            cursor.moveToNext();
        }
        cursor.close();

        return allStoptimes;
    }

    public boolean insertAllStoptimes(ArrayList<Stoptime> stoptimeArrayList) {

        ContentValues mValuesStoptime = new ContentValues();
        for(Stoptime s : stoptimeArrayList) {
            mValuesStoptime.put(SQLiteHelper.getColumnTripId(), s.getTrip_id());
            mValuesStoptime.put(SQLiteHelper.getColumnArrivalTime(), s.getArrival_time());
            mValuesStoptime.put(SQLiteHelper.getColumnDepartureTime(), s.getDeparture_time());
            mValuesStoptime.put(SQLiteHelper.getColumnStopId(), s.getStop_id());
            mValuesStoptime.put(SQLiteHelper.getColumnStopSequence(), s.getStop_sequence());
            mValuesStoptime.put(SQLiteHelper.getColumnPickupType(), s.getPickup_type());
            mValuesStoptime.put(SQLiteHelper.getColumnDropOffType(), s.getDrop_off_type());

            long resultID = db.insert(SQLiteHelper.getTableStopTimes(), null, mValuesStoptime);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Stoptime cursorToStoptime (Cursor cursor) {
        Stoptime temp = new Stoptime();

        temp.setTrip_id(cursor.getString(0));
        temp.setArrival_time(cursor.getString(1));
        temp.setDeparture_time(cursor.getString(2));
        temp.setStop_id(cursor.getString(3));
        temp.setStop_sequence(cursor.getString(4));
        temp.setPickup_type(cursor.getString(5));
        temp.setDrop_off_type(cursor.getString(6));

        return temp;
    }




}

package ehb.be.eindprojectmivbopendata.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.parsers.RouteParser;
import ehb.be.eindprojectmivbopendata.source.Route;

/**
 * Created by mobapp10 on 17/05/17.
 */

public class databaseDAO {
    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public databaseDAO(Context c) {
    }

    public void open(Context c) {
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

    public ArrayList<Route> getAllRoutes() {
        ArrayList<Route> allRoutes = new ArrayList<>();

        Cursor cursor = db.query(false, SQLiteHelper.getTableRoutes(), SQLiteHelper.getColumnsRoutes(),
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

    public boolean setAllRoutes(ArrayList<Route> routeArrayList) {

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
        }

        long resultID = db.insert(SQLiteHelper.getTableRoutes(), null, mValues);

        if(resultID == -1)
            return false;
        else
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


}

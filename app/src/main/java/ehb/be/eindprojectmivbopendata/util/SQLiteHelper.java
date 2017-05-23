package ehb.be.eindprojectmivbopendata.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mobapp10 on 16/05/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "mivbDB";
    private final static int DB_VERSION = 1;

    //tabellen die we lokaal willen opslaan
    private final static String TABLE_STOPS = "stops";
    private final static String TABLE_ROUTES = "routes";
    private final static String TABLE_STOP_TIMES = "stop_times";
    private final static String TABLE_TRIPS = "trips";

    //kolommen van tabel stops
    private final static String COLUMN_STOP_ID = "stop_id";
    private final static String COLUMN_STOP_CODE = "stop_code";
    private final static String COLUMN_STOP_NAME = "stop_name";
    private final static String COLUMN_STOP_DESC = "stop_desc";
    private final static String COLUMN_STOP_LAT = "stop_lat";
    private final static String COLUMN_STOP_LON = "stop_lon";
    private final static String COLUMN_ZONE_ID = "zone_id";
    private final static String COLUMN_STOP_URL = "stop_url";
    private final static String COLUMN_LOCATION_TYPE = "location_type";
    private final static String[] COLUMNS_STOPS = {COLUMN_STOP_ID, COLUMN_STOP_CODE, COLUMN_STOP_NAME,
            COLUMN_STOP_DESC, COLUMN_STOP_LAT, COLUMN_STOP_LON, COLUMN_ZONE_ID, COLUMN_STOP_URL, COLUMN_LOCATION_TYPE};

    //kolommen van tabel routes
    private final static String COLUMN_ROUTE_ID = "route_id";
    private final static String COLUMN_ROUTE_SHORT_NAME = "route_short_name";
    private final static String COLUMN_ROUTE_LONG_NAME = "route_long_name";
    private final static String COLUMN_ROUTE_DESC = "route_desc";
    private final static String COLUMN_ROUTE_TYPE = "route_type";
    private final static String COLUMN_ROUTE_URL = "route_url";
    private final static String COLUMN_ROUTE_COLOR = "route_color";
    private final static String COLUMN_ROUTE_TEXT_COLOR = "route_text_color";
    private final static String[] COLUMNS_ROUTES = {COLUMN_ROUTE_ID, COLUMN_ROUTE_SHORT_NAME, COLUMN_ROUTE_LONG_NAME, COLUMN_ROUTE_DESC,
            COLUMN_ROUTE_TYPE, COLUMN_ROUTE_URL, COLUMN_ROUTE_COLOR, COLUMN_ROUTE_TEXT_COLOR};

    //kolommen van tabel stop_times
    private final static String COLUMN_TRIP_ID = "trip_id";
    private final static String COLUMN_ARRIVAL_TIME = "arrival_time";
    private final static String COLUMN_DEPARTURE_TIME = "departure_time";
    private final static String COLUMN_STOP_SEQUENCE = "stop_sequence";
    private final static String COLUMN_PICKUP_TYPE = "pickup_type";
    private final static String COLUMN_DROP_OFF_TYPE = "drop_off_type";
    private final static String[] COLUMNS_STOP_TIMES = {COLUMN_TRIP_ID, COLUMN_ARRIVAL_TIME, COLUMN_DEPARTURE_TIME, COLUMN_STOP_ID,
            COLUMN_STOP_SEQUENCE, COLUMN_PICKUP_TYPE, COLUMN_DROP_OFF_TYPE};

    //kolommen van tabel TRIPS
    private final static String COLUMN_SERVICE_ID = "service_id";
    private final static String COLUMN_TRIP_HEADSIGN = "trip_headsign";
    private final static String COLUMN_DIRECTION_ID = "direction_id";
    private final static String COLUMN_BLOCK_ID = "pickup_type";
    private final static String COLUMN_SHAPE_ID = "drop_off_type";
    private final static String[] COLUMNS_TRIPS = {COLUMN_ROUTE_ID, COLUMN_SERVICE_ID, COLUMN_TRIP_ID, COLUMN_TRIP_HEADSIGN,
            COLUMN_DIRECTION_ID, COLUMN_BLOCK_ID, COLUMN_SHAPE_ID};

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO execsql beter maken
        db.execSQL("CREATE TABLE " + TABLE_ROUTES + " (" + COLUMN_ROUTE_ID + " TEXT NOT NULL, "
                + COLUMN_ROUTE_SHORT_NAME + " TEXT NOT NULL, " + COLUMN_ROUTE_LONG_NAME + " TEXT NOT NULL, "
                + COLUMN_ROUTE_DESC + " TEXT, " + COLUMN_ROUTE_TYPE + " TEXT, "
                + COLUMN_ROUTE_URL + " TEXT, " + COLUMN_ROUTE_COLOR + " TEXT, " + COLUMN_ROUTE_TEXT_COLOR + " TEXT" + " );"
        );
        db.execSQL("CREATE TABLE " + TABLE_STOPS + " (" + COLUMN_STOP_ID + " TEXT NOT NULL, "
                + COLUMN_STOP_CODE + " TEXT NOT NULL, " + COLUMN_STOP_NAME + " TEXT NOT NULL, "
                + COLUMN_STOP_DESC + " TEXT, " + COLUMN_STOP_LAT+ " TEXT NOT NULL, "
                + COLUMN_STOP_LON + " TEXT NOT NULL, " + COLUMN_ZONE_ID + " TEXT, " + COLUMN_STOP_URL + " TEXT, "
                + COLUMN_LOCATION_TYPE + " TEXT " + " );"
        );
        db.execSQL("CREATE TABLE " + TABLE_STOP_TIMES + " (" + COLUMN_TRIP_ID + " TEXT NOT NULL, "
                + COLUMN_ARRIVAL_TIME + " TEXT NOT NULL, " + COLUMN_DEPARTURE_TIME + " TEXT NOT NULL, "
                + COLUMN_STOP_SEQUENCE + " TEXT, " + COLUMN_PICKUP_TYPE + " TEXT, "
                + COLUMN_DROP_OFF_TYPE + " TEXT " + " );"
        );
        db.execSQL("CREATE TABLE " + TABLE_TRIPS + " (" + COLUMN_ROUTE_ID + " TEXT NOT NULL, "
                + COLUMN_SERVICE_ID + " TEXT NOT NULL, " + COLUMN_TRIP_ID + " TEXT NOT NULL, "
                + COLUMN_TRIP_HEADSIGN + " TEXT, " + COLUMN_DIRECTION_ID + " TEXT, "
                + COLUMN_BLOCK_ID + " TEXT NOT NULL, " + COLUMN_SHAPE_ID + " TEXT " + " );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public static String getDbName() {
        return DB_NAME;
    }

    public static int getDbVersion() {
        return DB_VERSION;
    }

    //TABELLEN
    public static String getTableStops() {
        return TABLE_STOPS;
    }

    public static String getTableRoutes() {
        return TABLE_ROUTES;
    }

    public static String getTableStopTimes() {
        return TABLE_STOP_TIMES;
    }

    public static String getTableTrips() {
        return TABLE_TRIPS;
    }

    //KOLLOMEN
    public static String[] getColumnsStops() {
        return COLUMNS_STOPS;
    }

    public static String[] getColumnsRoutes() {
        return COLUMNS_ROUTES;
    }

    public static String[] getColumnsStopTimes() {
        return COLUMNS_STOP_TIMES;
    }

    public static String[] getColumnsTrips() {
        return COLUMNS_TRIPS;
    }


    //STOP KOLLOMEN
    public static String getColumnStopId() {
        return COLUMN_STOP_ID;
    }

    public static String getColumnStopCode() {
        return COLUMN_STOP_CODE;
    }

    public static String getColumnStopName() {
        return COLUMN_STOP_NAME;
    }

    public static String getColumnStopDesc() {
        return COLUMN_STOP_DESC;
    }

    public static String getColumnStopLat() {
        return COLUMN_STOP_LAT;
    }

    public static String getColumnStopLon() {
        return COLUMN_STOP_LON;
    }

    public static String getColumnZoneId() {
        return COLUMN_ZONE_ID;
    }

    public static String getColumnStopUrl() {
        return COLUMN_STOP_URL;
    }

    public static String getColumnLocationType() {
        return COLUMN_LOCATION_TYPE;
    }

    //ROUTE KOLLOMEN
    public static String getColumnRouteId() {
        return COLUMN_ROUTE_ID;
    }

    public static String getColumnRouteShortName() {
        return COLUMN_ROUTE_SHORT_NAME;
    }

    public static String getColumnRouteLongName() {
        return COLUMN_ROUTE_LONG_NAME;
    }

    public static String getColumnRouteDesc() {
        return COLUMN_ROUTE_DESC;
    }

    public static String getColumnRouteType() {
        return COLUMN_ROUTE_TYPE;
    }

    public static String getColumnRouteUrl() {
        return COLUMN_ROUTE_URL;
    }

    public static String getColumnRouteColor() {
        return COLUMN_ROUTE_COLOR;
    }

    public static String getColumnRouteTextColor() {
        return COLUMN_ROUTE_TEXT_COLOR;
    }

    public static String getColumnTripId() {
        return COLUMN_TRIP_ID;
    }

    public static String getColumnArrivalTime() {
        return COLUMN_ARRIVAL_TIME;
    }

    public static String getColumnDepartureTime() {
        return COLUMN_DEPARTURE_TIME;
    }

    public static String getColumnStopSequence() {
        return COLUMN_STOP_SEQUENCE;
    }

    public static String getColumnPickupType() {
        return COLUMN_PICKUP_TYPE;
    }

    public static String getColumnDropOffType() {
        return COLUMN_DROP_OFF_TYPE;
    }

    public static String getColumnServiceId() {
        return COLUMN_SERVICE_ID;
    }

    public static String getColumnTripHeadsign() {
        return COLUMN_TRIP_HEADSIGN;
    }

    public static String getColumnDirectionId() {
        return COLUMN_DIRECTION_ID;
    }

    public static String getColumnBlockId() {
        return COLUMN_BLOCK_ID;
    }

    public static String getColumnShapeId() {
        return COLUMN_SHAPE_ID;
    }

}

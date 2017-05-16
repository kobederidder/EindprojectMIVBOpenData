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

    //kolommen van tabel stops
    private final static String COLUMN_STOP_ID = "_id";
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
    private final static String COLUMN_ROUTE_ID = "_id";
    private final static String COLUMN_ROUTE_SHORT_NAME = "route_short_name";
    private final static String COLUMN_ROUTE_LONG_NAME = "route_long_name";
    private final static String COLUMN_ROUTE_DESC = "route_desc";
    private final static String COLUMN_ROUTE_TYPE = "route_type";
    private final static String COLUMN_ROUTE_URL = "route_url";
    private final static String COLUMN_ROUTE_COLOR = "route_color";
    private final static String COLUMN_ROUTE_TEXT_COLOR = "route_text_color";

    private final static String[] COLUMNS_ROUTES = {COLUMN_ROUTE_ID, COLUMN_ROUTE_SHORT_NAME, COLUMN_ROUTE_LONG_NAME, COLUMN_ROUTE_DESC,
            COLUMN_ROUTE_TYPE, COLUMN_ROUTE_URL, COLUMN_ROUTE_COLOR, COLUMN_ROUTE_TEXT_COLOR};

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO execsql opstellen
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

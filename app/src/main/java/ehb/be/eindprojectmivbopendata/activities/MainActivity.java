package ehb.be.eindprojectmivbopendata.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.fragments.SettingsFragment;
import ehb.be.eindprojectmivbopendata.fragments.ZoekenFragment;
import ehb.be.eindprojectmivbopendata.fragments.ListFragment;
import ehb.be.eindprojectmivbopendata.fragments.MapFragment;
import ehb.be.eindprojectmivbopendata.fragments.RouteListFragment;
import ehb.be.eindprojectmivbopendata.requests.InputStreamRequest;
import ehb.be.eindprojectmivbopendata.parsers.AgencyParser;
import ehb.be.eindprojectmivbopendata.parsers.CalendarParser;
import ehb.be.eindprojectmivbopendata.parsers.Calendar_DatesParser;
import ehb.be.eindprojectmivbopendata.parsers.RouteParser;
import ehb.be.eindprojectmivbopendata.parsers.ShapeParser;
import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.parsers.TripParser;
import ehb.be.eindprojectmivbopendata.source.Agency;
import ehb.be.eindprojectmivbopendata.source.Stop;
import ehb.be.eindprojectmivbopendata.util.DatabaseDAO;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Stop> mStopList = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private final String FRAGMENT_BACKSTACK = "fragment_backstack";
    private GoogleMap mMap;
    private RouteListFragment mRoute = RouteListFragment.newInstance();
    private MapFragment mapFragment = new MapFragment();
    private ZoekenFragment zoekenFragment = new ZoekenFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPreferences.getBoolean("first", true)) {
            downloadZIP();
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.container, zoekenFragment)
                .addToBackStack(FRAGMENT_BACKSTACK)
                .commit();

        downloadZIP();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
        //HOME = zoeken functie met vertrek & bestemmingspunt, datum en uur
            case R.id.nav_home:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, zoekenFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
            break;
        //Ga naar lijst weergave
            case R.id.nav_list:
            ListFragment listFragment = ListFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, listFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
            break;
        //Ga naar Map weergave
            case R.id.nav_map:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, mapFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
            break;
        //Ga naar meer gedetailleerde listView van routes
            case R.id.nav_detaillist:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, mRoute)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
            break;

        //Ga naar Opties (a.k.a. Settings, Preferences)
            case R.id.nav_settings:
                getFragmentManager().beginTransaction()
                    .replace(R.id.container, settingsFragment)
                    .addToBackStack(FRAGMENT_BACKSTACK)
                    .commit();
            break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void downloadZIP() {


        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        //params voor header
        HashMap<String, String> params = new HashMap<>();

        params.put("Authorization:", "Bearer 81f11d4c20401a22a38e0138c3713d66");
        Toast.makeText(this, "download accessed", Toast.LENGTH_SHORT).show();
        //headers kan je niet setten, fast and dirty de klasse overschrijven
        InputStreamRequest getRequest = new InputStreamRequest(Request.Method.GET,
                "https://opendata-api.stib-mivb.be/Files/1.0/Gtfs",
                responseGETListener,
                responseGETErrorListener,
                params
        );
        Toast.makeText(this, "stream started", Toast.LENGTH_SHORT).show();
        mQueue.add(getRequest);
    }

    private Response.Listener<byte[]> responseGETListener = new Response.Listener<byte[]>() {
        @Override
        public void onResponse(byte[] response) {

            //http://stackoverflow.com/questions/8367126/how-can-i-convert-byte-array-to-zip-file
            //https://techstricks.com/download-file-using-android-volley/

            try {
                //set the path where we want to save the file
                //in this case, going to save it on the cache directory of the project
                File cacheDir = getCacheDir();

                ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(response));
                ZipEntry entry;

                while ((entry = zipStream.getNextEntry()) != null) {
                    //gets filenames from zip -> party
                    String entryName = entry.getName();

                    File f = new File(cacheDir + File.pathSeparator + entryName);
                    FileOutputStream out = new FileOutputStream(f);

                    byte[] byteBuff = new byte[4096];
                    int bytesRead = 0;
                    while ((bytesRead = zipStream.read(byteBuff)) != -1)
                    {
                        out.write(byteBuff, 0, bytesRead);
                    }
                    out.close();

                    zipStream.closeEntry();
                }
                zipStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            parseFiles();
        }
    };
    private Response.ErrorListener responseGETErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("error in response", error.toString());
        }
    };

    private void parseFiles() {
        DatabaseDAO dao = new DatabaseDAO(getApplication());
        try {

            AgencyParser.getInstance()
                    .parseAgency(new FileInputStream(getCacheDir()+File.pathSeparator+"agency.txt"));
            //CalendarParser.getInstance()
             //       .parseCalendar(new FileInputStream(getCacheDir()+File.pathSeparator+"calendar.txt"));
            //Calendar_DatesParser.getInstance()
             //       .parseCalDates(new FileInputStream(getCacheDir()+File.pathSeparator+"calendar_dates.txt"));
            if(!sharedPreferences.getBoolean("done_routes", false)) {
            RouteParser.getInstance()
                   .parseRoute(new FileInputStream(getCacheDir()+File.pathSeparator+"routes.txt"), this);
                dao.insertAllRoutes(RouteParser.getInstance().getmRouteList());
                sharedPreferences.edit().putBoolean("done_routes", true).apply();
            }
            //ShapeParser.getInstance()
            //        .parseShape(new FileInputStream(getCacheDir()+File.pathSeparator+"shapes.txt"));
            if(!sharedPreferences.getBoolean("done_stops", false)) {
            StopParser.getInstance()
                    .parseStop(new FileInputStream(getCacheDir()+File.pathSeparator+"stops.txt"));
                dao.insertAllStops(StopParser.getInstance().getmStopList());
                sharedPreferences.edit().putBoolean("done_stops", true).apply();
            }
            //StoptimeParser.getInstance()
            //        .parseStoptime(new FileInputStream(getCacheDir()+File.pathSeparator+"stop_times.txt"));
            //TripParser.getInstance()
            //        .parseTrip(new FileInputStream(getCacheDir()+File.pathSeparator+"trips.txt"));
            sharedPreferences.edit().putBoolean("first", false).apply();
            mRoute.addAll();
            mapFragment.getAllStopsOnMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dao.close();

        Toast.makeText(this, "Download finished", Toast.LENGTH_SHORT).show();

    }

}

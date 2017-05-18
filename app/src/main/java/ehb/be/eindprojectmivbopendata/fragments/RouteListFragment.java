package ehb.be.eindprojectmivbopendata.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.parsers.RouteParser;
import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.source.Route;
import ehb.be.eindprojectmivbopendata.source.Stop;
import ehb.be.eindprojectmivbopendata.util.RouteAdapter;
import ehb.be.eindprojectmivbopendata.util.StopAdapter;

/**
 * Created by mobapp10 on 15/05/17.
 */

public class RouteListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private SharedPreferences sharedPreferences;
    private final String FRAGMENT_BACKSTACK = "fragment_backstack";
    ArrayList<Route> mRoute = new ArrayList<>();
    ArrayList<Stop> mStop = new ArrayList<>();
    RouteAdapter aa;
    StopAdapter sa;
    ListView lvRoutes;

    public RouteListFragment() {
    }

    public static RouteListFragment newInstance() {
        RouteListFragment fragment = new RouteListFragment();
        return fragment;
    }
    /*
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            aa = new RouteAdapter(getActivity(), mRoute);

            //lvRoutes.setAdapter(aa);
            lvRoutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Fragment newFragment = new ListFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.container, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
            });
        }
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_route_list, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.lv_routelist);

        aa = new RouteAdapter(getActivity(), mRoute);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(this);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        return rootView;
    }



    @Override
    public void onStart() {
        super.onStart();
        /*
        lvRoutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ListFragment.class);

                intent.putExtra("text", lvRoutes.getItemAtPosition((int) id).toString());
                startActivity(intent);
            }
        });
        //aa.addAll(StopParser.getInstance().getmStopList());
        //aa.addAll(CalendarParser.getInstance().getmCalendarList());

        aa.notifyDataSetChanged();
        */
    }

    public void addAll() {
        mRoute = RouteParser.getInstance().getmRouteList();

        aa.addAllRoutes(mRoute);
        aa.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Route r = (Route) parent.getItemAtPosition(position);

        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.container, ListFragment.newInstance(r))
                .addToBackStack(FRAGMENT_BACKSTACK)
                .commit();

        Toast.makeText(getActivity(), "List for Stops", Toast.LENGTH_SHORT).show();
    }
}

package ehb.be.eindprojectmivbopendata.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.parsers.RouteParser;
import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.source.Route;
import ehb.be.eindprojectmivbopendata.util.RouteAdapter;

/**
 * Created by mobapp10 on 15/05/17.
 */

public class RouteListFragment extends Fragment {
    private SharedPreferences sharedPreferences;

    ArrayList<Route> mRoute = new ArrayList<>();
    RouteAdapter aa;

    public RouteListFragment() {
    }

    public static RouteListFragment newInstance() {
        RouteListFragment fragment = new RouteListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_route_list, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.lv_routelist);

        aa = new RouteAdapter(getActivity(), mRoute);

        lv.setAdapter(aa);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //aa.addAll(StopParser.getInstance().getmStopList());
        //aa.addAll(CalendarParser.getInstance().getmCalendarList());

        aa.notifyDataSetChanged();
    }

    public void addAll() {
        mRoute = RouteParser.getInstance().getmRouteList();

        aa.addAll(mRoute);
        aa.notifyDataSetChanged();
    }
}

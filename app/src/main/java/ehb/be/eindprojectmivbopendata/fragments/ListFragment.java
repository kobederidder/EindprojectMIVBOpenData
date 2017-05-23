package ehb.be.eindprojectmivbopendata.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.source.Route;
import ehb.be.eindprojectmivbopendata.source.Stop;
import ehb.be.eindprojectmivbopendata.util.DatabaseDAO;
import ehb.be.eindprojectmivbopendata.util.StopAdapter;


/**
 * Created by MVH on 11-5-2017.
 */

public class ListFragment extends Fragment {
    private SharedPreferences sharedPreferences;

    DatabaseDAO dao;
    ArrayList<Stop> mStop = new ArrayList<>();
    StopAdapter sa;

    public ListFragment() {

    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    public static ListFragment newInstance(Route r) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable("route", r);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.lv_list);

        dao = new DatabaseDAO(getActivity());

        // Route r = (Route) getArguments().getSerializable("route");

        sa = new StopAdapter(getActivity(), dao.getDistinctStopNames());
        lv.setAdapter(sa);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        addAllStops();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // aa.addAll(CalendarParser.getInstance().getmCalendarList());
        sa.notifyDataSetChanged();
    }

    public void addAllStops() {
        mStop = dao.getDistinctStopNames();

        sa.addAllStops(mStop);
        sa.notifyDataSetChanged();
    }
}

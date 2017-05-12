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
import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.source.Stop;


/**
 * Created by MVH on 11-5-2017.
 */

public class ListFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    ArrayList<Stop> mStop = new ArrayList<>();
    ArrayAdapter<Stop> aa;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.lv_list);

        aa = new ArrayAdapter<Stop>(getActivity(), android.R.layout.simple_list_item_1,mStop);

        lv.setAdapter(aa);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        aa.addAll(StopParser.getInstance().getmStopList());
        // aa.addAll(CalendarParser.getInstance().getmCalendarList());
        aa.notifyDataSetChanged();
    }
}

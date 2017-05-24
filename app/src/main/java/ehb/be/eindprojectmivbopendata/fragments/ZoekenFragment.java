package ehb.be.eindprojectmivbopendata.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.source.Stop;
import ehb.be.eindprojectmivbopendata.util.DatabaseDAO;

/**
  Created by mobapp06 on 16/05/17.
 */


public class ZoekenFragment extends Fragment {

    private Calendar mCalendar = new GregorianCalendar();

    private AutoCompleteTextView actvBestemming, actvVertrek;
    private Button btnZoeken, btnDatum, btnUur;

    private String datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private String uur = new SimpleDateFormat("hh:mm").format(new Date());
    //TO DO - UUR FORMATEREN NAAR EUROPESE WEERGAVE (iets met functie 'LOCALE' ? )

    private final String FRAGMENT_BACKSTACK = "fragment_backstack";

    ArrayList<Stop> haltes;
    DatabaseDAO dao;

    public ZoekenFragment() {
    }

    public static ZoekenFragment newInstance() {
        ZoekenFragment fragment = new ZoekenFragment();
        return fragment;
    }

    public void geefVertrekHalte() {
        dao = new DatabaseDAO(getActivity());
        haltes = dao.getDistinctStopNames();
        Log.d("AUTOCOMPLETE", String.valueOf(haltes));

        Log.d("AUTOCOMPLETE", "build autocomplete");
        ArrayList<String> halteNamen = new ArrayList<>();
        for (Stop temp : haltes) {
            halteNamen.add(temp.getStop_name().replace("\"", ""));
            Log.d("AUTOCOMPLETE", temp.getStop_name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.select_dialog_item, halteNamen);
        actvVertrek.setAdapter(adapter);
        actvVertrek.setThreshold(1);
        //actvVertrek.enoughToFilter();
        //actvVertrek.getDropDownAnchor();


        //Stop temp = haltes.get(position);

        //actvVertrek.getText(temp.getStop_name().replace("\"", ""));
    }

    public void geefAankomstHalte() {
        dao = new DatabaseDAO(getActivity());
        haltes = dao.getDistinctStopNames();
        Log.d("AUTOCOMPLETE", String.valueOf(haltes));

        Log.d("AUTOCOMPLETE", "build autocomplete");
        ArrayList<String> halteNamen = new ArrayList<>();
        for (Stop temp : haltes) {
            halteNamen.add(temp.getStop_name().replace("\"", ""));
            Log.d("AUTOCOMPLETE", temp.getStop_name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.select_dialog_item, halteNamen);
        actvBestemming.setAdapter(adapter);
        actvBestemming.setThreshold(1);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);

        actvBestemming = (AutoCompleteTextView) rootView.findViewById(R.id.et_bestemming);
        actvVertrek = (AutoCompleteTextView) rootView.findViewById(R.id.et_vertrek);

        btnDatum = (Button) rootView.findViewById(R.id.btn_datum);
        btnDatum.setText(datum);
        btnDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatePicker();
            }
        });

        btnUur = (Button) rootView.findViewById(R.id.btn_uur);
        btnUur.setText(uur);
        btnUur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTimePicker();
            }
        });

        btnZoeken = (Button) rootView.findViewById(R.id.btn_zoeken);

        btnZoeken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.container, RouteListFragment.newInstance())
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
            }
        });

        geefVertrekHalte();
        geefAankomstHalte();

        return rootView;
    }

    private void createTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);

                btnUur.setText(hourToString(mCalendar));
            }
        };

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), onTimeSetListener
                , mCalendar.get(Calendar.HOUR_OF_DAY)
                , mCalendar.get(Calendar.MINUTE),
                true);

        //dialoogvenster weergeven
        tpd.show();
    }

    private void createDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, day);

                btnDatum.setText(calendarToString(mCalendar));
            }
        };
        //aanmaken dialoogvenster (popup) met datepicker
        //waar weergeeven? this -> deze activity zelf
        //verwijzing naar jaar, maand en dag uit mCalendar object
        DatePickerDialog dpd = new DatePickerDialog(getActivity(), onDateSetListener
                , mCalendar.get(Calendar.YEAR)
                , mCalendar.get(Calendar.MONTH)
                , mCalendar.get(Calendar.DAY_OF_MONTH));

        //dialoogvenster weergeven
        dpd.show();
    }

    private String calendarToString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(calendar.getTime());
    }

    private String hourToString(Calendar calendar) {
        SimpleDateFormat stf = new SimpleDateFormat("hh:mm");
        return stf.format(calendar.getTime());
    }

    View.OnClickListener zoeken = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            getActivity().getFragmentManager().beginTransaction()
                    .replace(R.id.container, RouteListFragment.newInstance())
                    .addToBackStack(FRAGMENT_BACKSTACK)
                    .commit();
        }
    };

}



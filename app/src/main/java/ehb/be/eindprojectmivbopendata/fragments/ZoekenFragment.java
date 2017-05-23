package ehb.be.eindprojectmivbopendata.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import java.util.Locale;
import java.util.SimpleTimeZone;

import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.source.Stop;

/**
  Created by mobapp06 on 16/05/17.
 */


public class ZoekenFragment extends Fragment{

    private Calendar mCalendar;
    private TimePicker mTimePicker;
    private AutoCompleteTextView actvBestemming, actvVertrek;
    private Button btnZoeken, btnDatumVertrek, btnUurVertrek;

    private String datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private String uur = new SimpleDateFormat("hh:mm").format(new Date());
    //TO DO - UUR FORMATEREN NAAR EUROPESE WEERGAVE (iets met functie 'LOCALE' ? )

    ArrayList<Stop> haltes = StopParser.getInstance().mStopList;

    public ZoekenFragment() {
    }

    public static ZoekenFragment newInstance() {
        ZoekenFragment fragment = new ZoekenFragment();
        return fragment;
    }

    public void geefVertrekHalte(){
        ArrayAdapter<Stop> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, haltes);
        actvVertrek.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_main, container, false);

        actvBestemming = (AutoCompleteTextView) rootView.findViewById(R.id.et_bestemming);
        actvVertrek = (AutoCompleteTextView) rootView.findViewById(R.id.et_vertrek);

        btnDatumVertrek =(Button) rootView.findViewById(R.id.btn_datum);
        btnUurVertrek = (Button) rootView.findViewById(R.id.btn_uur);
        btnZoeken = (Button) rootView.findViewById(R.id.btn_zoeken);

        btnDatumVertrek.setText(datum);
        btnDatumVertrek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatePicker();
            }
        });

        btnUurVertrek.setText(uur);
        btnUurVertrek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //createTimePicker();
            }
        });



        return rootView;
    }


    //btnZoeken - FUNCTIE OM TE ZOEKEN clickListener



    //toon calender om datum te selecteren (hiermee is invoer ook meteen beveiligd)
    private void createDatePicker(){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, day);
                btnDatumVertrek.setText(calendarToString(mCalendar));
            }
        };
        //aanmaken dialoogvenster (popup) met datepicker , verwijzing naar jaar, maand en dag uit mCalendar object
        DatePickerDialog dpd = new DatePickerDialog(getActivity(), onDateSetListener
                , mCalendar.get(Calendar.YEAR)
                , mCalendar.get(Calendar.MONTH)
                , mCalendar.get(Calendar.DAY_OF_MONTH));
        //dialoogvenster weergeven
        dpd.show();
    }
/*
    //toon timePicker om uur te selecteren (hiermee is invoer ook meteen beveiligd)
    private void createTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);
                btnUurVertrek.setText(timeToString(mCalendar));
            }
        };
        TimePickerDialog tpd = new TimePickerDialog(getActivity(), onTimeSetListener
            , mCalendar.get(Calendar.HOUR)
            , mCalendar.get(Calendar.MINUTE));

        tdp.show();
    }
*/

    private String calendarToString(Calendar calendar)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(calendar.getTime());
    }

    private String timeToString(Calendar calendar)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(calendar.getTime());
    }

}

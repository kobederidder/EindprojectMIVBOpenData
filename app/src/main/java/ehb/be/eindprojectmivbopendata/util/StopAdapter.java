package ehb.be.eindprojectmivbopendata.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.source.Stop;


/**
 * Created by mobapp10 on 18/05/17.
 */

public class StopAdapter  extends BaseAdapter {

    private ArrayList<Stop> stopArrayList;
    private static LayoutInflater inflater;
    ViewHolder viewHolder;

    public void addAllStops(ArrayList<Stop> mStop) {
        this.stopArrayList = mStop;
    }

    public static class ViewHolder {
        TextView tvStopName;
    }

    public StopAdapter(Activity activity, ArrayList<Stop> stopArrayList) {
        super();
        this.stopArrayList = stopArrayList;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return stopArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return stopArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            convertView = inflater.inflate(R.layout.row_stop, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvStopName = (TextView) convertView.findViewById(R.id.tv_stop);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Stop temp = stopArrayList.get(position);
        viewHolder.tvStopName.setText(temp.getStop_name().replace("\"", ""));

        return convertView;
    }
}

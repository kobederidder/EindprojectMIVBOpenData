package ehb.be.eindprojectmivbopendata.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.source.Route;

/**
 * Created by mobapp10 on 15/05/17.
 */

public class RouteAdapter extends BaseAdapter {

    private ArrayList<Route> listVoorAdapter;
    private static LayoutInflater inflater;
    private ViewHolder viewHolder;
    public void addAll(ArrayList<Route> mRoute) {
        listVoorAdapter = mRoute;
    }


    public static class ViewHolder {
        TextView tvRouteID;
        TextView tvRouteDirections;
    }

    public RouteAdapter(Activity activity, ArrayList<Route> listVoorAdapter) {
        super();
        this.listVoorAdapter = listVoorAdapter;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return listVoorAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return listVoorAdapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            convertView = inflater.inflate(R.layout.row_route, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvRouteID = (TextView) convertView.findViewById(R.id.tv_route_id);
            viewHolder.tvRouteDirections = (TextView) convertView.findViewById(R.id.tv_route_directions);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

            Route temp = listVoorAdapter.get(position);
            viewHolder.tvRouteID.setText(temp.getRoute_short_name());
            try {
                viewHolder.tvRouteID.setBackgroundColor(Color.parseColor("#" + temp.getRoute_color()));
                viewHolder.tvRouteID.setTextColor(Color.parseColor("#"+temp.getRoute_text_color()));
            } catch (NumberFormatException ex) {
                viewHolder.tvRouteID.setBackgroundColor(Color.parseColor("#AAAAAA"));
                viewHolder.tvRouteID.setTextColor(Color.parseColor("#000000"));
            }
            viewHolder.tvRouteDirections.setText(temp.getRoute_long_name().replace("\"", ""));

        return convertView;
    }


}

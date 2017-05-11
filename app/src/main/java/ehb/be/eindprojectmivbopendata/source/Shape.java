package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Shape {
    String shape_id, shape_pt_lat, shape_pt_lon, shape_pt_sequence;

    public Shape(String string) {
        String[] temp = string.split(",");

        this.shape_id = temp[0];
        this.shape_pt_lat = temp[1];
        this.shape_pt_lon = temp[2];
        this.shape_pt_sequence = temp[3];
    }


    public String getShape_id() {
        return shape_id;
    }

    public void setShape_id(String shape_id) {
        this.shape_id = shape_id;
    }

    public String getShape_pt_lat() {
        return shape_pt_lat;
    }

    public void setShape_pt_lat(String shape_pt_lat) {
        this.shape_pt_lat = shape_pt_lat;
    }

    public String getShape_pt_lon() {
        return shape_pt_lon;
    }

    public void setShape_pt_lon(String shape_pt_lon) {
        this.shape_pt_lon = shape_pt_lon;
    }

    public String getShape_pt_sequence() {
        return shape_pt_sequence;
    }

    public void setShape_pt_sequence(String shape_pt_sequence) {
        this.shape_pt_sequence = shape_pt_sequence;
    }


}

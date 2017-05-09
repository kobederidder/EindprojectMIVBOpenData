package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Shape {
    String shape_id, shape_pt_lat, shape_pt_lon, shape_pt_sequence, shape_dist_traveled;

    public Shape(String string) {
        String[] temp = string.split(",");

        this.shape_id = temp[0];
        this.shape_pt_lat = temp[1];
        this.shape_pt_lon = temp[2];
        this.shape_pt_sequence = temp[3];
        this.shape_dist_traveled = temp[4];
    }
}

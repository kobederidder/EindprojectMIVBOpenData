package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Stoptime {
    String trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_traveled;

    public Stoptime(String string) {
        String[] temp = string.split(",");

        this.trip_id = temp[0];
        this.arrival_time = temp[1];
        this.departure_time = temp[2];
        this.stop_id = temp[3];
        this.stop_sequence = temp[4];
        this.stop_headsign = temp[5];
        this.pickup_type = temp[6];
        this.drop_off_type = temp[7];
        this.shape_dist_traveled = temp[8];
    }
}

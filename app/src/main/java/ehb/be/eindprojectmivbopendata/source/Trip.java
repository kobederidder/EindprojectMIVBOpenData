package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Trip {
    String route_id, service_id, trip_id, trip_headsign, trip_short_name, direction_id, block_id, shape_id;

    public Trip(String string) {
        String[] temp = string.split(",");

        this.route_id = temp[0];
        this.service_id = temp[1];
        this.trip_id = temp[2];
        this.trip_headsign = temp[3];
        this.trip_short_name = temp[4];
        this.direction_id = temp[5];
        this.block_id = temp[6];
        this.shape_id = temp[7];
    }
}

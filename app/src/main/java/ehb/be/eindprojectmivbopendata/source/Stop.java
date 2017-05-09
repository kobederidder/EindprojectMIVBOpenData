package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Stop {
    String stop_id, stop_code, stop_name, stop_desc,stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station;

    public Stop(String string) {
        String[] temp = string.split(",");

        this.stop_id = temp[0];
        this.stop_code = temp[1];
        this.stop_name = temp[2];
        this.stop_desc = temp[3];
        this.stop_lat = temp[4];
        this.stop_lon = temp[5];
        this.zone_id = temp[6];
        this.stop_url = temp[7];
        this.location_type = temp[8];
        this.parent_station = temp[9];
    }
}

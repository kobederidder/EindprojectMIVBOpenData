package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Stop {
    String stop_id, stop_code, stop_name, stop_desc,stop_lat, stop_lon, zone_id, stop_url, location_type;

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
    }


    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_code() {
        return stop_code;
    }

    public void setStop_code(String stop_code) {
        this.stop_code = stop_code;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_desc() {
        return stop_desc;
    }

    public void setStop_desc(String stop_desc) {
        this.stop_desc = stop_desc;
    }

    public String getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(String stop_lat) {
        this.stop_lat = stop_lat;
    }

    public String getStop_lon() {
        return stop_lon;
    }

    public void setStop_lon(String stop_lon) {
        this.stop_lon = stop_lon;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getStop_url() {
        return stop_url;
    }

    public void setStop_url(String stop_url) {
        this.stop_url = stop_url;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

}

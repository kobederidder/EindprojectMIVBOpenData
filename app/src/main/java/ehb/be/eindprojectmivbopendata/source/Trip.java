package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

//Commentaar in trip

public class Trip {
    String route_id, service_id, trip_id, trip_headsign, direction_id, block_id, shape_id;

    public Trip(String string) {
        String[] temp = string.split(",");

        this.route_id = temp[0];
        this.service_id = temp[1];
        this.trip_id = temp[2];
        this.trip_headsign = temp[3];
        this.direction_id = temp[4];
        this.block_id = temp[5];
        this.shape_id = temp[6];
    }


    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getShape_id() {
        return shape_id;
    }

    public void setShape_id(String shape_id) {
        this.shape_id = shape_id;
    }
}

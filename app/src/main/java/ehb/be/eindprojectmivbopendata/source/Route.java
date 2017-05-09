package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Route {
    String route_id, agency_id, route_short_name, route_long_name, route_desc, route_type, route_url, route_color, route_text_color;

    public Route(String string) {
        String[] temp = string.split(",");

        this.route_id = temp[0];
        this.agency_id = temp[1];
        this.route_short_name = temp[2];
        this.route_long_name = temp[3];
        this.route_desc = temp[4];
        this.route_type = temp[5];
        this.route_url = temp[6];
        this.route_color = temp[7];
        this.route_text_color = temp[8];
    }
}

package ehb.be.eindprojectmivbopendata.source;

import java.io.Serializable;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Route implements Serializable {
    String route_id, route_short_name, route_long_name, route_desc, route_type, route_url, route_color, route_text_color;

    public Route(String string) {
        String[] temp = string.split(",");

        this.route_id = temp[0];
        this.route_short_name = temp[1];
        this.route_long_name = temp[2];
        this.route_desc = temp[3];
        this.route_type = temp[4];
        this.route_url = temp[5];
        this.route_color = temp[6];
        this.route_text_color = temp[7];
    }

    public Route() {
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }


    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public String getRoute_desc() {
        return route_desc;
    }

    public void setRoute_desc(String route_desc) {
        this.route_desc = route_desc;
    }

    public String getRoute_type() {
        return route_type;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }

    public String getRoute_url() {
        return route_url;
    }

    public void setRoute_url(String route_url) {
        this.route_url = route_url;
    }

    public String getRoute_color() {
        return route_color;
    }

    public void setRoute_color(String route_color) {
        this.route_color = route_color;
    }

    public String getRoute_text_color() {
        return route_text_color;
    }

    public void setRoute_text_color(String route_text_color) {
        this.route_text_color = route_text_color;
    }
}

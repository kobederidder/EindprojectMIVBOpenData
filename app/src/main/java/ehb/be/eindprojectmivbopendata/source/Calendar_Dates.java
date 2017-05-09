package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Calendar_Dates {
    String service_id, date, exception_type;

    public Calendar_Dates(String string) {
        String[] temp = string.split(",");

        this.service_id = temp[0];
        this.date = temp[1];
        this.exception_type = temp[2];
    }
}

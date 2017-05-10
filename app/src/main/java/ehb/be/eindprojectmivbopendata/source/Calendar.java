package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 09/05/17.
 */

public class Calendar {
    String service_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday, start_date, end_date;

    public Calendar(String string) {
        String[] temp = string.split(",");

        this.service_id = temp[0];
        this.monday = temp[1];
        this.tuesday = temp[2];
        this.wednesday = temp[3];
        this.thursday = temp[4];
        this.friday = temp[5];
        this.saturday = temp[6];
        this.sunday = temp[7];
        this.start_date = temp[8];
        this.end_date = temp[9];
    }

        //COMMENTAAR ALS TEST GITHUB

}

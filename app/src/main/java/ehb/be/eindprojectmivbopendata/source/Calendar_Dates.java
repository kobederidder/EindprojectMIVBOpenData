package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp02, mobapp10 on 09/05/17.
 */

public class Calendar_Dates {
    String service_id, date, exception_type;

    public Calendar_Dates(String string) {
        String[] temp = string.split(",");

        this.service_id = temp[0];
        this.date = temp[1];
        this.exception_type = temp[2];
    }



    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getException_type() {
        return exception_type;
    }

    public void setException_type(String exception_type) {
        this.exception_type = exception_type;
    }
}

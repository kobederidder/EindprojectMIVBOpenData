package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp02, mobapp10 on 09/05/17.
 */

//Testjeu op 10/5/2017

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


    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}

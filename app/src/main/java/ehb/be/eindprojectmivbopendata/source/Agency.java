package ehb.be.eindprojectmivbopendata.source;

/**
 * Created by mobapp10 on 11/05/17.
 */

public class Agency {

    public String agency_name, agency_url, agency_timezone, agency_lang, agency_phone;

    public Agency(String str) {
        String[] temp = str.split(",");

        this.agency_name = temp[0];
        this.agency_url = temp[1];
        this.agency_timezone = temp[2];
        this.agency_lang = temp[3];
        this.agency_phone = temp[4];
    }
}

package es.urjc.mov.sibanez.fichas.BBDD;

public class CardsActivitys {
    private String ID;
    private String NAME;
    private String DESCRIPTION;
    private String LEVEL;
    private String LATITUDE;
    private String LONGITUDE;
    private String CITY;
    private String ZONE;
    private String TIME;
    private String DISTANCE;
    private String PHOTO;

    public CardsActivitys(String ID, String NAME, String DESCRIPTION, String LEVEL,
                          String LATITUDE, String LONGITUDE, String CITY, String ZONE,
                          String TIME, String DISTANCE, String PHOTO) {
        this.ID = ID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.LEVEL = LEVEL;
        this.LATITUDE = LATITUDE;
        this.LONGITUDE = LONGITUDE;
        this.CITY = CITY;
        this.ZONE = ZONE;
        this.TIME = TIME;
        this.DISTANCE = DISTANCE;
        this.PHOTO = PHOTO;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(String LEVEL) {
        this.LEVEL = LEVEL;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDISTANCE() {
        return DISTANCE;
    }

    public void setDISTANCE(String DISTANCE) {
        this.DISTANCE = DISTANCE;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }
}

package es.urjc.mov.sibanez.fichas.BBDD;

public class DescrActivity {
    private String ID;
    private String NAME;
    private String DESCRIPTION;
    private String PHOTO;

    public DescrActivity(String ID, String NAME, String DESCRIPTION, String PHOTO) {
        this.ID = ID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
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

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }
}

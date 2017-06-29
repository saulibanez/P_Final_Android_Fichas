package es.urjc.mov.sibanez.fichas.BBDD;

public class Activitys {
    private String _id;
    private String escalada;
    private String barranquismo;
    private String rapel;
    private String raq_nieve;
    private String vias_ferratas;

    public Activitys(String _id, String escalada, String barranquismo, String rapel, String raq_nieve, String vias_ferratas) {
        this._id = _id;
        this.escalada = escalada;
        this.barranquismo = barranquismo;
        this.rapel = rapel;
        this.raq_nieve = raq_nieve;
        this.vias_ferratas = vias_ferratas;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEscalada() {
        return escalada;
    }

    public void setEscalada(String escalada) {
        this.escalada = escalada;
    }

    public String getBarranquismo() {
        return barranquismo;
    }

    public void setBarranquismo(String barranquismo) {
        this.barranquismo = barranquismo;
    }

    public String getRapel() {
        return rapel;
    }

    public void setRapel(String rapel) {
        this.rapel = rapel;
    }

    public String getRaq_nieve() {
        return raq_nieve;
    }

    public void setRaq_nieve(String raq_nieve) {
        this.raq_nieve = raq_nieve;
    }

    public String getVias_ferratas() {
        return vias_ferratas;
    }

    public void setVias_ferratas(String vias_ferratas) {
        this.vias_ferratas = vias_ferratas;
    }
}

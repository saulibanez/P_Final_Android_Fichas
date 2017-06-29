package es.urjc.mov.sibanez.fichas.BBDD;

public class Photos {
    private String id;
    private String photos;

    public Photos(String id, String photos) {
        this.id = id;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}

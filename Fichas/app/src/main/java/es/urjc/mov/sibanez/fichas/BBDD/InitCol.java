package es.urjc.mov.sibanez.fichas.BBDD;

import java.util.UUID;

/**
 * Clase que establece los nombres a usar en la base de datos
 */
public class InitCol {

    interface ColHeaderActivity {
        String ID = "_id";
        String ESCALADA = "id_escalada";
        String BARRANQUISMO = "id_barranquismo";
        String RAPEL = "id_rapel";
        String RAQUETA_DE_NIEVE = "id_raqueta_de_nieve";
        String VIAS_FERRATAS = "id_vias_ferratas";
    }

    interface ColHeaderEscalada {
        String ID = "_id";
        String NAME = "id_escalada";
        String DESCRIPTION = "id_descr_escalada";
        String LEVEL = "id_level_escalada";
        String LATITUDE = "id_latitude_escalada";
        String LONGITUDE = "id_longitude_escalada";
        String CITY = "id_city_escalada";
        String ZONE = "id_zone_escalada";
        String TIME = "id_time_escalada";
        String DISTANCE = "id_distance_escalada";
        String PHOTO = "id_photo_escalada";
    }

    interface ColDescrEscalada {
        String ID = "_id";
        String NAME = "id_name_escalada";
        String DESCRIPTION = "id_descr_escalada";
        String PHOTO = "id_photo_escalada";
    }

    interface ColPhotoEscalada {
        String ID = "_id";
        String PHOTO = "id_photo_escalada";
    }

    interface ColHeaderBarranquismo {
        String ID = "_id";
        String NAME = "id_barranquismo";
        String DESCRIPTION = "id_descr_barranquismo";
        String LEVEL = "id_level_barranquismo";
        String LATITUDE = "id_latitude_barranquismo";
        String LONGITUDE = "id_longitude_barranquismo";
        String CITY = "id_city_barranquismo";
        String ZONE = "id_zone_barranquismo";
        String TIME = "id_time_barranquismo";
        String DISTANCE = "id_distance_barranquismo";
        String PHOTO = "id_photo_barranquismo";
    }

    interface ColDescrBarranquismo {
        String ID = "_id";
        String NAME = "id_name_barranquismo";
        String DESCRIPTION = "id_descr_barranquismo";
        String PHOTO = "id_photo_barranquismo";
    }

    interface ColPhotoBarranquismo {
        String ID = "_id";
        String PHOTO = "id_photo_barranquismo";
    }

    interface ColHeaderRapel {
        String ID = "_id";
        String NAME = "id_rapel";
        String DESCRIPTION = "id_descr_rapel";
        String LEVEL = "id_level_rapel";
        String LATITUDE = "id_latitude_rapel";
        String LONGITUDE = "id_longitude_rapel";
        String CITY = "id_city_rapel";
        String ZONE = "id_zone_rapel";
        String TIME = "id_time_rapel";
        String DISTANCE = "id_distance_rapel";
        String PHOTO = "id_photo_rapel";
    }

    interface ColDescrRapel {
        String ID = "_id";
        String NAME = "id_name_rapel";
        String DESCRIPTION = "id_descr_rapel";
        String PHOTO = "id_photo_rapel";
    }

    interface ColPhotoRapel {
        String ID = "_id";
        String PHOTO = "id_photo_rapel";
    }

    interface ColHeaderRaqNieve {
        String ID = "_id";
        String NAME = "id_raquetas_de_nieve";
        String DESCRIPTION = "id_descr_raquetas_de_nieve";
        String LEVEL = "id_level_raquetas_de_nieve";
        String LATITUDE = "id_latitude_raquetas_de_niev";
        String LONGITUDE = "id_longitude_raquetas_de_niev";
        String CITY = "id_city_raquetas_de_niev";
        String ZONE = "id_zone_raquetas_de_niev";
        String TIME = "id_time_raquetas_de_niev";
        String DISTANCE = "id_distance_raquetas_de_niev";
        String PHOTO = "id_photo_raquetas_de_niev";
    }

    interface ColDescrRaqNieve {
        String ID = "_id";
        String NAME = "id_name_raquetas_de_nieve";
        String DESCRIPTION = "id_descr_raquetas_de_nieve";
        String PHOTO = "id_photo_raquetas_de_nieve";
    }

    interface ColPhotoRaqNieve {
        String ID = "_id";
        String PHOTO = "id_photo_raquetas_de_nieve";
    }

    interface ColHeaderViasFerratas {
        String ID = "_id";
        String NAME = "id_vias_ferratas";
        String DESCRIPTION = "id_descr_vias_ferratas";
        String LEVEL = "id_level_vias_ferratas";
        String LATITUDE = "id_latitude_vias_ferratas";
        String LONGITUDE = "id_longitude_vias_ferratas";
        String CITY = "id_city_vias_ferratas";
        String ZONE = "id_zone_vias_ferratas";
        String TIME = "id_time_vias_ferratas";
        String DISTANCE = "id_distance_vias_ferratas";
        String PHOTO = "id_photo_vias_ferratas";
    }

    interface ColDescrViasFerratas {
        String ID = "_id";
        String NAME = "id_name_vias_ferratas";
        String DESCRIPTION = "id_descr_vias_ferratas";
        String PHOTO = "id_photo_vias_ferratas";
    }

    interface ColPhotoViasFerratas {
        String ID = "_id";
        String PHOTO = "id_photo_vias_ferratas";
    }

    public static class HeaderActivity implements ColHeaderActivity {
        public static String geneteIdHeaderActivity() {
            return "CHA-" + UUID.randomUUID().toString();
        }
    }

    public static class HeaderEscalada implements ColHeaderEscalada {
        public static String geneteIdHeaderEscalada() {
            return "CHE-" + UUID.randomUUID().toString();
        }
    }

    public static class DescrEscalada implements ColDescrEscalada {
        public static String geneteIdDescrEscalada() {
            return "CDE-" + UUID.randomUUID().toString();
        }
    }

    public static class HeaderBarranquismo implements ColHeaderBarranquismo {
        public static String geneteIdHeaderLevelBarranquismo() {
            return "CHB-" + UUID.randomUUID().toString();
        }
    }

    public static class DescrBarranquismo implements ColDescrBarranquismo {
        public static String geneteIdBarranquismo() {
            return "CDB-" + UUID.randomUUID().toString();
        }
    }

    public static class HeaderRapel implements ColHeaderRapel {
        public static String geneteIdHeaderRapel() {
            return "CHR-" + UUID.randomUUID().toString();
        }
    }

    public static class DescrRapel implements ColDescrRapel {
        public static String geneteIdRapel() {
            return "CDR-" + UUID.randomUUID().toString();
        }
    }

    public static class HeaderRaqNieve implements ColHeaderRaqNieve {
        public static String geneteIdHeaderRaqNieve() {
            return "CHRN-" + UUID.randomUUID().toString();
        }
    }

    public static class DescrRaqNieve implements ColDescrRaqNieve {
        public static String geneteIdRaqNieve() {
            return "CDRN-" + UUID.randomUUID().toString();
        }
    }

    public static class HeaderViasFerratas implements ColHeaderViasFerratas {
        public static String geneteIdHeaderViasFerratas() {
            return "CHVF-" + UUID.randomUUID().toString();
        }
    }

    public static class DescrViasFerratas implements ColDescrViasFerratas {
        public static String geneteIdViasFerratas() {
            return "CDVF-" + UUID.randomUUID().toString();
        }
    }

    public static class PhotoEscalada implements ColPhotoEscalada {
        public static String generateIdPhotoEscalada(){
            return "CPE-" +  UUID.randomUUID().toString();
        }
    }

    public static class PhotoBarranquismo implements ColPhotoBarranquismo {
        public static String generateIdPhotoBarranquismo(){
            return "CPB-" +  UUID.randomUUID().toString();
        }
    }

    public static class PhotoRapel implements ColPhotoRapel {
        public static String generateIdPhotoRapel(){
            return "CPR-" +  UUID.randomUUID().toString();
        }
    }

    public static class PhotoRaqNieve implements ColPhotoRaqNieve {
        public static String generateIdPhotoRaqNieve(){
            return "CPRN-" +  UUID.randomUUID().toString();
        }
    }

    public static class PhotoViasFerratas implements ColPhotoViasFerratas {
        public static String generateIdPhotoViasFerratas(){
            return "CPVF-" +  UUID.randomUUID().toString();
        }
    }

    public InitCol() {
    }
}

package es.urjc.mov.sibanez.fichas;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import es.urjc.mov.sibanez.fichas.BBDD.BBDDCards;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TestBBDD {

    @Test
    public void bbddTest() throws Exception {
        Context ctx = InstrumentationRegistry.getTargetContext();
        BBDDCards bbdd = new BBDDCards(ctx.getApplicationContext());

        bbdd.insertActivitys("Escalada", "Barranquismo", "Rápel", "Raquetas de Nieve", "Vías Ferratas");
        int tamanio_t_activity = bbdd.getActivitys().size();
        System.out.println("tamaño de la tabla t_activity: " + tamanio_t_activity);

        assertTrue(bbdd.getActivitys().get(0).getEscalada().equals("Escalada"));
        assertTrue(bbdd.getActivitys().get(0).getBarranquismo().equals("Barranquismo"));
        assertTrue(bbdd.getActivitys().get(0).getRapel().equals("Rápel"));
        assertTrue(bbdd.getActivitys().get(0).getRaq_nieve().equals("Raquetas de Nieve"));
        assertTrue(bbdd.getActivitys().get(0).getVias_ferratas().equals("Vías Ferratas"));

        //------------------------------------------------------------------------------------------

        bbdd.insertCardActivity("Escalada", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa");
        bbdd.insertCardActivity("Escalada", "bb", "bb", "bb", "bb", "bb", "bb", "bb", "bb", "bb");
        bbdd.insertCardActivity("Escalada", "cc", "cc", "cc", "cc", "cc", "cc", "cc", "cc", "cc");
        int tamanio_t_escalada = bbdd.getCards("Escalada").size();
        assertTrue(bbdd.getCards("Escalada").get(0).getCITY().equals("aa"));
        assertTrue(bbdd.getCards("Escalada").get(2).getLEVEL().equals("cc"));

        bbdd.insertCardActivity("Barranquismo", "dd", "dd", "dd", "dd", "dd", "dd", "dd", "dd", "dd");
        bbdd.insertCardActivity("Barranquismo", "ee", "ee", "ee", "ee", "ee", "ee", "ee", "ee", "ee");
        bbdd.insertCardActivity("Barranquismo", "ff", "ff", "ff", "ff", "ff", "ff", "ff", "ff", "ff");
        int tamanio_t_barranquismo = bbdd.getCards("Barranquismo").size();
        assertTrue(bbdd.getCards("Barranquismo").get(0).getDISTANCE().equals("dd"));
        assertTrue(bbdd.getCards("Barranquismo").get(2).getLONGITUDE().equals("ff"));

        bbdd.insertCardActivity("Rápel", "gg", "gg", "gg", "gg", "gg", "gg", "gg", "gg", "gg");
        bbdd.insertCardActivity("Rápel", "hh", "hh", "hh", "hh", "hh", "hh", "hh", "hh", "hh");
        int tamanio_t_rapel = bbdd.getCards("Rápel").size();
        assertTrue(bbdd.getCards("Rápel").get(0).getLATITUDE().equals("gg"));
        assertTrue(bbdd.getCards("Rápel").get(1).getZONE().equals("hh"));

        bbdd.insertCardActivity("Raquetas de Nieve", "ii", "ii", "ii", "ii", "ii", "ii", "ii", "ii", "ii");
        bbdd.insertCardActivity("Raquetas de Nieve", "jj", "jj", "jj", "jj", "jj", "jj", "jj", "jj", "jj");
        int tamanio_t_raq_nieve = bbdd.getCards("Raquetas de Nieve").size();
        assertTrue(bbdd.getCards("Raquetas de Nieve").get(0).getLATITUDE().equals("ii"));
        assertTrue(bbdd.getCards("Raquetas de Nieve").get(1).getZONE().equals("jj"));

        bbdd.insertCardActivity("Vías Ferratas", "ii", "ii", "ii", "ii", "ii", "ii", "ii", "ii", "ii");
        bbdd.insertCardActivity("Vías Ferratas", "jj", "jj", "jj", "jj", "jj", "jj", "jj", "jj", "jj");
        int tamanio_t_via_ferrata = bbdd.getCards("Vías Ferratas").size();
        assertTrue(bbdd.getCards("Vías Ferratas").get(0).getLATITUDE().equals("ii"));
        assertTrue(bbdd.getCards("Vías Ferratas").get(1).getZONE().equals("jj"));

        //------------------------------------------------------------------------------------------

        bbdd.insertDescrActivity("Escalada", "kk","kk");
        bbdd.insertDescrActivity("Escalada", "ll","ll");
        bbdd.insertDescrActivity("Escalada", "mm","mm");
        bbdd.insertDescrActivity("Escalada", "nn","nn");
        int tamanio_t_desc_escalada = bbdd.getDescrActivity("Escalada").size();
        assertTrue(bbdd.getDescrActivity("Escalada").get(0).getDESCRIPTION().equals("kk"));
        assertTrue(bbdd.getDescrActivity("Escalada").get(3).getDESCRIPTION().equals("nn"));

        bbdd.insertDescrActivity("Barranquismo", "kk","kk");
        bbdd.insertDescrActivity("Barranquismo", "ll","ll");
        bbdd.insertDescrActivity("Barranquismo", "mm","mm");
        bbdd.insertDescrActivity("Barranquismo", "nn","nn");
        int tamanio_t_desc_barranquismo = bbdd.getDescrActivity("Barranquismo").size();
        assertTrue(bbdd.getDescrActivity("Barranquismo").get(1).getDESCRIPTION().equals("ll"));
        assertTrue(bbdd.getDescrActivity("Barranquismo").get(3).getDESCRIPTION().equals("nn"));

        bbdd.insertDescrActivity("Rápel", "kk","kk");
        bbdd.insertDescrActivity("Rápel", "ll","ll");
        bbdd.insertDescrActivity("Rápel", "mm","mm");
        bbdd.insertDescrActivity("Rápel", "nn","nn");
        int tamanio_t_desc_rapel = bbdd.getDescrActivity("Rápel").size();
        assertTrue(bbdd.getDescrActivity("Rápel").get(1).getDESCRIPTION().equals("ll"));
        assertTrue(bbdd.getDescrActivity("Rápel").get(2).getDESCRIPTION().equals("mm"));

        bbdd.insertDescrActivity("Raquetas de Nieve", "kk","kk");
        bbdd.insertDescrActivity("Raquetas de Nieve", "ll","ll");
        bbdd.insertDescrActivity("Raquetas de Nieve", "mm","mm");
        bbdd.insertDescrActivity("Raquetas de Nieve", "nn","nn");
        int tamanio_t_desc_raq_nieve = bbdd.getDescrActivity("Raquetas de Nieve").size();
        assertTrue(bbdd.getDescrActivity("Raquetas de Nieve").get(2).getDESCRIPTION().equals("mm"));
        assertTrue(bbdd.getDescrActivity("Raquetas de Nieve").get(3).getDESCRIPTION().equals("nn"));

        bbdd.insertDescrActivity("Vías Ferratas", "kk","kk");
        bbdd.insertDescrActivity("Vías Ferratas", "ll","ll");
        bbdd.insertDescrActivity("Vías Ferratas", "mm","mm");
        bbdd.insertDescrActivity("Vías Ferratas", "nn","nn");
        int tamanio_t_desc_via_ferrata = bbdd.getDescrActivity("Vías Ferratas").size();
        assertTrue(bbdd.getDescrActivity("Vías Ferratas").get(3).getDESCRIPTION().equals("nn"));
        assertTrue(bbdd.getDescrActivity("Vías Ferratas").get(3).getDESCRIPTION().equals("nn"));

        bbdd.insertPhotos("Escalada", "oo");
        bbdd.insertPhotos("Escalada", "pp");
        bbdd.insertPhotos("Escalada", "qq");
        int tamanio_t_photo_escalada = bbdd.getPhotos("Escalada").size();
        assertTrue(bbdd.getPhotos("Escalada").get(0).getPhotos().equals("oo"));
        assertTrue(bbdd.getPhotos("Escalada").get(1).getPhotos().equals("pp"));
        assertTrue(bbdd.getPhotos("Escalada").get(2).getPhotos().equals("qq"));

        bbdd.insertPhotos("Barranquismo", "oo");
        bbdd.insertPhotos("Barranquismo", "pp");
        bbdd.insertPhotos("Barranquismo", "qq");
        int tamanio_t_photo_barranquismo = bbdd.getPhotos("Barranquismo").size();
        assertTrue(bbdd.getPhotos("Barranquismo").get(0).getPhotos().equals("oo"));
        assertTrue(bbdd.getPhotos("Barranquismo").get(1).getPhotos().equals("pp"));
        assertTrue(bbdd.getPhotos("Barranquismo").get(2).getPhotos().equals("qq"));

        bbdd.insertPhotos("Rápel", "oo");
        bbdd.insertPhotos("Rápel", "pp");
        bbdd.insertPhotos("Rápel", "qq");
        int tamanio_t_photo_rapel = bbdd.getPhotos("Rápel").size();
        assertTrue(bbdd.getPhotos("Rápel").get(0).getPhotos().equals("oo"));
        assertTrue(bbdd.getPhotos("Rápel").get(1).getPhotos().equals("pp"));
        assertTrue(bbdd.getPhotos("Rápel").get(2).getPhotos().equals("qq"));

        bbdd.insertPhotos("Raquetas de Nieve", "oo");
        bbdd.insertPhotos("Raquetas de Nieve", "pp");
        bbdd.insertPhotos("Raquetas de Nieve", "qq");
        int tamanio_t_photo_raq_nieve = bbdd.getPhotos("Raquetas de Nieve").size();
        assertTrue(bbdd.getPhotos("Raquetas de Nieve").get(0).getPhotos().equals("oo"));
        assertTrue(bbdd.getPhotos("Raquetas de Nieve").get(1).getPhotos().equals("pp"));
        assertTrue(bbdd.getPhotos("Raquetas de Nieve").get(2).getPhotos().equals("qq"));

        bbdd.insertPhotos("Vías Ferratas", "oo");
        bbdd.insertPhotos("Vías Ferratas", "pp");
        bbdd.insertPhotos("Vías Ferratas", "qq");
        int tamanio_t_photo_vias_ferratas = bbdd.getPhotos("Vías Ferratas").size();
        assertTrue(bbdd.getPhotos("Vías Ferratas").get(0).getPhotos().equals("oo"));
        assertTrue(bbdd.getPhotos("Vías Ferratas").get(1).getPhotos().equals("pp"));
        assertTrue(bbdd.getPhotos("Vías Ferratas").get(2).getPhotos().equals("qq"));



        /*
         * La bbdd se va poniendo en la posicion 0 según voy borrando, es decir, mientras borro, el que
         * estaba en la pos 1 pasa a la pos 0, y el que estaba en la pos 2 pasa a la pos 1.
         */

        for (int i = 0; i < tamanio_t_activity; i++) {
            bbdd.deleteActivity(bbdd.getActivitys().get(0).get_id(), "t_activitys");
        }

        //------------------------------------------------------------------------------------------

        for (int i = 0; i < tamanio_t_escalada; i++) {
            bbdd.deleteActivity(bbdd.getCards("Escalada").get(0).getID(), "t_escalada");
        }

        for (int i = 0; i < tamanio_t_barranquismo; i++) {
            bbdd.deleteActivity(bbdd.getCards("Barranquismo").get(0).getID(), "t_barranquismo");
        }

        for (int i = 0; i < tamanio_t_rapel; i++) {
            bbdd.deleteActivity(bbdd.getCards("Rápel").get(0).getID(), "t_rapel");
        }

        for (int i = 0; i < tamanio_t_raq_nieve; i++) {
            bbdd.deleteActivity(bbdd.getCards("Raquetas de Nieve").get(0).getID(), "t_raq_nieve");
        }

        for (int i = 0; i < tamanio_t_via_ferrata; i++) {
            bbdd.deleteActivity(bbdd.getCards("Vías Ferratas").get(0).getID(), "t_vias_ferratas");
        }

        //------------------------------------------------------------------------------------------

        for (int i = 0; i < tamanio_t_desc_escalada; i++) {
            bbdd.deleteActivity(bbdd.getDescrActivity("Escalada").get(0).getID(), "t_descr_escalada");
        }

        for (int i = 0; i < tamanio_t_desc_barranquismo; i++) {
            bbdd.deleteActivity(bbdd.getDescrActivity("Barranquismo").get(0).getID(), "t_descr_barranquismo");
        }

        for (int i = 0; i < tamanio_t_desc_rapel; i++) {
            bbdd.deleteActivity(bbdd.getDescrActivity("Rápel").get(0).getID(), "t_descr_rapel");
        }

        for (int i = 0; i < tamanio_t_desc_raq_nieve; i++) {
            bbdd.deleteActivity(bbdd.getDescrActivity("Raquetas de Nieve").get(0).getID(), "t_descr_raq_nieve");
        }

        for (int i = 0; i < tamanio_t_desc_via_ferrata; i++) {
            bbdd.deleteActivity(bbdd.getDescrActivity("Vías Ferratas").get(0).getID(), "t_descr_vias_ferratas");
        }

        //------------------------------------------------------------------------------------------

        for (int i = 0; i < tamanio_t_photo_escalada; i++) {
            bbdd.deleteActivity(bbdd.getPhotos("Escalada").get(0).getId(), "t_photo_escalada");
        }

        for (int i = 0; i < tamanio_t_photo_barranquismo; i++) {
            bbdd.deleteActivity(bbdd.getPhotos("Barranquismo").get(0).getId(), "t_photo_barranquismo");
        }

        for (int i = 0; i < tamanio_t_photo_rapel; i++) {
            bbdd.deleteActivity(bbdd.getPhotos("Rápel").get(0).getId(), "t_photo_rapel");
        }

        for (int i = 0; i < tamanio_t_photo_raq_nieve; i++) {
            bbdd.deleteActivity(bbdd.getPhotos("Raquetas de Nieve").get(0).getId(), "t_photo_raq_nieve");
        }

        for (int i = 0; i < tamanio_t_photo_vias_ferratas; i++) {
            bbdd.deleteActivity(bbdd.getPhotos("Vías Ferratas").get(0).getId(), "t_photo_vias_ferratas");
        }
    }
}


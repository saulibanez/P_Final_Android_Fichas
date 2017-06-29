package es.urjc.mov.sibanez.fichas.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Clase que administra la conexión de la base de datos y su estructuración
 */
public class BBDDCards extends SQLiteOpenHelper {

    private static final String NAME_BBDD = "activitys.db";
    private static final int VERSION_BASEDATOS = 1;
    private static final String T_ACTIVITYS = "t_activitys";
    private static final String T_ESCALADA = "t_escalada";
    private static final String T_BARRANQUISMO = "t_barranquismo";
    private static final String T_RAPEL = "t_rapel";
    private static final String T_RAQ_NIEVE = "t_raq_nieve";
    private static final String T_VIAS_FERRATAS = "t_vias_ferratas";
    private static final String T_DESCR_ESCALADA = "t_descr_escalada";
    private static final String T_PHOTO_ESCALADA = "t_photo_escalada";
    private static final String T_DESCR_BARRANQUISMO = "t_descr_barranquismo";
    private static final String T_PHOTO_BARRANQUISMO = "t_photo_barranquismo";
    private static final String T_DESCR_RAPEL = "t_descr_rapel";
    private static final String T_PHOTO_RAPEL = "t_photo_rapel";
    private static final String T_DESCR_RAQ_NIEVE = "t_descr_raq_nieve";
    private static final String T_PHOTO_RAQ_NIEVE = "t_photo_raq_nieve";
    private static final String T_DESCR_VIAS_FERRATAS = "t_descr_vias_ferratas";
    private static final String T_PHOTO_VIAS_FERRATAS = "t_photo_vias_ferratas";

    private String[] NAME_TABLES = {T_ACTIVITYS, T_ESCALADA, T_BARRANQUISMO, T_RAPEL, T_RAQ_NIEVE,
            T_VIAS_FERRATAS, T_DESCR_ESCALADA, T_PHOTO_ESCALADA, T_DESCR_BARRANQUISMO,
            T_PHOTO_BARRANQUISMO, T_DESCR_RAPEL, T_PHOTO_RAPEL, T_DESCR_RAQ_NIEVE, T_PHOTO_RAQ_NIEVE,
            T_DESCR_VIAS_FERRATAS, T_PHOTO_VIAS_FERRATAS};

    // Sentencias SQL para crear las tablas y las FK.
    private static final String TABLE_DESCR_ESCALADA = "CREATE TABLE t_descr_escalada (" +
            InitCol.ColDescrEscalada.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColDescrEscalada.NAME + " TEXT, " +
            InitCol.ColDescrEscalada.DESCRIPTION + " TEXT, " +
            InitCol.ColDescrEscalada.PHOTO + " TEXT)";

    private static final String TABLE_PHOTO_ESCALADA = "CREATE TABLE t_photo_escalada (" +
            InitCol.ColPhotoEscalada.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColPhotoEscalada.PHOTO + " TEXT)";

    private static final String TABLE_ESCALADA = "CREATE TABLE t_escalada (" +
            InitCol.ColHeaderEscalada.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColHeaderEscalada.NAME + " TEXT, " +
            InitCol.ColHeaderEscalada.DESCRIPTION + " TEXT, " +
            InitCol.ColHeaderEscalada.LEVEL + " TEXT, " +
            InitCol.ColHeaderEscalada.LATITUDE + " TEXT, " +
            InitCol.ColHeaderEscalada.LONGITUDE + " TEXT, " +
            InitCol.ColHeaderEscalada.CITY + " TEXT, " +
            InitCol.ColHeaderEscalada.ZONE + " TEXT, " +
            InitCol.ColHeaderEscalada.TIME + " TEXT, " +
            InitCol.ColHeaderEscalada.DISTANCE + " TEXT, " +
            InitCol.ColHeaderEscalada.PHOTO + " TEXT, " +
            "FOREIGN KEY(" + InitCol.ColHeaderEscalada.NAME + ") REFERENCES t_descr_escalada(" + InitCol.ColDescrEscalada.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderEscalada.PHOTO + ") REFERENCES t_photo_escalada(" + InitCol.ColPhotoEscalada.PHOTO + "))";

    private static final String TABLE_DESCR_BARRANQUISMO = "CREATE TABLE t_descr_barranquismo (" +
            InitCol.ColDescrBarranquismo.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColDescrBarranquismo.NAME + " TEXT, " +
            InitCol.ColDescrBarranquismo.DESCRIPTION + " TEXT, " +
            InitCol.ColDescrBarranquismo.PHOTO + " TEXT)";

    private static final String TABLE_PHOTO_BARRANQUISMO = "CREATE TABLE t_photo_barranquismo (" +
            InitCol.ColPhotoBarranquismo.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColPhotoBarranquismo.PHOTO + " TEXT)";

    private static final String TABLE_BARRANQUISMO = "CREATE TABLE t_barranquismo (" +
            InitCol.ColHeaderBarranquismo.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColHeaderBarranquismo.NAME + " TEXT, " +
            InitCol.ColHeaderBarranquismo.DESCRIPTION + " TEXT, " +
            InitCol.ColHeaderBarranquismo.LEVEL + " TEXT, " +
            InitCol.ColHeaderBarranquismo.LATITUDE + " TEXT, " +
            InitCol.ColHeaderBarranquismo.LONGITUDE + " TEXT, " +
            InitCol.ColHeaderBarranquismo.CITY + " TEXT, " +
            InitCol.ColHeaderBarranquismo.ZONE + " TEXT, " +
            InitCol.ColHeaderBarranquismo.TIME + " TEXT, " +
            InitCol.ColHeaderBarranquismo.DISTANCE + " TEXT, " +
            InitCol.ColHeaderBarranquismo.PHOTO + " TEXT, " +
            "FOREIGN KEY(" + InitCol.ColHeaderBarranquismo.NAME + ") REFERENCES t_descr_barranquismo(" + InitCol.ColDescrBarranquismo.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderBarranquismo.PHOTO + ") REFERENCES t_photo_barranquismo(" + InitCol.ColPhotoBarranquismo.PHOTO + "))";

    private static final String TABLE_DESCR_RAPEL = "CREATE TABLE t_descr_rapel (" +
            InitCol.ColDescrRapel.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColDescrRapel.NAME + " TEXT, " +
            InitCol.ColDescrRapel.DESCRIPTION + " TEXT, " +
            InitCol.ColDescrRapel.PHOTO + " TEXT)";

    private static final String TABLE_PHOTO_RAPEL = "CREATE TABLE t_photo_rapel (" +
            InitCol.ColPhotoRapel.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColPhotoRapel.PHOTO + " TEXT)";

    private static final String TABLE_RAPEL = "CREATE TABLE t_rapel (" +
            InitCol.ColHeaderRapel.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColHeaderRapel.NAME + " TEXT, " +
            InitCol.ColHeaderRapel.DESCRIPTION + " TEXT, " +
            InitCol.ColHeaderRapel.LEVEL + " TEXT, " +
            InitCol.ColHeaderRapel.LATITUDE + " TEXT, " +
            InitCol.ColHeaderRapel.LONGITUDE + " TEXT, " +
            InitCol.ColHeaderRapel.CITY + " TEXT, " +
            InitCol.ColHeaderRapel.ZONE + " TEXT, " +
            InitCol.ColHeaderRapel.TIME + " TEXT, " +
            InitCol.ColHeaderRapel.DISTANCE + " TEXT, " +
            InitCol.ColHeaderRapel.PHOTO + " TEXT, " +
            "FOREIGN KEY(" + InitCol.ColHeaderRapel.NAME + ") REFERENCES t_descr_rapel(" + InitCol.ColDescrRapel.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderRapel.PHOTO + ") REFERENCES t_photo_rapel(" + InitCol.ColPhotoRapel.PHOTO + "))";

    private static final String TABLE_DESCR_RAQ_NIEVE = "CREATE TABLE t_descr_raq_nieve (" +
            InitCol.ColDescrRaqNieve.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColDescrRaqNieve.NAME + " TEXT, " +
            InitCol.ColDescrRaqNieve.DESCRIPTION + " TEXT, " +
            InitCol.ColDescrRaqNieve.PHOTO + " TEXT)";

    private static final String TABLE_PHOTO_RAQ_NIEVE = "CREATE TABLE t_photo_raq_nieve (" +
            InitCol.ColPhotoRaqNieve.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColPhotoRaqNieve.PHOTO + " TEXT)";

    private static final String TABLE_RAQ_NIEVE = "CREATE TABLE t_raq_nieve (" +
            InitCol.ColHeaderRaqNieve.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColHeaderRaqNieve.NAME + " TEXT, " +
            InitCol.ColHeaderRaqNieve.DESCRIPTION + " TEXT, " +
            InitCol.ColHeaderRaqNieve.LEVEL + " TEXT, " +
            InitCol.ColHeaderRaqNieve.LATITUDE + " TEXT, " +
            InitCol.ColHeaderRaqNieve.LONGITUDE + " TEXT, " +
            InitCol.ColHeaderRaqNieve.CITY + " TEXT, " +
            InitCol.ColHeaderRaqNieve.ZONE + " TEXT, " +
            InitCol.ColHeaderRaqNieve.TIME + " TEXT, " +
            InitCol.ColHeaderRaqNieve.DISTANCE + " TEXT, " +
            InitCol.ColHeaderRaqNieve.PHOTO + " TEXT, " +
            "FOREIGN KEY(" + InitCol.ColHeaderRaqNieve.NAME + ") REFERENCES t_descr_raq_nieve(" + InitCol.ColDescrRaqNieve.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderRaqNieve.PHOTO + ") REFERENCES t_photo_raq_nieve(" + InitCol.ColPhotoRaqNieve.PHOTO + "))";

    private static final String TABLE_DESCR_VIAS_FERRATAS = "CREATE TABLE t_descr_vias_ferratas (" +
            InitCol.ColDescrViasFerratas.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColDescrViasFerratas.NAME + " TEXT, " +
            InitCol.ColDescrViasFerratas.DESCRIPTION + " TEXT, " +
            InitCol.ColDescrViasFerratas.PHOTO + " TEXT)";

    private static final String TABLE_PHOTO_VIAS_FERRATAS = "CREATE TABLE t_photo_vias_ferratas (" +
            InitCol.ColPhotoViasFerratas.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColPhotoViasFerratas.PHOTO + " TEXT)";

    private static final String TABLE_VIAS_FERRATAS = "CREATE TABLE t_vias_ferratas (" +
            InitCol.ColHeaderViasFerratas.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColHeaderViasFerratas.NAME + " TEXT, " +
            InitCol.ColHeaderViasFerratas.DESCRIPTION + " TEXT, " +
            InitCol.ColHeaderViasFerratas.LEVEL + " TEXT, " +
            InitCol.ColHeaderViasFerratas.LATITUDE + " TEXT, " +
            InitCol.ColHeaderViasFerratas.LONGITUDE + " TEXT, " +
            InitCol.ColHeaderViasFerratas.CITY + " TEXT, " +
            InitCol.ColHeaderViasFerratas.ZONE + " TEXT, " +
            InitCol.ColHeaderViasFerratas.TIME + " TEXT, " +
            InitCol.ColHeaderViasFerratas.DISTANCE + " TEXT, " +
            InitCol.ColHeaderViasFerratas.PHOTO + " TEXT, " +
            "FOREIGN KEY(" + InitCol.ColHeaderViasFerratas.NAME + ") REFERENCES t_descr_vias_ferratas(" + InitCol.ColDescrViasFerratas.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderViasFerratas.PHOTO + ") REFERENCES t_photo_vias_ferratas(" + InitCol.ColPhotoViasFerratas.PHOTO + "))";

    private static final String TABLE_ACTIVITYS = "CREATE TABLE t_activitys (" +
            InitCol.ColHeaderActivity.ID + " TEXT PRIMARY KEY NOT NULL," +
            InitCol.ColHeaderActivity.ESCALADA + " TEXT, " +
            InitCol.ColHeaderActivity.BARRANQUISMO + " TEXT, " +
            InitCol.ColHeaderActivity.RAPEL + " TEXT, " +
            InitCol.ColHeaderActivity.RAQUETA_DE_NIEVE + " TEXT, " +
            InitCol.ColHeaderActivity.VIAS_FERRATAS + " TEXT, " +
            "FOREIGN KEY(" + InitCol.ColHeaderActivity.ESCALADA + ") REFERENCES t_escalada(" + InitCol.ColHeaderEscalada.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderActivity.BARRANQUISMO + ") REFERENCES t_barranquismo(" + InitCol.ColHeaderBarranquismo.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderActivity.RAPEL + ") REFERENCES t_rapel(" + InitCol.ColHeaderRapel.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderActivity.RAQUETA_DE_NIEVE + ") REFERENCES t_raq_nieve(" + InitCol.ColHeaderRaqNieve.NAME + ")," +
            "FOREIGN KEY(" + InitCol.ColHeaderActivity.VIAS_FERRATAS + ") REFERENCES t_vias_ferratas(" + InitCol.ColHeaderViasFerratas.NAME + "))";

    public BBDDCards(Context context) {
        super(context, NAME_BBDD, null, VERSION_BASEDATOS);
    }

    /*@Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_DESCR_ESCALADA);
        db.execSQL(TABLE_PHOTO_ESCALADA);
        db.execSQL(TABLE_ESCALADA);

        db.execSQL(TABLE_DESCR_BARRANQUISMO);
        db.execSQL(TABLE_PHOTO_BARRANQUISMO);
        db.execSQL(TABLE_BARRANQUISMO);

        db.execSQL(TABLE_DESCR_RAPEL);
        db.execSQL(TABLE_PHOTO_RAPEL);
        db.execSQL(TABLE_RAPEL);

        db.execSQL(TABLE_DESCR_RAQ_NIEVE);
        db.execSQL(TABLE_PHOTO_RAQ_NIEVE);
        db.execSQL(TABLE_RAQ_NIEVE);

        db.execSQL(TABLE_DESCR_VIAS_FERRATAS);
        db.execSQL(TABLE_PHOTO_VIAS_FERRATAS);
        db.execSQL(TABLE_VIAS_FERRATAS);

        db.execSQL(TABLE_ACTIVITYS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESCR_ESCALADA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_ESCALADA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESCALADA);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESCR_BARRANQUISMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_BARRANQUISMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARRANQUISMO);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESCR_RAPEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_RAPEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RAPEL);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESCR_RAQ_NIEVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_RAQ_NIEVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RAQ_NIEVE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESCR_VIAS_FERRATAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_VIAS_FERRATAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIAS_FERRATAS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITYS);
        onCreate(db);
    }

    public Boolean checkRegistry() {
        SQLiteDatabase mDatabase = openDatabase("activitys.db", null, Context.MODE_PRIVATE);

        Cursor c = null;
        boolean tableExists = false;
        try {
            c = mDatabase.query("t_photo_escalada", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableExists;
    }

    public void insertActivitys(String escalada, String barranquismo, String rapel, String raq_nieve, String vias_ferratas) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(InitCol.ColHeaderActivity.ID, InitCol.HeaderActivity.geneteIdHeaderActivity());
            values.put(InitCol.ColHeaderActivity.ESCALADA, escalada);
            values.put(InitCol.ColHeaderActivity.BARRANQUISMO, barranquismo);
            values.put(InitCol.ColHeaderActivity.RAPEL, rapel);
            values.put(InitCol.ColHeaderActivity.RAQUETA_DE_NIEVE, raq_nieve);
            values.put(InitCol.ColHeaderActivity.VIAS_FERRATAS, vias_ferratas);

            if (db.insert("t_activitys", null, values) == -1) {
                db.close();
                throw new RuntimeException("Can't insert activitys in database");
            }
            db.close();
        }
    }

    private void insertCardActivity(String _id, String _id_generate, String id_name, String name_act,
                                    String id_level, String level, String id_latitude, String latitude,
                                    String id_longitude, String longitude, String id_city, String city,
                                    String id_zone, String zone, String id_time, String time,
                                    String id_distance, String distance, String id_photo, String photo,
                                    String name_table, String id_description, String description) {

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(_id, _id_generate);
            values.put(id_name, name_act);
            values.put(id_description, description);
            values.put(id_level, level);
            values.put(id_latitude, latitude);
            values.put(id_longitude, longitude);
            values.put(id_city, city);
            values.put(id_zone, zone);
            values.put(id_time, time);
            values.put(id_distance, distance);
            values.put(id_photo, photo);
            if (db.insert(name_table, null, values) == -1) {
                db.close();
                throw new RuntimeException("Can't insert " + name_table + " in database");
            }
            db.close();
        }
    }

    public void insertCardActivity(String name_activity, String description, String level, String latitude, String longitude,
                                   String city, String zone, String time, String distance, String photo) {
        switch (name_activity) {
            case "Escalada":
                insertCardActivity(InitCol.ColHeaderEscalada.ID, InitCol.HeaderEscalada.geneteIdHeaderEscalada(),
                        InitCol.ColHeaderEscalada.NAME, name_activity, InitCol.ColHeaderEscalada.LEVEL, level,
                        InitCol.ColHeaderEscalada.LATITUDE, latitude, InitCol.ColHeaderEscalada.LONGITUDE, longitude,
                        InitCol.ColHeaderEscalada.CITY, city, InitCol.ColHeaderEscalada.ZONE, zone,
                        InitCol.ColHeaderEscalada.TIME, time, InitCol.ColHeaderEscalada.DISTANCE, distance,
                        InitCol.ColHeaderEscalada.PHOTO, photo, "t_escalada", InitCol.ColHeaderEscalada.DESCRIPTION, description);
                break;
            case "Barranquismo":
                insertCardActivity(InitCol.ColHeaderBarranquismo.ID, InitCol.HeaderBarranquismo.geneteIdHeaderLevelBarranquismo(),
                        InitCol.ColHeaderBarranquismo.NAME, name_activity, InitCol.ColHeaderBarranquismo.LEVEL, level,
                        InitCol.ColHeaderBarranquismo.LATITUDE, latitude, InitCol.ColHeaderBarranquismo.LONGITUDE, longitude,
                        InitCol.ColHeaderBarranquismo.CITY, city, InitCol.ColHeaderBarranquismo.ZONE, zone,
                        InitCol.ColHeaderBarranquismo.TIME, time, InitCol.ColHeaderBarranquismo.DISTANCE, distance,
                        InitCol.ColHeaderBarranquismo.PHOTO, photo, "t_barranquismo", InitCol.ColHeaderBarranquismo.DESCRIPTION, description);
                break;
            case "Rápel":
                insertCardActivity(InitCol.ColHeaderRapel.ID, InitCol.HeaderRapel.geneteIdHeaderRapel(),
                        InitCol.ColHeaderRapel.NAME, name_activity, InitCol.ColHeaderRapel.LEVEL, level,
                        InitCol.ColHeaderRapel.LATITUDE, latitude, InitCol.ColHeaderRapel.LONGITUDE, longitude,
                        InitCol.ColHeaderRapel.CITY, city, InitCol.ColHeaderRapel.ZONE, zone,
                        InitCol.ColHeaderRapel.TIME, time, InitCol.ColHeaderRapel.DISTANCE, distance,
                        InitCol.ColHeaderRapel.PHOTO, photo, "t_rapel", InitCol.ColHeaderRapel.DESCRIPTION, description);
                break;
            case "Raquetas de Nieve":
                insertCardActivity(InitCol.ColHeaderRaqNieve.ID, InitCol.HeaderRaqNieve.geneteIdHeaderRaqNieve(),
                        InitCol.ColHeaderRaqNieve.NAME, name_activity, InitCol.ColHeaderRaqNieve.LEVEL, level,
                        InitCol.ColHeaderRaqNieve.LATITUDE, latitude, InitCol.ColHeaderRaqNieve.LONGITUDE, longitude,
                        InitCol.ColHeaderRaqNieve.CITY, city, InitCol.ColHeaderRaqNieve.ZONE, zone,
                        InitCol.ColHeaderRaqNieve.TIME, time, InitCol.ColHeaderRaqNieve.DISTANCE, distance,
                        InitCol.ColHeaderRaqNieve.PHOTO, photo, "t_raq_nieve", InitCol.ColHeaderRaqNieve.DESCRIPTION, description);
                break;
            case "Vías Ferratas":
                insertCardActivity(InitCol.ColHeaderViasFerratas.ID, InitCol.HeaderViasFerratas.geneteIdHeaderViasFerratas(),
                        InitCol.ColHeaderViasFerratas.NAME, name_activity, InitCol.ColHeaderViasFerratas.LEVEL, level,
                        InitCol.ColHeaderViasFerratas.LATITUDE, latitude, InitCol.ColHeaderViasFerratas.LONGITUDE, longitude,
                        InitCol.ColHeaderViasFerratas.CITY, city, InitCol.ColHeaderViasFerratas.ZONE, zone,
                        InitCol.ColHeaderViasFerratas.TIME, time, InitCol.ColHeaderViasFerratas.DISTANCE, distance,
                        InitCol.ColHeaderViasFerratas.PHOTO, photo, "t_vias_ferratas", InitCol.ColHeaderViasFerratas.DESCRIPTION, description);
                break;
            default:
                System.err.println("El nombre de la actividad no coincide");

        }
    }

    public void insertDescrActivity(String name_activity, String descr, String photo) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            switch (name_activity) {
                case "Escalada":
                    values.put(InitCol.ColDescrEscalada.ID, InitCol.DescrEscalada.geneteIdDescrEscalada());
                    values.put(InitCol.ColDescrEscalada.NAME, name_activity);
                    values.put(InitCol.ColDescrEscalada.DESCRIPTION, descr);
                    values.put(InitCol.ColDescrEscalada.PHOTO, photo);
                    if (db.insert("t_descr_escalada", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Barranquismo":
                    values.put(InitCol.ColDescrBarranquismo.ID, InitCol.DescrBarranquismo.geneteIdBarranquismo());
                    values.put(InitCol.ColDescrBarranquismo.NAME, name_activity);
                    values.put(InitCol.ColDescrBarranquismo.DESCRIPTION, descr);
                    values.put(InitCol.ColDescrBarranquismo.PHOTO, photo);
                    if (db.insert("t_descr_barranquismo", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Rápel":
                    values.put(InitCol.ColDescrRapel.ID, InitCol.DescrRapel.geneteIdRapel());
                    values.put(InitCol.ColDescrRapel.NAME, name_activity);
                    values.put(InitCol.ColDescrRapel.DESCRIPTION, descr);
                    values.put(InitCol.ColDescrRapel.PHOTO, photo);
                    if (db.insert("t_descr_rapel", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Raquetas de Nieve":
                    values.put(InitCol.ColDescrRaqNieve.ID, InitCol.DescrRaqNieve.geneteIdRaqNieve());
                    values.put(InitCol.ColDescrRaqNieve.NAME, name_activity);
                    values.put(InitCol.ColDescrRaqNieve.DESCRIPTION, descr);
                    values.put(InitCol.ColDescrRaqNieve.PHOTO, photo);
                    if (db.insert("t_descr_raq_nieve", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Vías Ferratas":
                    values.put(InitCol.ColDescrViasFerratas.ID, InitCol.DescrViasFerratas.geneteIdViasFerratas());
                    values.put(InitCol.ColDescrViasFerratas.NAME, name_activity);
                    values.put(InitCol.ColDescrViasFerratas.DESCRIPTION, descr);
                    values.put(InitCol.ColDescrViasFerratas.PHOTO, photo);
                    if (db.insert("t_descr_vias_ferratas", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
            }
        }
    }

    public void insertPhotos(String name_activity, String photo) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            switch (name_activity) {
                case "Escalada":
                    values.put(InitCol.ColPhotoEscalada.ID, InitCol.PhotoEscalada.generateIdPhotoEscalada());
                    values.put(InitCol.ColPhotoEscalada.PHOTO, photo);
                    if (db.insert("t_photo_escalada", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Barranquismo":
                    values.put(InitCol.ColPhotoBarranquismo.ID, InitCol.PhotoBarranquismo.generateIdPhotoBarranquismo());
                    values.put(InitCol.ColPhotoBarranquismo.PHOTO, photo);
                    if (db.insert("t_photo_barranquismo", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Rápel":
                    values.put(InitCol.ColPhotoRapel.ID, InitCol.PhotoRapel.generateIdPhotoRapel());
                    values.put(InitCol.ColPhotoRapel.PHOTO, photo);
                    if (db.insert("t_photo_rapel", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Raquetas de Nieve":
                    values.put(InitCol.ColPhotoRaqNieve.ID, InitCol.PhotoRaqNieve.generateIdPhotoRaqNieve());
                    values.put(InitCol.ColPhotoRaqNieve.PHOTO, photo);
                    if (db.insert("t_photo_raq_nieve", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
                    break;

                case "Vías Ferratas":
                    values.put(InitCol.ColPhotoViasFerratas.ID, InitCol.PhotoViasFerratas.generateIdPhotoViasFerratas());
                    values.put(InitCol.ColPhotoViasFerratas.PHOTO, photo);
                    if (db.insert("t_photo_vias_ferratas", null, values) == -1) {
                        db.close();
                        throw new RuntimeException("Can't insert in database");
                    }
            }
        }
    }

    public void deleteActivity(String id, String table) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table, "_id='" + id + "'", null);
        db.close();
    }

    public List<Activitys> getActivitys() {
        SQLiteDatabase db = getReadableDatabase();
        List<Activitys> list_act = new ArrayList<Activitys>();
        String[] recover_value = {InitCol.HeaderActivity.ID, InitCol.HeaderActivity.ESCALADA, InitCol.HeaderActivity.BARRANQUISMO,
                InitCol.HeaderActivity.RAPEL, InitCol.HeaderActivity.RAQUETA_DE_NIEVE, InitCol.HeaderActivity.VIAS_FERRATAS};
        Cursor c = db.query("t_activitys", recover_value,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Activitys act = new Activitys(c.getString(0), c.getString(1),
                    c.getString(2), c.getString(3), c.getString(4), c.getString(5));
            list_act.add(act);
        } while (c.moveToNext());
        db.close();
        c.close();
        return list_act;
    }

    private String[] fieldsActivity(String _id, String name_activity, String description, String level, String latitude, String longitude,
                                    String city, String zone, String time, String distance, String photo) {
        String[] recover_value = {_id, name_activity, description, level, latitude, longitude, city, zone, time, distance, photo};
        return recover_value;
    }

    public List<CardsActivitys> getCards(String name_activity) {

        SQLiteDatabase db = getReadableDatabase();
        List<CardsActivitys> list_card = new ArrayList<CardsActivitys>();

        String[] recover_value;
        Cursor c = null;
        switch (name_activity) {
            case "Escalada":
                recover_value = fieldsActivity(InitCol.ColHeaderEscalada.ID, InitCol.ColHeaderEscalada.NAME,
                        InitCol.ColHeaderEscalada.DESCRIPTION, InitCol.ColHeaderEscalada.LEVEL,
                        InitCol.ColHeaderEscalada.LATITUDE, InitCol.ColHeaderEscalada.LONGITUDE, InitCol.ColHeaderEscalada.CITY,
                        InitCol.ColHeaderEscalada.ZONE, InitCol.ColHeaderEscalada.TIME, InitCol.ColHeaderEscalada.DISTANCE,
                        InitCol.ColHeaderEscalada.PHOTO);
                c = db.query("t_escalada", recover_value, null, null, null, null, null, null);
                break;
            case "Barranquismo":
                recover_value = fieldsActivity(InitCol.ColHeaderBarranquismo.ID, InitCol.ColHeaderBarranquismo.NAME,
                        InitCol.ColHeaderBarranquismo.DESCRIPTION, InitCol.ColHeaderBarranquismo.LEVEL,
                        InitCol.ColHeaderBarranquismo.LATITUDE, InitCol.ColHeaderBarranquismo.LONGITUDE,
                        InitCol.ColHeaderBarranquismo.CITY, InitCol.ColHeaderBarranquismo.ZONE,
                        InitCol.ColHeaderBarranquismo.TIME, InitCol.ColHeaderBarranquismo.DISTANCE,
                        InitCol.ColHeaderBarranquismo.PHOTO);
                c = db.query("t_barranquismo", recover_value, null, null, null, null, null, null);
                break;
            case "Rápel":
                recover_value = fieldsActivity(InitCol.ColHeaderRapel.ID, InitCol.ColHeaderRapel.NAME,
                        InitCol.ColHeaderRapel.DESCRIPTION, InitCol.ColHeaderRapel.LEVEL,
                        InitCol.ColHeaderRapel.LATITUDE, InitCol.ColHeaderRapel.LONGITUDE,
                        InitCol.ColHeaderRapel.CITY, InitCol.ColHeaderRapel.ZONE,
                        InitCol.ColHeaderRapel.TIME, InitCol.ColHeaderRapel.DISTANCE,
                        InitCol.ColHeaderRapel.PHOTO);
                c = db.query("t_rapel", recover_value, null, null, null, null, null, null);
                break;
            case "Raquetas de Nieve":
                recover_value = fieldsActivity(InitCol.ColHeaderRaqNieve.ID, InitCol.ColHeaderRaqNieve.NAME,
                        InitCol.ColHeaderRaqNieve.DESCRIPTION, InitCol.ColHeaderRaqNieve.LEVEL,
                        InitCol.ColHeaderRaqNieve.LATITUDE, InitCol.ColHeaderRaqNieve.LONGITUDE,
                        InitCol.ColHeaderRaqNieve.CITY, InitCol.ColHeaderRaqNieve.ZONE,
                        InitCol.ColHeaderRaqNieve.TIME, InitCol.ColHeaderRaqNieve.DISTANCE,
                        InitCol.ColHeaderRaqNieve.PHOTO);
                c = db.query("t_raq_nieve", recover_value, null, null, null, null, null, null);
                break;
            case "Vías Ferratas":
                recover_value = fieldsActivity(InitCol.ColHeaderViasFerratas.ID, InitCol.ColHeaderViasFerratas.NAME,
                        InitCol.ColHeaderViasFerratas.DESCRIPTION, InitCol.ColHeaderViasFerratas.LEVEL,
                        InitCol.ColHeaderViasFerratas.LATITUDE, InitCol.ColHeaderViasFerratas.LONGITUDE,
                        InitCol.ColHeaderViasFerratas.CITY, InitCol.ColHeaderViasFerratas.ZONE,
                        InitCol.ColHeaderViasFerratas.TIME, InitCol.ColHeaderViasFerratas.DISTANCE,
                        InitCol.ColHeaderViasFerratas.PHOTO);
                c = db.query("t_vias_ferratas", recover_value, null, null, null, null, null, null);
                break;
        }

        if (c != null) {
            c.moveToFirst();
            do {
                CardsActivitys cards_act = new CardsActivitys(c.getString(0), c.getString(1),
                        c.getString(2), c.getString(3), c.getString(4), c.getString(5),
                        c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10));
                list_card.add(cards_act);
            } while (c.moveToNext());
            db.close();
            c.close();
        }

        return list_card;
    }

    private String getFieldsString(String _id, String name_activity, String description, String level,
                                   String latitude, String longitude, String city, String zone,
                                   String time, String distance, String photo, Cursor cursor, String level_activity) {
        String text = "fail";

        if (cursor.getCount() == 0) {
            Log.v("count: ", "" + cursor.getCount());
            return text;
        } else if (level_activity.equals("all_levels") || level_activity.equals(cursor.getString(cursor.getColumnIndex(level)))) {
            text = cursor.getString(cursor.getColumnIndex(name_activity)) + ";" +
                    cursor.getString(cursor.getColumnIndex(description)) + ";" +
                    cursor.getString(cursor.getColumnIndex(level)) + ";" +
                    cursor.getString(cursor.getColumnIndex(latitude)) + ";" +
                    cursor.getString(cursor.getColumnIndex(longitude)) + ";" +
                    cursor.getString(cursor.getColumnIndex(city)) + ";" +
                    cursor.getString(cursor.getColumnIndex(zone)) + ";" +
                    cursor.getString(cursor.getColumnIndex(time)) + ";" +
                    cursor.getString(cursor.getColumnIndex(distance)) + ";" +
                    cursor.getString(cursor.getColumnIndex(photo)) + "|";
        } else {
            text = "";
        }
        Log.v("text:", "" + "el texto que se imprime " + text);
        return text;
    }

    public String getCardsWithOutInternet(String name_activity, String level_activity) {
        SQLiteDatabase db = this.getReadableDatabase();
        String text = null;

        String[] fields = {"actividades", "Escalada", "Barranquismo", "Rápel", "Raquetas de Nieve",
                "Vías Ferratas", "descr_Escalada", "photo_Escalada", "descr_Barranquismo", "photo_Barranquismo",
                "descr_Rápel", "photo_Rápel", "descr_Raquetas de Nieve", "photo_Raquetas de Nieve",
                "descr_Vías Ferratas", "photo_Vías Ferratas"};

        String name_table = "";
        for (int i = 0; i < fields.length; i++) {
            if (name_activity.equals(fields[i])) {
                name_table = NAME_TABLES[i];
            }
        }

        Log.v("Name_table:", "" + name_table);

        String query = "SELECT * FROM '" + name_table + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();

        text = "";
        do {
            switch (name_activity) {
                case "Escalada":
                    text += getFieldsString(InitCol.ColHeaderEscalada.ID, InitCol.ColHeaderEscalada.NAME,
                            InitCol.ColHeaderEscalada.DESCRIPTION, InitCol.ColHeaderEscalada.LEVEL,
                            InitCol.ColHeaderEscalada.LATITUDE, InitCol.ColHeaderEscalada.LONGITUDE, InitCol.ColHeaderEscalada.CITY,
                            InitCol.ColHeaderEscalada.ZONE, InitCol.ColHeaderEscalada.TIME, InitCol.ColHeaderEscalada.DISTANCE,
                            InitCol.ColHeaderEscalada.PHOTO, cursor, level_activity);
                    break;
                case "Barranquismo":
                    text += getFieldsString(InitCol.ColHeaderBarranquismo.ID, InitCol.ColHeaderBarranquismo.NAME,
                            InitCol.ColHeaderBarranquismo.DESCRIPTION, InitCol.ColHeaderBarranquismo.LEVEL,
                            InitCol.ColHeaderBarranquismo.LATITUDE, InitCol.ColHeaderBarranquismo.LONGITUDE,
                            InitCol.ColHeaderBarranquismo.CITY, InitCol.ColHeaderBarranquismo.ZONE,
                            InitCol.ColHeaderBarranquismo.TIME, InitCol.ColHeaderBarranquismo.DISTANCE,
                            InitCol.ColHeaderBarranquismo.PHOTO, cursor, level_activity);
                    break;
                case "Rápel":
                    text += getFieldsString(InitCol.ColHeaderRapel.ID, InitCol.ColHeaderRapel.NAME,
                            InitCol.ColHeaderRapel.DESCRIPTION, InitCol.ColHeaderRapel.LEVEL,
                            InitCol.ColHeaderRapel.LATITUDE, InitCol.ColHeaderRapel.LONGITUDE,
                            InitCol.ColHeaderRapel.CITY, InitCol.ColHeaderRapel.ZONE,
                            InitCol.ColHeaderRapel.TIME, InitCol.ColHeaderRapel.DISTANCE,
                            InitCol.ColHeaderRapel.PHOTO, cursor, level_activity);
                    break;
                case "Raquetas de Nieve":
                    text += getFieldsString(InitCol.ColHeaderRaqNieve.ID, InitCol.ColHeaderRaqNieve.NAME,
                            InitCol.ColHeaderRaqNieve.DESCRIPTION, InitCol.ColHeaderRaqNieve.LEVEL,
                            InitCol.ColHeaderRaqNieve.LATITUDE, InitCol.ColHeaderRaqNieve.LONGITUDE,
                            InitCol.ColHeaderRaqNieve.CITY, InitCol.ColHeaderRaqNieve.ZONE,
                            InitCol.ColHeaderRaqNieve.TIME, InitCol.ColHeaderRaqNieve.DISTANCE,
                            InitCol.ColHeaderRaqNieve.PHOTO, cursor, level_activity);
                    break;
                case "Vías Ferratas":
                    text += getFieldsString(InitCol.ColHeaderViasFerratas.ID, InitCol.ColHeaderViasFerratas.NAME,
                            InitCol.ColHeaderViasFerratas.DESCRIPTION, InitCol.ColHeaderViasFerratas.LEVEL,
                            InitCol.ColHeaderViasFerratas.LATITUDE, InitCol.ColHeaderViasFerratas.LONGITUDE,
                            InitCol.ColHeaderViasFerratas.CITY, InitCol.ColHeaderViasFerratas.ZONE,
                            InitCol.ColHeaderViasFerratas.TIME, InitCol.ColHeaderViasFerratas.DISTANCE,
                            InitCol.ColHeaderViasFerratas.PHOTO, cursor, level_activity);
                    break;
            }
            //text += cursor.getString(cursor.getColumnIndex(InitCol.ColHeaderEscalada.CITY)) + "\n";
        } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return text;
    }

    private String[] descrActivity(String _id, String name_activity, String descr, String photo) {
        String[] recover_value = {_id, name_activity, descr, photo};
        return recover_value;
    }

    public List<DescrActivity> getDescrActivity(String name_activity) {

        SQLiteDatabase db = getReadableDatabase();
        List<DescrActivity> list_card = new ArrayList<DescrActivity>();

        String[] recover_value;
        Cursor c = null;
        switch (name_activity) {
            case "Escalada":
                recover_value = descrActivity(InitCol.ColDescrEscalada.ID, InitCol.ColDescrEscalada.NAME,
                        InitCol.ColDescrEscalada.DESCRIPTION, InitCol.ColDescrEscalada.PHOTO);
                c = db.query("t_descr_escalada", recover_value, null, null, null, null, null, null);
                break;
            case "Barranquismo":
                recover_value = descrActivity(InitCol.ColDescrBarranquismo.ID, InitCol.ColDescrBarranquismo.NAME,
                        InitCol.ColDescrBarranquismo.DESCRIPTION, InitCol.ColDescrBarranquismo.PHOTO);
                c = db.query("t_descr_barranquismo", recover_value, null, null, null, null, null, null);
                break;
            case "Rápel":
                recover_value = descrActivity(InitCol.ColDescrRapel.ID, InitCol.ColDescrRapel.NAME,
                        InitCol.ColDescrRapel.DESCRIPTION, InitCol.ColDescrRapel.PHOTO);
                c = db.query("t_descr_rapel", recover_value, null, null, null, null, null, null);
                break;
            case "Raquetas de Nieve":
                recover_value = descrActivity(InitCol.ColDescrRaqNieve.ID, InitCol.ColDescrRaqNieve.NAME,
                        InitCol.ColDescrRaqNieve.DESCRIPTION, InitCol.ColDescrRaqNieve.PHOTO);
                c = db.query("t_descr_raq_nieve", recover_value, null, null, null, null, null, null);
                break;
            case "Vías Ferratas":
                recover_value = descrActivity(InitCol.ColDescrViasFerratas.ID, InitCol.ColDescrViasFerratas.NAME,
                        InitCol.ColDescrViasFerratas.DESCRIPTION, InitCol.ColDescrViasFerratas.PHOTO);
                c = db.query("t_descr_vias_ferratas", recover_value, null, null, null, null, null, null);
                break;
        }

        if (c != null) {
            c.moveToFirst();
            do {
                DescrActivity descr_act = new DescrActivity(c.getString(0), c.getString(1),
                        c.getString(2), c.getString(3));
                list_card.add(descr_act);
            } while (c.moveToNext());
            db.close();
            c.close();
        }
        return list_card;
    }

    private String getFieldsStringDescr(String id, String name, String description, String photo, Cursor cursor) {
        String text = "fail";
        if (cursor.getCount() == 0) {
            return text;
        } else {
            text = cursor.getString(cursor.getColumnIndex(name)) + ";" +
                    cursor.getString(cursor.getColumnIndex(description)) + ";" +
                    cursor.getString(cursor.getColumnIndex(photo)) + "|";
        }
        Log.v("count: ", "" + cursor.getCount());
        return text;
    }

    public String getDescr(String name_activity, String name_descr) {
        SQLiteDatabase db = this.getReadableDatabase();
        String text = null;

        String[] fields = {"actividades", "Escalada", "Barranquismo", "Rápel", "Raquetas de Nieve",
                "Vías Ferratas", "descr_Escalada", "photo_Escalada", "descr_Barranquismo", "photo_Barranquismo",
                "descr_Rápel", "photo_Rápel", "descr_Raquetas de Nieve", "photo_Raquetas de Nieve",
                "descr_Vías Ferratas", "photo_Vías Ferratas"};

        String name_table = "";

        for (int i = 0; i < fields.length; i++) {
            if (name_descr.equals(fields[i])) {
                name_table = NAME_TABLES[i];
            }
        }

        String query = "SELECT * FROM '" + name_table + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();

        text = "";
        do {
            switch (name_activity) {
                case "Escalada":
                    text += getFieldsStringDescr(InitCol.ColDescrEscalada.ID, InitCol.ColDescrEscalada.NAME,
                            InitCol.ColDescrEscalada.DESCRIPTION, InitCol.ColDescrEscalada.PHOTO, cursor);
                    break;
                case "Barranquismo":
                    text += getFieldsStringDescr(InitCol.ColDescrBarranquismo.ID, InitCol.ColDescrBarranquismo.NAME,
                            InitCol.ColDescrBarranquismo.DESCRIPTION, InitCol.ColDescrBarranquismo.PHOTO, cursor);
                    break;
                case "Rápel":
                    text += getFieldsStringDescr(InitCol.ColDescrRapel.ID, InitCol.ColDescrRapel.NAME,
                            InitCol.ColDescrRapel.DESCRIPTION, InitCol.ColDescrRapel.PHOTO, cursor);
                    break;
                case "Raquetas de Nieve":
                    text += getFieldsStringDescr(InitCol.ColDescrRaqNieve.ID, InitCol.ColDescrRaqNieve.NAME,
                            InitCol.ColDescrRaqNieve.DESCRIPTION, InitCol.ColDescrRaqNieve.PHOTO, cursor);
                    break;
                case "Vías Ferratas":
                    text += getFieldsStringDescr(InitCol.ColDescrViasFerratas.ID, InitCol.ColDescrViasFerratas.NAME,
                            InitCol.ColDescrViasFerratas.DESCRIPTION, InitCol.ColDescrViasFerratas.PHOTO, cursor);
                    break;
            }
        } while (cursor.moveToNext());


        Log.v("Valor de text", "" + text);
        cursor.close();
        db.close();

        return text;
    }

    private String[] photo(String _id, String photo) {
        String[] recover_value = {_id, photo};
        return recover_value;
    }

    public List<Photos> getPhotos(String name_activity) {

        SQLiteDatabase db = getReadableDatabase();
        List<Photos> list_photos = new ArrayList<Photos>();

        String[] recover_value;
        Cursor c = null;
        switch (name_activity) {
            case "Escalada":
                recover_value = photo(InitCol.ColPhotoEscalada.ID, InitCol.ColPhotoEscalada.PHOTO);
                c = db.query("t_photo_escalada", recover_value, null, null, null, null, null, null);
                break;
            case "Barranquismo":
                recover_value = photo(InitCol.ColPhotoBarranquismo.ID, InitCol.ColPhotoBarranquismo.PHOTO);
                c = db.query("t_photo_barranquismo", recover_value, null, null, null, null, null, null);
                break;
            case "Rápel":
                recover_value = photo(InitCol.ColPhotoRapel.ID, InitCol.ColPhotoRapel.PHOTO);
                c = db.query("t_photo_rapel", recover_value, null, null, null, null, null, null);
                break;
            case "Raquetas de Nieve":
                recover_value = photo(InitCol.ColPhotoRaqNieve.ID, InitCol.ColPhotoRaqNieve.PHOTO);
                c = db.query("t_photo_raq_nieve", recover_value, null, null, null, null, null, null);
                break;
            case "Vías Ferratas":
                recover_value = photo(InitCol.ColPhotoViasFerratas.ID, InitCol.ColPhotoViasFerratas.PHOTO);
                c = db.query("t_photo_vias_ferratas", recover_value, null, null, null, null, null, null);
                break;
        }

        if (c != null) {
            c.moveToFirst();
            do {
                Photos photo = new Photos(c.getString(0), c.getString(1));
                list_photos.add(photo);
            } while (c.moveToNext());
            db.close();
            c.close();
        }

        return list_photos;
    }

    public boolean isTableExists(String name) {

        boolean isExist = false;
        String[] fields = {"actividades", "Escalada", "Barranquismo", "Rápel", "Raquetas de Nieve",
                "Vías Ferratas", "descr_Escalada", "photo_Escalada", "descr_Barranquismo", "photo_Barranquismo",
                "descr_Rápel", "photo_Rápel", "descr_Raquetas de Nieve", "photo_Raquetas de Nieve",
                "descr_Vías Ferratas", "photo_Vías Ferratas"};

        String name_table = "";
        for (int i = 0; i < fields.length; i++) {
            if (name.equals(fields[i])) {
                name_table = NAME_TABLES[i];
            }
        }
        Log.v("name_table: ", name_table);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='" + name_table + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        Log.v("table_exist: ", "" + isExist + ", getCount" + cursor.getCount());
        return isExist;
    }

    public SQLiteDatabase bbdd() {
        SQLiteDatabase db = getReadableDatabase();
        return db;
    }

}


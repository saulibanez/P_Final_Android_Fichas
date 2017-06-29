package es.urjc.mov.sibanez.fichas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import es.urjc.mov.sibanez.fichas.BBDD.BBDDCards;
import es.urjc.mov.sibanez.fichas_v0.R;


public class Cards extends AppCompatActivity {

    private TextView name, description, txt_level, ubication, city, zone, time, distance;
    private ImageView photo;
    private Button btn_next, btn_back, btn_ubication;
    private BBDDCards bbdd = null;
    private int pos = 0;
    private String[] campos = null;
    private String level, ubicacion;
    private boolean exist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);

        name = ((TextView) findViewById(R.id.id_name));
        description = ((TextView) findViewById(R.id.id_description));
        txt_level = ((TextView) findViewById(R.id.id_level));
        ubication = ((TextView) findViewById(R.id.id_ubicacion));
        city = ((TextView) findViewById(R.id.id_city));
        zone = ((TextView) findViewById(R.id.id_zone));
        time = ((TextView) findViewById(R.id.id_time));
        distance = ((TextView) findViewById(R.id.id_distance));
        photo = ((ImageView) findViewById(R.id.id_photo));

        btn_next = (Button) findViewById(R.id.next);
        btn_back = (Button) findViewById(R.id.back);
        btn_ubication = (Button) findViewById(R.id.id_button_ubicacion);

        String name_activity = getIntent().getExtras().getString("activity");
        Log.v("name_activity", "" + name_activity);
        level = getIntent().getExtras().getString("level");
        Log.v("level", "" + level);

        bbdd = new BBDDCards(getApplicationContext());

        if (existBBDD(name_activity)) {
            processBBDD(name_activity);
            handlerButtons();
        } else {
            MyATaskCliente myATaskYW = new MyATaskCliente(this);
            myATaskYW.execute(name_activity, level);

            try {
                if (!myATaskYW.get().equals("")) {
                    campos = myATaskYW.get().split("\\|");
                    printCards();
                    handlerButtons();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlerButtons() {
        btn_back.setVisibility(View.INVISIBLE);
        if (campos.length == 1) {
            btn_next.setVisibility(View.INVISIBLE);
        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back.setVisibility(View.VISIBLE);
                if (pos < campos.length - 1) {
                    pos = pos + 1;
                    if (pos == campos.length - 1) {
                        btn_next.setVisibility(View.INVISIBLE);
                    }
                }
                printCards();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if (pos > 0) {
                    pos = pos - 1;
                    if (pos == 0) {
                        btn_back.setVisibility(View.INVISIBLE);
                    }
                }
                printCards();
            }
        });

        btn_ubication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ubicacion != null) {
                    String[] ub = ubicacion.split(" ");
                    String lat = ub[0];
                    String lon = ub[1];
                    if (!ub[0].equals("-")) {
                        Intent description = new Intent(Cards.this, Maps.class);
                        description.putExtra("latitude", lat);
                        description.putExtra("longitude", lon);
                        description.putExtra("zone", zone.getText().toString());
                        startActivity(description);
                    }
                }
            }
        });
    }

    private Boolean existBBDD(String name_activity) {
        exist = false;

        if (!bbdd.getCardsWithOutInternet(name_activity, level).equals("fail")) {
            exist = true;
        }

        return exist;
    }

    private void processBBDD(String name_activity) {
        campos = bbdd.getCardsWithOutInternet(name_activity, level).split("\\|");
        Log.v("campos", "" + campos[pos]);
        printCards();
    }

    private void insertBBDDCards(String[] campos) {
        String name_activity = campos[0];
        String description = campos[1];
        String level = campos[2];
        String latitude = campos[3];
        String longitude = campos[4];
        String city = campos[5];
        String zone = campos[6];
        String time = campos[7];
        String distance = campos[8];
        String photo = "";

        if (campos[9].equals("null")) {
            photo = null;
        } else {
            photo = campos[9];
        }
        bbdd.insertCardActivity(name_activity, description, level, latitude, longitude, city, zone, time, distance, photo);
    }

    private void printCards() {
        String[] fields, aux = null;
        fields = campos[pos].split("\\;");
        //bbdd = new BBDDCards(getApplicationContext());

        if (!exist) {
            for (int j = 0; j < campos.length; j++) {
                aux = campos[j].split("\\;");
                Log.v("---> campos: ", "" + aux);
                insertBBDDCards(aux);
            }
            exist = true;
        }

        if (fields != null && !fields[0].equals("")) {
            if (fields[2].equals(level) || level.equals("all_levels")) {
                name.setText(fields[0]);
                description.setText(fields[1]);
                txt_level.setText(fields[2]);
                ubicacion = fields[3] + " " + fields[4];
                ubication.setText("Latitud: " + fields[3] + "\nLongitud: " + fields[4]);
                city.setText(fields[5]);
                zone.setText(fields[6]);
                time.setText(fields[7]);
                distance.setText(fields[8]);
                if (!fields[9].equals("null")) {
                    photo.setImageBitmap(decodeImage(fields[9]));
                } else {
                    photo.setImageBitmap(null);
                }
            }
        }
    }

    private String encodeImage(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    private Bitmap decodeImage(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private Bitmap resizeImage(Bitmap bm) {
        int w = 200;
        int h = 300;

        float scale_w = ((float) w) / bm.getWidth();
        float scale_h = ((float) h) / bm.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);

        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, false);
        return bitmap;
    }

}

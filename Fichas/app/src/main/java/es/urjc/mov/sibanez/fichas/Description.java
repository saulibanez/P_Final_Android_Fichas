package es.urjc.mov.sibanez.fichas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import es.urjc.mov.sibanez.fichas.BBDD.BBDDCards;
import es.urjc.mov.sibanez.fichas_v0.R;


public class Description extends AppCompatActivity {

    private TextView activity, description;
    private BBDDCards bbdd = null;
    private String[] campos = null;
    private ImageView photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        String name_activity = getIntent().getExtras().getString("activity");
        activity = ((TextView) findViewById(R.id.activity));
        description = ((TextView) findViewById(R.id.description));
        photo = (ImageView) findViewById(R.id.photo_desc);

        bbdd = new BBDDCards(getApplicationContext());

        if (existBBDD(name_activity)) {
            processBBDD(name_activity);
        } else {
            MyATaskCliente myATaskYW = new MyATaskCliente(this);
            myATaskYW.execute(name_activity);
            try {
                Log.v("myATaskYW", "" + myATaskYW.get());
                campos = myATaskYW.get().split("\\|");
                printDescr();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean existBBDD(String name_activity) {
        boolean exist = false;

        if (!bbdd.getDescr(name_activity, "descr_" + name_activity).equals("fail")) {
            exist = true;
        }
        Log.v("EL RETORNO ES: ", "" + exist);
        return exist;
    }

    private void processBBDD(String name_activity) {
        campos = bbdd.getDescr(name_activity, "descr_" + name_activity).split("\\|");
        printDescr();
    }

    private void insertBBDDDescriptions(String[] campos) {
        bbdd.insertDescrActivity(campos[0], campos[1], campos[2]);
    }

    private BBDDCards printDescr() {
        String[] fields, aux = null;

        fields = campos[0].split("\\;");

        Log.v("******: ", "" + "descr_" + fields[0]);
        Log.v("++++++ ", "" + "+++" + campos[0]);

        if (bbdd.getDescr(fields[0], "descr_" + fields[0]).toString().equals("fail")) {
            for (int j = 0; j < campos.length; j++) {
                aux = campos[j].split("\\;");
                insertBBDDDescriptions(aux);
            }
        }

        if (fields != null) {
            activity.setText(fields[0]);
            description.setText(fields[1]);
            photo.setImageBitmap(decodeImage(fields[2]));
        }

        return null;
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

        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
        return bitmap;
    }
}

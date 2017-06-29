package es.urjc.mov.sibanez.fichas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import es.urjc.mov.sibanez.fichas.BBDD.BBDDCards;
import es.urjc.mov.sibanez.fichas_v0.R;

public class AddCard extends AppCompatActivity {
    private String activity = "Escalada";
    private String level = "fácil";
    private Spinner spinner, spinner_lvl;
    private EditText descr, city, zone, time, dist;
    private Context context;
    private BBDDCards bbdd = null;
    private String latitude = "-";
    private String longitude = "-";
    private boolean exist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);

        descr = ((EditText) findViewById(R.id.id_description_add));
        city = ((EditText) findViewById(R.id.id_city_add));
        zone = ((EditText) findViewById(R.id.id_zone_add));
        time = ((EditText) findViewById(R.id.id_time_add));
        dist = ((EditText) findViewById(R.id.id_distance_add));

        context = this;

        spinner = (Spinner) findViewById(R.id.spinner_act);
        String[] values = {"Escalada", "Barranquismo", "Rápel", "Raquetas de Nieve", "Vías Ferratas"};

        ArrayAdapter<String> arry =
                new ArrayAdapter<String>(
                        this, R.layout.sppiner_items, values);
        arry.setDropDownViewResource(R.layout.sppiner_items);
        spinner.setAdapter(arry);
        spinner.setOnItemSelectedListener(new AddCard.Seleccion(this));

        spinner_lvl = (Spinner) findViewById(R.id.spinner_lvl);
        String[] values_lvl = {"fácil", "moderado", "difícil"};

        ArrayAdapter<String> arry_lvl =
                new ArrayAdapter<String>(
                        this, R.layout.sppiner_items, values_lvl);
        arry.setDropDownViewResource(R.layout.sppiner_items);
        spinner_lvl.setAdapter(arry_lvl);
        spinner_lvl.setOnItemSelectedListener(new AddCard.Seleccion(this));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        Button send = (Button) findViewById(R.id.send_add);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyATaskCliente myATaskYW = new MyATaskCliente(context);
                myATaskYW.execute(activity, descr.getText().toString(), level,
                        city.getText().toString(), zone.getText().toString(), time.getText().toString(),
                        dist.getText().toString(), latitude, longitude, null);

                try {
                    Log.v("myATaskYW", "" + myATaskYW.get());
                    if (myATaskYW.get().equals("ok")) {
                        bbdd = new BBDDCards(getApplicationContext());
                        if(existBBDD(activity)) {
                            insertBBDDCards();
                        }
                        Intent accept = new Intent(AddCard.this, MainActivity.class);
                        startActivity(accept);
                        Toast.makeText(getApplicationContext(), "Ficha añadida", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Fallo al añadir", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
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

    private void insertBBDDCards() {
        String name_activity = activity;
        String description = descr.getText().toString();
        String lvl = level;
        String lat = latitude;
        String lon = longitude;
        String cty = city.getText().toString();
        String zne = zone.getText().toString();
        String tme = time.getText().toString();
        String distance = dist.getText().toString();
        String photo = null;
        bbdd.insertCardActivity(name_activity, description, lvl, lat, lon, cty, zne, tme, distance, photo);
    }

    private class Seleccion extends Activity implements AdapterView.OnItemSelectedListener {
        private AddCard card;
        private boolean isFirst;

        public Seleccion(AddCard card) {
            this.card = card;
            isFirst = true;
        }

        @Override
        public void onItemSelected(AdapterView<?> lista, View item,
                                   int idx, long id) {
            Spinner spinner = (Spinner) lista;
            Spinner spinner_lvl = (Spinner) lista;
            if (isFirst) {
                isFirst = false;
                return;
            }
            String str = lista.getItemAtPosition(idx).toString();
            if (spinner.getId() == R.id.spinner_act) {
                activity = str;
            } else if (spinner.getId() == R.id.spinner_lvl) {
                level = str;
            }
            int time = Toast.LENGTH_SHORT;
            str += " seleccionado";
            Toast msg = Toast.makeText(AddCard.this, str, time);
            msg.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> lista) {
            int time = Toast.LENGTH_SHORT;
            String str = "nada seleccionado";
            Toast msg = Toast.makeText(AddCard.this, str, time);
            msg.show();
        }
    }


    private void locationStart() {
        Log.v("--->", "mi direccion es" + "casa cada casa");
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);

                    Log.v("--->", "mi direccion es" + DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        MainActivity mainActivity;

        public MainActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();

            Log.v("-->", "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude());

            latitude = "" + loc.getLatitude();
            longitude = "" + loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
}

package es.urjc.mov.sibanez.fichas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


import es.urjc.mov.sibanez.fichas_v0.R;

public class MainActivity extends AppCompatActivity {

    //private EditText activity;
    private Spinner spinner;
    private CheckBox easy, medium, hard;
    private ArrayList<String> list = new ArrayList<String>();
    private String activity = "Escalada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.editText);
        String[] values = {"Escalada", "Barranquismo", "Rápel", "Raquetas de Nieve", "Vías Ferratas"};

        ArrayAdapter<String> arry =
                new ArrayAdapter<String>(
                        this, R.layout.sppiner_items, values);
        arry.setDropDownViewResource(R.layout.sppiner_items);
        spinner.setAdapter(arry);
        spinner.setOnItemSelectedListener(new Seleccion(this));

        addListenerOnChk();

        Button aceptar = (Button) findViewById(R.id.ok);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accept = new Intent(MainActivity.this, Cards.class);
                accept.putExtra("activity", activity);
                if (easy.isChecked()) {
                    accept.putExtra("level", "fácil");
                } else if (medium.isChecked()) {
                    accept.putExtra("level", "moderado");
                } else if (hard.isChecked()) {
                    accept.putExtra("level", "difícil");
                } else {
                    accept.putExtra("level", "all_levels");
                }
                startActivity(accept);
            }
        });

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accept = new Intent(MainActivity.this, AddCard.class);
                startActivity(accept);
            }
        });
    }

    public void addListenerOnChk() {

        easy = (CheckBox) findViewById(R.id.easy);
        medium = (CheckBox) findViewById(R.id.medium);
        hard = (CheckBox) findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    if (medium.isChecked()) {
                        medium.setChecked(false);
                    }
                    if (hard.isChecked()) {
                        hard.setChecked(false);
                    }
                }

            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    if (easy.isChecked()) {
                        easy.setChecked(false);
                    }
                    if (hard.isChecked()) {
                        hard.setChecked(false);
                    }
                }
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    if (easy.isChecked()) {
                        easy.setChecked(false);
                    }
                    if (medium.isChecked()) {
                        medium.setChecked(false);
                    }
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.climbing:
                Toast.makeText(getApplicationContext(), "Escalada Selected", Toast.LENGTH_SHORT).show();
                changeActivityDescr("Escalada");
                break;
            case R.id.canyoning:
                Toast.makeText(getApplicationContext(), "Barranquismo Selected", Toast.LENGTH_SHORT).show();
                changeActivityDescr("Barranquismo");
                break;
            case R.id.rapel:
                Toast.makeText(getApplicationContext(), "Rápel Selected", Toast.LENGTH_SHORT).show();
                changeActivityDescr("Rápel");
                break;
            case R.id.snowshoe:
                Toast.makeText(getApplicationContext(), "Raquetas de Nieve Selected", Toast.LENGTH_SHORT).show();
                changeActivityDescr("Raquetas de Nieve");
                break;
            case R.id.ferrata_roads:
                Toast.makeText(getApplicationContext(), "Vías Ferratas Selected", Toast.LENGTH_SHORT).show();
                changeActivityDescr("Vías Ferratas");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void changeActivityDescr(String activity) {
        Intent description = new Intent(MainActivity.this, Description.class);
        description.putExtra("activity", activity);
        startActivity(description);
    }

    private class Seleccion extends Activity implements AdapterView.OnItemSelectedListener {
        private MainActivity main;
        private boolean isFirst;

        public Seleccion(MainActivity m) {
            main = m;
            isFirst = true;
        }

        @Override
        public void onItemSelected(AdapterView<?> lista, View item,
                                   int idx, long id) {
            if (isFirst) {
                isFirst = false;
                return;
            }
            String str = lista.getItemAtPosition(idx).toString();
            activity = str;
            int time = Toast.LENGTH_SHORT;
            str += " seleccionado";
            Toast msg = Toast.makeText(MainActivity.this, str, time);
            msg.show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> lista) {
            int time = Toast.LENGTH_SHORT;
            String str = "nada seleccionado";
            Toast msg = Toast.makeText(MainActivity.this, str, time);
            msg.show();
        }
    }


}

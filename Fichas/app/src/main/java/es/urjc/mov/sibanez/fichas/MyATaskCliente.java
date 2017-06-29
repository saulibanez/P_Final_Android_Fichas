package es.urjc.mov.sibanez.fichas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MyATaskCliente extends AsyncTask<String, Void, String> {
    private Context context;

    public MyATaskCliente(Context context) {
        this.context = context;
    }


    /**
     * Ventana que bloqueara la pantalla del movil hasta recibir respuesta del servidor
     */
    ProgressDialog progressDialog;

    /**
     * muestra una ventana emergente
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Connecting to server");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    /*
     * recibe respuesta del servidor y formatea a String
     */
    private String requestServer(Socket socket) {
        Log.i("I/TCP Client", "Received data to server");
        InputStream stream = null;
        String received = "";
        try {
            stream = socket.getInputStream();
            byte[] lenBytes = new byte[2048];
            stream.read(lenBytes, 0, 2048);
            received = new String(lenBytes, "UTF-8").trim();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return received;
    }

    @Override
    protected String doInBackground(String... params) {
        String request = "";
        String received = "";

        Client cl = new Client();
        cl.connect();
        if (params.length == 1) {
            cl.writeTo(params[0]);
        } else if (params.length == 2) {
            cl.writeTo(params[0] + ";" + params[1]);
        } else {
            for (int i = 0; i < params.length; i++) {
                if (i == params.length - 1) {
                    request += params[i];
                } else {
                    request += params[i] + ";";
                }
            }
            cl.writeTo(request);
        }

        received = cl.readFrom();
        cl.closeAll();

        return received;
    }

    /**
     * Oculta ventana emergente y muestra resultado en pantalla
     */
    @Override
    protected void onPostExecute(String value) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}


package es.urjc.mov.sibanez.fichas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final int PORT = 5000;
    private static final String ADDRESS = "10.0.2.2";

    private Socket socket;
    private DataInputStream idata;
    private DataOutputStream odata;

    public Client() {
        try {
            this.socket = new Socket(ADDRESS, PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        InputStream instream = null;
        OutputStream outstream = null;
        try {
            instream = socket.getInputStream();
            outstream = socket.getOutputStream();
            idata = new DataInputStream(instream);
            odata = new DataOutputStream(outstream);
        } catch (IOException e) {
            System.err.println(this.socket + ": " + e);
        } catch (Exception e) {
            System.out.println("\nConection closed");
        }
    }

    public String readFrom() {
        String data = null;
        try {
            byte[] buffer = new byte[idata.readInt()];
            idata.readFully(buffer);
            data = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeTo(String name) {
        try {
            byte[] buffer = name.getBytes("UTF-8");
            odata.writeInt(buffer.length);
            odata.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new RuntimeException(this + "write: " + e);
        }
    }

    public void closeAll() {
        try {
            if (idata != null) {
                idata.close();
            }
            if (odata != null) {
                odata.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println(this + ": " + e);
        }
    }
}

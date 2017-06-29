package MainServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {
	
	private static final int PORT = 5000;
	private ServerSocket socket;
	private Thread thread;
	
	public MainServer(){
		try {
			this.socket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void connect(){
		try{
			System.out.println("wait clients ...");
			for(;;){
				Socket incon = socket.accept();
				System.out.println("client acepted " + incon.getRemoteSocketAddress());
				thread = new Thread(new Attend(incon));
				thread.start();	
			}
		} catch (IOException e) {
			System.err.println("connection error: " + e);
		} finally{
			try{
				if(socket != null){
					System.out.println("Cliente cerró");
					socket.close();
				}
			} catch (IOException e) {
				System.err.println("error to close: " + e);  	
			}
		}
	}
	
	private void start(){
		thread = new Thread(){
			public void run(){
				connect();
			}
		};
		thread.start();
	}
	
	private class Attend implements Runnable{

		private Socket incon;
		private DataInputStream idata;
		private DataOutputStream odata;
		private String card;

		public Attend(Socket incon){
			this.incon = incon;
			try {
				this.idata = new DataInputStream(incon.getInputStream());
				this.odata = new DataOutputStream(incon.getOutputStream());
				this.card = "";
			} catch (IOException e) {
				closeAll();
				thread.interrupt();
				throw new RuntimeException(this + "open streams: " + e);
			}
		}

		public String readFrom(DataInputStream idata){
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
		
		public void writeTo(DataOutputStream odata){
			try{
				byte[] buffer = card.getBytes("UTF-8");
				odata.writeInt(buffer.length);
				odata.write(buffer, 0, buffer.length);	
				odata.flush();
			}catch (IOException e){
				throw new RuntimeException(this + "write: " + e);
			}	
		}
	
		public void sendMessages(){

			Json j = new Json();
			try{
				String request = readFrom(idata);
				String[] strOutput = splitCampos(request);
				
				if(strOutput.length == 1){
					card = j.readJsonCardDescr(request);
				}else if(strOutput.length == 2){
					card = j.readJson(strOutput[0], strOutput[1]);
				}else{
					card = j.writeJson(strOutput);
				}
				
				writeTo(odata);
				
			}catch(RuntimeException e) {
				System.out.println("Closed the connection of client");
			}
		}
		
		public void run(){
			try{
				sendMessages();	
			} catch (Exception e) {
				System.out.println(e.getMessage());;	
			} finally{
				closeAll();
			}
		}
		public void closeAll(){
			try {
				if(idata != null){
					idata.close();
					idata = null;
				}
				if(odata != null){
					odata.close();
					odata = null;
				}	
				if(incon != null){
					incon.close();
					incon = null;
				}
			} catch (IOException e) {
				System.err.println(this + ": " + e);  
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(){
			public void run(){
				MainServer s = new MainServer();
				s.start();
			}		
		}.start();	
	}
	
	public static String[] splitCampos(String args) {
		String[] campos = args.split(";");
		for (int i = 0; i < campos.length; i++) {
			System.out.println(campos[i]);
		}
		
		return campos;
	}
	
}

package MainServer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {

	private static String PATH = "/home/alumnos/sibanez/workspace/Server/Climbing/climbing.json";
	private static String PATH_DESCR = "/home/alumnos/sibanez/workspace/Server/Climbing/activity.json";

	public String readJson(String activity, String level) {
		String student = "";

		JSONParser parser = new JSONParser();

		try {

			JSONArray a = null;
			a = (JSONArray) parser.parse(new FileReader(PATH));

			System.out.println(a);

			for (Object o : a) {
				JSONObject jsonObject = (JSONObject) o;

				String act = (String) jsonObject.get("activity");
				String desc = (String) jsonObject.get("description");
				String lvl = (String) jsonObject.get("level");

				String lat = (String) jsonObject.get("latitude");
				String lon = (String) jsonObject.get("longitude");

				String city = (String) jsonObject.get("city");
				String zone = (String) jsonObject.get("zone");
				String time = (String) jsonObject.get("time");
				String dist = (String) jsonObject.get("distance");

				String ph = (String) jsonObject.get("photo");
				String route_ph = "";
				try {
					if(!ph.equals("null")){
						route_ph = getImage(ph);
					}else{
						route_ph = "null";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(act.equals(activity)){
					student += act + ";" + desc + ";" + lvl + ";" + lat + ";" + lon + ";" + city + ";" + zone + ";"
							+ time + ";" + dist + ";" + route_ph + "|";
				}
				
			}

		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}

		return student;
	}

	public String readJsonCardDescr(String activity) {
		String card = "";
		JSONParser parser = new JSONParser();

		try {

			JSONArray a = null;
			a = (JSONArray) parser.parse(new FileReader(PATH_DESCR));

			System.out.println(a);

			for (Object o : a) {
				JSONObject jsonObject = (JSONObject) o;

				String act = (String) jsonObject.get("activity");
				String desc = (String) jsonObject.get("description");
				String ph = (String) jsonObject.get("photo");
				String route_ph = "";
				try {
					route_ph = getImage(ph);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (act.equals(activity)) {
					// System.out.println("activity: " + act);
					card += act + ";" + desc + ";" + route_ph + "|";
				}
			}

		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}

		return card;
	}

	@SuppressWarnings("unchecked")
	public String writeJson(String args[]) {
		JSONObject obj = new JSONObject();
		String cards_activity[] = { "activity", "description", "level", "city", "zone",
				"time", "distance", "latitude", "longitude", "photo" };

		String request = "";

		if (cards_activity.length == args.length) {

			for (int i = 0; i < args.length; i++) {
				obj.put(cards_activity[i], args[i]);
			}

			try {
				String origin_json = readFile();

				FileWriter file = new FileWriter(PATH);
				file.write(origin_json);
				file.write(obj.toJSONString());
				file.write("\n" + "]");
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			request = "ok";
		} else {
			System.err.println("No se ha podido modificar el json");
			request = "fail";
		}
		return request;
	}

	private String readFile() {
		String s = "";
		String cadena = "";
		try {
			FileReader f = new FileReader(PATH);
			BufferedReader br = new BufferedReader(f);
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				if (!s.equals("]"))
					cadena += s + "\n";
			}
			cadena += ",";
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cadena;
	}
	
	//enviar una ficha
	protected static String getImage(String route) throws Exception{
    	File imageFile= new File(route);
    	System.err.println(route);

        String image=null;
        BufferedImage buffImage = ImageIO.read(imageFile);

        if (buffImage != null) {
       	 java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
       	 ImageIO.write(buffImage, "jpg", os);
       	 byte[] data = os.toByteArray();
       	 image = B64.encode(data);
        }
        
        return image;
    }
    
	//añadir en una carpeta, una imagen de verdad
    protected static void createImage(String imageString, String name) throws IOException{

    	byte[] imageByte = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageString);
    	ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
    	BufferedImage image = ImageIO.read(bis);
    	bis.close();

    	File outputfile = new File("/home/saul/Escritorio/Climbing/"+name+".jpg");
    	ImageIO.write(image, "jpg", outputfile);
    	
    }
}

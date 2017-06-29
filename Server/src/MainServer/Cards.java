package MainServer;

public class Cards {
	
    private String activity;
    private String latitude;
    private String longitude;
    private String city;
    private String zone;
    private String description;
    private String level;
    private String time;
    private String distance;
    private String photo;
    
	public Cards(String activity, String latitude, String longitude, String city, String zone, String description,
			String level, String time, String distance, String photo) {
		super();
		this.activity = activity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.zone = zone;
		this.description = description;
		this.level = level;
		this.time = time;
		this.distance = distance;
		this.photo = photo;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}

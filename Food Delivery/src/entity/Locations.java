package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Locations {
	
	@Id
	@Column(name="PLACE")	
	private String place;
	
	@Column(name="LONGITUDE")
	private double longitude;
	
	@Column(name="LATITUDE")	
	private double latitude;
	
	
	public Locations() {}
	
	public Locations(String place, double longitude, double latitude ) {
		this.place = place;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Locations [place=" + place+ "]";
	}	
}

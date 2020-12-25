package services;

import java.util.*;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Locations;
import util.HibernateUtil;

public class LocationService {
	HashMap<String, ArrayList<Double>> locations = new HashMap<String, ArrayList<Double>>();
	
	public ArrayList<Double> getLocation(String location){
		if(locations.isEmpty()) {
			getLocationsDB();
		}
		return locations.get(location);
	}
	
	public double getDistance(String currLocation, String pickupLocation) {
		double reqDistance = 0;
		
		if(currLocation == null || pickupLocation == null) {
			return reqDistance;
		}
		
		ArrayList<Double> currPoints = getLocation(currLocation);
		ArrayList<Double> pickPoints = getLocation(pickupLocation);
		
		reqDistance = Math.sqrt(Math.pow(pickPoints.get(0) - currPoints.get(0),2) + Math.pow(pickPoints.get(1) - currPoints.get(1),2));
		return reqDistance;
	}
	
	
	@SuppressWarnings("unchecked")
	private void getLocationsDB(){
		List<Locations> dbLocations ;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query queryLocations = session.createQuery("select location from Locations as location");
		dbLocations = queryLocations.list();
		
		session.getTransaction().commit();
		session.close();
		
		//Output
		for(Locations location: dbLocations) {
			ArrayList<Double> currLocation = new ArrayList<Double>();
			currLocation.add(location.getLatitude());
			currLocation.add(location.getLongitude());
			locations.put(location.getPlace(), currLocation);
		}
		
		
		
	}
}

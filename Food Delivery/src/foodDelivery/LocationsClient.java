package foodDelivery;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
//import entity.Locations;
//import entity.User;
import entity.DeliveryBoy;
import entity.Locations;
import entity.Request;
import entity.User;
import java.lang.Math;
import util.HibernateUtil;
import services.LocationService;

public class LocationsClient {
	/*
 static double calcDistance(String dboylocation,String pickup) {
		double distance = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Locations location = (Locations)session.get(Locations.class, dboylocation);
		double x1 = location.getLatitude();
		double y1 = location.getLongitude();
		
		location = (Locations)session.get(Locations.class, pickup);
		double x2 = location.getLatitude();
		double y2 = location.getLongitude();
		
		distance = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		
		
		session.getTransaction().commit();
		session.close();
		
		return distance;
	}
	*/
	
	//@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//User user1;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Request req1 = new Request("Varun","8790563878","Jubille hills","Borabanda","Laxman","true");
		Request req3 = new Request("Abhishek","8854676237","Shilparamam","Balanagar","","false");
		Request req2 = new Request("Ajay","9978854289","Banjaara hills","Raidurg","","false");
		Request req4 = new Request("Rahul","9866593656","Sanath Nagar","Khajaguda","","false");
		session.save(req1);
		session.save(req2);
		session.save(req3);
		session.save(req4);
		
		//session.save(user2);
	
		session.getTransaction().commit();
		session.close();
		
		
		/*
		@SuppressWarnings("unchecked")
		public static ArrayList<DeliveryBoy> getDrivers(String pickupLocation){
		ArrayList<DeliveryBoy> dBoys;
		ArrayList<DeliveryBoy> availableBoys = new ArrayList<DeliveryBoy>();
		LocationService locServ = new LocationService();
		String dropLocation = pickupLocation;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select dboys from DeliveryBoy as dboys");
		dBoys = (ArrayList) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		for(DeliveryBoy dBoy : dBoys) {
			if(dBoy.getStatus().equals("Available")) {
				double distPoss = locServ.getDistance(dBoy.getAddress(), dropLocation);
				if(!(distPoss > dBoy.getAcceptDistance())) {
					availableBoys.add(dBoy);
				}
			}
			else if(dBoy.getStatus().equals("Busy")){
				if(dBoy.getDropLocation().equals(dropLocation)) {
					availableBoys.add(dBoy);
				}
			}
		}
		//System.out.println(availableBoys + " ");
		return dBoys;
	}
	*/
	}
}

package services;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import entity.DeliveryBoy;
import entity.User;
import util.HibernateUtil;

public class DeliveryService {
	public static boolean signup = false;
	public static DeliveryBoy CurrentDB;
	
	public static void setUserPersist(DeliveryBoy db) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CurrentDB = db;
		session.save(db);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public static void updateUser() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		//CurrentDB = db;
		session.update(CurrentDB);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public static void updateSpecUser(DeliveryBoy db) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		//CurrentDB = db;
		session.update(db);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public static DeliveryBoy getUserPersist(String userName) {
		DeliveryBoy db;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		db = (DeliveryBoy) session.get(DeliveryBoy.class, userName);
		CurrentDB = db;
		session.getTransaction().commit();
		session.close();
		
		return db;
	}
	
	public static DeliveryBoy getDeliveryBoy(String userName) {
		
		DeliveryBoy db;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		db = (DeliveryBoy) session.get(DeliveryBoy.class, userName);
		
		session.getTransaction().commit();
		session.close();
		
		return db;
	}
	
	@SuppressWarnings("unchecked")
	public static List<DeliveryBoy> getDrivers(double dist){
		List<DeliveryBoy> dBoys;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select dboys from DeliveryBoy as dboys where dboys.status=:status and dboys.distance >= :distance");
		query.setString("status", "Available");
		query.setDouble("distance", dist);
		
		dBoys = query.list();
		
		for(DeliveryBoy dBoy : dBoys) {
			System.out.print(dBoy);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return dBoys;
	}
	//selecting drivers
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
	
	/*public static double updateReview(double newreview)
	{
		double oldreview = CurrentDB.getRating();
		int noofreviews	= CurrentDB.getNoOfReviws();
		double updatedreview = ((oldreview*noofreviews) + newreview)/(noofreviews +1);
		noofreviews += 1;
		CurrentDB.setNoOfReviws(noofreviews);
		CurrentDB.setRating(updatedreview);
		updateUser();
		return updatedreview;
	}*/
}

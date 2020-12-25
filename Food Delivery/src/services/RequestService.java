package services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Locations;
import util.HibernateUtil;
import entity.Request;
import entity.DeliveryBoy;

public class RequestService {
	
	LocationService locServ = new LocationService();
	public static Request getRequestDetails(String bookingId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select request from Request as request where bookingid=:bookingId");
		query.setString("bookingId", bookingId);
		//Request currRequest = (Request)session.get(Request.class, id);
		
		Request currRequest = (Request)query.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return currRequest;
	}
	
	public void setRequest(Request currRequest) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(currRequest);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateRequest(Request currRequest) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(currRequest);
		
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Request> getRequestsDeliveryBoy(DeliveryBoy deliveryBoy){
		ArrayList<Request> requests = new ArrayList<Request>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query queryRequests = session.createQuery("select request from Request as request");
		List<Request> resultList = queryRequests.list();
		
		session.getTransaction().commit();
		session.close();
		
		for(Request currRequest : resultList) {
			String pickupLocation 	= currRequest.getPickup();
			String currLocationBoy 	= deliveryBoy.getAddress();
			String dropLocation 	= currRequest.getDrop();
			
			double currDist = locServ.getDistance(currLocationBoy, pickupLocation);
			
			if(!currRequest.getAccepted().equals("false")) {
				continue;
			}
						
			if(currDist <= deliveryBoy.getAcceptDistance() && deliveryBoy.getStatus().equals("Available")) {
				requests.add(currRequest);
				continue;
			}
			if(deliveryBoy.getStatus().equals("Busy") &&  dropLocation.equals(deliveryBoy.getDropLocation())){
				requests.add(currRequest);
				continue;
			}
		}
		
		return requests;
	}
}

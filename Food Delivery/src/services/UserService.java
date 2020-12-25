package services;

import org.hibernate.Session;


import entity.User;
import entity.Locations;
import entity.Request;
import util.HibernateUtil;
import java.lang.Math;
import java.util.regex.Pattern;
public class UserService {
	public static boolean signup = false;
	public static User CurrentUser; //Store who is the present user doubt with static
	//static String CurrentUserName =  CurrentUser.getUsername();
	public static void updateVisited() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		CurrentUser.setVisited(CurrentUser.getVisited()+1);
		
		session.update(CurrentUser);
		session.getTransaction().commit();
		session.close();
		}
	
	public static void setUserAddress(String address) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		CurrentUser.setAddress(address);
		
		session.update(CurrentUser);
		session.getTransaction().commit();
		session.close();
		}
	public static void updateUser() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(CurrentUser);
		
		session.getTransaction().commit();
		session.close();
	}
	public static String getDropLocation() {
		return CurrentUser.getAddress();
		}
	
	public static void updateWallet(int amountAdding) { 
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		CurrentUser.setWallet(CurrentUser.getWallet() + amountAdding);
		
		session.update(CurrentUser);
		session.getTransaction().commit();
		session.close();
		}
	
	public static int getWallet() {
		//return Integer.toString(CurrentUser.getWallet());
		return CurrentUser.getWallet();
		}
	public static boolean IsPhoneNumber(String number)
    {
        return Pattern.matches("[789]{1}[0-9]{9}", number);
    }
	public static void setUserPersist(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CurrentUser = user;
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public static User getUserPersist(String userName) {
		User user;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		user = (User)session.get(User.class, userName);
		CurrentUser = user;
		session.getTransaction().commit();
		session.close();
		
		return user;
	}
	
	public static int totalCost(String pickup, String drop, double packPrice) {
		System.out.println(pickup+ " "+ drop);
		System.out.println(packPrice);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Locations location = (Locations)session.get(Locations.class, pickup);
		double x1 = location.getLatitude();
		double y1 = location.getLongitude();
		
		location = (Locations)session.get(Locations.class, drop);
		double x2 = location.getLatitude();
		double y2 = location.getLongitude();
		
		int customer = CurrentUser.getVisited();
		double mealCost = packPrice;
        double distance;
        double deliveryCost;
        double totalCost;
        double discount;
        int finalCost = 0;
        
        System.out.println( packPrice);
         // x1,y1 are coordinates of 'pickup' and x2,y2 of 'drop'
        distance = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
        deliveryCost = 15 * distance;
        
        totalCost = mealCost + deliveryCost;
        discount = 0.2*totalCost;
        
        // Please enter 1 if customer is regular and 0 if occasional
        if(customer > 4) {
            finalCost = (int) (totalCost - discount);
        }
        else if(customer <= 4) {
            finalCost = (int) totalCost;
        }
		//String costing = Integer.toString(finalCost);
		return finalCost;
	}
	

}

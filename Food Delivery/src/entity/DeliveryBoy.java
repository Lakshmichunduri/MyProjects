package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="deliveryboy")
public class DeliveryBoy {
	@Id
	@Column(name="USERNAME")	
	private String username;
	
	@Column(name="EMAILID")
	private String emailId;
	
	@Column(name="PHONENO")	
	private String phoneNo;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="RATING")
	private double rating;
	
	@Column(name="DISTANCE")
	private double acceptDistance;
	
	@Column(name="DROPLOCATION")
	private String dropLocation;
	
	@Column(name="NOOFREVIEWS")
	private int noOfReviws;
	
	public DeliveryBoy() {
	}
	public DeliveryBoy(String username, String emailId, String phoneNo, String password) {
		this.username = username;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
	}
	
	public DeliveryBoy(String username, String emailId, String phoneNo, String password, String address, String status,
			double rating, double acceptDistance, String dropLocation, int noOfReviws) {
		this.username = username;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
		this.address = address;
		this.status = status;
		this.rating = rating;
		this.acceptDistance = acceptDistance;
		this.dropLocation = dropLocation;
		this.noOfReviws = noOfReviws;
	}
	
	public DeliveryBoy(String username, String emailId, String phoneNo, String password, String status,
			double rating, double acceptDistance, int noOfReviws) {
		this.username = username;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
		this.status = status;
		this.rating = rating;
		this.acceptDistance = acceptDistance;
		this.noOfReviws = noOfReviws;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getAcceptDistance() {
		return acceptDistance;
	}
	public void setAcceptDistance(double acceptDistance) {
		this.acceptDistance = acceptDistance;
	}
	public String getDropLocation() {
		return dropLocation;
	}
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	public int getNoOfReviws() {
		return noOfReviws;
	}
	public void setNoOfReviws(int noOfReviws) {
		this.noOfReviws = noOfReviws;
	}
	@Override
	public String toString() {
		return "Current User " + this.username;
	}
	
}

package entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="request")
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="ID")
	private Long id;

	@Column(name="USERNAME")	
	private String username;
	
	@Column(name="PHONENO")	
	private String phoneNo;
	
	@Column(name="PICKUPLOC")
	private String pickupLoc;
	
	@Column(name="DROPLOC")
	private String dropLoc;
	
	@Column(name="DELIVERYBOY")
	private String deliveryBoy;
	
	@Column(name="BOOKINGID")	
	private String bookingId;
	
	@Column(name="ACCEPTED")	
	private String accepted;

	@Override
	public String toString() {
		return this.username;
	}

	public Request() {
	}

	public Request(String username, String phoneNo, String pickup, String drop, String deliveryBoy) {
		this.username = username;
		this.phoneNo = phoneNo;
		this.pickupLoc = pickup;
		this.dropLoc = drop;
		this.deliveryBoy = deliveryBoy;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPickup() {
		return pickupLoc;
	}

	public void setPickup(String pickup) {
		this.pickupLoc = pickup;
	}

	public String getDrop() {
		return dropLoc;
	}

	public Request(String username, String phoneNo, String pickupLoc, String dropLoc, String bookingId,
			String accepted) {
		super();
		this.username = username;
		this.phoneNo = phoneNo;
		this.pickupLoc = pickupLoc;
		this.dropLoc = dropLoc;
		this.bookingId = bookingId;
		this.accepted = accepted;
	}

	public void setDrop(String drop) {
		this.dropLoc = drop;
	}

	public String getDeliveryBoy() {
		return deliveryBoy;
	}

	public void setDeliveryBoy(String deliveryBoy) {
		this.deliveryBoy = deliveryBoy;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	
	
}

package entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="USERNAME")	
	private String username;
	
	@Column(name="EMAILID")
	private String emailId;
	
	@Column(name="PHONENO")	
	private String phoneNo;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="VISITED")
	private int visited;
	
	@Column(name="WALLET")
	private int wallet;

	@Column(name="ADDRESS")
	private String address;
	

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

	public int getVisited() {
		return visited;
	}

	public void setVisited(int visited) {
		this.visited = visited;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public User() {
	}

	public User(String username, String emailId, String phoneNo, String password) {
		this.username = username;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
	}
	public User(String username, String emailId, String phoneNo, String password, int visited, int wallet,
			String address) {
		this.username = username;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
		this.visited = visited;
		this.wallet = wallet;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Current User " + this.username;
	}
}

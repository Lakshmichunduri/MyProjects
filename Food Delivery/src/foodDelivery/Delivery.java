package foodDelivery;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.EmptyBorder;

import entity.DeliveryBoy;
import entity.Request;
import services.UserService;
import services.DeliveryService;
import services.RequestService;
import services.SendMailBySite;

public class Delivery extends JFrame {
	private JFrame currDelv = this;
	private JPanel contentPane,bookPan;
	private JTextField txtBill;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public double packprice;
	double finalCost = 0;
	String drop,pickup;
	private final JLabel Wallet = new JLabel("Wallet :");
	private JTextField amountToAdd;
	private JTextField walletAmount;
	int billing = 0;
	int wallet = 0;
	String bookingId;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delivery frame = new Delivery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Delivery() {
		setTitle("User Address");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 702);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pickup Point : \r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(153, 51, 153));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		//lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBounds(10, 97, 192, 35);
		contentPane.add(lblNewLabel);
		
		JLabel delivery = new JLabel("Delivery Point : \r\n");
		delivery.setBackground(Color.WHITE);
		delivery.setHorizontalAlignment(SwingConstants.CENTER);
		delivery.setFont(new Font("Tahoma", Font.BOLD, 20));
		delivery.setForeground(new Color(153, 51, 153));
		delivery.setBounds(15, 177, 199, 35);
		contentPane.add(delivery);
		// Handle selections
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.addItem("Balanagar");
		comboBox.addItem("Banjaara hills");
		comboBox.addItem("Borabanda");
		comboBox.addItem("Gachibowli");
		comboBox.addItem("Hitec city");
		comboBox.addItem("Jubille hills");
		comboBox.addItem("Khajaguda");
		comboBox.addItem("Kothaguda");
		comboBox.addItem("Motinagar");
		comboBox.addItem("Nandagiri hills");
		comboBox.addItem("Raidurg");
		comboBox.addItem("Sanath Nagar");
		comboBox.addItem("Shilparamam");
		comboBox.addItem("Yousufguda");
		comboBox.setSelectedItem(null);
		comboBox.setBounds(32, 141, 202, 29);
		comboBox.setForeground(new Color(102, 0, 102));
		contentPane.add(comboBox);
		// Handle selections
		comboBox.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pickup = (String) comboBox.getSelectedItem();
				System.out.println(pickup);
			}
		});
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.addItem("Balanagar");
		comboBox_1.addItem("Banjaara hills");
		comboBox_1.addItem("Borabanda");
		comboBox_1.addItem("Gachibowli");
		comboBox_1.addItem("Hitec city");
		comboBox_1.addItem("Jubille hills");
		comboBox_1.addItem("Khajaguda");
		comboBox_1.addItem("Kothaguda");
		comboBox_1.addItem("Motinagar");
		comboBox_1.addItem("Nandagiri hills");
		comboBox_1.addItem("Raidurg");
		comboBox_1.addItem("Sanath Nagar");
		comboBox_1.addItem("Shilparamam");
		comboBox_1.addItem("Yousufguda");
		comboBox_1.setSelectedItem(UserService.getDropLocation());
		//comboBox_1.setSelectedItem(null);
		comboBox_1.setBounds(32, 224, 206, 29);
		comboBox_1.setForeground(new Color(102, 0, 102));
		contentPane.add(comboBox_1);
		drop = UserService.getDropLocation();
		// Handle selections
				comboBox_1.addActionListener((ActionListener) new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent ae) {
						// TODO Auto-generated method stub
						drop = (String) comboBox_1.getSelectedItem();
						if(UserService.signup) {
							UserService.setUserAddress(drop);
						}
						System.out.println(drop);
					}
				});
		if(UserService.signup) {
			UserService.CurrentUser.setAddress(drop);
			UserService.updateUser();
		}
		JLabel packetSize = new JLabel("Packet Size");
		packetSize.setHorizontalAlignment(SwingConstants.CENTER);
		packetSize.setFont(new Font("Tahoma", Font.BOLD, 20));
		packetSize.setForeground(new Color(153, 51, 153));
		packetSize.setBackground(Color.BLACK);
		packetSize.setBounds(6, 14, 172, 44);
		contentPane.add(packetSize);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Small ");
		//rdbtnNewRadioButton.setActionCommand("120");
		//rdbtnNewRadioButton.addActionListener(this);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton.setBounds(45, 62, 90, 26);
		rdbtnNewRadioButton.setBackground(new Color(255, 204, 255));
		rdbtnNewRadioButton.setForeground(new Color(102, 0, 102));
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Medium ");
		//rdbtnNewRadioButton_1.setActionCommand("180");
		//rdbtnNewRadioButton_1.addActionListener(this);
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton_1.setBounds(225, 62, 103, 25);
		rdbtnNewRadioButton_1.setBackground(new Color(255, 204, 255));
		rdbtnNewRadioButton_1.setForeground(new Color(102, 0, 102));
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Large ");
		//rdbtnNewRadioButton_2.setActionCommand("240");
		//rdbtnNewRadioButton_2.addActionListener(this);
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton_2.setBounds(400, 62, 90, 22);
		rdbtnNewRadioButton_2.setBackground(new Color(255, 204, 255));
		rdbtnNewRadioButton_2.setForeground(new Color(102, 0, 102));
		contentPane.add(rdbtnNewRadioButton_2);
		
		// Handle selections
		rdbtnNewRadioButton.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(rdbtnNewRadioButton.isSelected()) packprice = 10;
		        	System.out.println(packprice);
		        }			
		});
		rdbtnNewRadioButton_1.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(rdbtnNewRadioButton_1.isSelected()) packprice = 30;
		        	System.out.println(packprice);
		        }			
		});
		rdbtnNewRadioButton_2.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(rdbtnNewRadioButton_2.isSelected()) packprice = 50;
		        	System.out.println(packprice);
		        }			
		});
		/*rdbtnNewRadioButton.addActionListener((ActionListener) new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent ab) {
		        if (ab.getActionCommand().equals("Check")) {
		        	packprice = Integer.parseInt(ab.getActionCommand());
		        	System.out.println(packprice);
		            //packprice = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
		        }
		    }			
		});*/
		/*public void actionPerformed (ActionEvent ab); {
	        
	        	packprice = Integer.parseInt(ab.getActionCommand());
	        	System.out.println(packprice);
	            //packprice = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
	        
	    }*/
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 255));
		panel.setBounds(2, 343, 570, 312);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		
		JLabel bill = new JLabel("Bill : ");
		bill.setBounds(10, 10, 76, 37);
		panel.add(bill);
		bill.setHorizontalAlignment(SwingConstants.CENTER);
		bill.setFont(new Font("Tahoma", Font.BOLD, 20));
		bill.setForeground(new Color(153, 51, 153));
		//bill.setFont(new Font("Tahoma", Font.PLAIN, 25));
		//bill.setForeground(new Color(0, 0, 139));
		
		txtBill = new JTextField();
		txtBill.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBill.setHorizontalAlignment(SwingConstants.CENTER);
		txtBill.setForeground(new Color(102, 0, 102));
		txtBill.setBounds(113, 15, 101, 34);
		panel.add(txtBill);
		txtBill.setColumns(10);
		
		Wallet.setHorizontalAlignment(SwingConstants.CENTER);
		Wallet.setFont(new Font("Tahoma", Font.BOLD, 20));
		Wallet.setForeground(new Color(153, 51, 153));
		Wallet.setBounds(20, 48, 89, 42);
		panel.add(Wallet);
		//Wallet.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JButton addMoney = new JButton("Add money");
		addMoney.setFont(new Font("Tahoma", Font.BOLD, 14));
		addMoney.setBounds(150, 110, 131, 42);
		addMoney.setForeground(new Color(102, 0, 102));
		panel.add(addMoney);
		
		JButton pay = new JButton("Pay");
		pay.setFont(new Font("Tahoma", Font.BOLD, 14));
		pay.setBounds(347, 110, 131, 42);
		pay.setForeground(new Color(102, 0, 102));
		panel.add(pay);
		
		walletAmount = new JTextField();
		walletAmount.setHorizontalAlignment(SwingConstants.CENTER);
		walletAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		walletAmount.setBounds(113, 59, 101, 34);
		walletAmount.setForeground(new Color(102, 0, 102));
		panel.add(walletAmount);
		walletAmount.setColumns(10);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(137, 162, 144, 99);
		panel_1.setBackground(new Color(255, 204, 255));
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		amountToAdd = new JTextField();
		amountToAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		amountToAdd.setHorizontalAlignment(SwingConstants.CENTER);
		amountToAdd.setBounds(30, 10, 115, 34);
		amountToAdd.setForeground(new Color(102, 0, 102));
		panel_1.add(amountToAdd);
		amountToAdd.setText("Amount to add");
		amountToAdd.setColumns(10);
		
		
		
		JButton add = new JButton("Add");
		add.setBounds(35, 54, 80, 35);
		add.setFont(new Font("Tahoma", Font.BOLD, 15));
		add.setForeground(new Color(102, 0, 102));
		panel_1.add(add);
		
		JLabel paymentStatus = new JLabel("");
		paymentStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentStatus.setHorizontalAlignment(SwingConstants.CENTER);
		paymentStatus.setBounds(347, 172, 131, 22);
		paymentStatus.setForeground(new Color(102, 0, 102));
		panel.add(paymentStatus);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(208, 277, 153, 42);
		contentPane.add(submit);
		//submit.setBackground(new Color(204, 153, 255));
		submit.setFont(new Font("Tahoma", Font.BOLD, 23));
		submit.setForeground(new Color(102, 0, 102));
		
		bookPan = new JPanel();
		bookPan.setBounds(330, 200, 200, 57);
		bookPan.setBackground(new Color(255, 204, 255));
		panel.add(bookPan);
		bookPan.setLayout(null);
		bookPan.setVisible(false);
		
		JButton bookingDetails = new JButton("Booking Details");
		bookingDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		bookingDetails.setBounds(10, 10, 180, 45);
		bookingDetails.setForeground(new Color(102, 0, 102));
		bookPan.add(bookingDetails);
		
		JLabel findingLabel = new JLabel("");
		findingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		findingLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		findingLabel.setForeground(new Color(102, 0, 102));
		findingLabel.setBounds(291, 262, 278, 25);
		panel.add(findingLabel);
		
		submit.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent k) {
				billing = UserService.totalCost(pickup,drop,packprice);
				wallet = UserService.getWallet();
				String x = Integer.toString(billing);
		    	txtBill.setText(x);
		    	walletAmount.setText(Integer.toString(wallet)); //get's wallet balance 
		    	panel.setVisible(true);
			}
		});
		
		addMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(billing-wallet>0)
					amountToAdd.setText(Integer.toString(billing-wallet));
				else
					amountToAdd.setText("100");
				panel_1.setVisible(true);
			}
		});
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
			//write here;
				UserService.updateWallet(Integer.parseInt(amountToAdd.getText()));
				walletAmount.setText(Integer.toString(UserService.getWallet()));
				panel_1.setVisible(false);
				//update database
				//setwallet amount again
			}
		});
		
		
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				int bill = UserService.totalCost(pickup,drop,packprice);
				int walletPresent = UserService.getWallet();
				if(bill > walletPresent) {
					 //panel_1.setVisible(false);
					 paymentStatus.setText("Insufficient Amount");
				}
				else
				{
					//go to next page
					//initiate many many things
					
					panel_1.setVisible(false);
					UserService.updateVisited();
					UserService.updateWallet(-1*billing);
					walletAmount.setText(Integer.toString(UserService.getWallet()));
					//List<DeliveryBoy> drivers = DeliveryService.getDrivers(drop);//get drivers
					bookingId = randGen();
					Request thisRequest = new Request(UserService.CurrentUser.getUsername(),UserService.CurrentUser.getPhoneNo(),pickup,drop, bookingId,"false");
					RequestService reqServ = new RequestService();
					reqServ.setRequest(thisRequest);
					paymentStatus.setText("");
					
					bookPan.setVisible(true);
					//call the launching function
				}
				
			}
		});
		bookingDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {	
				try {
					Request reqDetails = RequestService.getRequestDetails(bookingId);
					String dBoyName = reqDetails.getDeliveryBoy();
					String toMail = UserService.CurrentUser.getEmailId();
					SendMailBySite.sendVerificationMail(toMail,bookingId,dBoyName);
					UserGetsDetails userpg3 = new UserGetsDetails(bookingId);
					currDelv.dispose();
					
				//getsdetails
				/*
				Request reqDetails = RequestService.getRequestDetails(bookingId);
				String dBoyName = reqDetails.getDeliveryBoy();
				DeliveryBoy dBoy = DeliveryService.getDeliveryBoy(dBoyName);
				
				userpg3.namev.setText(dBoy.getUsername());
				userpg3.bookv.setText(reqDetails.getBookingId());
				userpg3.phnov.setText(dBoy.getPhoneNo());
				*/
					userpg3.setVisible(true);
				
				}
				catch(Exception e) {
					findingLabel.setText("Connecting to near by Delivery boys");
				}
			}
		});
	}
	public void showBookPan() {
		bookPan.setVisible(true);
	}
	
	public String randGen(){
		Random rand = new Random();
		int num = rand.nextInt(1000000);
		String bookingId = Integer.toString(num);
		return bookingId;
	}
}
package foodDelivery;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entity.DeliveryBoy;
import entity.Request;
import services.DeliveryService;
import services.RequestService;
import services.UserService;
import util.RandomGen1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class DetailsToDelBoy extends JFrame {
	private JFrame currComp = this;
	private JPanel contentPane = new JPanel();
	private JTextField accDisText;
	private RequestService reqServ = new RequestService();
	/*private JTextField idtext1;
	private JTextField idtext2;
	private JTextField idtext3;
	private JTextField otptext1;
	private JTextField otptext2;
	private JTextField otptext3;*/
	
	ArrayList<Request> requests = getRequestDetails();
	
	int panelBounds[][] 	= {{5, 128, 421, 118},{5, 259, 421, 118},{5, 390, 421, 118}};
	int pickupBounds[][] 	= {{10, 142, 188, 28},{10, 268, 188, 28},{10, 398, 188, 28}};
	int dropBounds[][]		= {{229, 134, 201, 36},{229, 268, 188, 28},{229, 398, 188, 28}};
	int nameBounds[][]		= {{448, 134, 201, 36},{448, 268, 177, 28},{448, 398, 177, 28}};
	int phnoBounds[][]		= {{678, 134, 191, 36},{668, 268, 201, 28},{668, 398, 201, 28}};
	int acceptBounds[][]    = {{80, 191, 308, 36},{80, 320, 308, 36},{80, 459, 308, 36}};
	int idLabelBounds[][]	= {{448, 191, 57, 36},{448, 320, 57, 36},{448, 459, 51, 36}};
	int idTextBounds[][]	= {{515, 189, 96, 36},{515, 320, 96, 36},{515, 459, 96, 36}};
	int otpLabelBounds[][]	= {{634, 191, 75, 36},{634, 320, 75, 36},{634, 459, 75, 36}};
	int otpTextBounds[][]  = {{720, 191, 166, 36},{720, 320, 166, 36},{720, 459, 166, 36}};
	int userDetPanBounds[][]= {{431, 128, 468, 118},{431, 259, 468, 118},{431, 386, 468, 118}};
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 DetailsToDelBoy frame = new  DetailsToDelBoy();
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 *
	 */
	
	public  DetailsToDelBoy() {
		setTitle("Details for the Delivery Boy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 542);
		//contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel pickup = new JLabel("Pickup Point");
		pickup.setForeground(new Color(51, 102, 0));
		pickup.setHorizontalAlignment(SwingConstants.CENTER);
		pickup.setFont(new Font("Tahoma", Font.BOLD, 24));
		pickup.setBounds(10, 84, 188, 40);
		contentPane.add(pickup);
		
		JLabel drop = new JLabel("Drop Point");
		drop.setForeground(new Color(51, 102, 0));
		drop.setHorizontalAlignment(SwingConstants.CENTER);
		drop.setFont(new Font("Tahoma", Font.BOLD, 24));
		drop.setBounds(229, 84, 188, 40);
		contentPane.add(drop);
		
		JLabel name = new JLabel("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setForeground(new Color(51, 102, 0));
		name.setFont(new Font("Tahoma", Font.BOLD, 24));
		name.setBounds(462, 84, 163, 40);
		contentPane.add(name);
		
		JLabel phno = new JLabel("Phone Number");
		phno.setHorizontalAlignment(SwingConstants.CENTER);
		phno.setFont(new Font("Tahoma", Font.BOLD, 24));
		phno.setForeground(new Color(51, 102, 0));
		phno.setBounds(668, 84, 201, 40);
		contentPane.add(phno);
		
		JButton logout = new JButton("Logout");
		logout.setBackground(new Color(152, 251, 152));
		logout.setForeground(new Color(0, 102, 102));
		logout.setFont(new Font("Tahoma", Font.BOLD, 24));
		logout.setBounds(736, 10, 163, 46);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeliveryService.CurrentDB.setStatus("Offduty");
				DeliveryService.updateUser();
			}
		});
		contentPane.add(logout);
		
		JLabel accDistLabel = new JLabel("Distance accepted (in KM) :");
		accDistLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		accDistLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accDistLabel.setBounds(98, 22, 228, 25);
		contentPane.add(accDistLabel);
		
		accDisText = new JTextField();
		accDisText.setHorizontalAlignment(SwingConstants.CENTER);
		accDisText.setFont(new Font("Tahoma", Font.BOLD, 15));
		accDisText.setBounds(336, 24, 96, 25);
		accDisText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double dist = Double.parseDouble(accDisText.getText());
				DeliveryService.CurrentDB.setAcceptDistance(dist);
				DeliveryService.updateUser();
				DetailsToDelBoy boypage2_1 = new DetailsToDelBoy();
				boypage2_1.setVisible(true);
				currComp.dispose();
				//SwingUtilities.updateComponentTreeUI(currComp);
				//currComp.setVisible(false);
				//currComp.setVisible(true);
			}
		});
		contentPane.add(accDisText);
		accDisText.setColumns(10);
		
		createAccPanels();
	}
	
	private ArrayList<Request> getRequestDetails(){
		ArrayList<Request> currentRequests;
		ArrayList<Request> finalRequests = new ArrayList<Request>();
		DeliveryBoy deliveryBoy = DeliveryService.CurrentDB;
		//RequestService reqServ = new RequestService();
		currentRequests = reqServ.getRequestsDeliveryBoy(deliveryBoy);
		
		for(int i = 0 ; i < currentRequests.size(); i++) {
			if(i < 3) {
				finalRequests.add(currentRequests.get(i));
			}else {
				break;
			}
		}
		
		return finalRequests;
	}
	
	void createAccPanels() {
		for(int i = 0; i < this.requests.size(); i++) {
			//create panel
			//set labels
			//make panel visible
			//set bounds
			Request currRequest = this.requests.get(i);
			System.out.println(currRequest);
			
			JPanel acceptPan = new JPanel();
			acceptPan.setBounds(panelBounds[i][0], panelBounds[i][1], panelBounds[i][2], panelBounds[i][3]);
			acceptPan.setBackground(new Color(204, 255, 204));
			acceptPan.setLayout(null);
			//acceptPan.setVisible(true);
			
			
			JLabel pickuptext = new JLabel("New label");
			pickuptext.setHorizontalAlignment(SwingConstants.CENTER);
			pickuptext.setFont(new Font("Tahoma", Font.PLAIN, 20));
			pickuptext.setBounds(pickupBounds[i][0], pickupBounds[i][1], pickupBounds[i][2], pickupBounds[i][3]);
			String pickAt = currRequest.getPickup();
			System.out.println(pickAt);
			pickuptext.setText(pickAt);
			getContentPane().add(pickuptext);
			
			
			JLabel droptext = new JLabel("New label");
			droptext.setHorizontalAlignment(SwingConstants.CENTER);
			droptext.setFont(new Font("Tahoma", Font.PLAIN, 20));
			droptext.setBounds(dropBounds[i][0], dropBounds[i][1], dropBounds[i][2], dropBounds[i][3]);
			String dropAt = currRequest.getDrop();
			System.out.println(dropAt);
			droptext.setText(dropAt);
			getContentPane().add(droptext);
			
			JButton accept = new JButton("Accept");
			int j = i;
			accept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(currRequest.getAccepted().equals("delivered")) {
						accept.setText("Delivered");
					}
					
					if(accept.getText().equals("Accept")) {
						accept.setText("PickedUp");
						System.out.println(j);
						System.out.println(currRequest);
						createUserDetPanels(currRequest, j);
						getContentPane().repaint();
						
						//update accept and delivery boy
						currRequest.setAccepted("accepted");
						currRequest.setDeliveryBoy(DeliveryService.CurrentDB.getUsername());	
						reqServ.updateRequest(currRequest);
						
						DeliveryService.CurrentDB.setStatus("Busy");
						DeliveryService.CurrentDB.setDropLocation(currRequest.getDrop());
						DeliveryService.updateUser();
						
						//show the show details panel on 2nd page
						//update values in 3rd page
						
					}
					/*else if(currRequest.getAccepted().equals("delivered")) {
						accept.setText("Delivered");
					}*/
					
					else if(accept.getText().equals("PickedUp")) {
						accept.setText("Arrived");
						currRequest.setAccepted("pickedup");
						reqServ.updateRequest(currRequest);
					}
					else if(accept.getText().equals("Arrived")) {
						currRequest.setAccepted("arrived");
						reqServ.updateRequest(currRequest);
						DeliveryService.CurrentDB.setAddress(currRequest.getDrop());
						DeliveryService.CurrentDB.setStatus("Available");
						DeliveryService.CurrentDB.setDropLocation("");
						DeliveryService.updateUser();
					}
				}
			});
			accept.setFont(new Font("Tahoma", Font.BOLD, 18));
			accept.setForeground(new Color(102, 0, 0));
			accept.setBounds(acceptBounds[i][0], acceptBounds[i][1], acceptBounds[i][2], acceptBounds[i][3]);
			getContentPane().add(accept);
			
			getContentPane().add(acceptPan);
			acceptPan.setVisible(true);
		}
	}
	
	void createUserDetPanels(Request accRequested, int i) {
		
		JPanel userDetailsPan = new JPanel();
		userDetailsPan.setBackground(new Color(204, 255, 204));
		userDetailsPan.setBounds(userDetPanBounds[i][0],userDetPanBounds[i][1],userDetPanBounds[i][2],userDetPanBounds[i][3]);
		userDetailsPan.setLayout(null);
		
		JLabel nametext = new JLabel("New label");
		nametext.setHorizontalAlignment(SwingConstants.CENTER);
		nametext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nametext.setBounds(nameBounds[i][0],nameBounds[i][1],nameBounds[i][2],nameBounds[i][3]);
		System.out.println(accRequested.getUsername());
		nametext.setText(accRequested.getUsername());
		getContentPane().add(nametext);
		
		JLabel phnotext = new JLabel("New label");
		phnotext.setHorizontalAlignment(SwingConstants.CENTER);
		phnotext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phnotext.setBounds(phnoBounds[i][0],phnoBounds[i][1],phnoBounds[i][2],phnoBounds[i][3]);
		System.out.println(accRequested.getPhoneNo());
		phnotext.setText(accRequested.getPhoneNo());
		getContentPane().add(phnotext);
		
		JLabel IdLabel = new JLabel("ID :");
		IdLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		IdLabel.setBounds(idLabelBounds[i][0],idLabelBounds[i][1],idLabelBounds[i][2],idLabelBounds[i][3]);
		getContentPane().add(IdLabel);
		
		JTextField idtext = new JTextField();
		idtext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idtext.setHorizontalAlignment(SwingConstants.CENTER);
		idtext.setBounds(idTextBounds[i][0],idTextBounds[i][1],idTextBounds[i][2],idTextBounds[i][3]);
		idtext.setText(accRequested.getBookingId());
		getContentPane().add(idtext);
		idtext.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("OTP :");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_20.setBounds(otpLabelBounds[i][0],otpLabelBounds[i][1],otpLabelBounds[i][2],otpLabelBounds[i][3]);
		getContentPane().add(lblNewLabel_20);
		
		JTextField otptext = new JTextField();
		otptext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		otptext.setHorizontalAlignment(SwingConstants.CENTER);
		otptext.setBounds(otpTextBounds[i][0],otpTextBounds[i][1],otpTextBounds[i][2],otpTextBounds[i][3]);
		otptext.setText("");
		otptext.setColumns(10);
		otptext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = otptext.getText().equals(RandomGen1.getRandomOTP(accRequested.getBookingId()));
				if(result) {
					otptext.setText("OTP Matched");
					accRequested.setAccepted("delivered");
					reqServ.updateRequest(accRequested);

				}
				else {
					otptext.setText("Error, ReEnter");
				}
				getContentPane().repaint();
			}
		});
		getContentPane().add(otptext);
		
		
		getContentPane().add(userDetailsPan);
		userDetailsPan.setVisible(true);
	}

}

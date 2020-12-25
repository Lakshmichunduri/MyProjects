package foodDelivery;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entity.DeliveryBoy;
import entity.Request;
import services.DeliveryService;
import services.RequestService;
import util.RandomGen1;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class UserGetsDetails extends JFrame {
	private JPanel contentPane;
	private JTextField feedbackText;
	JLabel namev, bookv, phnov,otpv;
	private String bookingId;
	Request reqDetails;
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGetsDetails frame = new UserGetsDetails();
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
	
	
	public UserGetsDetails(String bookingId) {
		this.bookingId = bookingId;
		
		setTitle("Details for the Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1006, 543);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel bookingDetails = new JLabel("Booking Details");
		bookingDetails.setForeground(new Color(102, 0, 102));
		bookingDetails.setFont(new Font("Tahoma", Font.BOLD, 46));
		bookingDetails.setHorizontalAlignment(SwingConstants.CENTER);
		bookingDetails.setBounds(31, 21, 828, 54);
		contentPane.add(bookingDetails);
		
		JLabel lblNewLabel_1 = new JLabel("Name of the deliverer : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(153, 51, 153));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 125, 258, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(153, 51, 153));
		lblNewLabel_2.setBounds(520, 125, 184, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Booking ID : ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(153, 51, 153));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 187, 240, 39);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("OTP :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(153, 51, 153));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(520, 187, 180, 39);
		contentPane.add(lblNewLabel_4);
		
		namev = new JLabel("New label");
		namev.setHorizontalAlignment(SwingConstants.CENTER);
		namev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		namev.setBounds(270, 125, 240, 39);
		contentPane.add(namev);
		
		bookv = new JLabel("New label");
		bookv.setHorizontalAlignment(SwingConstants.CENTER);
		bookv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bookv.setBounds(260, 187, 240, 39);
		contentPane.add(bookv);
		
	    phnov = new JLabel("New label");
		phnov.setHorizontalAlignment(SwingConstants.CENTER);
		phnov.setFont(new Font("Tahoma", Font.PLAIN, 18));
		phnov.setBounds(714, 125, 240, 39);
		contentPane.add(phnov);
		
		otpv = new JLabel("");
		otpv.setHorizontalAlignment(SwingConstants.CENTER);
		otpv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		otpv.setBounds(710, 187, 240, 39);
		contentPane.add(otpv);
		
		JButton requestButton = new JButton("Requested");
		requestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		requestButton.setForeground(new Color(0, 102, 102));
		requestButton.setBackground(new Color(152, 251, 152));
		requestButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		requestButton.setBounds(77, 263, 191, 39);
		contentPane.add(requestButton);
		
		JButton arrivedButton = new JButton("PickedUp");
		arrivedButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		arrivedButton.setBounds(358, 263, 191, 39);
		contentPane.add(arrivedButton);
		
		JButton deliveredButton = new JButton("Delivered");
		deliveredButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		deliveredButton.setBounds(643, 263, 191, 39);
		contentPane.add(deliveredButton);
		
		//feedback part
		
		JPanel feedBackpanel = new JPanel();
		feedBackpanel.setBackground(new Color(255, 204, 255));
		feedBackpanel.setBounds(10, 318, 972, 175);
		feedBackpanel.setLayout(null);
		
		contentPane.add(feedBackpanel);
		
		JLabel feedbackLabel = new JLabel("Feedback :");
		feedbackLabel.setBounds(160, 13, 120, 44);
		feedBackpanel.add(feedbackLabel);
		feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackLabel.setForeground(new Color(153, 51, 153));
		feedbackLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		feedbackText = new JTextField();
		feedbackText.setBounds(450, 31, 400, 38);
		feedBackpanel.add(feedbackText);
		feedbackText.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackText.setFont(new Font("Tahoma", Font.BOLD, 15));
		feedbackText.setForeground(new Color(102, 0, 102));
		feedbackText.setColumns(10);
		
		JLabel thanksLabel = new JLabel("Thank you for ordering! Have a good day!");
		thanksLabel.setBounds(22, 97, 845, 54);
		feedBackpanel.add(thanksLabel);
		thanksLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		thanksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//thanksLabel.setForeground(new Color(153, 51, 153));
		thanksLabel.setForeground(new Color(102, 0, 102));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButton Button = new JRadioButton("1");
		Button.setFont(new Font("Tahoma", Font.BOLD, 20));
		Button.setBounds(75, 68, 46, 21);
		Button.setBackground(new Color(255, 204, 255));
		Button.setForeground(new Color(153, 51, 153));
		Button.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(Button.isSelected()) updatingReview(1);  	
		        }			
		});
		buttonGroup.add(Button);
		feedBackpanel.add(Button);
		
		JRadioButton Button_1 = new JRadioButton("3");
		Button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		Button_1.setBounds(193, 68, 46, 21);
		Button_1.setBackground(new Color(255, 204, 255));
		Button_1.setForeground(new Color(153, 51, 153));
		Button_1.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(Button_1.isSelected()) updatingReview(3);  	
		        }			
		});
		buttonGroup.add(Button_1);
		feedBackpanel.add(Button_1);
	
		JRadioButton Button_2 = new JRadioButton("4");
		Button_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		Button_2.setBounds(252, 68, 46, 21);
		Button_2.setBackground(new Color(255, 204, 255));
		Button_2.setForeground(new Color(153, 51, 153));
		Button_2.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(Button_2.isSelected()) updatingReview(4);  	
		        }			
		});
		buttonGroup.add(Button_2);
		feedBackpanel.add(Button_2);
		
		JRadioButton Button_3 = new JRadioButton("2");
		Button_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		Button_3.setBounds(134, 68, 39, 21);
		Button_3.setBackground(new Color(255, 204, 255));
		Button_3.setForeground(new Color(153, 51, 153));
		Button_3.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(Button_3.isSelected()) updatingReview(2);  	
		        }			
		});
		buttonGroup.add(Button_3);
		feedBackpanel.add(Button_3);
		
		JRadioButton Button_4 = new JRadioButton("5");
		Button_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		Button_4.setBounds(311, 68, 46, 21);
		Button_4.setBackground(new Color(255, 204, 255));
		Button_4.setForeground(new Color(153, 51, 153));
		Button_4.addActionListener((ActionListener) new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ab) {
		    	if(Button_4.isSelected()) updatingReview(5);  	
		        }			
		});
		buttonGroup.add(Button_4);
		feedBackpanel.add(Button_4);
		
		/*public void actionPerformed(ActionEvent ae){
		 int value = Integer.parseInt(ae.getActionCommand());
		}*/
		
		feedBackpanel.setVisible(false);
		
		JButton reloadButton = new JButton("Reload");
		reloadButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		reloadButton.setBounds(0, 0, 80, 34);
		reloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				setUserGetsDetails();
				if(reqDetails.getAccepted().equals("pickedup")) {
					arrivedButton.setForeground(new Color(0, 102, 102));
					arrivedButton.setBackground(new Color(152, 251, 152));
				}
				else if(reqDetails.getAccepted().equals("arrived")) {
					otpv.setText(RandomGen1.getRandomOTP(bookingId));
				}
				else if(reqDetails.getAccepted().equals("delivered")) {
					arrivedButton.setForeground(new Color(0, 102, 102));
					arrivedButton.setBackground(new Color(152, 251, 152));
					deliveredButton.setForeground(new Color(0, 102, 102));
					deliveredButton.setBackground(new Color(152, 251, 152));
					feedBackpanel.setVisible(true);
				}
			}
			
		});
		
		contentPane.add(reloadButton);
		
		/*JSpinner spinner = new JSpinner();
		spinner.setBounds(741, 21, 49, 40);
		contentPane.add(spinner);
		spinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 27));
		
		JLabel label5 = new JLabel("/5");
		label5.setBounds(791, 21, 43, 34);
		contentPane.add(label5);
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setForeground(new Color(153, 51, 153));
		label5.setFont(new Font("Tahoma", Font.BOLD, 27));
		spinner.addChangeListener((ChangeListener) new ChangeListener() {      
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int value = (Integer)spinner.getModel().getValue();
				updatingReview(value);
				//DeliveryService.CurrentDB.setRating(value);
				//System.out.println(value);
			}
			});*/
		
		
		setUserGetsDetails();
	}
	
	public void setUserGetsDetails() {
		
		reqDetails = RequestService.getRequestDetails(bookingId);
		String dBoyName = reqDetails.getDeliveryBoy();
		DeliveryBoy dBoy = DeliveryService.getDeliveryBoy(dBoyName);
		
		namev.setText(dBoy.getUsername());
		bookv.setText(reqDetails.getBookingId());
		phnov.setText(dBoy.getPhoneNo());
		
	}

	public void updatingReview(int newreview) {
		reqDetails = RequestService.getRequestDetails(bookingId);
		String dBoyName = reqDetails.getDeliveryBoy();
		DeliveryBoy dBoy = DeliveryService.getDeliveryBoy(dBoyName);
		double oldreview = dBoy.getRating();
		int noofreviews = dBoy.getNoOfReviws();
		double updatedreview = ((oldreview*noofreviews) + newreview)/(noofreviews +1);
		noofreviews += 1;
		dBoy.setRating(updatedreview);
		dBoy.setNoOfReviws(noofreviews);
		DeliveryService.updateSpecUser(dBoy);
	}
}

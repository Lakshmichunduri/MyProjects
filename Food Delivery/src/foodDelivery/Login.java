package foodDelivery;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import entity.User;
//import prac.LoginPanel;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import entity.DeliveryBoy;
import services.UserService;
import services.DeliveryService;
import java.awt.FlowLayout;
import java.net.URI;
import java.net.URISyntaxException;

class SignupPanel extends JPanel{
	
    JLabel name,phno,email,community,password;
    JTextField namev,phnov,emailv;
    JPasswordField passv;
    JLabel Message;
    ButtonGroup communityv;
    JButton submit; 
    
    SignupPanel(){
	name = new JLabel("Username");
	email = new JLabel("EmailId");
	phno = new JLabel("Phone No");
	password = new JLabel("Password");
	community = new JLabel("Community");
	namev = new JTextField(40);
	phnov = new JTextField(40);
	emailv = new JTextField(40);
	passv = new JPasswordField(40);
	JRadioButton user = new JRadioButton("User");
	JRadioButton deliveryBoy = new JRadioButton("DeliveryBoy");
	communityv = new ButtonGroup();
	communityv.add(user);
	communityv.add(deliveryBoy);
	Message = new JLabel(" ");
	Message.setFont(new Font("SansSerif", Font.PLAIN, 19));
	Message.setHorizontalAlignment(SwingConstants.CENTER);
	Message.setBounds(275,567,300,30);
	Message.setForeground(new Color(102, 0, 102));
	add(Message);
	submit = new JButton("Submit");
	submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae){
			if(user.isSelected()) {
				boolean validPhno = UserService.IsPhoneNumber(phnov.getText());
				if (!validPhno) {
					Message.setText("Invalid Phone number");
				}
				else {
					Message.setText("");
					String password = new String(passv.getPassword());
					User currUser = new User(namev.getText(), emailv.getText(), phnov.getText(), password);
					UserService.setUserPersist(currUser);
					UserService.signup = true;
					Delivery delivery = new Delivery();
					delivery.setVisible(true);
				}
			    
			}
			if(deliveryBoy.isSelected()) {
				boolean validPhno = UserService.IsPhoneNumber(phnov.getText());
				if (!validPhno) {
					Message.setText("Invalid Phone number");
				}
				else {
					Message.setText("");
					String password = new String(passv.getPassword());
					DeliveryBoy db = new DeliveryBoy(namev.getText(), emailv.getText(), phnov.getText(), password,"Available",0,30,0);
					DeliveryService.setUserPersist(db);
					DetailsToDelBoy boypage2 = new DetailsToDelBoy();
					boypage2.setVisible(true);
				//go to next page;
				}
			}
		}	
	});
	Font font4 = new Font("SansSerif", Font.PLAIN, 21);
	Font font5 = new Font("SansSerif", Font.PLAIN, 19);
	name.setFont(font4);
	name.setBounds(225,250,500,30);
	name.setForeground(new Color(102, 0, 102));
	add(name);
	namev.setFont(font5);
	namev.setBounds(350,250,300,30);
	namev.setForeground(new Color(153, 51, 153));
	add(namev);
	email.setFont(font4);
	email.setBounds(225,300,500,30);
	email.setForeground(new Color(102, 0, 102));
	add(email);
	emailv.setFont(font5);
	emailv.setBounds(350,300,300,30);
	emailv.setForeground(new Color(153, 51, 153));
	add(emailv);
	phno.setFont(font4);
	phno.setBounds(225,350,500,30);
	phno.setForeground(new Color(102, 0, 102));
	add(phno);
	phnov.setFont(font5);
	phnov.setBounds(350,350,300,30);
	phnov.setForeground(new Color(153, 51, 153));
	add(phnov);
	community.setFont(font4);
	community.setBounds(225,400,500,30);
	community.setForeground(new Color(102, 0, 102));
	add(community );
	user.setFont(font4);
	user.setBounds(350,400,100,30);
	user.setForeground(new Color(102, 0, 102));
	add(user);
	deliveryBoy.setFont(font4);
	deliveryBoy.setBounds(500,400,150,30);
	deliveryBoy.setForeground(new Color(102, 0, 102));
	add(deliveryBoy);
	password.setFont(font4);
	password.setBounds(225,450,500,30);
	password.setForeground(new Color(102, 0, 102));
	add(password);
	passv.setFont(font5);
	passv.setBounds(350,450,300,30);
	passv.setForeground(new Color(153, 51, 153));
	add(passv);
	submit.setFont(font4);
	submit.setBounds(325,520,200,30);
	submit.setForeground(new Color(102, 0, 102));
	add(submit);
	setSize(900,1400);
	setVisible(false);
	}
	
}

class LoginPanel extends JPanel{
	JLabel user;
	JLabel password;
	JTextField userv;
	JPasswordField passv;
	JButton submit;
	JLabel Message;
	LoginPanel(){
		user = new JLabel("Username");
		password = new JLabel("Password");
		userv = new JTextField(40);
		passv = new JPasswordField(40);
		Message = new JLabel(" ");
		Message.setFont(new Font("SansSerif", Font.PLAIN, 19));
		Message.setHorizontalAlignment(SwingConstants.CENTER);
		Message.setBounds(275,567,300,30);
		Message.setForeground(new Color(102, 0, 102));
		submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				String password = new String(passv.getPassword());
				boolean bool1 = false, bool2 = false;
				try {
					User user = UserService.getUserPersist(userv.getText());
					bool1 = user.getPassword().equals(password);
					if(bool1)  {
						UserService.getUserPersist(userv.getText());
						Message.setText("");
					    Delivery delivery = new Delivery();
					    delivery.setVisible(true);
					
						//go to next page of user
					}
				System.out.print(bool1 + " ");

				}
				catch(NullPointerException e){
				}
				try {
					DeliveryBoy db = DeliveryService.getUserPersist(userv.getText());
					bool2 = db.getPassword().equals(password);
					if(bool2) {
						Message.setText("");
						DeliveryService.getUserPersist(userv.getText());
						DeliveryService.CurrentDB.setStatus("Available");
						DeliveryService.updateUser();
						DetailsToDelBoy boypage2 = new DetailsToDelBoy();
						boypage2.setVisible(true);
						//go to next page for delivery boy
					}
				}
				catch(NullPointerException e){
					//Message = new JLabel("UserName/Password incorrect");
				}
				if (!(bool1 | bool2)) Message.setText("UserName/Password incorrect");
			}
		});
		
		Font font3 = new Font("SansSerif", Font.PLAIN, 21);
		Font font5 = new Font("SansSerif", Font.PLAIN, 19);
		user.setFont(font3);
		user.setBounds(375,300,200,30);
		user.setForeground(new Color(102, 0, 102));
		add(user);
		userv.setFont(font5);
		userv.setBounds(325,350,200,30);
		userv.setHorizontalAlignment(SwingConstants.CENTER);
		userv.setForeground(new Color(153, 51, 153));
		add(userv);
		password.setFont(font3);
		password.setBounds(375,400,200,30);
		password.setForeground(new Color(102, 0, 102));
		add(password);
		passv.setFont(font5);
		passv.setBounds(325,450,200,30);
		passv.setHorizontalAlignment(SwingConstants.CENTER);
		passv.setForeground(new Color(153, 51, 153));
		add(passv);
		submit.setFont(font3);
		submit.setBounds(350,520,150,30);
		submit.setForeground(new Color(102, 0, 102));
		
		add(submit);
		add(Message);
		
		setSize(900,1400);
		setVisible(false);
	}
}
public class Login {
	JFrame jfrm;
	JLabel head1;
	JLabel head2;
	JButton login,signup;
	private LoginPanel pan1;
	public Login() {
		jfrm = new JFrame("FOODIE");
		jfrm.getContentPane().setLayout(null);
		//jfrm.setLayout(new FlowLayout());
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setSize(900, 1400);
		jfrm.setVisible(true);
		Font font1 = new Font("SansSerif", Font.BOLD, 30);
		Font font2 = new Font("SansSerif", Font.BOLD, 27);
		head1 = new JLabel("WELCOME TO FOODIE");
		head1.setFont(font1);
		head2 = new JLabel(" To Experience the Best of Home Food ");
		head2.setFont(font2);
		head1.setBounds(275,100,500,50);
		head2.setBounds(200,175,600,50);
		head1.setForeground(new Color(102, 0, 102));
		head2.setForeground(new Color(102, 0, 102));
		jfrm.getContentPane().add(head1);
		jfrm.getContentPane().add(head2);
		
		login = new JButton("LOGIN");
		login.setFont(new Font("SansSerif", Font.BOLD, 14));
		login.setBounds(200,625,200,40);
		login.setForeground(new Color(102, 0, 102));
		jfrm.getContentPane().add(login);
		
		signup = new JButton("SIGNUP");
		signup.setFont(new Font("SansSerif", Font.BOLD, 14));
		signup.setBounds(450,625,200,40);
		signup.setForeground(new Color(102, 0, 102));
		jfrm.getContentPane().add(signup);
		
		pan1 = new LoginPanel();
		pan1.setBackground(new Color(255, 204, 255));
		pan1.setLayout(null);
		//pan1.setLocation(73, 65);
		jfrm.getContentPane().add(pan1);
		//pan1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		SignupPanel pan2 = new SignupPanel();
		pan2.setBackground(new Color(255, 204, 255));
		jfrm.getContentPane().add(pan2);
		pan2.setLayout(null);
		
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				pan1.setVisible(true);
				pan2.setVisible(false);
				jfrm.repaint();
			}
		});
		
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				pan2.setVisible(true);
				pan1.setVisible(false);
				jfrm.repaint();
			}
		});
		
		ImageIcon faceb = new ImageIcon("images/fb.png");
		
		Image image = faceb.getImage(); 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);   
		ImageIcon newimgf = new ImageIcon(newimg);
		
		JButton facebb = new JButton(newimgf);
		facebb.setActionCommand("Facebook");
		
		facebb.setBounds(200, 700, 100, 100);
		jfrm.getContentPane().add(facebb);
		
		facebb.addMouseListener(new MouseAdapter() 
		{
		 public void mouseClicked(MouseEvent e) 
		 {
		  if (e.getClickCount() > 0) 
		  {
		   if (Desktop.isDesktopSupported()) 
		   {
			Desktop desktop = Desktop.getDesktop();
			URI uri = null;
			try {
				uri = new URI("https://en-gb.facebook.com/");
			} catch (URISyntaxException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				desktop.browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
		  }
		 }
		});
		
		ImageIcon gmail = new ImageIcon("images/gmail.jpg");
		
		Image image2 = gmail.getImage(); 
		Image gmail2 = image2.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);   
		ImageIcon newgmail = new ImageIcon(gmail2);
		
		JButton gmailb = new JButton(newgmail);
		gmailb.setActionCommand("Gmail");
		gmailb.setBounds(375, 700, 100, 100);
		jfrm.getContentPane().add(gmailb);
		gmailb.addMouseListener(new MouseAdapter() 
		{
		 public void mouseClicked(MouseEvent e) 
		 {
		  if (e.getClickCount() > 0) 
		  {
		   if (Desktop.isDesktopSupported()) 
		   {
			Desktop desktop = Desktop.getDesktop();
			URI uri = null;
			try {
				uri = new URI("https://accounts.google.com/");
			} catch (URISyntaxException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				desktop.browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
		  }
		 }
		});
		
ImageIcon insta = new ImageIcon("images/insta.jpg");
		
		Image dump = insta.getImage(); 
		Image newimg2 = dump.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);   
		ImageIcon newimgi = new ImageIcon(newimg2);
		
		JButton instab = new JButton(newimgi);
		instab.setBounds(550, 700, 100, 100);
		instab.setActionCommand("Instagram");
		jfrm.getContentPane().add(instab);
		
		instab.addMouseListener(new MouseAdapter() 
		{
		 public void mouseClicked(MouseEvent e) 
		 {
		  if (e.getClickCount() > 0) 
		  {
		   if (Desktop.isDesktopSupported()) 
		   {
			Desktop desktop = Desktop.getDesktop();
			URI uri = null;
			try {
				uri = new URI("https://www.instagram.com/");
			} catch (URISyntaxException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				desktop.browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
		  }
		 }
		});
		
		jfrm.getContentPane().setBackground(new Color(255, 204, 255));
		
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(
		new Runnable(){
			public void run(){
				new Login();
			}
		}
	);
	}
	public Color getPan1Background() {
		return pan1.getBackground();
	}
	public void setPan1Background(Color background) {
		pan1.setBackground(background);
	}

}

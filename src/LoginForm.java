import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 * Name: LoginForm
 * Description:
 *
 */
public class LoginForm {
	ImageIcon mjLogo;
	ImageIcon background;
	JLabel mjLogoLabel;
	JLabel error;
	JLabel backgroundLabel;
	JLabel userNameLabel, passwordLabel, typeLabel;
	String[] userType = {"Blogger", "Admin", "Developer"};
	JComboBox userTypeList;
	JTextField userNameField;
	JPasswordField passwordField;
	JButton loginButton;
	JFrame app;
	
	//Default Constructor
	public LoginForm(){
	}
	
	void display(){
		mjLogo = new ImageIcon(getClass().getResource("MidnightJabber_Logo.png"));
		background = new ImageIcon(getClass().getResource("Login_Background.jpg"));
		mjLogoLabel = new JLabel(mjLogo);
		backgroundLabel = new JLabel(background);
		userNameLabel = new JLabel("User-Name:");
		passwordLabel = new JLabel("Password:");
		typeLabel = new JLabel("User-Type:");
		userTypeList = new JComboBox(userType);
		userNameField = new JTextField(30);
		passwordField = new JPasswordField(30);
		loginButton = new JButton("Login");
		error = new JLabel("");
		app = new JFrame("MidnightJabber Content Manager");
		
		//Setting Text(Label) as white
		userNameLabel.setForeground(Color.white);
		passwordLabel.setForeground(Color.white);
		typeLabel.setForeground(Color.white);
		error.setForeground(Color.red);
		
		app.setLayout(new BorderLayout());
		app.add(backgroundLabel);
		
		//Setting Layout to be null for absolute 
		//Component positioning control
		backgroundLabel.setLayout(null);
		
		//Positioning MJ-LOGO
		mjLogoLabel.setBounds(20, 20, 260, 80);
		backgroundLabel.add(mjLogoLabel);
		
		//Positioning User-Name Components
		userNameLabel.setBounds(112, 140, 76, 20);
		backgroundLabel.add(userNameLabel);
		userNameField.setBounds(20,160, 260, 30);
		backgroundLabel.add(userNameField);
		
		//Positioning Password Components
		passwordLabel.setBounds(118, 200, 63, 20);
		backgroundLabel.add(passwordLabel);
		passwordField.setBounds(20, 220, 260, 30);
		backgroundLabel.add(passwordField);
		
		//Positioning User-Type Components
		typeLabel.setBounds(112, 260, 76, 20);
		backgroundLabel.add(typeLabel);
		userTypeList.setBounds(75, 280, 150, 20);
		backgroundLabel.add(userTypeList);
		
		//Positioning Login Button
		loginButton.setBounds(75, 380, 150, 40);
		backgroundLabel.add(loginButton);
		
		//Positioning Potential Error Message
		error.setBounds(90, 440, 400, 20);
		backgroundLabel.add(error);	
		
		loginButton.addActionListener(new Login());	
		
		app.pack();
		app.setSize(300, 500);
		app.setResizable(false);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
			
	}
	
	public String getUserEntry(){
		return userNameField.getText();
	}
	
	public String getPassEntry(){
		return new String(passwordField.getPassword());
	}
	
	public String getTypeEntry(){
		return userTypeList.getSelectedItem().toString();
	}
	
	public void invalidCredentials(){
		error.setText("Invalid Input Data");	
		passwordField.setText("");
		userNameField.setText("");
		backgroundLabel.revalidate();
	}
	
	public void loginSuccess(){
		app.dispose();
	}
	
	class Login implements ActionListener{
		
		public Login(){
		}
		
		public void actionPerformed(ActionEvent ae){
			
			DBConnect connection = new DBConnect();
			boolean match;
			//Retrieve Input Data
			String userEntry = getUserEntry();
			String passEntry = getPassEntry();
			String typeEntry = getTypeEntry();
			
			//Calling function from DBConnect.java
			match = connection.verifyUser(userEntry, passEntry, typeEntry);
			
			if(!match){
				invalidCredentials();
			}
			else{
				loginSuccess();
			}
		}
	};
	
};




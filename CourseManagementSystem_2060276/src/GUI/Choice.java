package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



@SuppressWarnings("serial")
public class Choice extends JFrame implements ActionListener{
	private JLabel userChoice;
	private JButton login;
	private JButton register;
	
	
	
	
	
	Choice(){
		userChoice = new JLabel();
		login = new JButton("Login");
		register = new JButton("Register");
		login.setBounds(50, 80,120 ,50);
		login.addActionListener(this);
		login.setFocusable(false);
		register.setBounds(50, 140, 120, 50);
		register.addActionListener(this);
		register.setFocusable(false);
			
		userChoice.setText("Would you like to login or register?");

		userChoice.setBounds(0, 0, 0, 0);

		userChoice.setVerticalAlignment(JLabel.TOP);
		userChoice.setHorizontalAlignment(JLabel.LEFT);
		userChoice.setBounds(17, 37, 200, 50);

		this.setTitle("CMS");
		this.setSize(300, 290);
		

		this.setResizable(false);
		ImageIcon image = new ImageIcon("../CourseManagementSystem_2060276/pngwing.com.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(0X008080));
		this.add(userChoice);
		this.add(login);
		this.add(register);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}



	public void actionPerformed(ActionEvent e) {	
			
		if (e.getSource()==login) {
			this.setVisible(false);
			new Login().createGui();
						
		}
		else if (e.getSource()==register) {
			this.setVisible(false);
			new Register().createGui();
		}
		
		
	}

}

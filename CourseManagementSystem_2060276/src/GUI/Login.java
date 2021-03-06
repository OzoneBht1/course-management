package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DbConnect.Conn;

@SuppressWarnings("serial")
public class Login extends JFrame implements MouseListener, ActionListener {
	private JLabel userName;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passField;
	private String indicator;
	private JRadioButton student;
	private JRadioButton admin;
	private JRadioButton teacher;
	private ButtonGroup bodyIndicator;
	private String enteredName;
	private String enteredPass;
	private Conn conn;
	private ImageIcon image;
	private JLabel hyperlink;
	private JButton cancel;
	private JButton login;
	private Font myFont;
	
	
	
	
	private void connectDb() {
		try {
			conn = new Conn();	
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "<html> The app requires database to be connected to work.<br> Please connect to database and try again!</html>");
			System.exit(-1);
			
			
			
		}
	}
		

	

	public void createGui() {
		connectDb();
		

		myFont = new Font("Calibri", Font.BOLD, 17);
		image = new ImageIcon("../CourseManagementSystem_2060276/pngwing.com.png");
		hyperlink = new JLabel("Or click here to register instead.");
		usernameField = new JTextField();
		password = new JLabel("Password");
		password.setFont(myFont);

		passField = new JPasswordField();
		cancel = new JButton("Cancel");
		cancel.setFont(myFont);
		login = new JButton("Login");
		login.setFont(myFont);
		login.setFocusable(false);
		userName = new JLabel("Username");
		userName.setFont(myFont);

		student = new JRadioButton("student");
		student.setFont(myFont);
		teacher = new JRadioButton("teacher");
		teacher.setFont(myFont);
		admin = new JRadioButton("admin");
		admin.setFont(myFont);

		userName.setBounds(40, 70, 100, 30);

		userName.setForeground(Color.white);

		usernameField.setBackground(new Color(0X8193a8));
		usernameField.setBounds(150, 70, 150, 30);

		password.setForeground(Color.white);
		password.setBounds(40, 120, 100, 30);

		passField.setBounds(150, 120, 150, 30);
		passField.setBackground(new Color(0X8193a8));

		cancel.setBounds(140, 220, 75, 30);
		cancel.setBackground(new Color(0xff3b2b));
		cancel.addActionListener(this);

		login.setBounds(240, 220, 90, 30);
		login.setBackground(new Color(0x38b8ff));
		login.addActionListener(this);
		hyperlink.setForeground(Color.WHITE.darker());
		hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(this);
		hyperlink.setBounds(130, 270, 240, 30);
		hyperlink.setFont(new Font("monospace", Font.BOLD + Font.ITALIC, 14));

		bodyIndicator = new ButtonGroup();
		bodyIndicator.add(student);
		bodyIndicator.add(teacher);
		bodyIndicator.add(admin);

		student.setBounds(70, 175, 90, 20);
		teacher.setBounds(165, 175, 90, 20);
		admin.setBounds(255, 175, 90, 20);
		student.setBackground(new Color(0X2d3e50));
		student.setForeground(Color.white);

		teacher.setBackground(new Color(0X2d3e50));
		teacher.setForeground(Color.white);

		admin.setBackground(new Color(0X2d3e50));
		admin.setForeground(Color.white);

		student.addActionListener(this);
		teacher.addActionListener(this);
		admin.addActionListener(this);

		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0X2d3e50));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setIconImage(image.getImage());
		this.add(usernameField);

		this.add(userName);
		this.add(password);
		this.add(passField);
		this.add(cancel);
		this.add(login);
		this.add(hyperlink);
		this.add(student);
		this.add(teacher);
		this.add(admin);

		this.setSize(450, 360);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);

	}

	public void mousePressed(MouseEvent e) {
		// --
	}

	public void mouseReleased(MouseEvent e) {
		// --

	}

	public void mouseEntered(MouseEvent e) {
		hyperlink.setFont(new Font("monospace", Font.BOLD + Font.ITALIC, 15));

	}

	public void mouseExited(MouseEvent e) {
		hyperlink.setFont(new Font("monospace", Font.BOLD + Font.ITALIC, 14));

	}

	public void mouseClicked(MouseEvent e) {
		try {
			this.dispose();
			new Register().createGui();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			if (student.isSelected()) {
				indicator = "student";
			} else if (admin.isSelected()) {
				indicator = "admin";

			} else if (teacher.isSelected()) {
				indicator = "teacher";
			} else {
				JOptionPane.showMessageDialog(null, "You need to select a user.");

			}
			try {
						
				
				enteredName = usernameField.getText();
				enteredPass = String.valueOf(passField.getPassword());
				if (indicator.equals("student")) {
				String query = "SELECT * FROM studentdetails WHERE userName = '" + enteredName + "' AND password = '"
						+ enteredPass + "'";
				ResultSet rs = conn.s.executeQuery(query);
				if (rs.next()) {
					new StudentGui().homeGui(enteredName);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Login.");

				}
				}
				else if(indicator.equals("teacher")) {
					String query = "SELECT * FROM teacherdetails WHERE username = '" + enteredName + "' AND password = '"
							+ enteredPass + "'";
					ResultSet rs = conn.s.executeQuery(query);
					if (rs.next()) {
						new TeacherGui().homeGui(enteredName);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Login.");

					}
					
				}
				else if(indicator.equals("admin")) {
					String query = "SELECT * FROM admindetails WHERE username = '" + enteredName + "' AND password = '"
							+ enteredPass + "'";
					ResultSet rs = conn.s.executeQuery(query);
					if (rs.next()) {
						new AdminGui().homeGui(enteredName);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Login.");

					}
					
				}
					
				

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else if (e.getSource() == cancel) {
			this.dispose();
			new Choice();
		}

	}

}

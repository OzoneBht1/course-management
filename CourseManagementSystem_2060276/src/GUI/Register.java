package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Scanner;

import DbConnect.Conn;

@SuppressWarnings("serial")

public class Register extends JFrame implements ActionListener {
	private ImageIcon image;
	private JButton cancel;
	private JButton register;
	private JLabel firstName;
	private JTextField nameField;
	private JLabel lastName;
	private JTextField surnameField;
	private JLabel password;
	private JPasswordField passField;
	private JLabel retypePass;
	private JPasswordField rePassField;
	private JLabel phoneNo;
	private JTextField phoneNoField;
	private JLabel email;
	private JTextField emailField;
	private JLabel level;
	private JComboBox<String> levelChoice;
	private JComboBox<String> semesterChoice;
	private JComboBox<String> courseChoice;

	private String levelUser;
	private String[] courses;
	private String first_name;
	private String last_name;
	private String id;

	private String pass;

	private String re_pass;
	private String phone_no;
	private String emailId;
	private String checkIfexist;
	private String query;
	private String[] levels = { "4", "5", "6" };

	private String[] semesters = { "1", "2" };
	private String semester;
	private String course;
	private JLabel userName;
	private JTextField userField;
	private String username;
	private Conn conn;
	private ResultSet rs;
	private String query2;

	private Scanner sc;
	private ArrayList<String> lst;
	private JLabel courseLabel;
	private JLabel semesterLabel;
	private ArrayList<String> defaultModules;

	private String updateTeachers;

	public ArrayList<String> getCourses() {
		lst = new ArrayList<String>();

		File file = new File("courses.txt");
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			lst.add(sc.nextLine());

		}

		return lst;
	}

	private void connectDb() {
		try {
			conn = new Conn();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"<html> The app requires database to be connected to work.<br> Please connect to database and try again!</html>");
			System.exit(-1);

		}
	}

	public void createGui() {
		connectDb();

		image = new ImageIcon("../CourseManagementSystem_2060276/pngwing.com.png");
		cancel = new JButton("Cancel");
		register = new JButton("Register");

		firstName = new JLabel("First Name");
		firstName.setBounds(50, 70, 100, 30);
		firstName.setForeground(Color.white);
		firstName.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		nameField = new JTextField();
		nameField.setBackground(new Color(0X8193a8));
		nameField.setBounds(180, 70, 200, 30);
		nameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		lastName = new JLabel("Last Name");
		lastName.setForeground(Color.white);
		lastName.setBounds(50, 130, 100, 30);
		lastName.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		surnameField = new JTextField();
		surnameField.setBackground(new Color(0X8193a8));
		surnameField.setBounds(180, 130, 200, 30);
		surnameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		userName = new JLabel("Username");
		userName.setForeground(Color.white);
		userName.setBounds(50, 190, 100, 30);
		userName.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		userField = new JTextField();
		userField.setBackground(new Color(0X8193a8));
		userField.setBounds(180, 190, 200, 30);
		userField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		password = new JLabel("Password");
		password.setForeground(Color.white);
		password.setBounds(50, 250, 100, 30);
		password.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		passField = new JPasswordField();
		passField.setBackground(new Color(0X8193a8));
		passField.setBounds(180, 250, 200, 30);
		passField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		retypePass = new JLabel("Retype Pass");
		retypePass.setForeground(Color.white);
		retypePass.setBounds(50, 310, 120, 30);
		retypePass.setFont(new Font("Lucida Fax", Font.PLAIN, 16));

		rePassField = new JPasswordField();
		rePassField.setBackground(new Color(0X8193a8));
		rePassField.setBounds(180, 310, 200, 30);
		rePassField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		phoneNo = new JLabel("Phone No.");
		phoneNo.setForeground(Color.white);
		phoneNo.setBounds(50, 370, 100, 30);
		phoneNo.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		phoneNoField = new JTextField();
		phoneNoField.setBackground(new Color(0X8193a8));
		phoneNoField.setBounds(180, 370, 200, 30);
		phoneNoField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		email = new JLabel("Email");
		email.setForeground(Color.white);
		email.setBounds(50, 430, 100, 30);
		email.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		emailField = new JTextField();
		emailField.setBackground(new Color(0X8193a8));
		emailField.setBounds(180, 430, 200, 30);
		emailField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		courseLabel = new JLabel("Course");
		courseLabel.setForeground(Color.white);
		courseLabel.setBounds(50, 490, 100, 30);
		courseLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		lst = getCourses();
		courses = lst.toArray(new String[lst.size()]);
		courseChoice = new JComboBox<String>(courses);
		courseChoice.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		courseChoice.setBackground(new Color(0X8193a8));
		courseChoice.setBounds(180, 490, 200, 30);
		courseChoice.addActionListener(this);

		level = new JLabel("Level");
		level.setForeground(Color.white);
		level.setBounds(50, 550, 100, 30);
		level.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		levelChoice = new JComboBox<String>(levels);
		levelChoice.setBackground(new Color(0X8193a8));
		levelChoice.setBounds(180, 550, 200, 30);
		levelChoice.addActionListener(this);

		semesterLabel = new JLabel("semester");
		semesterLabel.setForeground(Color.white);
		semesterLabel.setBounds(50, 610, 100, 30);
		semesterLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		semesterChoice = new JComboBox<String>(semesters);
		semesterChoice.setBackground(new Color(0X8193a8));
		semesterChoice.setBounds(180, 610, 200, 30);
		semesterChoice.addActionListener(this);

		cancel.addActionListener(this);
		cancel.setBounds(150, 680, 90, 30);
		cancel.setBackground(new Color(0xff3b2b));
		cancel.setFont(new Font("Lucida Fax", Font.PLAIN, 12));

		register.addActionListener(this);
		register.setBounds(310, 680, 90, 30);
		register.setBackground(new Color(0x38b8ff));
		register.setFont(new Font("Lucida Fax", Font.PLAIN, 12));

		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0X2d3e50));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setIconImage(image.getImage());

		this.add(firstName);
		this.add(nameField);
		this.add(lastName);
		this.add(surnameField);
		this.add(userName);
		this.add(userField);
		this.add(password);
		this.add(passField);
		this.add(retypePass);
		this.add(rePassField);
		this.add(phoneNo);
		this.add(phoneNoField);
		this.add(email);
		this.add(emailField);
		this.add(level);
		this.add(levelChoice);
		this.add(courseLabel);
		this.add(courseChoice);
		this.add(semesterLabel);
		this.add(semesterChoice);
		this.add(register);
		this.add(cancel);

		this.setSize(550, 790);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cancel) {
			this.dispose();
			new Choice();
		} else if (e.getSource() == register) {
			defaultModules = new ArrayList<String>();
			levelUser = (String) levelChoice.getSelectedItem();
			semester = (String) semesterChoice.getSelectedItem();
			course = (String) courseChoice.getSelectedItem();

			try {

				first_name = nameField.getText();
				last_name = surnameField.getText();
				username = userField.getText();

				pass = String.valueOf(passField.getPassword());

				re_pass = String.valueOf(rePassField.getPassword());
				phone_no = phoneNoField.getText();

				emailId = emailField.getText();

				checkIfexist = "SELECT * FROM studentdetails WHERE username = '" + username + "'";
				rs = conn.s.executeQuery(checkIfexist);
				if (first_name.isEmpty() || last_name.isEmpty() || pass.isEmpty() || re_pass.isEmpty()
						|| phone_no.isEmpty() || emailId.isEmpty()) {
					JOptionPane.showMessageDialog(null, "None of the fields can be left empty");
				} else if (!pass.equals(re_pass)) {
					JOptionPane.showMessageDialog(null, "The two passwords do not match!");
				} else if (pass.length() < 6) {
					JOptionPane.showMessageDialog(null, "The length of the password must be greater than 6");

				} else if (emailId.indexOf("@") == -1 && emailId.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(null, "Invalid Email! Please try again.");

				} else if (rs.next()) {
					JOptionPane.showMessageDialog(null, "The given information already exists in the database.");
				}

				else {
					try {
						query = String.format(
								"INSERT INTO studentdetails(first_name, last_name,username, password, phone_number, email, course, level, semester,modules) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','null')",
								first_name, last_name, username, pass, phone_no, emailId, course, levelUser, semester);

						conn.s.executeUpdate(query);
						query = String.format(
								"SELECT moduleName from courses where level = '%s' and semester = '%s' and mandatory='1' and course = '%s'",
								levelUser, semester, course);

						rs = conn.s.executeQuery(query);

						while (rs.next()) {
							defaultModules.add(rs.getString(1));
						}

						for (int i = 0; i < defaultModules.size(); i++) {
							System.out.println(defaultModules.get(i));
						}

//						
						if (levelUser.equals("6")) {
							query = String.format("UPDATE studentdetails SET modules ='%s,null' WHERE username = '%s'",
									defaultModules.get(0), username);
							query2 = String.format(
									"INSERT INTO level6results(studentname, username, course,semester, module1, grade1, teacher1, module2, grade2, teacher2) VALUES ('%s','%s','%s','%s','%s','null', 'null','null','null','null');",
									first_name + " " + last_name, username, course, semester, defaultModules.get(0));

							ArrayList<String> teachers = new ArrayList<String>();
							for (int i = 0; i < 2; i++) {
								String fetchTeachers = "SELECT first_name, last_name FROM teacherdetails WHERE modules LIKE CONCAT('%','"
										+ defaultModules.get(i) + "','%')";
								ResultSet reSet = conn.s.executeQuery(fetchTeachers);
								while (reSet.next()) {
									if (!reSet.getString(1).isEmpty()) {
										teachers.add(reSet.getString(1) + " " + reSet.getString(2));
									} else {
										teachers.add("null");
									}

								}
							}
							updateTeachers = String.format("UPDATE level6results SET teacher1 = '%s', teacher2 = '%s'",
									teachers.get(0), teachers.get(1));
						} else if (levelUser.equals("4")) {
							query = String.format(
									"UPDATE studentdetails SET modules ='%s,%s,%s,%s' WHERE username = '%s'",
									defaultModules.get(0), defaultModules.get(1), defaultModules.get(2),
									defaultModules.get(3), username);
							query2 = String.format(
									"INSERT INTO level4results(studentname, username, course,semester, module1, grade1, teacher1, module2, grade2, teacher2, module3, grade3, teacher3, module4, grade4, teacher4) VALUES ('%s','%s','%s','%s','%s','null', 'null', '%s', 'null','null','%s', 'null','null','%s', 'null','null');",
									first_name + " " + last_name, username, course, semester, defaultModules.get(0),
									defaultModules.get(1), defaultModules.get(2), defaultModules.get(3));
							ArrayList<String> teachers = new ArrayList<String>();
							for (int i = 0; i < 4; i++) {
								String fetchTeachers = "SELECT first_name, last_name FROM teacherdetails WHERE modules LIKE CONCAT('%','"
										+ defaultModules.get(i) + "','%')";
								ResultSet reSet = conn.s.executeQuery(fetchTeachers);
								for (int count =0 ; i < 4; i++) {
									if (reSet.next()) {									
								
									if (!reSet.getString(1).isEmpty()) {
										teachers.add(reSet.getString(1));										
									}
									}
									else {
										teachers.add(null);
										
									}
								
								}
//								while (reSet.next()) {
//									System.out.println(reSet.getString(1));
//									if (!reSet.getString(1).isEmpty()) {
//										teachers.add(reSet.getString(1) + " " + reSet.getString(2));
//									} else {
//										teachers.add("null");
//									}
//
//								}
							}
							System.out.println(teachers.get(0)+ teachers.get(3));
							updateTeachers = String.format(
									"UPDATE level4results SET teacher1 = '%s', teacher2 = '%s', teacher3 = '%s', teacher4='%s'",
									teachers.get(0), teachers.get(1), teachers.get(2), teachers.get(3));
						} else if (levelUser.equals("5")) {
							query = String.format(
									"UPDATE studentdetails SET modules ='%s,%s,%s,%s' WHERE username = '%s'",
									defaultModules.get(0), defaultModules.get(1), defaultModules.get(2),
									defaultModules.get(3), username);
							query2 = String.format(
									"INSERT INTO level5results(studentname, username, course,semester, module1, grade1, teacher1, module2, grade2, teacher2, module3, grade3, teacher3, module4, grade4, teacher4) VALUES ('%s','%s','%s','%s','%s','null', 'null', '%s', 'null','null','%s', 'null','null','%s', 'null','null');",
									first_name + " " + last_name, username, course, semester, defaultModules.get(0),
									defaultModules.get(1), defaultModules.get(2), defaultModules.get(3));

							ArrayList<String> teachers = new ArrayList<String>();
							for (int i = 0 ; i < 4;i++) {
							String fetchTeachers = "SELECT first_name, last_name FROM teacherdetails WHERE modules LIKE CONCAT('%','"+ defaultModules.get(i) + "','%')";
							ResultSet reSet = conn.s.executeQuery(fetchTeachers);
							while(reSet.next()) {
							if (!reSet.getString(1).isEmpty()) {
								teachers.add(reSet.getString(1)+" "+reSet.getString(2));
							}
							else {
								teachers.add("null");
							}
								
							}
							}
							updateTeachers = String.format("UPDATE level5results SET teacher1 = '%s', teacher2 = '%s', teacher3 = '%s', teacher4='%s'",teachers.get(0), teachers.get(1), teachers.get(2), teachers.get(3));
						
						
						}
						
						conn.s.executeUpdate(query);
						conn.s.executeUpdate(query2);
						conn.s.executeUpdate(updateTeachers);

						JOptionPane.showMessageDialog(null,
								"<html>User Created Successfully!! <br> Redirecting to Login...</html>");

						this.dispose();
						new Login().createGui();

					} catch (Exception except) {
						except.printStackTrace();
					}

				}

			} catch (Exception excp) {
				excp.printStackTrace();
			}
		}
	}
}

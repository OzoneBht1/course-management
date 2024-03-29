
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.IDN;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.cj.Query;
import com.sun.org.apache.bcel.internal.generic.FNEG;

import DbConnect.Conn;
import course_users.CourseAdministrator;
import course_users.Instructors;
import course_users.Students;

@SuppressWarnings("serial")
public class AdminGui extends JFrame implements ActionListener {
	private Container mainContainer;
	private ImageIcon image;
	private JPanel topPanel;
	private JPanel middlePanel;	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JLabel welcomeText;
	private JLabel appInfo;
	private JPanel mainPart;
	private CourseAdministrator admin;
	private Conn con;
	private File myObj;

	private JLabel first_name;
	private JLabel last_name;
	private JLabel username;
	private JLabel id;
	private JLabel phone_number;
	private JLabel password;
	private JLabel email;

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JLabel usernameField;
	private JLabel idField;
	private JTextField phoneNumberField;
	private JTextField passwordField;
	private JTextField emailField;
	private JLabel note;
	private JButton registerButton;

	private JLabel teacherFirstName;
	private JLabel teacherLastName;
	private JLabel teacherUsername;
	private JLabel teacherId;
	private JLabel teacherPassword;
	private JLabel teacherRetypePassword;
	private JLabel teacherphoneNumber;
	private JLabel teacherEmail;
	private JLabel teacherCourse;
	private JButton button12;

	private JTextField teacherFirstNameField;
	private JTextField teacherLastNameField;
	private JTextField teacherUsernameField;
	private JLabel teacherIdField;
	private JPasswordField teacherPasswordField;
	private JPasswordField teacherRetypePasswordField;
	private JTextField teacherphoneNumberField;
	private JTextField teacherEmailField;
	private JComboBox<String> teacherCourseField;
	private ResultSet rs;
	private JComboBox<String> enrollOption1;
	private JComboBox<String> enrollOption2;
	private JComboBox<String> enrollOption3;
	private JComboBox<String> enrollOption4;
	private String courseSelected;
	private String fn;
	private String ln;
	private String user;
	private String pw;
	private String rpw;
	private String em;
	private String pn;
	private ArrayList<String> courseList;
	private Scanner sc;

	private ArrayList<String> modules;
	private JLabel enrollLabel1;
	private JLabel enrollLabel2;
	private JLabel enrollLabel3;
	private JLabel enrollLabel4;
	private JButton enrollButton;
	private ArrayList<String> currentModules;
	private String[] module;
	private ArrayList<String> selectedOptions;
	private int countAvailable;
	private String moduleString;
	private String[] moduleToDelete;
	private JComboBox<String> removeModuleCombo;
	private JButton remove;
	private JLabel informationText;
	private JLabel addText;
	private JLabel removeText;
	private String level;
	private String firstNameTeacher;
	private String lastNameTeacher;
	private String usernameTeacher;
	private JButton button10;
	private JButton button11;
	private JButton button13;
	private JComboBox<String> teachers;
	private String course;
	private ArrayList<String> teachersArray;
	private JTable table;
	private Instructors t1;
	private JFrame selectTeacherFrame;

	private JComboBox<String> levelOptions;
	private JComboBox<String> courseToCancel;
	private JComboBox<String> cancelledCoursesCombo;

	private JLabel level4Label;
	private JLabel level5Label;
	private JLabel level6label;
	private JComboBox<String> studentChoice;
	private ArrayList<String> studentName;
	private ArrayList<String> studentLev;

	private ArrayList<ArrayList<String>> tableData;
	private String remarks;
	private int passed = 0;
	private int n = 0;
	private ArrayList<String> defaultModules;
	private JLabel text;
	private JLabel courseNameLabel;
	private JTextField[] moduleCodeFields;
	private JTextField[] moduleNameFields;
	private JTextField[] semesterFields;
	private JTextField courseNameField;
	private JButton next1;

	private JLabel moduleCodeLabel;
	private JLabel moduleSemesterLabel;
	private JLabel moduleNameLabel;
	private JComboBox<String>[] moduleMandatoryCombo;
	private JLabel moduleMandatoryLabel;
	private ArrayList<String> lst;
	private JComboBox<String> courseChoice;
	private JComboBox<String> modulesChoice;

	private void connectDB() {
		try {
			con = new Conn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void homeGui(String username) {
		connectDB();

		admin = new CourseAdministrator();
		admin.setAdminDetails(username);
		welcomeText = new JLabel("Welcome,");

		button1 = new JButton("Teacher Registration");
		button1.setFocusable(false);
		button1.addActionListener(this);
		button2 = new JButton("Grades");
		button2.setFocusable(false);
		button2.addActionListener(this);
		button3 = new JButton("Teacher Details");
		button3.setFocusable(false);
		button3.addActionListener(this);
		button9 = new JButton("Add/Remove Modules");
		button9.setFocusable(false);
		button10 = new JButton("New Course");
		button10.setFocusable(false);
		button10.addActionListener(this);

		button11 = new JButton();
		button4 = new JButton("Student Details");
		button4.setFocusable(false);
		button4.addActionListener(this);
		button5 = new JButton("My Details");
		button5.setFocusable(false);
		button5.addActionListener(this);
		button6 = new JButton("Logout");
		button6.addActionListener(this);
		button6.setFocusable(false);
		button7 = new JButton("Print results");
		button7.setFocusable(false);
		button7.addActionListener(this);
		button12 = new JButton("Set Remarks");
		button12.setFocusable(false);
		button12.addActionListener(this);
		button13 = new JButton("Alter Courses");
		button13.addActionListener(this);
		button13.setFocusable(false);
		button8 = new JButton("Exit");
		button8.addActionListener(this);
		button8.setFocusable(false);
		button9.addActionListener(this);
		mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(8, 6));
		mainContainer.setBackground(new Color(0xb2d3e6));

		topPanel = new JPanel();
		middlePanel = new JPanel();

		middlePanel.setBorder(new LineBorder(Color.BLACK, 3));
		middlePanel.setLayout(new BorderLayout());
		middlePanel.setBackground(new Color(0xa0e7a0));

		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(2, 1, 5, 1));

		gridPanel.add(button5);

		gridPanel.add(button6);

		mainPart = new JPanel();
		mainPart.setBackground(Color.white);
		mainPart.setBorder(new LineBorder(Color.black, 3));
		mainPart.setLayout(null);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(button13);
		bottomPanel.add(button12);
		bottomPanel.add(button7);
		bottomPanel.add(button8);
		bottomPanel.setBackground(new Color(0xa0e7a0));
		bottomPanel.setBorder(new LineBorder(Color.black, 3));
		mainContainer.add(bottomPanel, BorderLayout.SOUTH);

		middlePanel.add(gridPanel, BorderLayout.SOUTH);
		mainContainer.add(mainPart, BorderLayout.CENTER);
		mainContainer.add(middlePanel, BorderLayout.WEST);

		topPanel.setBorder(new LineBorder(Color.BLACK, 3));
		topPanel.setBackground(new Color(0xa0e7a0));
		topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 12, 0));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 425, 10, 0));

		topPanel.add(button1);

		topPanel.add(button2);

		topPanel.add(button3);

		topPanel.add(button9);
		topPanel.add(button4);
		topPanel.add(button10);
		mainContainer.add(topPanel, BorderLayout.NORTH);

		image = new ImageIcon("../CourseManagementSystem_2060276/pngwing.com.png");

		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0x9ebbc6)));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setIconImage(image.getImage());
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);

	}

	public void detailViewer() {
		mainPart.removeAll();
		mainPart.setLayout(null);

		first_name = new JLabel("First Name");
		last_name = new JLabel("Last Name");
		username = new JLabel("Username");
		id = new JLabel("ID");
		phone_number = new JLabel("Phone no.");
		password = new JLabel("Password");
		email = new JLabel("Email");

		firstNameField = new JTextField(admin.getFirstName());
		lastNameField = new JTextField(admin.getLastName());
		usernameField = new JLabel(admin.getUsername());
		idField = new JLabel(admin.getId());
		phoneNumberField = new JTextField(admin.getPhoneNumber());
		passwordField = new JTextField(admin.getPassword());
		emailField = new JTextField(admin.getEmail());

		note = new JLabel("Note : You can fix the typos and errors in most of the fields by clicking them!");
		note.setForeground(Color.red);
		note.setBounds(200, 500, 690, 30);
		note.setFont(new Font("Lucida Fox", Font.BOLD, 18));

		first_name.setBounds(60, 60, 100, 30);
		firstNameField.setBounds(200, 60, 200, 30);
		last_name.setBounds(60, 155, 100, 30);
		lastNameField.setBounds(200, 155, 200, 30);
		username.setBounds(60, 250, 100, 30);
		usernameField.setBounds(200, 250, 200, 30);
		id.setBounds(60, 345, 200, 30);
		idField.setBounds(200, 345, 200, 30);
		phone_number.setBounds(660, 250, 200, 30);
		phoneNumberField.setBounds(800, 250, 200, 30);

		password.setBounds(660, 60, 100, 30);
		passwordField.setBounds(800, 60, 200, 30);

		email.setBounds(660, 155, 100, 30);
		emailField.setBounds(800, 155, 200, 30);

		first_name.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		last_name.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		username.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		usernameField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		id.setFont(new Font("Lucida Fox", Font.PLAIN, 18));

		phone_number.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		email.setFont(new Font("Lucida Fox", Font.PLAIN, 18));

		firstNameField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		firstNameField.setBackground(null);

		lastNameField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		lastNameField.setBackground(null);

		usernameField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		usernameField.setBackground(null);

		idField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		idField.setBackground(null);

		password.setFont(new Font("Lucida Fox", Font.PLAIN, 18));

		passwordField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		passwordField.setBackground(null);

		phoneNumberField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		phoneNumberField.setBackground(null);

		emailField.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		emailField.setBackground(null);

		registerButton = new JButton("Update");
		registerButton.setBounds(900, 500, 200, 30);
		registerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String fn = firstNameField.getText();
				String ln = lastNameField.getText();
				String pass = passwordField.getText();
				String pn = phoneNumberField.getText();
				String email = emailField.getText();
				
				if (fn.isEmpty() || ln.isEmpty() || pass.isEmpty() || pn.isEmpty() || email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "None of the fields can be left empty");
				} else if (pass.length() < 6) {
					JOptionPane.showMessageDialog(null, "The length of the password must be greater than 6");

				} else if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
					JOptionPane.showMessageDialog(null, "Invalid Email! Please try again.");

				} else {

					try {

						String query = String.format(
								"UPDATE admindetails SET first_name = '%s' ,last_name = '%s', password = '%s', phone_number = '%s', email = '%s' WHERE username = '%s'",
								fn, ln, pass, pn, email, admin.getUsername());
						con.s.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Successfully Updated!");

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		mainPart.add(first_name);
		mainPart.add(firstNameField);
		mainPart.add(last_name);
		mainPart.add(lastNameField);
		mainPart.add(username);
		mainPart.add(usernameField);
		mainPart.add(id);
		mainPart.add(idField);
		mainPart.add(email);
		mainPart.add(emailField);

		mainPart.add(phone_number);
		mainPart.add(phoneNumberField);
		mainPart.add(password);
		mainPart.add(passwordField);
		mainPart.add(note);
		mainPart.add(registerButton);

		mainContainer.repaint();
		validate();

	}

	public void teacherRegistration() {
		mainPart.removeAll();
		mainPart.setLayout(null);
		courseList = new ArrayList<String>();

		File file = new File("courses.txt");
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			courseList.add(sc.nextLine());
		}
		teacherFirstName = new JLabel("First Name");
		teacherFirstNameField = new JTextField();
		teacherLastName = new JLabel("Last Name");
		teacherLastNameField = new JTextField();
		teacherUsername = new JLabel("Username");
		teacherUsernameField = new JTextField();
		teacherId = new JLabel("ID");
		teacherIdField = new JLabel();
		teacherPassword = new JLabel("Password");
		teacherPasswordField = new JPasswordField();
		teacherRetypePassword = new JLabel("Retype Password");
		teacherRetypePasswordField = new JPasswordField();
		teacherEmail = new JLabel("Email");
		teacherEmailField = new JTextField();
		teacherphoneNumber = new JLabel("Phone No.");
		teacherphoneNumberField = new JTextField();
		teacherCourse = new JLabel("Course");
		String[] lst = courseList.toArray(new String[courseList.size()]);
		teacherCourseField = new JComboBox<String>(lst);

		teacherFirstName.setBounds(60, 60, 100, 30);
		teacherFirstNameField.setBounds(200, 60, 200, 30);
		teacherLastName.setBounds(60, 155, 100, 30);
		teacherLastNameField.setBounds(200, 155, 200, 30);
		teacherUsername.setBounds(60, 250, 100, 30);
		teacherUsernameField.setBounds(200, 250, 200, 30);
		teacherPassword.setBounds(60, 345, 200, 30);
		teacherPasswordField.setBounds(200, 345, 200, 30);
		teacherRetypePassword.setBounds(60, 440, 200, 30);
		teacherRetypePasswordField.setBounds(200, 440, 200, 30);
		teacherId.setBounds(660, 60, 100, 30);
		teacherIdField.setBounds(800, 60, 250, 30);
		teacherEmail.setBounds(660, 155, 100, 30);
		teacherEmailField.setBounds(800, 155, 200, 30);
		teacherphoneNumber.setBounds(660, 250, 100, 30);
		teacherphoneNumberField.setBounds(800, 250, 200, 30);
		teacherCourse.setBounds(660, 345, 100, 30);
		teacherCourseField.setBounds(800, 345, 200, 30);

		teacherFirstName.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherLastName.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherUsername.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherId.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherPassword.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherRetypePassword.setFont(new Font("Lucida Fax", Font.PLAIN, 16));
		teacherphoneNumber.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherEmail.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherCourse.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		teacherFirstNameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherFirstNameField.setBackground(null);
		teacherLastNameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherLastNameField.setBackground(null);
		teacherUsernameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherUsernameField.setBackground(null);
		teacherIdField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherIdField.setBackground(null);
		teacherPasswordField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherPasswordField.setBackground(null);
		teacherRetypePasswordField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherRetypePasswordField.setBackground(null);
		teacherphoneNumberField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherphoneNumberField.setBackground(null);
		teacherEmailField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherEmailField.setBackground(null);
		teacherCourseField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		teacherCourseField.setBackground(null);
		int idNum = 0;

		try {

			String query = "SELECT id FROM teacherdetails ORDER BY id DESC LIMIT 1";
			rs = con.s.executeQuery(query);
			if (rs.next()) {
				idNum = (Integer.parseInt(rs.getString(1)) + 1);
			} else {
				idNum = 45000;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		registerButton = new JButton("Register");
		registerButton.setBounds(900, 500, 200, 30);
		registerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				courseSelected = (String) teacherCourseField.getSelectedItem();

				try {

					fn = teacherFirstNameField.getText();
					ln = teacherLastNameField.getText();
					user = teacherUsernameField.getText();
					pw = String.valueOf(teacherPasswordField.getPassword());
					rpw = String.valueOf(teacherRetypePasswordField.getPassword());
					pn = teacherphoneNumberField.getText();
					em = teacherEmailField.getText();
					

					String checkIfexist = "SELECT * FROM teacherdetails WHERE username = '" + user + "'";
					rs = con.s.executeQuery(checkIfexist);
					if (fn.isEmpty() || ln.isEmpty() || pw.isEmpty() || rpw.isEmpty() || pn.isEmpty() || em.isEmpty()) {
						JOptionPane.showMessageDialog(null, "None of the fields can be left empty");
						return;
					}
					if (!pw.equals(rpw)) {
						JOptionPane.showMessageDialog(null, "The two passwords do not match!");
					}
					if (pw.length() < 6) {
						JOptionPane.showMessageDialog(null, "The length of the password must be greater than 6");
						return;

					}
					if (em.indexOf("@") == -1 && em.indexOf(".") == -1) {
						JOptionPane.showMessageDialog(null, "Invalid Email! Please try again.");
						return;

					}
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "The given information already exists in the database.");
						return;
					}
					
					try {

						String query = String.format(
								"INSERT INTO teacherdetails(first_name, last_name,username, password, phone_number, email, course,modules) "
										+ "VALUES ('%s','%s','%s','%s','%s','%s','%s','null,null,null,null');",
								fn, ln, user, pw, pn, em, courseSelected);

						con.s.executeUpdate(query);
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						addRemoveModules(user, courseSelected, fn, ln);
					}

				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});

		teacherIdField.setText(String.valueOf(idNum) + " (Auto Generated)");

		mainPart.add(teacherFirstName);
		mainPart.add(teacherFirstNameField);
		mainPart.add(teacherLastName);
		mainPart.add(teacherLastNameField);
		mainPart.add(teacherUsername);
		mainPart.add(teacherUsernameField);
		mainPart.add(teacherPassword);
		mainPart.add(teacherPasswordField);
		mainPart.add(teacherRetypePassword);
		mainPart.add(teacherRetypePasswordField);
		mainPart.add(teacherEmail);
		mainPart.add(teacherEmailField);
		mainPart.add(teacherphoneNumber);
		mainPart.add(teacherphoneNumberField);
		mainPart.add(teacherCourse);
		mainPart.add(teacherCourseField);
		mainPart.add(teacherId);
		mainPart.add(teacherIdField);
		mainPart.add(registerButton);

		mainContainer.repaint();

		validate();

	}

	public void addRemoveModules() {
		mainPart.removeAll();
		mainPart.setLayout(null);
		mainContainer.repaint();
		selectTeacherFrame = new JFrame();
		String query = "SELECT first_name, last_name, username, course, modules FROM teacherdetails";
		teachers = new JComboBox<String>();
		teachersArray = new ArrayList<String>();

		ResultSet rs;
		try {
			rs = con.s.executeQuery(query);
			while (rs.next()) {
				teachersArray.add(rs.getString(1) + " " + rs.getString(2));

			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}

		String[] allTeachers = teachersArray.toArray(new String[teachersArray.size()]);
		teachers = new JComboBox<String>(allTeachers);
		teachers.setBounds(40, 50, 200, 30);

		selectTeacherFrame.setTitle("Choose Teacher");
		selectTeacherFrame.setSize(300, 250);
		JButton proceed = new JButton("Proceed");
		proceed.setBounds(50, 100, 150, 30);
		proceed.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String selectedTeacher = (String) teachers.getSelectedItem();
				String query = "SELECT username, course, first_name, last_name FROM teacherdetails WHERE first_name LIKE CONCAT('%','"
						+ selectedTeacher.split(" ", 2)[0] + "','%')";
				try {
					ResultSet rs = con.s.executeQuery(query);
					if (rs.next()) {
						addRemoveModules(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
						selectTeacherFrame.dispose();

					}
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});

		selectTeacherFrame.setResizable(false);

		selectTeacherFrame.getContentPane().setBackground(Color.lightGray);

		selectTeacherFrame.setLayout(null);
		selectTeacherFrame.setLocationRelativeTo(null);
		selectTeacherFrame.setVisible(true);

		selectTeacherFrame.add(teachers);
		selectTeacherFrame.add(proceed);

	}

	public void addRemoveModules(String username, String course, String name, String surname) {
		mainPart.removeAll();
		mainPart.setLayout(null);
//		****************************************************
//		Module add part
		modules = new ArrayList<String>();
		firstNameTeacher = name;
		lastNameTeacher = surname;
		usernameTeacher = username;
		modules.add("--null--");
		Instructors t1 = new Instructors();
		t1.setTeacherDetails(username);

		String query1 = String.format("SELECT moduleName,teacher FROM courses WHERE course = '%s';", course);
		try {
			rs = con.s.executeQuery(query1);
			while (rs.next()) {
				modules.add(rs.getString(1));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		

		currentModules = new ArrayList<String>(Arrays.asList(t1.getModules()));
		module = modules.toArray(new String[modules.size()]);
		countAvailable = Collections.frequency(currentModules, "null");
//			Counting number of modules the teacher can be assigned

		enrollLabel1 = new JLabel("1st Module");
		enrollLabel2 = new JLabel("2nd Module");
		enrollLabel3 = new JLabel("3rd Module");
		enrollLabel4 = new JLabel("4th Module");

		enrollOption1 = new JComboBox<String>(module);
		enrollOption2 = new JComboBox<String>(module);
		enrollOption3 = new JComboBox<String>(module);
		enrollOption4 = new JComboBox<String>(module);

		enrollLabel1.setBounds(60, 110, 100, 30);
		enrollLabel2.setBounds(60, 190, 100, 30);
		enrollLabel3.setBounds(60, 270, 100, 30);
		enrollLabel4.setBounds(60, 350, 100, 30);

		enrollOption1.setBounds(160, 110, 200, 30);
		enrollOption2.setBounds(160, 190, 200, 30);
		enrollOption3.setBounds(160, 270, 200, 30);
		enrollOption4.setBounds(160, 350, 200, 30);

		enrollButton = new JButton("Enroll");
		enrollButton.setBounds(180, 460, 100, 30);
		enrollButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String md1 = (String) enrollOption1.getSelectedItem();
				String md2 = (String) enrollOption2.getSelectedItem();
				String md3 = (String) enrollOption3.getSelectedItem();
				String md4 = (String) enrollOption4.getSelectedItem();
				selectedOptions = new ArrayList<String>();
				selectedOptions.add(md1);
				selectedOptions.add(md2);
				selectedOptions.add(md3);
				selectedOptions.add(md4);
				String getTeachers = String.format(
						"SELECT teacher from courses where moduleName = '%s' or moduleName = '%s' or moduleName = '%s' or moduleName = '%s';",
						md1, md2, md3, md4);
//					Adding the modules selected by administrator in an ArrayList, as it is easier to count duplicate elements

				try {
					int c = 0;
					rs = con.s.executeQuery(getTeachers);
					while (rs.next()) {
						if (!rs.getString(1).equals("null")) {
							JOptionPane.showMessageDialog(null, "The module " + selectedOptions.get(c)
									+ " is already taught by " + rs.getString(1));
							return;

						}
						c+=1;

					}
				} catch (SQLException e2) {

					e2.printStackTrace();
				}

				for (int i = 0; i < 4; i++) {
					if (currentModules.get(i).equals(md1) || currentModules.get(i).equals(md2)
							|| currentModules.get(i).equals(md3) || currentModules.get(i).equals(md4)) {
						JOptionPane.showMessageDialog(null,
								"The teacher has already been assigned to " + currentModules.get(i) + " module.");
						return;

					}

				}
				if ((md1.equals(md2) && !(md1.equals("--null--"))) || (md2.equals(md3) && !(md2.equals("--null--")))
						|| (md3.equals(md4) && !(md3.equals("--null--")))
						|| (md1.equals(md3) && !(md1.equals("--null--")))
						|| (md1.equals(md4) && !(md1.equals("--null--")))
						|| (md2.equals(md4) && !(md2.equals("--null--")))) {
					JOptionPane.showMessageDialog(null,
							"You cannot select same modules from the different combo boxes");
//				
				} else if (countAvailable < 4 - (Collections.frequency(selectedOptions, "--null--"))) {
					JOptionPane.showMessageDialog(null,
							"<html>The teacher has already been assigned " + Integer.toString(4 - countAvailable)
									+ " modules.<br>You cannot assign more than " + Integer.toString(countAvailable)
									+ " modules for the instructor.</html>");

				} else {
					for (Iterator<String> iterator = currentModules.iterator(); iterator.hasNext();) {
						if (iterator.next().equals("null")) {
							iterator.remove();
						}
					}

					for (Iterator<String> iterator = selectedOptions.iterator(); iterator.hasNext();) {
						if (iterator.next().equals("--null--")) {
							iterator.remove();
						}
					}
					for (int i = 0; i < selectedOptions.size(); i++) {
						currentModules.add(selectedOptions.get(i));
					}
				
					if (currentModules.size() < 4) {
						int cnt = currentModules.size();
						while (cnt != 4) {
							currentModules.add("null");
							cnt += 1;
						}
					}
					
					String query = String.format(
							"UPDATE teacherdetails SET modules = '%s,%s,%s,%s' where username = '%s'",
							currentModules.get(0), currentModules.get(1), currentModules.get(2), currentModules.get(3),
							usernameTeacher);
					String query2 = String.format(
							"UPDATE courses SET teacher = '%s %s' WHERE moduleName = '%s' or moduleName = '%s' or moduleName = '%s' or moduleName = '%s';",
							firstNameTeacher, lastNameTeacher, currentModules.get(0), currentModules.get(1),
							currentModules.get(2), currentModules.get(3));
					for (int i = 1; i < 5; i++) {
						String query3 = String.format(
								"UPDATE level4results SET teacher" + Integer.toString(i) + " = '%s' WHERE module"
										+ Integer.toString(i) + "  = '%s' or module" + Integer.toString(i)
										+ " = '%s' or module" + Integer.toString(i) + " = '%s' or module"
										+ Integer.toString(i) + "='%s'",
								firstNameTeacher + " " + lastNameTeacher, currentModules.get(0), currentModules.get(1),
								currentModules.get(2), currentModules.get(3));
						String query4 = String.format(
								"UPDATE level5results SET teacher" + Integer.toString(i) + " = '%s' WHERE module"
										+ Integer.toString(i) + "  = '%s' or module" + Integer.toString(i)
										+ " = '%s' or module" + Integer.toString(i) + " = '%s' or module"
										+ Integer.toString(i) + "='%s'",
								firstNameTeacher + " " + lastNameTeacher, currentModules.get(0), currentModules.get(1),
								currentModules.get(2), currentModules.get(3));
						try {
							con.s.executeUpdate(query3);
							con.s.executeUpdate(query4);
						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					}
					for (int i = 1; i < 3; i++) {
						String query5 = String.format(
								"UPDATE level6results SET teacher" + Integer.toString(i) + " = '%s' WHERE module"
										+ Integer.toString(i) + "  = '%s' or module" + Integer.toString(i)
										+ " = '%s' or module" + Integer.toString(i) + " = '%s' or module"
										+ Integer.toString(i) + "='%s'",
								firstNameTeacher + " " + lastNameTeacher, currentModules.get(0), currentModules.get(1),
								currentModules.get(2), currentModules.get(3));
						try {
							con.s.executeUpdate(query5);

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					try {
						con.s.executeUpdate(query);
						con.s.executeUpdate(query2);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}
			}
		});

//		****************************************************
//		Module remove part 

		removeModuleCombo = new JComboBox<String>();
		moduleToDelete = currentModules.toArray(new String[currentModules.size()]);
		removeModuleCombo.setModel(new DefaultComboBoxModel<String>(moduleToDelete));//

		informationText = new JLabel("<html>This is the add/remove module section for the teachers.");
		informationText.setBounds(290, 500, 800, 50);
		informationText.setFont(new Font("Lucida Fox", Font.PLAIN, 18));
		informationText.setForeground(Color.red);

		removeModuleCombo.setBounds(600, 130, 250, 30);
		remove = new JButton("Remove");
		remove.setBounds(660, 190, 100, 30);
		remove.setFocusable(false);

		addText = new JLabel("Add Module");
		removeText = new JLabel("Remove Module");
		addText.setBounds(170, 60, 200, 30);
		removeText.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		removeText.setBounds(660, 60, 200, 30);
		addText.setFont(new Font("Lucida Fox", Font.BOLD, 18));

		remove.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String selectedMod = (String) removeModuleCombo.getSelectedItem();
				
				currentModules.remove(selectedMod);
				if (currentModules.size() < 4) {
					currentModules.add("null");
				}
				
				String query = String.format("UPDATE teacherdetails SET modules = '%s,%s,%s,%s' where username = '%s'",
						currentModules.get(0), currentModules.get(1), currentModules.get(2), currentModules.get(3),
						usernameTeacher);
				for (int i = 1; i < 5; i++) {
					String query3 = String.format(
							"UPDATE level4results SET teacher" + Integer.toString(i) + " = 'null' WHERE module"
									+ Integer.toString(i) + "  = '%s' or module" + Integer.toString(i)
									+ " = '%s' or module" + Integer.toString(i) + " = '%s' or module"
									+ Integer.toString(i) + "='%s'",
							currentModules.get(0), currentModules.get(1), currentModules.get(2), currentModules.get(3));
					String query4 = String.format(
							"UPDATE level5results SET teacher" + Integer.toString(i) + " = 'null' WHERE module"
									+ Integer.toString(i) + "  = '%s' or module" + Integer.toString(i)
									+ " = '%s' or module" + Integer.toString(i) + " = '%s' or module"
									+ Integer.toString(i) + "='%s'",
							currentModules.get(0), currentModules.get(1), currentModules.get(2), currentModules.get(3));
					try {
						con.s.executeUpdate(query3);
						con.s.executeUpdate(query4);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

				for (int i = 1; i < 3; i++) {
					String query2 = String.format(
							"UPDATE level6results SET teacher" + Integer.toString(i) + " = 'null' WHERE module"
									+ Integer.toString(i) + "  = '%s' or module" + Integer.toString(i)
									+ " = '%s' or module" + Integer.toString(i) + " = '%s' or module"
									+ Integer.toString(i) + "='%s'",
							firstNameTeacher + " " + lastNameTeacher, currentModules.get(0), currentModules.get(1),
							currentModules.get(2), currentModules.get(3));
					try {
						con.s.executeUpdate(query2);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
				try {
					con.s.execute(query);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});

		mainPart.add(enrollLabel1);
		mainPart.add(enrollLabel2);
		mainPart.add(enrollLabel3);
		mainPart.add(enrollLabel4);
		mainPart.add(informationText);
		mainPart.add(enrollOption1);
		mainPart.add(enrollOption2);
		mainPart.add(enrollOption3);
		mainPart.add(enrollOption4);
		mainPart.add(enrollButton);
		mainPart.add(remove);
		mainPart.add(removeModuleCombo);
		mainPart.add(addText);
		mainPart.add(removeText);
		mainContainer.repaint();
		revalidate();

	}

	public void teacherDetails() {
		mainPart.removeAll();
		mainPart.setLayout(null);

		String query = "SELECT * FROM teacherdetails;";
		ArrayList<ArrayList<String>> tabledata = new ArrayList<ArrayList<String>>();

		try {
			ResultSet rs = con.s.executeQuery(query);
			while (rs.next()) {
				ArrayList<String> rowData = new ArrayList<String>(
						Arrays.asList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				tabledata.add(rowData);

			}

			

		} catch (SQLException e) {

			e.printStackTrace();
		}

		ArrayList<String> columns = new ArrayList<String>();
		try {
			String getcols = "SELECT *FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'teacherdetails'";
			ResultSet rs = con.s.executeQuery(getcols);
			while (rs.next()) {
				columns.add(rs.getString(4));
//				In informationschema.columns the column names are stored at index 4
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[][] tableRows = new String[tabledata.size()][];
		for (int i = 0; i < tabledata.size(); i++) {
			ArrayList<String> row = tabledata.get(i);
			tableRows[i] = row.toArray(new String[row.size()]);
		}
	
		String[] columnNames = columns.toArray(new String[columns.size()]);
		table = new JTable(tableRows, columnNames) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(60+rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
//				This is for fitting the data to its cell
			}
			};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false);
		table.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
//		
		JScrollPane scrollpane = new JScrollPane(table);

		mainPart.setLayout(new BorderLayout());
		scrollpane.setBounds(2, 3, 1147, 460);
		table.setRowHeight(90);
		table.getTableHeader().setPreferredSize(new Dimension(50, 50));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < 9; x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		table.setShowGrid(true);
//		 For centering the column data

		mainPart.add(scrollpane);

		mainContainer.repaint();
		validate();

	}

	public void studentdetails() {
		mainPart.removeAll();
		mainPart.setLayout(null);
		String query = "SELECT * FROM studentdetails;";
		ArrayList<ArrayList<String>> tabledata = new ArrayList<ArrayList<String>>();

		try {
			ResultSet rs = con.s.executeQuery(query);
			while (rs.next()) {
				ArrayList<String> rowData = new ArrayList<String>(Arrays.asList(rs.getString(1), rs.getString(2),
						rs.getString(3),rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
				tabledata.add(rowData);

			}

		

		} catch (SQLException e) {

			e.printStackTrace();
		}

		ArrayList<String> columns = new ArrayList<String>();
		try {
			String getcols = "SELECT *FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'studentdetails'";
			ResultSet rs = con.s.executeQuery(getcols);
			while (rs.next()) {
				columns.add(rs.getString(4));
//				In informationschema.columns the column names are stored at index 4
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		columns.remove(4);
		String[][] tableRows = new String[tabledata.size()][];
		for (int i = 0; i < tabledata.size(); i++) {
			ArrayList<String> row = tabledata.get(i);
			tableRows[i] = row.toArray(new String[row.size()]);
		}
		
		String[] columnNames = columns.toArray(new String[columns.size()]);
		table = new JTable(tableRows, columnNames) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(60+rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
//				This is for fitting the data to its cell
			}
			};
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
//		table.setBounds(50, 50, 200, 230);
//		table.setFillsViewportHeight(true);
		JScrollPane scrollpane = new JScrollPane(table);

		mainPart.setLayout(new BorderLayout());
		scrollpane.setBounds(2, 3, 1147, 460);
		table.setRowHeight(90);
		table.getTableHeader().setPreferredSize(new Dimension(50, 50));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < 10; x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		table.setShowGrid(true);
//		 For centering the column data

		mainPart.add(scrollpane);

		mainContainer.repaint();
		validate();

	}

	public void setRemarks() {
		String queryLev4 = "SELECT grade1, grade2, grade3, grade4, username FROM level4results";

		try {

			rs = con.s.executeQuery(queryLev4);
			while (rs.next()) {
				int passed = 0;
				int n = 0;
				String user = rs.getString(5);
				for (int i = 1; i < 5; i++) {

					if (!rs.getString(i).equals("null")) {
						if (Integer.parseInt(rs.getString(i)) > 40) {
							passed += 1;
						}

					} else if (rs.getString(i).equals("null")) {
						n += 1;
					}
				}
				if (passed >= 2 && n < 1) {
					remarks = "PASSED";
				} else if (passed < 2 && n < 1) {
					remarks = "FAILED";
				} else {
					remarks = "TBD";
				}

				String query2 = String.format("UPDATE level4results SET remarks = '%s' WHERE username = '%s'", remarks,
						user);
				try {
					Conn conn = new Conn();
					conn.s.executeUpdate(query2);
				} catch (SQLException e) {

					e.printStackTrace();
				}
				
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		String queryLev5 = "SELECT grade1, grade2, grade3, grade4, username FROM level5results";

		try {
			ResultSet rs2 = con.s.executeQuery(queryLev5);
			while (rs2.next()) {
				String user = rs2.getString(5);
				for (int i = 1; i < 5; i++) {

					if (!rs2.getString(i).equals("null")) {
						if (Integer.parseInt(rs2.getString(i)) > 40) {
							passed += 1;
						}

					} else if (rs2.getString(i).equals("null")) {
						n += 1;
					}
					if (passed > 2 && n < 1) {
						remarks = "PASSED";
					} else if (passed < 2 && n < 1) {
						remarks = "FAILED";
					} else {
						remarks = "TBD";
					}

					String query2 = String.format("UPDATE level5results SET remarks = '%s' WHERE username = '%s'",
							remarks, user);
					try {
						Conn conn = new Conn();
						conn.s.executeUpdate(query2);
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		String queryLev6 = "SELECT grade1, grade2, username FROM level6results";

		try {
			ResultSet rs3 = con.s.executeQuery(queryLev6);
			while (rs3.next()) {
				String user = rs3.getString(5);
				for (int i = 1; i < 3; i++) {

					if (!rs3.getString(i).equals("null")) {
						if (Integer.parseInt(rs3.getString(i)) > 40) {
							passed += 1;
						}

					} else if (rs3.getString(i).equals("null")) {
						n += 1;
					}
					if (passed > 2 && n < 1) {
						remarks = "PASSED";
					} else if (passed < 2 && n < 1) {
						remarks = "FAILED";
					} else {
						remarks = "TBD";
					}

					String query2 = String.format("UPDATE level6results SET remarks = '%s' WHERE username = '%s'",
							remarks, user);
					try {
						Conn conn = new Conn();
						conn.s.executeUpdate(query2);
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Successfully Updated!");

	}

	public void grades() {
		mainPart.removeAll();
		mainPart.setLayout(null);
		String[] levels = { "Level 4", "Level 5", "Level 6" };
		text = new JLabel("Select a level to view results.");
		text.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		text.setBounds(40, 40, 300, 40);
		levelOptions = new JComboBox<String>(levels);
		levelOptions.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		levelOptions.setBounds(350, 40, 200, 40);

		tableData = new ArrayList<ArrayList<String>>();

		levelOptions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Component[] components = mainPart.getComponents();
				for (Component component : components) {
					mainPart.remove(component);
						
					
				}

				String selectedLevel = ((String) levelOptions.getSelectedItem()).split(" ", 2)[1];
				String query = String.format("SELECT * FROM level%sresults", selectedLevel);
				try {
					ResultSet rs = con.s.executeQuery(query);
					while (rs.next()) {
						ArrayList<String> rowData = new ArrayList<String>(Arrays.asList(rs.getString(1),
								rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
								rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
								rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
								rs.getString(16), rs.getString(17), rs.getString(18)));
						tableData.add(rowData);
					}
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}
				ArrayList<String> columns = new ArrayList<String>();
				try {
					String getcols = String.format(
							"SELECT *FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'level%sresults'",
							selectedLevel);
					ResultSet rs = con.s.executeQuery(getcols);
					while (rs.next()) {
						columns.add(rs.getString(4));
//						In informationschema.columns the column names are stored at index 4
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				String[][] tableRows = new String[tableData.size()][];
				for (int i = 0; i < tableData.size(); i++) {
					ArrayList<String> row = tableData.get(i);
					tableRows[i] = row.toArray(new String[row.size()]);
				}
			
				String[] columnNames = columns.toArray(new String[columns.size()]);
				table = new JTable(tableRows, columnNames) {
					@Override
					public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
						Component component = super.prepareRenderer(renderer, row, column);
						int rendererWidth = component.getPreferredSize().width;
						TableColumn tableColumn = getColumnModel().getColumn(column);
						tableColumn.setPreferredWidth(
								Math.max(60+rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
						return component;
//						This is for fitting the data to its cell
					}

				};

				table.setEnabled(false);
				table.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
//				table.setBounds(50, 50, 200, 230);
//				table.setFillsViewportHeight(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane scrollpane = new JScrollPane(table);

				mainPart.setLayout(new BorderLayout());
				scrollpane.setBounds(2, 3, 1147, 460);
				table.setRowHeight(90);
				table.getTableHeader().setPreferredSize(new Dimension(50, 50));
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				for (int x = 0; x < 18; x++) {
					table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
				}
				table.setShowGrid(true);
//				 For centering the column data

				mainPart.add(scrollpane);
				mainContainer.repaint();
				validate();

			}
		});

		mainPart.add(text);
		mainPart.add(levelOptions);

		mainContainer.repaint();
		validate();
	}

	public void newCourse() {
		mainPart.removeAll();
		mainPart.setLayout(null);
		courseNameLabel = new JLabel("Course Name:");
		moduleCodeLabel = new JLabel("Module Codes:");
		moduleCodeLabel.setBounds(120, 155, 100, 30);
		moduleCodeLabel.setForeground(Color.red);

		moduleSemesterLabel = new JLabel("Semesters:");
		moduleSemesterLabel.setForeground(Color.red);
		moduleSemesterLabel.setBounds(390, 155, 100, 30);
		moduleNameLabel = new JLabel("Module Names:");
		moduleNameLabel.setForeground(Color.red);
		moduleNameLabel.setBounds(660, 155, 100, 30);
		courseNameField = new JTextField();
		moduleCodeFields = new JTextField[8];
		moduleNameFields = new JTextField[8];
		semesterFields = new JTextField[8];

		level4Label = new JLabel("Level 4 Modules:");
		courseNameField.setBounds(170, 40, 200, 40);
		courseNameLabel.setBounds(30, 40, 250, 40);
		courseNameLabel.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		level4Label.setBounds(30, 120, 250, 30);
		level4Label.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		int y = 190;
		int yAdd = 0;
		next1 = new JButton("Next");
		next1.setBounds(925, 500, 100, 30);

		for (int i = 0; i < 8; i++) {
			moduleCodeFields[i] = new JTextField();
			moduleCodeFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			moduleCodeFields[i].setBounds(120, y + yAdd, 200, 30);
			mainPart.add(moduleCodeFields[i]);

			semesterFields[i] = new JTextField();

			semesterFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			semesterFields[i].setBounds(390, y + yAdd, 200, 30);

			mainPart.add(semesterFields[i]);

			moduleNameFields[i] = new JTextField();

			moduleNameFields[i].setBounds(660, y + yAdd, 200, 30);
			moduleNameFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			mainPart.add(moduleNameFields[i]);

			yAdd += 45;

		}
		next1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String course = courseNameField.getText();
				for (int i = 0; i < 8; i++) {
					String query = String.format(
							"INSERT INTO courses(moduleCode, moduleName, course, level, semester, mandatory, teacher) VALUES ('%s', '%s', '%s','4','%s','1', 'null')",
							moduleCodeFields[i].getText(), moduleNameFields[i].getText(), course,
							semesterFields[i].getText());
					try {
						con.s.executeUpdate(query);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "Level 4 Modules Added.");
				level5Modules(course);

			}
		});

		mainPart.add(courseNameField);
		mainPart.add(courseNameLabel);
		mainPart.add(moduleSemesterLabel);
		mainPart.add(moduleNameLabel);
		mainPart.add(moduleCodeLabel);
		mainPart.add(level4Label);
		mainPart.add(next1);
		mainContainer.repaint();
		validate();

	}

	public void level5Modules(String course) {

		mainPart.removeAll();

		moduleCodeLabel = new JLabel("Module Codes:");
		moduleCodeLabel.setBounds(120, 155, 100, 30);
		moduleCodeLabel.setForeground(Color.red);

		moduleSemesterLabel = new JLabel("Semesters:");
		moduleSemesterLabel.setForeground(Color.red);
		moduleSemesterLabel.setBounds(390, 155, 100, 30);
		moduleNameLabel = new JLabel("Module Names:");
		moduleNameLabel.setForeground(Color.red);
		moduleNameLabel.setBounds(660, 155, 100, 30);

		moduleCodeFields = new JTextField[8];
		moduleNameFields = new JTextField[8];
		semesterFields = new JTextField[8];

		level4Label = new JLabel("Level 5 Modules:");
		courseNameField.setBounds(110, 40, 200, 40);
		courseNameLabel.setBounds(30, 40, 250, 40);
		courseNameLabel.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		level4Label.setBounds(30, 120, 250, 30);
		level4Label.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		int y = 190;
		int yAdd = 0;
		next1 = new JButton("Next");
		next1.setBounds(925, 500, 100, 30);

		for (int i = 0; i < 8; i++) {
			moduleCodeFields[i] = new JTextField();
			moduleCodeFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			moduleCodeFields[i].setBounds(120, y + yAdd, 200, 30);
			mainPart.add(moduleCodeFields[i]);

			semesterFields[i] = new JTextField();

			semesterFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			semesterFields[i].setBounds(390, y + yAdd, 200, 30);

			mainPart.add(semesterFields[i]);

			moduleNameFields[i] = new JTextField();

			moduleNameFields[i].setBounds(660, y + yAdd, 200, 30);
			moduleNameFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			mainPart.add(moduleNameFields[i]);

			yAdd += 45;

		}
		next1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String course = courseNameField.getText();
				for (int i = 0; i < 8; i++) {
					String query = String.format(
							"INSERT INTO courses(moduleCode, moduleName, course, level, semester, mandatory, teacher) VALUES ('%s', '%s', '%s','5','%s','1', 'null')",
							moduleCodeFields[i].getText(), moduleNameFields[i].getText(), course,
							semesterFields[i].getText());
					try {
						con.s.executeUpdate(query);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "Level 5 Modules Added.");
				level6Modules(course);

			}
		});

		mainPart.add(moduleSemesterLabel);
		mainPart.add(moduleNameLabel);
		mainPart.add(moduleCodeLabel);
		mainPart.add(level4Label);
		mainPart.add(next1);
		mainContainer.repaint();
		validate();

	}

	public void level6Modules(String course) {
		mainPart.removeAll();

		moduleCodeLabel = new JLabel("Module Codes:");
		moduleCodeLabel.setBounds(120, 155, 100, 30);
		moduleCodeLabel.setForeground(Color.red);

		moduleSemesterLabel = new JLabel("Semesters:");
		moduleSemesterLabel.setForeground(Color.red);
		moduleSemesterLabel.setBounds(390, 155, 100, 30);
		moduleNameLabel = new JLabel("Module Names:");
		moduleNameLabel.setForeground(Color.red);
		moduleNameLabel.setBounds(660, 155, 100, 30);
		moduleMandatoryLabel = new JLabel("Mandatory:");
		moduleMandatoryLabel.setBounds(930, 155, 100, 30);
		String[] options = { "0", "1" };
		moduleMandatoryCombo = new JComboBox[4];

		moduleCodeFields = new JTextField[4];
		moduleNameFields = new JTextField[4];
		semesterFields = new JTextField[4];

		level4Label = new JLabel("Level 6 Modules:");
		courseNameField.setBounds(110, 40, 200, 40);
		courseNameLabel.setBounds(30, 40, 250, 40);
		courseNameLabel.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		level4Label.setBounds(30, 120, 250, 30);
		level4Label.setFont(new Font("Lucida Fox", Font.BOLD, 18));
		int y = 190;
		int yAdd = 0;
		next1 = new JButton("Done");
		next1.setBounds(925, 500, 100, 30);

		for (int i = 0; i < 4; i++) {
			moduleCodeFields[i] = new JTextField();
			moduleCodeFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			moduleCodeFields[i].setBounds(120, y + yAdd, 200, 30);
			mainPart.add(moduleCodeFields[i]);

			semesterFields[i] = new JTextField();

			semesterFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			semesterFields[i].setBounds(390, y + yAdd, 200, 30);

			mainPart.add(semesterFields[i]);

			moduleNameFields[i] = new JTextField();

			moduleNameFields[i].setBounds(660, y + yAdd, 200, 30);
			moduleNameFields[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			mainPart.add(moduleNameFields[i]);

			moduleMandatoryCombo[i] = new JComboBox<String>(options);
			moduleMandatoryCombo[i].setBounds(930, y + yAdd, 200, 30);
			moduleMandatoryCombo[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
			mainPart.add(moduleMandatoryCombo[i]);

			yAdd += 45;

		}
		next1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String course = courseNameField.getText();
				for (int i = 0; i < 4; i++) {
					String query = String.format(
							"INSERT INTO courses(moduleCode, moduleName, course, level, semester, mandatory, teacher) VALUES ('%s', '%s', '%s','6','%s','%s', 'null')",
							moduleCodeFields[i].getText(), moduleNameFields[i].getText(), course,
							semesterFields[i].getText(), moduleMandatoryCombo[i].getSelectedItem());
					try {
						con.s.executeUpdate(query);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "Level 6 Modules Added. Course has successfully been created!");
				level6Modules(course);

			}
		});

		mainPart.add(moduleMandatoryLabel);
		mainPart.add(moduleSemesterLabel);
		mainPart.add(moduleNameLabel);
		mainPart.add(moduleCodeLabel);
		mainPart.add(level4Label);
		mainPart.add(next1);

		mainContainer.repaint();
		validate();

	}

	public void deleteCourse() {
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

		mainPart.removeAll();
		mainPart.setLayout(null);
		JLabel text = new JLabel("Select a course to delete:");
		text.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		text.setBounds(30, 40, 300, 40);

		String[] courses = lst.toArray(new String[lst.size()]);

		courseChoice = new JComboBox<String>(courses);
		courseChoice.setBounds(290, 40, 200, 40);
		JButton confirm = new JButton("Delete");
		confirm.setBounds(290, 100, 100, 30);

		confirm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String selectedCourse = (String) courseChoice.getSelectedItem();
				String query1 = String.format("DELETE * FROM courses WHERE course = '%s'", selectedCourse);
				String query2 = String.format("DELETE * FROM studentdetails WHERE course='%s'", selectedCourse);
				try {
					con.s.executeUpdate(query1);
					con.s.executeUpdate(query2);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		ArrayList<String> modules = new ArrayList<String>();
		String fetchModules = "SELECT moduleName FROM courses";
		try {
			ResultSet rs = con.s.executeQuery(fetchModules);
			while (rs.next()) {
				modules.add(rs.getString(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel text2 = new JLabel("Select a module to delete:");
		text2.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		text2.setBounds(550, 40, 300, 40);

		String[] modulesCombo = modules.toArray(new String[modules.size()]);

		modulesChoice = new JComboBox<String>(modulesCombo);
		modulesChoice.setBounds(820, 40, 270, 40);
		JButton confirm2 = new JButton("Delete");
		confirm2.setBounds(810, 100, 100, 30);

		confirm2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String selectedModule = (String) modulesChoice.getSelectedItem();
				String query1 = String.format("DELETE FROM courses WHERE moduleName = '%s'", selectedModule);
				String query2 = String.format("UPDATE studentdetails SET modules =REPLACE('modules','%s','null');",
						selectedModule);
				try {
					con.s.executeUpdate(query1);
					con.s.executeUpdate(query2);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JLabel text3 = new JLabel("Select a course to cancel:");

		JLabel text4 = new JLabel("These courses might be avaiable at a later time");
		text4.setForeground(Color.red);
		courseToCancel = new JComboBox<String>(courses);
		courseToCancel.setBounds(290, 240, 200, 40);
		courseToCancel.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(290, 310, 100, 30);
		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String cancelSelection = (String) courseToCancel.getSelectedItem();
				String query = "INSERT INTO cancelledcourses SELECT * FROM courses WHERE course ='" + cancelSelection
						+ "'";
				String query2 = "DELETE FROM courses WHERE course = '" + cancelSelection + "'";
				try {
					con.s.executeUpdate(query);
					con.s.executeUpdate(query2);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});

		text3.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		text3.setBounds(30, 240, 300, 40);

		JLabel text5 = new JLabel("Reactivate Cancelled Courses:");
		text5.setBounds(550, 240, 300, 30);
		text5.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		String query = "SELECT DISTINCT course FROM cancelledcourses";
		ArrayList<String> cancelledCourses = new ArrayList<String>();
		try {
			ResultSet rs = con.s.executeQuery(query);
			while (rs.next()) {
				cancelledCourses.add(rs.getString(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		String[] cancCourses = cancelledCourses.toArray(new String[cancelledCourses.size()]);
		cancelledCoursesCombo = new JComboBox<String>(cancCourses);
		cancelledCoursesCombo.setBounds(840, 240, 200, 30);
		JButton retreive = new JButton("Retrieve");
		retreive.setBounds(810, 310, 100, 30);
		retreive.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String retrieveSelection = (String) courseToCancel.getSelectedItem();
				String query = "INSERT INTO courses SELECT * FROM courses WHERE course ='" + retrieveSelection + "'";
				String query2 = "DELETE FROM cancelledcourses WHERE course = '" + retrieveSelection + "'";
				try {
					con.s.executeUpdate(query);
					con.s.executeUpdate(query2);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		mainPart.add(text);
		mainPart.add(text2);
		mainPart.add(modulesChoice);
		mainPart.add(confirm2);
		mainPart.add(cancel);
		mainPart.add(courseChoice);
		mainPart.add(courseToCancel);
		mainPart.add(courseToCancel);
		mainPart.add(retreive);
		mainPart.add(cancelledCoursesCombo);

		mainPart.add(text3);
		mainPart.add(text4);
		mainPart.add(text5);
		mainPart.add(confirm);
		mainContainer.repaint();
		validate();

	}

	public void printResults() {

		mainPart.removeAll();
		mainPart.setLayout(null);
		JLabel infoText = new JLabel("Select a student to print their results:");
		infoText.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		infoText.setBounds(30, 40, 400, 30);

		studentName = new ArrayList<String>();
		studentLev = new ArrayList<String>();
		String query = "SELECT first_name, last_name FROM studentdetails";
		try {
			ResultSet rs = con.s.executeQuery(query);
			while (rs.next()) {
				studentName.add(rs.getString(1) + " " + rs.getString(2));
				System.out.println(studentName.get(0));

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		String[] names = studentName.toArray(new String[studentName.size()]);
		studentChoice = new JComboBox<String>(names);
		studentChoice.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		studentChoice.setBounds(400, 40, 200, 30);

		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		confirmButton.setBounds(380, 150, 120, 30);

		confirmButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String selectedStud = (String) studentChoice.getSelectedItem();
				String lev = "";
				String query = "SELECT level FROM studentdetails WHERE first_name LIKE CONCAT('%','"
						+ selectedStud.split(" ", 2)[0] + "','%')";
				try {
					ResultSet rs = con.s.executeQuery(query);
					if (rs.next()) {
						lev = rs.getString(1);
						System.out.println(lev);
						

					}

				} catch (Exception excp) {
					excp.printStackTrace();
				}

				try {
					myObj = new File(selectedStud.replaceAll(" \\s", "") + ".txt");
//			      removing space from name
					if (myObj.createNewFile()) {
						System.out.println("File created: " + myObj.getName());

					} else {
						System.out.println("File already exists.");
					}
				} catch (Exception exc) {
					System.out.println("An error occurred.");
					exc.printStackTrace();
				}
				String getData = "SELECT * FROM level" + lev + "results WHERE studentname='" + selectedStud + "'";
			
				ArrayList<String> columns = new ArrayList<String>();
				try {
					String getcols = String.format(
							"SELECT *FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'level%sresults'", lev);
					ResultSet rs = con.s.executeQuery(getcols);
					while (rs.next()) {
						columns.add(rs.getString(4));
//						In informationschema.columns the column names are stored at index 4
					}
					
				} catch (Exception excp) {
					excp.printStackTrace();
				}

				try {
					ResultSet rs = con.s.executeQuery(getData);
					if (rs.next()) {
						System.out.println("AHIDASHNID");
						try {
							System.out.println(rs.getString(1));

//							
							FileWriter writer = new FileWriter(myObj.getName());
							for (int i = 1; i < columns.size(); i++) {
								writer.write(columns.get(i) + " : " + rs.getString(i + 1) + "\n");

							}
							writer.close();

						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Done! The result has been printed to file: " + myObj.getName());

//				

			}

		});

		mainPart.add(confirmButton);
		mainPart.add(studentChoice);
		mainPart.add(infoText);
		mainContainer.repaint();
		mainContainer.validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button5) {
			detailViewer();

		} else if (e.getSource() == button8) {
			System.exit(1);
		} else if (e.getSource() == button1) {
			teacherRegistration();
		} else if (e.getSource() == button9) {
			addRemoveModules();
		} else if (e.getSource() == button3) {
			teacherDetails();

		} else if (e.getSource() == button4) {
			studentdetails();
		} else if (e.getSource() == button2) {
			grades();
		} else if (e.getSource() == button12) {
			setRemarks();
		} else if (e.getSource() == button10) {
			newCourse();
		} else if (e.getSource() == button13) {
			deleteCourse();
		} else if (e.getSource() == button7) {
			printResults();
		} else if (e.getSource() == button6) {
			JOptionPane.showMessageDialog(null, "Logging Out...");
			this.dispose();
			new Login().createGui();
		}

	}

}

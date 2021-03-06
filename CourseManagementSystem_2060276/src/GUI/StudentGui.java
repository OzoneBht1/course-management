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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.Query;

import DbConnect.Conn;
import course_users.Students;

@SuppressWarnings("serial")
public class StudentGui extends JFrame implements ActionListener {
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
	private JLabel welcomeText;
	private JLabel appInfo;
	private JPanel mainPart;
	private JLabel first_name;
	private JLabel last_name;
	private JLabel username;
	private JLabel id;
	private JLabel password;
	private JLabel phone_number;
	private JLabel email;
	private JLabel course;
	private JLabel level;
	private JLabel semester;
	private JTextField welcomeTextField;
	private JTextField appInfoField;
	private JTextField mainPartField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JLabel usernameField;
	private JLabel idField;
	private JTextField passwordField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JLabel courseField;
	private JLabel levelField;
	private JLabel semesterField;
	private Students student;
	private JLabel note;
	private JButton updateButton;
	private JPanel bottomPanel;

	private ArrayList<String> moduleNames;
	private ArrayList<String> moduleCodes;
	private ArrayList<String> moduleCourses;
	private JLabel enrollmentInfo;
	private JComboBox<String> optionalModules;
	private JLabel moduleCode;
	private JLabel moduleName;
	private JLabel fromCourse;
	private JLabel moduleCodeFromCBox;
	private JLabel courseFromCBox;
	private JButton enrollButton;
	private int index;
	private Conn con;
	private ResultSet rs;
	private JTable table;
	private ArrayList<String> modules;
	private ArrayList<String> grades;
	private ArrayList<String> teachers;
	private String[] columnNames = { "Module", "Marks Obtained", "Graded By" };

	private void connectDB() {
		try {
			con = new Conn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void homeGui(String username) {
		connectDB();

		student = new Students();
		student.setStudentDetails(username);
		

		
		welcomeText = new JLabel("Welcome,");
 
		
		button2 = new JButton("Results");
		button2.setFocusable(false);
		button2.addActionListener(this);
		button3 = new JButton("Teacher Details");
		button3.setFocusable(false);
		button3.addActionListener(this);
		button4 = new JButton("Enroll Modules");
		button4.setFocusable(false);

		button4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if (student.getLevel().equals("6")) {
					enrollModules();
				} else if (!student.getLevel().equals("6")) {
					JOptionPane.showMessageDialog(null,
							"<html> Only level 6 students have the option of enrolling in additional courses!</html>");

				}

			}

		});

		button5 = new JButton("My Details");
		button5.setFocusable(false);
		button5.addActionListener(this);
		button6 = new JButton("Logout");
		button6.setFocusable(false);
		button6.addActionListener(this);	
		button8 = new JButton("Exit");
		button8.setFocusable(false);
		button8.addActionListener(this);

		mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(8, 6));
		mainContainer.setBackground(new Color(0xb2d3e6));

		topPanel = new JPanel();
		middlePanel = new JPanel();

		middlePanel.setBorder(new LineBorder(Color.BLACK, 3));
		middlePanel.setLayout(new BorderLayout());
		middlePanel.setBackground(new Color(0xb2d3e6));

		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(2, 1, 5, 1));

		gridPanel.add(button5);

		gridPanel.add(button6);

		mainPart = new JPanel();
		mainPart.setBackground(Color.white);
		mainPart.setBorder(new LineBorder(Color.black, 3));
		mainPart.setLayout(null);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

	
		bottomPanel.add(button8);
		bottomPanel.setBackground(new Color(0xb2d3e6));
		bottomPanel.setBorder(new LineBorder(Color.black, 3));
		mainContainer.add(bottomPanel, BorderLayout.SOUTH);

		middlePanel.add(gridPanel, BorderLayout.SOUTH);
		mainContainer.add(mainPart, BorderLayout.CENTER);
		mainContainer.add(middlePanel, BorderLayout.WEST);

		topPanel.setBorder(new LineBorder(Color.BLACK, 3));
		topPanel.setBackground(new Color(0xb2d3e6));
		topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 12, 0));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 425, 10, 0));

		

		topPanel.add(button2);

		topPanel.add(button3);
		topPanel.add(button4);

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

		first_name = new JLabel("First Name");
		last_name = new JLabel("Last Name");
		username = new JLabel("Username");
		id = new JLabel("ID");
		phone_number = new JLabel("Phone no.");
		password = new JLabel("Password");
		email = new JLabel("Email");
		course = new JLabel("Course");
		level = new JLabel("Level");
		semester = new JLabel("Semester");
		welcomeTextField = new JTextField();

		firstNameField = new JTextField(student.getFirstName());
		lastNameField = new JTextField(student.getLastName());
		usernameField = new JLabel(student.getUsername());
		idField = new JLabel(student.getId());
		phoneNumberField = new JTextField(student.getPhoneNumber());
		passwordField = new JTextField(student.getPassword());
		emailField = new JTextField(student.getEmail());
		courseField = new JLabel(student.getCourse());
		levelField = new JLabel(student.getLevel());
		semesterField = new JLabel(student.getSemester());

		note = new JLabel("Note : You can fix the typos and errors in most of the fields by clicking them!");
		note.setForeground(Color.red);
		note.setBounds(200, 500, 690, 30);
		note.setFont(new Font("Lucida Fax", Font.BOLD, 18));

		first_name.setBounds(60, 60, 100, 30);
		firstNameField.setBounds(200, 60, 200, 30);
		last_name.setBounds(60, 155, 100, 30);
		lastNameField.setBounds(200, 155, 200, 30);
		username.setBounds(60, 250, 100, 30);
		usernameField.setBounds(200, 250, 200, 30);
		id.setBounds(60, 345, 200, 30);
		idField.setBounds(200, 345, 200, 30);
		phone_number.setBounds(60, 440, 200, 30);
		phoneNumberField.setBounds(200, 440, 200, 30);

		password.setBounds(660, 60, 100, 30);
		passwordField.setBounds(800, 60, 200, 30);

		email.setBounds(660, 155, 100, 30);
		emailField.setBounds(800, 155, 200, 30);
		course.setBounds(660, 250, 100, 30);
		courseField.setBounds(800, 250, 200, 30);
		level.setBounds(660, 345, 100, 30);
		levelField.setBounds(800, 345, 200, 30);

		semester.setBounds(660, 440, 100, 30);
		semesterField.setBounds(800, 440, 200, 30);

		first_name.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		last_name.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		username.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		usernameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		id.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		phone_number.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		email.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		course.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		level.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		level.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		semester.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		firstNameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		firstNameField.setBackground(null);

		lastNameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		lastNameField.setBackground(null);

		usernameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		usernameField.setBackground(null);

		idField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		idField.setBackground(null);

		password.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		passwordField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		passwordField.setBackground(null);

		phoneNumberField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		phoneNumberField.setBackground(null);

		emailField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		emailField.setBackground(null);

		courseField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		courseField.setBackground(null);

		levelField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		levelField.setBackground(null);

		semesterField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		semesterField.setBackground(null);

		updateButton = new JButton("Update");
		updateButton.setBounds(900, 500, 200, 30);
		updateButton.addActionListener(new ActionListener() {

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

				} else if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(null, "Invalid Email! Please try again.");

				} else {

					try {

						String query = String.format(
								"UPDATE studentdetails SET first_name = '%s' ,last_name = '%s', password = '%s', phone_number = '%s', email = '%s' WHERE username = '%s'",
								fn, ln, pass, pn, email, student.getUsername());
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
		mainPart.add(course);
		mainPart.add(courseField);
		mainPart.add(level);
		mainPart.add(levelField);
		mainPart.add(levelField);
		mainPart.add(semester);
		mainPart.add(semesterField);

		mainPart.add(phone_number);
		mainPart.add(phoneNumberField);
		mainPart.add(password);
		mainPart.add(passwordField);
		mainPart.add(note);
		mainPart.add(updateButton);

		mainContainer.repaint();
		validate();

	}

	public void enrollModules() {
		moduleNames = new ArrayList<String>();
		moduleCodes = new ArrayList<String>();
		moduleCourses = new ArrayList<String>();
		mainPart.removeAll();
		
		try {
			con = new Conn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = String.format(
				"SELECT moduleCode, moduleName, course FROM courses WHERE mandatory = '0' AND semester = '%s' ",
				student.getSemester());

		try {
			rs = con.s.executeQuery(query);

			while (rs.next()) {
				moduleCodes.add(rs.getString(1));
				moduleNames.add(rs.getString(2));
				moduleCourses.add(rs.getString(3));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		enrollmentInfo = new JLabel(
				"<html>As a level 6 student, you can enroll in one optional module per semester from any of the course provided!<br> Be sure to enroll in the module which you think will be useful to you in the long run. </html>");
		enrollmentInfo.setBounds(20, 50, 800, 80);
		enrollmentInfo.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		enrollmentInfo.setForeground(Color.red);

		String[] modules = moduleNames.toArray(new String[moduleNames.size()]);
		optionalModules = new JComboBox<String>(modules);

		moduleName = new JLabel("Module Name ");
		moduleName.setBounds(20, 150, 150, 30);
		moduleName.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		optionalModules.setBounds(200, 150, 250, 40);
		moduleCodeFromCBox = new JLabel();
		moduleCodeFromCBox.setBounds(200, 270, 150, 30);
		courseFromCBox = new JLabel();
		courseFromCBox.setBounds(200, 390, 150, 30);

		optionalModules.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				index = optionalModules.getSelectedIndex();
				moduleCodeFromCBox.setText(moduleCodes.get(index));
				courseFromCBox.setText(moduleCourses.get(index));

			}
		});

		enrollButton = new JButton("Enroll");
		enrollButton.setBounds(500, 500, 100, 30);
		enrollButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (student.getModules()[1].equals("null")) {
					String query = String.format("UPDATE studentdetails SET modules = replace(modules, 'null', '%s');",
							moduleNames.get(index));
					String query2 = String.format("UPDATE level6results SET module2 = replace(module2, 'null', '%s');",
							moduleNames.get(index));
					try {
						con.s.executeUpdate(query);
						con.s.executeUpdate(query2);

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {
					if (student.getModules()[1].equals(moduleNames.get(index))) {
						JOptionPane.showMessageDialog(null, "You have already enrolled in this module");
					} else {
						JOptionPane.showMessageDialog(null, "You have already enrolled in another optional module");
					}
				}
			}
		});

//			
		moduleCode = new JLabel("Module Code");
		moduleCode.setBounds(20, 270, 150, 30);
		moduleCode.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		fromCourse = new JLabel("Course");
		fromCourse.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		fromCourse.setBounds(20, 390, 150, 30);

		mainPart.add(enrollmentInfo);
		mainPart.add(moduleName);
		mainPart.add(optionalModules);
		mainPart.add(moduleCode);
		mainPart.add(fromCourse);
		mainPart.add(moduleCodeFromCBox);
		mainPart.add(courseFromCBox);
		mainPart.add(enrollButton);

		mainContainer.repaint();
		validate();

	}

	public void results() {
		modules = new ArrayList<String>();
		grades = new ArrayList<String>();
		teachers = new ArrayList<String>();
		mainPart.removeAll();
		String level = student.getLevel();
		String user = student.getUsername();
		String course = student.getCourse();
		String sem = student.getSemester();
		String[] modules = student.getModules();
		String[] grades = student.getGrades();
		String[] teachers = student.getTeachers();
		

		ArrayList<ArrayList<String>> tabledata = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < modules.length; i++) {
			ArrayList<String> rowData = new ArrayList<String>(Arrays.asList(modules[i], grades[i], teachers[i]));
			tabledata.add(rowData);

		}

//		String query = String.format("SELECT *FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'level%sresults';",
//				level);
////		This query is for getting the column names from the level(s)results table
//		ResultSet rs;
//		try {
//			rs = con.s.executeQuery(query);
//			while (rs.next()) {
//				columnNames.add(rs.getString(4));
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		columnNames.remove(4);
//		columnNames.remove(3);
//		columnNames.remove(2);
//		columnNames.remove(1);
//		columnNames.remove(0);

//		String[] columnName = columnNames.toArray(new String[columnNames.size()]);
		String[] columnName = { "Modules", "Grade", "Graded By" };

		String[][] tableRows = new String[tabledata.size()][];
		for (int i = 0; i < tabledata.size(); i++) {
			ArrayList<String> row = tabledata.get(i);
			tableRows[i] = row.toArray(new String[row.size()]);
		}

		table = new JTable(tableRows, columnName);
		table.setEnabled(false);
		table.setFont(new Font("Lucida Fax", Font.PLAIN, 18));//		
		JScrollPane scrollpane = new JScrollPane(table);

//		
		scrollpane.setBounds(2, 3, 1147, 460);
		table.setRowHeight(90);
		table.getTableHeader().setPreferredSize(new Dimension(50, 50));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < 3; x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		table.setShowGrid(true);
//		 For centering the column data

		mainPart.add(scrollpane);

		mainContainer.repaint();
		validate();

	}
	
	public void teacherdetails() {
		mainPart.removeAll();
		ArrayList<String> moduleArrayList = new ArrayList<String>(Arrays.asList(student.getModules()));
		for (Iterator<String> iterator = moduleArrayList.iterator(); iterator.hasNext();) {
			if (iterator.next().equals("null")) {
				iterator.remove();
			}
		}
		

		ArrayList<ArrayList<String>> tabledata = new ArrayList<ArrayList<String>>();
		for (int i =0; i < moduleArrayList.size();i++) {			
		
		String query = "SELECT first_name, last_name, id, phone_number, email FROM teacherdetails WHERE modules LIKE CONCAT('%','"+moduleArrayList.get(i)+"','%')";
		try {
			ResultSet rs = con.s.executeQuery(query);
			while (rs.next()) {
				ArrayList<String> rowData = new ArrayList<String>(Arrays.asList(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), moduleArrayList.get(i)));
				tabledata.add(rowData);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		}
		
		String[] columns = {"First Name", "Last Name", "ID", "Phone No.", "Email"};
		
		String[][] data = new String[tabledata.size()][];
		
		JTable table = new JTable(data, columns);		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(450,63));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		table.setRowHeight(90);
		table.getTableHeader().setPreferredSize(new Dimension(50, 50));
//		
		JScrollPane scrollpane = new JScrollPane(table);

//		mainPart.setLayout(new BorderLayout());
		scrollpane.setBounds(2, 3, 1147, 460);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < 5; x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		table.setShowGrid(true);
//		 For centering the column data

		
		
		
		mainPart.add(scrollpane);
		mainContainer.repaint();
		validate();
		
		
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button5) {
			detailViewer();
		} else if (e.getSource() == button8) {
			System.exit(1);
		} else if (e.getSource() == button6) {
			JOptionPane.showMessageDialog(null, "Logging out...");
			this.dispose();
			new Login().createGui();
		} else if (e.getSource() == button2) {
			results();
		}
		else if (e.getSource()==button3) {
			teacherdetails();
		}

	}

}

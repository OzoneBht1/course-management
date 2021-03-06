
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

import com.mysql.cj.Query;
import com.sun.org.apache.bcel.internal.generic.FNEG;

import DbConnect.Conn;
import course_users.CourseAdministrator;
import course_users.Instructors;
import course_users.Students;

@SuppressWarnings("serial")
public class TeacherGui extends JFrame implements ActionListener {
	private Instructors teacher;
	private Container mainContainer;
	private ImageIcon image;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	private JButton button8;
	private JPanel mainPart;
	private JLabel first_name;
	private JLabel last_name;
	private JLabel username;
	private JLabel id;
	private JLabel password;
	private JLabel phone_number;
	private JLabel email;
	private JLabel course;
	private JLabel noStudentText;
	private JTable table;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JLabel usernameField;
	private JLabel idField;
	private JTextField passwordField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JLabel courseField;
	private JLabel note;
	private JButton updateButton;
	private Conn con;
	private JComboBox<String> moduleSelection;
	private JLabel selectModuleText;
	private String[] levels;
	private String level;
	private JLabel name;
	private JLabel[] allLevels;
	private JLabel levelText;
	private JLabel semesterText;
	private JLabel gradeText;
	private JLabel[] names;
	private JLabel[] semesters;
	private JTextField[] grades;
	private String selectedModule;
	private ArrayList<ArrayList<String>> data;
	private JButton showButton;
	private JButton updateMarksButton;
	private ArrayList<String> usernames;
	private ArrayList<String> teacherModules;

	private void connectDB() {
		try {
			con = new Conn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void homeGui(String username) {
		connectDB();

		teacher = new Instructors();
		teacher.setTeacherDetails(username);

		button2 = new JButton("Assign Grades");
		button2.setFocusable(false);
		button2.addActionListener(this);
		button3 = new JButton("My Students Details");
		button3.setFocusable(false);
		button3.addActionListener(this);

		button5 = new JButton("My Details");
		button5.setFocusable(false);
		button5.addActionListener(this);
		button6 = new JButton("Logout");
		button6.setFocusable(false);
		button6.addActionListener(this);
		button8 = new JButton("Exit");
		button8.setFocusable(false);
		mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(8, 6));
		mainContainer.setBackground(new Color(0xb2d3e6));

		topPanel = new JPanel();
		middlePanel = new JPanel();

		middlePanel.setBorder(new LineBorder(Color.BLACK, 3));
		middlePanel.setLayout(new BorderLayout());
		middlePanel.setBackground(new Color(0xBEAEE2));

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
		bottomPanel.setBackground(new Color(0xBEAEE2));
		bottomPanel.setBorder(new LineBorder(Color.black, 3));
		mainContainer.add(bottomPanel, BorderLayout.SOUTH);

		middlePanel.add(gridPanel, BorderLayout.SOUTH);
		mainContainer.add(mainPart, BorderLayout.CENTER);
		mainContainer.add(middlePanel, BorderLayout.WEST);

		topPanel.setBorder(new LineBorder(Color.BLACK, 3));
		topPanel.setBackground(new Color(0xBEAEE2));
		topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 12, 0));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 425, 10, 0));

		topPanel.add(button2);

		topPanel.add(button3);

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

		firstNameField = new JTextField(teacher.getFirstName());
		lastNameField = new JTextField(teacher.getLastName());
		usernameField = new JLabel(teacher.getUsername());
		idField = new JLabel(teacher.getId());
		phoneNumberField = new JTextField(teacher.getPhoneNumber());
		passwordField = new JTextField(teacher.getPassword());
		emailField = new JTextField(teacher.getEmail());
		courseField = new JLabel(teacher.getCourse());

		note = new JLabel("Note : You can fix the typos and errors in most of the fields by clicking them!");
		note.setForeground(Color.red);
		note.setBounds(130, 500, 770, 30);
		note.setFont(new Font("Lucida Fax", Font.BOLD, 18));

		first_name.setBounds(60, 60, 100, 30);
		firstNameField.setBounds(200, 60, 200, 30);
		last_name.setBounds(60, 155, 100, 30);
		lastNameField.setBounds(200, 155, 200, 30);
		username.setBounds(60, 250, 100, 30);
		usernameField.setBounds(200, 250, 200, 30);
		id.setBounds(60, 345, 200, 30);
		idField.setBounds(200, 345, 200, 30);
		phone_number.setBounds(660, 345, 200, 30);
		phoneNumberField.setBounds(800, 345, 200, 30);

		password.setBounds(660, 60, 100, 30);
		passwordField.setBounds(800, 60, 200, 30);

		email.setBounds(660, 155, 100, 30);
		emailField.setBounds(800, 155, 200, 30);
		course.setBounds(660, 250, 100, 30);
		courseField.setBounds(800, 250, 200, 30);

		first_name.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		last_name.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		username.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		usernameField.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		id.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

		phone_number.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		email.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		course.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

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

				} else if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
					JOptionPane.showMessageDialog(null, "Invalid Email! Please try again.");

				} else {

					try {

						String query = String.format(
								"UPDATE teacherdetails SET first_name = '%s' ,last_name = '%s', password = '%s', phone_number = '%s', email = '%s' WHERE username = '%s'",
								fn, ln, pass, pn, email, teacher.getUsername());
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

		mainPart.add(phone_number);
		mainPart.add(phoneNumberField);
		mainPart.add(password);
		mainPart.add(passwordField);
		mainPart.add(note);
		mainPart.add(updateButton);

		mainContainer.repaint();
		validate();

	}

	public void assignGrade() {
		mainPart.removeAll();
		String username = teacher.getUsername();
		String[] modules = teacher.getModules();

		selectModuleText = new JLabel("Please select a module to grade:");
		selectModuleText.setBounds(30, 30, 300, 50);
		selectModuleText.setFont(new Font("Lucida Fax", Font.PLAIN, 18));

//		for (int i =4; i < 7; i++) {
//		String query = "SELECT modules from level%sresults where module1 =  ";
//		}

		moduleSelection = new JComboBox<String>(modules);
		moduleSelection.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
		moduleSelection.setBounds(30, 90, 280, 40);
		name = new JLabel("Name");
		levelText = new JLabel("Level");
		semesterText = new JLabel("Semester");
		gradeText = new JLabel("Grades");
		moduleSelection.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				selectedModule = (String) moduleSelection.getSelectedItem();
			}
		});

		showButton = new JButton("Display");
		showButton.setBounds(350, 90, 100, 40);
		updateMarksButton = new JButton("Update Marks");
		showButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Component[] components = mainPart.getComponents();
				for (Component component : components) {
					if (component != selectModuleText && component != moduleSelection && component != showButton
							&& component != name && component != semesterText && component != levelText
							&& component != gradeText) {
						mainPart.remove(component);
					}
				}

				mainPart.revalidate();
				mainPart.repaint();

				String query = String.format("SELECT level from courses where moduleName = '%s';", selectedModule);
				

				try {
					ResultSet getLevelSet = con.s.executeQuery(query);
					
					if (getLevelSet.next()) {
						level = getLevelSet.getString(1);
						

					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				
				usernames = new ArrayList<String>();
				data = new ArrayList<ArrayList<String>>();
				for (int i = 1; i < 5; i++) {
					String getStudents = String.format(
							"SELECT studentname,username, semester, grade%s from level%sresults where module%s = '%s';",
							Integer.toString(i), level, Integer.toString(i), selectedModule);
					ResultSet rs;
					try {
						rs = con.s.executeQuery(getStudents);
						while (rs.next()) {
							if (!rs.getString(1).equals("")) {
								ArrayList<String> rowData = new ArrayList<String>(
										Arrays.asList(rs.getString(1), level, rs.getString(3), rs.getString(4)));
								data.add(rowData);
								usernames.add(rs.getString(2));

							}

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

				names = new JLabel[data.size()];
				semesters = new JLabel[data.size()];
				grades = new JTextField[data.size()];
				allLevels = new JLabel[data.size()];

				int y = 260;
				int yAdd = 0;
				name.setBounds(120, 180, 100, 30);
				levelText.setBounds(320, 180, 100, 30);
				semesterText.setBounds(570, 180, 100, 30);
				gradeText.setBounds(790, 180, 100, 30);
				name.setFont(new Font("Lucida Fax", Font.BOLD, 18));
				levelText.setFont(new Font("Lucida Fax", Font.BOLD, 18));
				semesterText.setFont(new Font("Lucida Fax", Font.BOLD, 18));
				gradeText.setFont(new Font("Lucida Fax", Font.BOLD, 18));

//												
				if (data.isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are no students enrolled in this module!");

				}
				for (int i = 0; i < data.size(); i++) {
					names[i] = new JLabel();
					names[i].setText(data.get(i).get(0));
					names[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
					names[i].setBounds(120, y + yAdd, 200, 30);
					mainPart.add(names[i]);

					allLevels[i] = new JLabel();
					allLevels[i].setText(data.get(i).get(1));
					allLevels[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
					allLevels[i].setBounds(320, y + yAdd, 200, 30);

					mainPart.add(allLevels[i]);

					semesters[i] = new JLabel();
					semesters[i].setText(data.get(i).get(2));
					semesters[i].setBounds(570, y + yAdd, 200, 30);
					semesters[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
					mainPart.add(semesters[i]);
					grades[i] = new JTextField();
					grades[i].setText(data.get(i).get(3));
					grades[i].setBounds(790, y + yAdd, 200, 30);
					grades[i].setFont(new Font("Lucida Fax", Font.PLAIN, 18));
					mainPart.add(grades[i]);
					yAdd += 80;

					mainContainer.repaint();
					validate();

//						We repainted and validated after each label is added to prevent slow display

				}
				updateMarksButton.setBounds(700, 500, 200, 30);
				updateMarksButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

					
						if (level.equals("4") || level.equals("5")) {
							for (int i = 0; i < data.size(); i++) {

								String query1 = String.format(
										"UPDATE level%sresults SET grade1 = '%s' WHERE module1 = '%s' AND username ='%s';",
										level, grades[i].getText(), selectedModule, usernames.get(i));

								String query2 = String.format(
										"UPDATE level%sresults SET grade2 = '%s' WHERE module2 = '%s' AND username ='%s';",
										level, grades[i].getText(), selectedModule, usernames.get(i));

								String query3 = String.format(
										"UPDATE level%sresults SET grade3 = '%s' WHERE module3 = '%s' AND username ='%s';",
										level, grades[i].getText(), selectedModule, usernames.get(i));

								String query4 = String.format(
										"UPDATE level%sresults SET grade4 = '%s' WHERE module4 = '%s' AND username ='%s';",
										level, grades[i].getText(), selectedModule, usernames.get(i));

								try {
									con.s.executeUpdate(query1);
									con.s.executeUpdate(query2);
									con.s.executeUpdate(query3);
									con.s.executeUpdate(query4);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}

							}

						} else if (level.equals("6")) {
							for (int i = 0; i < data.size(); i++) {

								String query1 = String.format(
										"UPDATE level%sresults SET grade1 = '%s' WHERE module1 = '%s' AND username ='%s';",
										level, grades[i].getText(), selectedModule, usernames.get(i));

								String query2 = String.format(
										"UPDATE level%sresults SET grade2 = '%s' WHERE module2 = '%s' AND username ='%s';",
										level, grades[i].getText(), selectedModule, usernames.get(i));

								try {
									con.s.executeUpdate(query1);
									con.s.executeUpdate(query2);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}

							}
						}

						JOptionPane.showMessageDialog(null, "Updated Successfully!");

					}
				});
				mainPart.add(updateMarksButton);

			}
		});

		mainPart.add(name);
		mainPart.add(semesterText);
		mainPart.add(gradeText);
		mainPart.add(showButton);

		mainPart.add(selectModuleText);
		mainPart.add(moduleSelection);
		mainPart.add(levelText);

		mainContainer.repaint();
		validate();

	}

	public void studentdetails() {
		mainPart.removeAll();

		teacherModules = new ArrayList<String>(Arrays.asList(teacher.getModules()));

		for (Iterator<String> iterator = teacherModules.iterator(); iterator.hasNext();) {
			if (iterator.next().equals("null")) {
				iterator.remove();
			}
		}

		ArrayList<ArrayList<String>> tabledata = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < teacherModules.size(); i++) {

			String query = "SELECT first_name, last_name, username, id, phone_number, email, course, level, semester, modules FROM studentdetails WHERE modules LIKE CONCAT('%','"
					+ teacherModules.get(i) + "','%')";
			try {
				ResultSet rs = con.s.executeQuery(query);
				while (rs.next()) {
					ArrayList<String> rowData = new ArrayList<String>(Arrays.asList(rs.getString(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
							rs.getString(8), rs.getString(9), rs.getString(10)));
					tabledata.add(rowData);

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
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
//		Removing the password column

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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button5) {
			detailViewer();
		} else if (e.getSource() == button2) {
			assignGrade();

		} else if (e.getSource() == button3) {
			studentdetails();
		}
		else if (e.getSource()==button6) {
			JOptionPane.showMessageDialog(null, "Logging Out...");
			this.dispose();
			new Login().createGui();
		}
		else if (e.getSource()==button8) {
			System.exit(1);
		}


	}

}

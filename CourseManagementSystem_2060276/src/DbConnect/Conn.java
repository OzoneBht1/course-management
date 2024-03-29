package DbConnect;

import java.sql.*;

public class Conn {
	private final String url = "jdbc:mysql://localhost/";
	private final String dbUser = "root";
	private final String password = "";
	private final String DbName = "cms";

	public Connection c = null;
	public Statement s = null;

	public Conn() {
		try {
			c = DriverManager.getConnection(url, dbUser, password);
			s = c.createStatement();

//		establishing connection					
		} catch (SQLException e) {
			System.out.println(e);
		}
		try {

			String query = "CREATE DATABASE cms";
			s.executeUpdate(query);
			String useDbQuery = "USE " + DbName;
			try {
				s.executeUpdate(useDbQuery);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			System.out.println("Database has been created. Filling in dummy datas....");

			createTables();
		} catch (SQLException e) {
			System.out.println("Database already exists!");
			String useDbQuery = "USE " + DbName;
			try {
				s.executeUpdate(useDbQuery);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	private void createTables() {
		coursedetails();
		studentdetails();
		admindetails();
		teacherdetails();
		level4results();
		level5results();
		level6results();
		cancelledcourses();

	}

	private void studentdetails() {
		String query = "CREATE TABLE studentdetails(\n" + "  first_name varchar(120) DEFAULT NULL,\n"
				+ "  last_name varchar(120) DEFAULT NULL,\n" + "  username varchar(120) DEFAULT NULL,\n"
				+ "  id int(11) NOT NULL AUTO_INCREMENT,\n" + "  password varchar(120) DEFAULT NULL,\n"
				+ "  phone_number varchar(10) DEFAULT NULL,\n" + "  email varchar(100) DEFAULT NULL,\n"
				+ "  course varchar(100) NOT NULL,\n" + "  level varchar(10) NOT NULL,\n"
				+ "  semester varchar(1) NOT NULL,\n" + "  PRIMARY KEY (id),\n" +
				 "modules varchar(200));";

		try {
			s.executeUpdate(query);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void coursedetails() {
		String query = "CREATE TABLE courses(\n" + "  `S.N` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  moduleCode varchar(100) NOT NULL,\n" + "  moduleName varchar(100) NOT NULL,\n"
				+ "  course varchar(100) NOT NULL,\n" + "  level varchar(100) NOT NULL,\n"
				+ "  semester varchar(100) NOT NULL,\n" + "  mandatory varchar(1) NOT NULL DEFAULT '1',\n"
				+ "  PRIMARY KEY (`S.N`),\n" 
				+ " teacher varchar(200) NOT NULL);";
		try {
			s.executeUpdate(query);
			insertIntoCourse();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void insertIntoCourse() {
		String query = "INSERT INTO `courses` (`moduleCode`, `moduleName`, `course`, `level`, `semester`, `mandatory`, `teacher`) VALUES\n"
				+ "('4BU015', 'The Responsible Business', 'BIBM', '4', '1', '1', 'null'),\n"
				+ "('4BU016', 'The Sustainable Business', 'BIBM', '4', '1', '1', 'null'),\n"
				+ "('3GK012', 'Preparing for Success at University', 'BIBM', '4', '1', '1', 'null'),\n"
				+ "('3BU003', 'Principles of Business', 'BIBM', '4', '2', '1', 'null'),\n"
				+ "('4BE002', 'The Innovative Business', 'BIBM', '4', '2', '1', 'null'),\n"
				+ "('3GK013', 'Project Based Learning', 'BIBM', '4', '2', '1', 'null'),\n"
				+ "('4BU0017', 'The Digital Business', 'BIBM', '4', '2', '1', 'null'),\n"
				+ "('4AC007', 'Financial Accounting Principles', 'BIBM', '4', '1', '1', 'null'),\n"
				+ "('5IB006', 'Contemporary Issues in International Business', 'BIBM', '5', '1', '1', 'null'),\n"
				+ "('5HR009', 'The International HR Professional', 'BIBM', '5', '1', '1', 'null'),\n"
				+ "('5BU017', 'Operation and Project Planning', 'BIBM', '5', '1', '1', 'null'),\n"
				+ "('5FC004', 'Managing Finance and Accounts', 'BIBM', '5', '1', '1', 'null'),\n"
				+ "('5AC001', 'Budgeting And Financial Control', 'BIBM', '5', '2', '1', 'null'),\n"
				+ "('5AC069', 'Taxation Of Individuals', 'BIBM', '5', '2', '1', 'null'),\n"
				+ "('5MG001', 'The Professional Manager And Leadership', 'BIBM', '5', '2', '1', 'null'),\n"
				+ "('5BU018', 'Customer Acquisition and Retention', 'BIBM', '5', '2', '1', 'null'),\n"
				+ "('6BU024', 'Global Context for Multinational Enterprises', 'BIBM', '6', '1', '1', 'null'),\n"
				+ "('6BU020', 'The Professional Project', 'BIBM', '6', '2', '1', 'null'),\n"
				+ "('6BE005', 'The Strategic Business', 'BIBM', '6', '1', '0', 'null'),\n"
				+ "('6MK014', 'The Marketing Consultant', 'BIBM', '6', '2', '0', 'null'),\n"
				+ "('4CI018', 'Academic Skills and Team Based Learning', 'BIT', '4', '1', '1', 'null'),\n"
				+ "('4CS001', 'Introductory Programming and Problem Solving', 'BIT', '4', '1', '1', 'null'),\n"
				+ "('4CS015', 'Fundamentals of Computing', 'BIT', '4', '1', '1', 'null'),\n"
				+ "('4CS016', 'Embedded System Programming', 'BIT', '4', '2', '1', 'null'),\n"
				+ "('4CS017', 'Internet Software Architecture', 'BIT', '4', '2', '1', 'null'),\n"
				+ "('4MM013', 'Computational Mathematics', 'BIT', '4', '2', '1', 'null'),\n"
				+ "('5CS037', 'Concepts and Technologies of AI', 'BIT', '5', '1', '1', 'null'),\n"
				+ "('5CS019', 'Object-Oriented Design and Programming', 'BIT', '5', '1', '1', 'null'),\n"
				+ "('5CS021', 'Numerical Methods and Concurrency', 'BIT', '5', '1', '1', 'null'),\n"
				+ "('5CS022', 'Distributed and Cloud System Programming', 'BIT', '5', '2', '1', 'null'),\n"
				+ "('5CS024', 'Collaborative Development', 'BIT', '5', '2', '1', 'null'),\n"
				+ "('5CS020', 'Human Computer Interaction', 'BIT', '5', '2', '1', 'null'),\n"
				+ "('6CS007', 'Project and Professionalism', 'BIT', '6', '1', '1', 'null'),\n"
				+ "('6CS005', 'High Performance Computing', 'BIT', '6', '2', '1', 'null'),\n"
				+ "('6CS014', 'Complex System', 'BIT', '6', '1', '0', 'null'),\n"
				+ "('6CS012', 'Artificial Intelligence and Machine Learning', 'BIT', '6', '2', '0', 'null'),\n"
				+ "('6CS030', 'Big Data', 'BIT', '6', '2', '0', 'null'),\n"
				+ "('5CS010', 'Digital Forensics', 'BIT', '4', '1', '1', 'null'),\n"
				+ "('5CS099', 'Network Security', 'BIT', '4', '2', '1', 'null'),\n"
				+ "('5CS069', 'Visual Development', 'BIT', '5', '1', '1', 'null'),\n"
				+ "('5CS111', 'Interactivity for Game Design', 'BIT', '5', '2', '1', 'NULL'),\n"
				+ "('5CS422', 'Advanced Web Development', 'BIT', '6', '1', '0', 'null');\n" + "";

		try {
			s.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void admindetails() {
		String query = "\n" + "CREATE TABLE `admindetails` (\n" + "  `first_name` varchar(120) NOT NULL,\n"
				+ "  `last_name` varchar(120) NOT NULL,\n" + "  `username` varchar(120) NOT NULL,\n"
				+ "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n" + "  `password` varchar(120) NOT NULL,\n"
				+ "  `phone_number` varchar(120) NOT NULL,\n" + "  `email` varchar(120) NOT NULL,\n"
				+ "  PRIMARY KEY (`ID`)\n" + ") ";
		try {
			s.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	private void teacherdetails() {
		String query = "CREATE TABLE `teacherdetails` (\n"
				+ "  `first_name` varchar(120) NOT NULL,\n"
				+ "  `last_name` varchar(120) NOT NULL,\n"
				+ "  `username` varchar(120) NOT NULL,\n"
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `password` varchar(120) NOT NULL,\n"
				+ "  `phone_number` varchar(10) NOT NULL,\n"
				+ "  `email` varchar(120) NOT NULL,\n"
				+ "  `course` varchar(100) NOT NULL,\n"
				+ "  `modules` varchar(500) NOT NULL,\n"
				+ "  PRIMARY KEY (`id`)\n"
				+ ") ";
		try {
			s.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	private void level4results() {
		String query = "CREATE TABLE `level4results` (\n"
				+ "  `S.N` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `studentname` varchar(150) NOT NULL,\n"
				+ "  `username` varchar(150) NOT NULL,\n"
				+ "  `course` varchar(150) NOT NULL,\n"
				+ "  `semester` varchar(1) NOT NULL,\n"
				+ "  `module1` varchar(150) NOT NULL,\n"
				+ "  `grade1` varchar(150) NOT NULL,\n"
				+ "  `teacher1` varchar(150) NOT NULL,\n"
				+ "  `module2` varchar(150) NOT NULL,\n"
				+ "  `grade2` varchar(150) NOT NULL,\n"
				+ "  `teacher2` varchar(150) NOT NULL,\n"
				+ "  `module3` varchar(150) NOT NULL,\n"
				+ "  `grade3` varchar(150) NOT NULL,\n"
				+ "  `teacher3` varchar(150) NOT NULL,\n"
				+ "  `module4` varchar(150) NOT NULL,\n"
				+ "  `grade4` varchar(150) NOT NULL,\n"
				+ "  `teacher4` varchar(150) NOT NULL,\n"
				+ "  `remarks` varchar(30) NOT NULL DEFAULT 'TBD',\n"
				+ "  PRIMARY KEY (`S.N`)\n"
				+ ") ";
		try {
			s.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
	}
	
	private void level5results() {
		String query = "CREATE TABLE `level5results` (\n"
				+ "  `S.N` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `studentname` varchar(150) NOT NULL,\n"
				+ "  `username` varchar(150) NOT NULL,\n"
				+ "  `course` varchar(150) NOT NULL,\n"
				+ "  `semester` varchar(1) NOT NULL,\n"
				+ "  `module1` varchar(150) NOT NULL,\n"
				+ "  `grade1` varchar(150) NOT NULL,\n"
				+ "  `teacher1` varchar(150) NOT NULL,\n"
				+ "  `module2` varchar(150) NOT NULL,\n"
				+ "  `grade2` varchar(150) NOT NULL,\n"
				+ "  `teacher2` varchar(150) NOT NULL,\n"
				+ "  `module3` varchar(150) NOT NULL,\n"
				+ "  `grade3` varchar(150) NOT NULL,\n"
				+ "  `teacher3` varchar(150) NOT NULL,\n"
				+ "  `module4` varchar(150) NOT NULL,\n"
				+ "  `grade4` varchar(150) NOT NULL,\n"
				+ "  `teacher4` varchar(150) NOT NULL,\n"
				+ "  `remarks` varchar(30) NOT NULL DEFAULT 'TBD',\n"
				+ "  PRIMARY KEY (`S.N`)\n"
				+ ")";
		
		try {
			s.executeUpdate(query);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void level6results() {
		String query = "CREATE TABLE `level6results` (\n"
				+ "  `S.N` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `studentname` varchar(150) NOT NULL,\n"
				+ "  `username` varchar(150) NOT NULL,\n"
				+ "  `course` varchar(150) NOT NULL,\n"
				+ "  `semester` varchar(1) NOT NULL,\n"
				+ "  `module1` varchar(150) NOT NULL,\n"
				+ "  `grade1` varchar(150) NOT NULL,\n"
				+ "  `teacher1` varchar(150) NOT NULL,\n"
				+ "  `module2` varchar(150) NOT NULL,\n"
				+ "  `grade2` varchar(150) NOT NULL,\n"
				+ "  `teacher2` varchar(150) NOT NULL,\n"
				+ "  PRIMARY KEY (`S.N`)\n"
				+ ") ";
		
		try {
			s.executeUpdate(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void cancelledcourses() {
		String query = "CREATE TABLE `cancelledcourses` (\n"
				+ "  `S.N` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `moduleCode` varchar(100) NOT NULL,\n"
				+ "  `moduleName` varchar(100) NOT NULL,\n"
				+ "  `course` varchar(100) NOT NULL,\n"
				+ "  `level` varchar(100) NOT NULL,\n"
				+ "  `semester` varchar(100) NOT NULL,\n"
				+ "  `mandatory` varchar(1) NOT NULL DEFAULT '1',\n"
				+ "  `teacher` varchar(150) NOT NULL DEFAULT 'null',\n"
				+ "  PRIMARY KEY (`S.N`)\n"
				+ ") ";
		
		try {
			s.executeUpdate(query);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}

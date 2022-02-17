package course_users;


import java.sql.ResultSet;

import java.util.ArrayList;

import DbConnect.Conn;

public class Students {
	private String level;
	private String firstName;
	private String lastName;
	private String course;
	private String semester;
	private String id;
	private String username;
	private String password;
	private ResultSet rs;
	private Conn con;
	private String phoneNumber;
	private String email;
	private String[] modules;
	private String[] grades;
	private String[] teachers;
	private ArrayList<String> teacher;
	private ArrayList<String> grade;

	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String[] getModules() {
		return modules;
	}
	public void setModules(String[] modules) {
		this.modules = modules;
	}
	
	
	public String[] getGrades() {
		return grades;
	}
	public void setGrades(String[] grades) {
		this.grades = grades;
	}
	public String[] getTeachers() {
		return teachers;
	}
	public void setTeachers(String[] teachers) {
		this.teachers = teachers;
	}
	public void setStudentDetails(String username) {	
		try {
		con = new Conn();
		
		String info = "SELECT * FROM studentDetails where username ='"+username+"' ";
		rs = con.s.executeQuery(info);
		while(rs.next()) {
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setUsername(rs.getString("username"));			
			setId(rs.getString("id"));			
			setPassword(rs.getString("password"));			
			setPhoneNumber(rs.getString("phone_number"));
			setEmail(rs.getString("email"));			
			setCourse(rs.getString("course"));
			setLevel(rs.getString("level"));			
			setSemester(rs.getString("semester"));
			setModules(rs.getString("modules").split(","));			
			
			 
			
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		setting new connection for result table
		try {	
			teacher = new ArrayList<String>();
			grade = new ArrayList<String>();
			System.out.println(getLevel());
			System.out.println(username);
			System.out.println(getSemester());
			String query = String.format("SELECT * FROM level%sresults where username = '%s';", getLevel(),
					username);
			rs = con.s.executeQuery(query);
			System.out.println("ball");
			if (getLevel().equals("4")|| getLevel().equals("5")) {
				
			while(rs.next()) {
				grade.add(rs.getString("grade1"));
				
				grade.add(rs.getString(10));
				grade.add(rs.getString(13));
				grade.add(rs.getString(16));
				
				teacher.add(rs.getString(8));
				teacher.add(rs.getString(11));
				teacher.add(rs.getString(14));
				teacher.add(rs.getString(17));
				
			}
			}
			else if(getLevel().equals("6")) {
				while(rs.next()) {
				grade.add(rs.getString("grade1"));
				grade.add(rs.getString("grade2"));
				
				teacher.add(rs.getString("teacher1"));
				teacher.add(rs.getString("teacher2"));
				}
			}
			setGrades(grade.toArray(new String[grade.size()]));
			setTeachers(teacher.toArray(new String[teacher.size()]));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
	
	
	
}

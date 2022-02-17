package course_users;

import java.sql.ResultSet;

import DbConnect.Conn;

public class Instructors {
	private String firstName;
	private String lastName;	
	private String username;
	private String id;
	private String password;
	private ResultSet rs;
	private Conn con;
	private String phoneNumber;
	private String email;
	private String course;
	private String[] modules;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String[] getModules() {
		return modules;
	}
	public void setModules(String[] modules) {
		this.modules = modules;
	}
	public void setTeacherDetails(String username) {	
		try {
		con = new Conn();
		
		String info = "SELECT * FROM teacherDetails where username ='"+username+"' ";
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
			setModules(rs.getString("modules").split(","));			
				
			 
			
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	

}
}

package course_users;

import java.sql.ResultSet;


import DbConnect.Conn;

public class CourseAdministrator {

	
	private String firstName;
	private String lastName;
	private String id;
	private String username;
	private String password;
	private ResultSet rs;
	private Conn con;
	private String phoneNumber;
	private String email;

	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setAdminDetails(String username) {
		try {
			con = new Conn();

			String info = "SELECT * FROM adminDetails where username ='" + username + "' ";
			rs = con.s.executeQuery(info);
			while (rs.next()) {
				setFirstName(rs.getString(1));
				setLastName(rs.getString(2));
				setUsername(rs.getString(3));
				setId(rs.getString(4));
				setPassword(rs.getString(5));
				setPhoneNumber(rs.getString(6));
				setEmail(rs.getString(7));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

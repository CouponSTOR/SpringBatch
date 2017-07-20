package batch;

import java.io.Serializable;

public class Employee implements Serializable {
	int id;
	String lastName;
	String firstName;
	String designation;
	String department;
	int yearOfJoining;
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getYearOfJoining() {
		return yearOfJoining;
	}
	public void setYearOfJoining(int yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}
	
	public String toString() {
		return "Employee: ID="+ id + ", Last Name="+ lastName +
		", First Name="+ firstName + ", Designation="+ designation +
		", Department="+ department + ",Year of joining="+
		yearOfJoining;
		}
}

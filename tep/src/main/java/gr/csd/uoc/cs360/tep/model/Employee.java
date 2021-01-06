package gr.csd.uoc.cs360.tep.model;

import java.io.Serializable;

public class Employee extends User implements Serializable{
	private String firstName;
	private String lastName;
	
	public Employee() {
		this.setJob("employee");
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
}

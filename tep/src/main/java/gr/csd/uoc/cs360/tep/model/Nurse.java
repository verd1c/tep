package gr.csd.uoc.cs360.tep.model;

import java.io.Serializable;

public class Nurse extends User implements Serializable {
	private String firstName;
	private String lastName;
	
	public Nurse() {
		this.setJob("nurse");
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
	@Override
	public String toString() {
		return "Nurse [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}

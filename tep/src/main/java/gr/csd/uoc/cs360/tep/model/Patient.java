package gr.csd.uoc.cs360.tep.model;

import java.io.Serializable;
import java.util.List;

public class Patient extends User implements Serializable {

	private int AMKA;
	private String firstName;
	private String lastName;
	private String address;
	private String institution;
	private List<String> illnesses;
	private List<Visit> visits;
	
	public Patient() {
		this.setJob("patient");
	}
	
	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public int getAMKA() {
		return AMKA;
	}
	
	public void setAMKA(int aMKA) {
		AMKA = aMKA;
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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getInstitution() {
		return institution;
	}
	
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	
	@Override
	public String toString() {
		return "Patient [AMKA=" + AMKA + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", institution=" + institution + ", illnesses=" + illnesses + ", visits=" + visits + ", getID()="
				+ getUserID() + "]";
	}
}

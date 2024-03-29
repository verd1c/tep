package gr.csd.uoc.cs360.tep.model;

public class Visit {
	@Override
	public String toString() {
		return "Visit [visitID=" + visitID + ", AMKA=" + AMKA + ", doctorID=" + doctorID + ", illness=" + illness
				+ ", checked=" + checked + ", date=" + date + "]";
	}
	private Integer visitID;
	private String firstName;
	private String lastName;
	private Integer AMKA;
	private Integer doctorID;
	private Doctor doctor;
	private String illness;
	private boolean checked;
	private String date;
	
	public Integer getVisitID() {
		return visitID;
	}
	public void setVisitID(Integer visitID) {
		this.visitID = visitID;
	}
	public Integer getAMKA() {
		return AMKA;
	}
	public void setAMKA(Integer aMKA) {
		AMKA = aMKA;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

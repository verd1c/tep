package gr.csd.uoc.cs360.tep.model;

public class MedicalTest {
	private Integer visitID;
	private String type;
	private boolean completed;
	
	public Integer getVisitID() {
		return visitID;
	}
	public void setVisitID(Integer visitID) {
		this.visitID = visitID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "MedicalTest [visitID=" + visitID + ", type=" + type + ", completed=" + completed + "]";
	}
}

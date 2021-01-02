package gr.csd.uoc.cs360.tep.model;

public class Visit {
	@Override
	public String toString() {
		return "Visit [visitID=" + visitID + ", AMKA=" + AMKA + ", illness=" + illness + ", checked=" + checked + "]";
	}
	private Integer visitID;
	private Integer AMKA;
	private String illness;
	private boolean checked;
	
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
}

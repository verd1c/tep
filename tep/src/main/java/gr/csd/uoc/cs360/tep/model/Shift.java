package gr.csd.uoc.cs360.tep.model;

import java.util.List;

public class Shift {
	private int shiftID;
	private String since;
	private List<User> attendees;
	
	public int getShiftID() {
		return shiftID;
	}
	public void setShiftID(int shiftID) {
		this.shiftID = shiftID;
	}
	public String getSince() {
		return since;
	}
	public void setSince(String since) {
		this.since = since;
	}
	public List<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	
	@Override
	public String toString() {
		return "Shift [shiftID=" + shiftID + ", since=" + since + ", attendees=" + attendees + "]";
	}
}

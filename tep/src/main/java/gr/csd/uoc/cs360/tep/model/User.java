package gr.csd.uoc.cs360.tep.model;

public class User {
	private Integer userID;
	private String username;
	private String password;
	private Job job;
	
	public Integer getUserID() {
		return userID;
	}
	
	public void setUserID(Integer iD) {
		userID = iD;
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
	
	 /**
     * Get job
     *
     * @return
     */
    public Job getJob() {
        return job;
    }

    /**
     * Set job
     *
     * @param job
     */
    public void setJob(String job) {
        switch (job.toLowerCase().trim()) {
            case "patient":
                this.job = Job.PATIENT;
                break;
            case "doctor":
                this.job = Job.DOCTOR;
                break;
            case "nurse":
                this.job = Job.NURSE;
                break;
            case "employee":
                this.job = Job.EMPLOYEE;
                break;
            default:
                this.job = Job.UNKNOWN;
                break;
        }
    }
	
	/**
     * Enum for supporting job values
     */
    public enum Job {

        PATIENT("Patient"), DOCTOR("Doctor"), NURSE("Nurse"), EMPLOYEE("Employee"), UNKNOWN("Unknown");
        private final String value;

        private Job(String value) {
            this.value = value;
        }

        /**
         * Returns string representation of value
         *
         * @return
         */
        @Override
        public String toString() {
            return this.value;
        }
    }
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + "]";
	}
}

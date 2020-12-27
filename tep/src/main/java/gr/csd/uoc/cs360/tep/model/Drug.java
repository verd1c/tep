package gr.csd.uoc.cs360.tep.model;

public class Drug {
	private String code;
	private String name;
	private Type type;
	private Integer comprehensiveness;
	private String target;
	
	/**
     * Enum for supporting job values
     */
    public enum Type {

        ALLERGIST("Allergist"), ANESTHESIOLOGIST("Anesthesiologist"), CARDIOLOGIST("Cardiologist"), UNKNOWN("Unknown");
        private final String value;

        private Type(String value) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getComprehensiveness() {
		return comprehensiveness;
	}

	public void setComprehensiveness(Integer comprehensiveness) {
		this.comprehensiveness = comprehensiveness;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return "Drug [code=" + code + ", name=" + name + ", type=" + type + ", comprehensiveness=" + comprehensiveness
				+ ", target=" + target + "]";
	}
}

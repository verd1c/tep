package gr.csd.uoc.cs360.tep.model;

public class Drug {
	private String name;
	private String type;
	private Integer density;
	private String target;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDensity() {
		return density;
	}

	public void setDensity(Integer density) {
		this.density = density;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return "Drug [name=" + name + ", type=" + type + ", density=" + density
				+ ", target=" + target + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

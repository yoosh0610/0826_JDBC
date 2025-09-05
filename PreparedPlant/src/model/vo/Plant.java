package model.vo;

import java.util.Objects;

public class Plant {
	
	private String plName;
	private String plType;
	private String plColor;
	
	public Plant() {
		super();
	}

	public Plant(String plName, String plType, String plColor) {
		super();
		this.plName = plName;
		this.plType = plType;
		this.plColor = plColor;
	}

	public String getPlName() {
		return plName;
	}
	public void setPlName(String plName) {
		this.plName = plName;
	}
	public String getPlType() {
		return plType;
	}
	public void setPlType(String plType) {
		this.plType = plType;
	}
	public String getPlColor() {
		return plColor;
	}
	public void setPlColor(String plColor) {
		this.plColor = plColor;
	}

	@Override
	public String toString() {
		return "Plant [plName=" + plName + ", plType=" + plType + ", plColor=" + plColor + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(plColor, plName, plType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		return Objects.equals(plColor, other.plColor) && Objects.equals(plName, other.plName)
				&& Objects.equals(plType, other.plType);
	}

	
	
	
	
}

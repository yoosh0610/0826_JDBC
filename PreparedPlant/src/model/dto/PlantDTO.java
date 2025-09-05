package model.dto;

import java.util.Objects;

public class PlantDTO {

	private String plName;
	 private String plType;
	 private String newType;
	
	public PlantDTO() {
		super();
	}

	public PlantDTO(String plName, String plType, String newType) {
		super();
		this.plName = plName;
		this.plType = plType;
		this.newType = newType;
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

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	@Override
	public String toString() {
		return "PlantDTO [plName=" + plName + ", plType=" + plType + ", newType=" + newType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(newType, plName, plType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantDTO other = (PlantDTO) obj;
		return Objects.equals(newType, other.newType) && Objects.equals(plName, other.plName)
				&& Objects.equals(plType, other.plType);
	}
	 
	 
	 
	 
	 
}
